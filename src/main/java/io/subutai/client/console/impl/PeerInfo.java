package io.subutai.client.console.impl;


import io.subutai.client.console.util.gson.JsonUtil;


public class PeerInfo implements io.subutai.client.console.api.PeerInfo
{
    private String id;
    private String ownerId;
    private String publicUrl;
    private Integer publicSecurePort;
    private String name;
    private String ip;


    @Override
    public String getId()
    {
        return id;
    }


    @Override
    public String getOwnerId()
    {
        return ownerId;
    }


    @Override
    public String getPublicUrl()
    {
        return publicUrl;
    }


    @Override
    public Integer getPublicSecurePort()
    {
        return publicSecurePort;
    }


    @Override
    public String getName()
    {
        return name;
    }


    @Override
    public String getIp()
    {
        return ip;
    }


    public String toString()
    {
        return JsonUtil.toJson( this );
    }
}
