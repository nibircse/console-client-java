package io.subutai.client.console.api.hosts;


import io.subutai.client.console.api.HostArchitecture;


public interface ContainerHost
{
    enum Status
    {
        STARTING, RUNNING, STOPPING, STOPPED, ABORTING, FREEZING, FROZEN, UNKNOWN
    }

    Status getStatus();

    String getName();

    String getHostname();

    String getId();

    HostArchitecture getArch();

    Quota getQuota();

    String getIp();
}
