package io.subutai.client.console.impl.hosts;


import java.lang.reflect.Type;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import io.subutai.client.console.util.gson.JsonUtil;


public class ContainerHostDeserializer implements JsonDeserializer<ContainerHost>
{
    @Override
    public ContainerHost deserialize( final JsonElement jsonElement, final Type type,
                                      final JsonDeserializationContext jsonDeserializationContext )
            throws JsonParseException
    {
        JsonObject host = jsonElement.getAsJsonObject();
        JsonArray interfaces = host.get( "interfaces" ).getAsJsonArray();
        JsonObject iface = interfaces.get( 0 ).getAsJsonObject();

        ContainerHost containerHost = JsonUtil.fromJson( host.getAsJsonObject(), ContainerHost.class );

        containerHost.setIp( iface.get( "ip" ).getAsString() );

        return containerHost;
    }
}
