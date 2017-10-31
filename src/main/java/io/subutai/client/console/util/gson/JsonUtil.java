package io.subutai.client.console.util.gson;


import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;


public class JsonUtil
{
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();


    public static <T> T fromJson( String value, Class<T> clazz )
    {
        return GSON.fromJson( value, clazz );
    }


    public static <T> T fromJson( String value, Type type )
    {

        return GSON.fromJson( value, type );
    }


    public static <T> T fromJson( JsonElement value, Class<T> clazz )
    {

        return GSON.fromJson( value, clazz );
    }


    public static <T> String toJson( T value, Type type )
    {
        return GSON.toJson( value, type );
    }


    public static <T> String toJson( T value )
    {

        return GSON.toJson( value );
    }
}
