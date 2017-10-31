package io.subutai.client.console.impl.hosts;


import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import static org.junit.Assert.assertNotNull;


public class ContainerHostDeserializerTest
{
    private Gson gson;


    @Before
    public void setUp() throws Exception
    {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter( ContainerHostImpl.class, new ContainerHostDeserializer() ).setPrettyPrinting();
        gson = gsonBuilder.create();
    }


    @Test
    public void testDeserializeContainerHost() throws Exception
    {

        String json = "      {\n" + "        \"status\": \"RUNNING\",\n" + "        \"name\": \"Container1-sfi-1-2\",\n"
                + "        \"quota\": {\n" + "          \"cpu\": 10.0,\n" + "          \"ram\": 256.0,\n"
                + "          \"disk\": 4.0\n" + "        },\n" + "        \"interfaces\": [\n" + "          {\n"
                + "            \"interfaceName\": \"eth0\",\n" + "            \"ip\": \"172.19.20.2\"\n"
                + "          }\n" + "        ],\n" + "        \"id\": \"CE02845F9E395621E08490F0BB57247F10F55E30\",\n"
                + "        \"hostname\": \"Container1\",\n" + "        \"arch\": \"AMD64\"\n" + "      }";

        ContainerHostImpl containerHost = gson.fromJson( json, ContainerHostImpl.class );

        assertNotNull( containerHost.getStatus() );

        System.out.println( gson.toJson( containerHost ) );
    }


    @Test
    public void testDeserializeResourceHost() throws Exception
    {
        String json = "{\n" + "    \"containers\": [\n" + "      {\n" + "        \"status\": \"RUNNING\",\n"
                + "        \"name\": \"Container1-sfi-1-2\",\n" + "        \"quota\": {\n"
                + "          \"cpu\": 10.0,\n" + "          \"ram\": 256.0,\n" + "          \"disk\": 4.0\n"
                + "        },\n" + "        \"interfaces\": [\n" + "          {\n"
                + "            \"interfaceName\": \"eth0\",\n" + "            \"ip\": \"172.19.20.2\"\n"
                + "          }\n" + "        ],\n" + "        \"id\": \"CE02845F9E395621E08490F0BB57247F10F55E30\",\n"
                + "        \"hostname\": \"Container1\",\n" + "        \"arch\": \"AMD64\"\n" + "      },\n"
                + "      {\n" + "        \"status\": \"RUNNING\",\n" + "        \"name\": \"Container2-qwx-1-3\",\n"
                + "        \"quota\": {\n" + "          \"cpu\": 10.0,\n" + "          \"ram\": 256.0,\n"
                + "          \"disk\": 4.0\n" + "        },\n" + "        \"interfaces\": [\n" + "          {\n"
                + "            \"interfaceName\": \"eth0\",\n" + "            \"ip\": \"172.19.20.3\"\n"
                + "          }\n" + "        ],\n" + "        \"id\": \"D67577F401833CD1385023DE2E84F8A4E8A64569\",\n"
                + "        \"hostname\": \"Container2\",\n" + "        \"arch\": \"AMD64\"\n" + "      },\n"
                + "      {\n" + "        \"status\": \"RUNNING\",\n" + "        \"name\": \"management\",\n"
                + "        \"quota\": {},\n" + "        \"interfaces\": [\n" + "          {\n"
                + "            \"interfaceName\": \"eth0\",\n" + "            \"ip\": \"10.10.10.1\"\n"
                + "          }\n" + "        ],\n" + "        \"id\": \"7777C4ADC24AC0D403CABF05817352F960C5F1CA\",\n"
                + "        \"hostname\": \"management\",\n" + "        \"arch\": \"AMD64\"\n" + "      }\n" + "    ],\n"
                + "    \"instance\": \"LOCAL\",\n" + "    \"address\": \"192.168.88.12\",\n"
                + "    \"id\": \"753A84EDAE09AC2C6D904BE239890EC75B2E84EB\",\n" + "    \"hostname\": \"ubuntu\",\n"
                + "    \"arch\": \"AMD64\"\n" + "  }";

        ResourceHostImpl resourceHost = gson.fromJson( json, ResourceHostImpl.class );

        assertNotNull( resourceHost.getInstanceType() );

        System.out.println( resourceHost );
    }
}
