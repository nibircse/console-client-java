package io.subutai.client.console.impl.hosts;


import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import io.subutai.client.console.util.gson.JsonUtil;


public class ContainerHostDeserializer implements JsonDeserializer<ContainerHostImpl>
{
    @Override
    public ContainerHostImpl deserialize( final JsonElement jsonElement, final Type type,
                                          final JsonDeserializationContext jsonDeserializationContext )
            throws JsonParseException
    {
        JsonObject host = jsonElement.getAsJsonObject();
        JsonArray interfaces = host.get( "interfaces" ).getAsJsonArray();
        JsonObject iface = interfaces.get( 0 ).getAsJsonObject();

        ContainerHostImpl containerHost = JsonUtil.fromJson( host.getAsJsonObject(), ContainerHostImpl.class );

        containerHost.setIp( iface.get( "ip" ).getAsString() );

        return containerHost;
    }
}
