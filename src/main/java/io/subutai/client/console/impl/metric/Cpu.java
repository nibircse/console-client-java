package io.subutai.client.console.impl.metric;


public class Cpu implements io.subutai.client.console.api.metric.Cpu
{
    private String model = "UNKNOWN";
    private Double idle = 0D;
    private Integer coreCount = 0;
    private Double frequency = 0D;


    public String getModel()
    {
        return model;
    }


    public Double getIdle()
    {
        return idle;
    }


    public Integer getCoreCount()
    {
        return coreCount;
    }


    public Double getFrequency()
    {
        return frequency;
    }
}
