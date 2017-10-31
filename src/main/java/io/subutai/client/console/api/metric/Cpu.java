package io.subutai.client.console.api.metric;


public interface Cpu
{
    String getModel();


    Double getIdle();


    Integer getCoreCount();


    Double getFrequency();
}
