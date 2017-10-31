package io.subutai.client.console.impl.hosts;


import io.subutai.client.console.api.HostArchitecture;


public class ContainerHost implements io.subutai.client.console.api.hosts.ContainerHost
{
    private Status status;
    private String name;
    private String hostname;
    private String id;
    private HostArchitecture arch;
    private String ip;
    private Quota quota;


    @Override
    public Status getStatus()
    {
        return status;
    }


    @Override
    public String getName()
    {
        return name;
    }


    @Override
    public String getHostname()
    {
        return hostname;
    }


    @Override
    public String getId()
    {
        return id;
    }


    @Override
    public HostArchitecture getArch()
    {
        return arch;
    }


    @Override
    public String getIp()
    {
        return ip;
    }


    @Override
    public io.subutai.client.console.api.hosts.Quota getQuota()
    {
        return quota;
    }


    void setIp( String ip )
    {
        this.ip = ip;
    }
}
