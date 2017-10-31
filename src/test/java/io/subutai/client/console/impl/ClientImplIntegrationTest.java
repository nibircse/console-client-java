package io.subutai.client.console.impl;


import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import io.subutai.client.console.api.PeerInfo;
import io.subutai.client.console.api.hosts.ResourceHost;
import io.subutai.client.console.api.metric.ResourceHostMetric;

import static org.junit.Assert.assertNotNull;


/**
 * To run this test edit configuration(Junit) and add environment variable PEER_PASS containing admin password of SS
 * console
 */
@Ignore( "integration-test" )
public class ClientImplIntegrationTest
{
    private static final String URL = "https://192.168.88.12:8443";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = System.getenv( "PEER_PASS" );
    private Client client;


    @Before
    public void setUp() throws Exception
    {
        client = ( Client ) io.subutai.client.console.impl.Client.get( URL );

        client.login( USERNAME, PASSWORD );
    }


    @Test
    public void testGetPeerInfo() throws Exception
    {
        PeerInfo peerInfo = client.getPeerInfo();

        System.out.println( peerInfo );

        assertNotNull( peerInfo );
    }


    @Test
    public void testGetResourceHostMetrics() throws Exception
    {
        List<ResourceHostMetric> resourceHostMetricList = client.getResourceHostMetrics();

        System.out.println( resourceHostMetricList );
    }


    @Test
    public void testGetResourceHosts() throws Exception
    {
        List<ResourceHost> resourceHosts = client.getResourceHosts();

        System.out.println( resourceHosts );
    }
}
