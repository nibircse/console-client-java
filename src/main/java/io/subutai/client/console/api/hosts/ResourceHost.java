package io.subutai.client.console.api.hosts;


import java.util.List;

import io.subutai.client.console.api.HostArchitecture;
import io.subutai.client.console.api.InstanceType;


public interface ResourceHost
{
    InstanceType getInstanceType();

    String getIp();

    String getId();

    String getHostname();

    HostArchitecture getArch();

    List<ContainerHost> getContainers();
}
