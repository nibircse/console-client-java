package io.subutai.client.console.impl;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpRequestBase;

import com.google.common.collect.Lists;
import com.google.gson.reflect.TypeToken;

import io.subutai.client.console.impl.hosts.ResourceHost;
import io.subutai.client.console.impl.metric.ResourceHostMetricDeserializer;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;


@RunWith( MockitoJUnitRunner.class )
public class ClientTest
{

    private static final String URL = "https://e3cba43ce80f85d3f57d28e63575e4f199e38e89.peers.subut.ai:8443";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "secret1";
    private Client client;

    @Mock
    private CloseableHttpResponse response;
    @Mock
    private PeerInfo peerInfo;
    @Mock
    private ResourceHostMetricDeserializer.ResourceHostMetricWrapper resourceHostMetric;
    @Mock
    private ResourceHost resourceHost;


    private void returnHttpCode( int httpCode )
    {
        StatusLine statusLine = mock( StatusLine.class );
        doReturn( httpCode ).when( statusLine ).getStatusCode();
        doReturn( statusLine ).when( response ).getStatusLine();
    }


    @Before
    public void setUp() throws Exception
    {
        client = spy( ( Client ) io.subutai.client.console.impl.Client.get( URL ) );
        doReturn( response ).when( client ).execute( any( HttpRequestBase.class ) );
        returnHttpCode( HttpStatus.SC_OK );
    }


    @Test
    public void testLogin() throws Exception
    {
        client.login( USERNAME, PASSWORD );

        verify( client ).execute( any( HttpRequestBase.class ) );
    }


    @Test
    public void testGetPeerInfo() throws Exception
    {
        doReturn( peerInfo ).when( client ).parse( eq( response ), any( TypeToken.class ) );

        client.getPeerInfo();

        verify( client ).execute( any( HttpRequestBase.class ) );
    }


    @Test
    public void testGetResourceHostMetrics() throws Exception
    {
        doReturn( resourceHostMetric ).when( client ).parse( eq( response ), any( TypeToken.class ) );

        client.getResourceHostMetrics();

        verify( client ).execute( any( HttpRequestBase.class ) );
    }


    @Test
    public void testGetResourceHosts() throws Exception
    {
        doReturn( Lists.newArrayList( resourceHost ) ).when( client ).parse( eq( response ), any( TypeToken.class ) );

        client.getResourceHosts();

        verify( client ).execute( any( HttpRequestBase.class ) );
    }
}
