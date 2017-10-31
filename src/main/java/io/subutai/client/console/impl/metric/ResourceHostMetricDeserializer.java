package io.subutai.client.console.impl.metric;


import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import io.subutai.client.console.api.HostArchitecture;
import io.subutai.client.console.api.InstanceType;
import io.subutai.client.console.util.gson.JsonUtil;


public class ResourceHostMetricDeserializer implements JsonDeserializer<ResourceHostMetric>
{
    @Override
    public ResourceHostMetric deserialize( final JsonElement jsonElement, final Type typeOfT,
                                           final JsonDeserializationContext context ) throws JsonParseException
    {

        JsonObject metric = jsonElement.getAsJsonObject();
        JsonObject hostInfo = metric.get( "hostInfo" ).getAsJsonObject();
        String id = hostInfo.get( "id" ).getAsString();
        String hostname = hostInfo.get( "hostname" ).getAsString();
        HostArchitecture arch = HostArchitecture.valueOf( hostInfo.get( "arch" ).getAsString().toUpperCase() );

        Boolean isManagement = metric.get( "management" ).getAsBoolean();
        Boolean isConnected = metric.get( "connected" ).getAsBoolean();
        String peerId = metric.get( "peerId" ).getAsString();
        InstanceType instanceType = InstanceType.valueOf( metric.get( "instanceType" ).getAsString().toUpperCase() );

        Ram ram = JsonUtil.fromJson( metric.get( "RAM" ).getAsJsonObject(), Ram.class );
        Cpu cpu = JsonUtil.fromJson( metric.get( "CPU" ).getAsJsonObject(), Cpu.class );
        Disk disk = JsonUtil.fromJson( metric.get( "Disk" ).getAsJsonObject(), Disk.class );

        return new ResourceHostMetric( id, hostname, peerId, isManagement, isConnected, arch, instanceType, ram,
                cpu, disk );
    }


    public static class ResourceHostMetricWrapper
    {
        private List<ResourceHostMetric> resources;


        public List<ResourceHostMetric> getResources()
        {
            return resources;
        }
    }
}
