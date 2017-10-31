package io.subutai.client.console.impl.hosts;


import java.util.List;

import com.google.common.collect.Lists;
import com.google.gson.annotations.SerializedName;

import io.subutai.client.console.api.HostArchitecture;
import io.subutai.client.console.api.InstanceType;
import io.subutai.client.console.util.gson.JsonUtil;


public class ResourceHost implements io.subutai.client.console.api.hosts.ResourceHost
{
    @SerializedName( value = "instanceType", alternate = "instance" )
    private InstanceType instanceType;
    @SerializedName( value = "ip", alternate = "address" )
    private String ip;
    private String id;
    private String hostname;
    private HostArchitecture arch;
    private List<ContainerHost> containers;


    @Override
    public InstanceType getInstanceType()
    {
        return instanceType;
    }


    @Override
    public String getIp()
    {
        return ip;
    }


    @Override
    public String getId()
    {
        return id;
    }


    @Override
    public String getHostname()
    {
        return hostname;
    }


    @Override
    public HostArchitecture getArch()
    {
        return arch;
    }


    @Override
    public List<io.subutai.client.console.api.hosts.ContainerHost> getContainers()
    {
        return Lists.newArrayList( containers );
    }


    @Override
    public String toString()
    {
        return JsonUtil.toJson( this );
    }
}
