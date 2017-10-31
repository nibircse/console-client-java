package io.subutai.client.console.impl.metric;


import io.subutai.client.console.api.HostArchitecture;
import io.subutai.client.console.api.InstanceType;
import io.subutai.client.console.util.gson.JsonUtil;


public class ResourceHostMetric implements io.subutai.client.console.api.metric.ResourceHostMetric
{
    private String id;
    private String hostname;
    private String peerId;
    private Boolean isManagement;
    private Boolean isConnected;
    private HostArchitecture arch;
    private InstanceType instanceType;
    private Ram ram;
    private Cpu cpu;
    private Disk disk;


    public String getId()
    {
        return id;
    }


    public String getHostname()
    {
        return hostname;
    }


    public String getPeerId()
    {
        return peerId;
    }


    public Boolean getManagement()
    {
        return isManagement;
    }


    public Boolean getConnected()
    {
        return isConnected;
    }


    public InstanceType getInstanceType()
    {
        return instanceType;
    }


    public io.subutai.client.console.api.metric.Ram getRam()
    {
        return ram;
    }


    public io.subutai.client.console.api.metric.Cpu getCpu()
    {
        return cpu;
    }


    public io.subutai.client.console.api.metric.Disk getDisk()
    {
        return disk;
    }


    public HostArchitecture getArch()
    {
        return arch;
    }


    public ResourceHostMetric( final String id, final String hostname, final String peerId, final Boolean isManagement,
                               final Boolean isConnected, final HostArchitecture arch, final InstanceType instanceType,
                               final Ram ram, final Cpu cpu, final Disk disk )
    {
        this.id = id;
        this.hostname = hostname;
        this.peerId = peerId;
        this.isManagement = isManagement;
        this.isConnected = isConnected;
        this.arch = arch;
        this.instanceType = instanceType;
        this.ram = ram;
        this.cpu = cpu;
        this.disk = disk;
    }


    public String toString()
    {
        return JsonUtil.toJson( this );
    }
}
