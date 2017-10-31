package io.subutai.client.console.api;


public interface PeerInfo
{
    String getId();

    String getOwnerId();

    String getPublicUrl();

    Integer getPublicSecurePort();

    String getName();

    String getIp();
}
