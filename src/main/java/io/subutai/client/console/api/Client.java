package io.subutai.client.console.api;


import java.util.List;

import io.subutai.client.console.api.hosts.ContainerHost;
import io.subutai.client.console.api.hosts.ResourceHost;
import io.subutai.client.console.api.metric.ResourceHostMetric;


public interface Client
{

    /**
     * Authorizes client with Console
     */
    void login( String username, String password );

    /**
     * Returns short info about peer
     *
     * @return {@link PeerInfo}
     */
    PeerInfo getPeerInfo();

    /**
     * Returns list of resource host metrics
     *
     * @return list of {@link ResourceHostMetric}
     */
    List<ResourceHostMetric> getResourceHostMetrics();

    /**
     * Returns list of resource hosts along with their containers
     *
     * @return list of {@link ResourceHost}s with hosted {@link ContainerHost}s
     */
    List<ResourceHost> getResourceHosts();
}
