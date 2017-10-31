package io.subutai.client.console.api.metric;


import io.subutai.client.console.api.HostArchitecture;
import io.subutai.client.console.api.InstanceType;


public interface ResourceHostMetric
{

    String getId();


    String getHostname();


    String getPeerId();


    Boolean getManagement();


    Boolean getConnected();


    InstanceType getInstanceType();

    HostArchitecture getArch();


    Ram getRam();


    Cpu getCpu();


    Disk getDisk();
}
