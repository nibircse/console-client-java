package io.subutai.client.console.impl;


import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import io.subutai.client.console.api.OperationFailedException;
import io.subutai.client.console.impl.hosts.ContainerHostDeserializer;
import io.subutai.client.console.impl.hosts.ContainerHost;
import io.subutai.client.console.impl.hosts.ResourceHost;
import io.subutai.client.console.impl.metric.ResourceHostMetricDeserializer;
import io.subutai.client.console.impl.metric.ResourceHostMetric;
import io.subutai.client.console.util.StringUtil;


public class Client implements io.subutai.client.console.api.Client
{
    private static final Logger LOG = LoggerFactory.getLogger( Client.class );
    private static final String UTF8 = "UTF-8";

    private final String consoleUrl;
    private CloseableHttpClient httpclient;
    private HttpContext httpContext = new BasicHttpContext();
    private Gson gson;


    /**
     * Returns a new instance of client
     *
     * @param consoleUrl base console URL, e.g. https://localhost:8443
     */
    public static io.subutai.client.console.api.Client get( String consoleUrl )
    {
        return new Client( consoleUrl );
    }


    private Client( final String consoleUrl )
    {
        Preconditions.checkArgument( !StringUtil.isBlank( consoleUrl ) );

        this.consoleUrl = consoleUrl;
        this.httpContext.setAttribute( HttpClientContext.COOKIE_STORE, new BasicCookieStore() );

        //init ssl context to trust self-signed certificates since peers use them
        SSLContext sslContext;

        try
        {
            sslContext = SSLContexts.custom().loadTrustMaterial( ( chain, authType ) -> true ).build();
        }
        catch ( Exception e )
        {
            throw new OperationFailedException( "Failed to init http client", e );
        }

        SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory( sslContext,
                new String[] { "SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.1", "TLSv1.2" }, null,
                NoopHostnameVerifier.INSTANCE );

        httpclient = HttpClients.custom().setSSLSocketFactory( sslConnectionSocketFactory ).build();

        //init GSON instance
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter( ResourceHostMetric.class, new ResourceHostMetricDeserializer() );
        gsonBuilder.registerTypeAdapter( ContainerHost.class, new ContainerHostDeserializer() );
        gson = gsonBuilder.create();
    }


    public void login( final String username, final String password )
    {
        Preconditions.checkArgument( !StringUtil.isBlank( username ) );
        Preconditions.checkArgument( !StringUtil.isBlank( password ) );

        HttpPost request = new HttpPost( String.format( "%s/login", consoleUrl ) );

        List<NameValuePair> nvps = new ArrayList<>();
        nvps.add( new BasicNameValuePair( "username", username ) );
        nvps.add( new BasicNameValuePair( "password", password ) );
        request.setEntity( new UrlEncodedFormEntity( nvps, Charset.forName( UTF8 ) ) );

        CloseableHttpResponse response = null;
        try
        {
            response = execute( request );

            checkHttpStatus( response, HttpStatus.SC_OK, "login" );
        }
        finally
        {

            close( response );
        }
    }


    @Override
    public io.subutai.client.console.api.PeerInfo getPeerInfo()
    {
        HttpGet request = new HttpGet( String.format( "%s/rest/v1/peer/info", consoleUrl ) );

        PeerInfo peerInfo;
        CloseableHttpResponse response = null;
        try
        {
            response = execute( request );

            checkHttpStatus( response, HttpStatus.SC_OK, "get peer info" );

            peerInfo = parse( response, new TypeToken<PeerInfo>()
            {
            } );
        }
        finally
        {
            close( response );
        }

        return peerInfo;
    }


    @Override
    public List<io.subutai.client.console.api.metric.ResourceHostMetric> getResourceHostMetrics()
    {
        List<io.subutai.client.console.api.metric.ResourceHostMetric> resourceHostMetrics = Lists.newArrayList();

        HttpGet request = new HttpGet( String.format( "%s/rest/v1/peer/resources", consoleUrl ) );

        CloseableHttpResponse response = null;
        try
        {
            response = execute( request );

            checkHttpStatus( response, HttpStatus.SC_OK, "get resource host metrics" );

            ResourceHostMetricDeserializer.ResourceHostMetricWrapper wrapper =
                    parse( response, new TypeToken<ResourceHostMetricDeserializer.ResourceHostMetricWrapper>()
                    {
                    } );

            resourceHostMetrics.addAll( wrapper.getResources() );
        }
        finally
        {
            close( response );
        }

        return resourceHostMetrics;
    }


    @Override
    public List<io.subutai.client.console.api.hosts.ResourceHost> getResourceHosts()
    {
        List<io.subutai.client.console.api.hosts.ResourceHost> resourceHosts = Lists.newArrayList();

        HttpGet request = new HttpGet( String.format( "%s/rest/v1/hosts", consoleUrl ) );

        CloseableHttpResponse response = null;
        try
        {
            response = execute( request );

            checkHttpStatus( response, HttpStatus.SC_OK, "get resource hosts" );

            List<ResourceHost> resourceHostList = parse( response, new TypeToken<List<ResourceHost>>()
            {
            } );

            resourceHosts.addAll( resourceHostList );
        }
        finally
        {
            close( response );
        }

        return resourceHosts;
    }

    //<editor-fold desc="HELPERS">


    <T> T parse( CloseableHttpResponse response, TypeToken<T> typeToken )
    {
        try
        {
            String responseContent = EntityUtils.toString( response.getEntity() );

            LOG.info( "Response: {} {}", response.getEntity().getContentType(), responseContent );

            return gson.fromJson( responseContent, typeToken.getType() );
        }
        catch ( Exception e )
        {
            LOG.error( "Error parsing response", e );

            throw new OperationFailedException( "Failed to parse response", e );
        }
    }


    CloseableHttpResponse execute( HttpRequestBase httpRequest )
    {
        try
        {
            return httpclient.execute( httpRequest, httpContext );
        }
        catch ( Exception e )
        {
            LOG.error( "Error executing http request", e );

            throw new OperationFailedException( "Failed to execute http request", e );
        }
    }


    private void checkHttpStatus( CloseableHttpResponse response, int expectedStatus, String actionName )
    {
        int actualStatus = response.getStatusLine().getStatusCode();

        if ( actualStatus != expectedStatus )
        {
            LOG.warn( "Http status code expectation failed: expected {},  actual {}", expectedStatus, actualStatus );

            throw new OperationFailedException(
                    String.format( "Failed to %s: %s, %s", actionName, response.getStatusLine(),
                            readContent( response ) ), null );
        }
    }


    private void close( CloseableHttpResponse response )
    {
        IOUtils.closeQuietly( response );
    }


    private String readContent( CloseableHttpResponse response )
    {
        try
        {
            return EntityUtils.toString( response.getEntity() );
        }
        catch ( Exception e )
        {
            LOG.error( "Error reading entity content", e );

            return null;
        }
    }
    //</editor-fold>
}
