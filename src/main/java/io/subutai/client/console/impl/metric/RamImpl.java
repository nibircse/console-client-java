package io.subutai.client.console.impl.metric;


import io.subutai.client.console.api.metric.Ram;


public class RamImpl implements Ram
{
    private Double total;

    private Double free;

    private Double cached;


    public Double getTotal()
    {
        return total;
    }


    public Double getFree()
    {
        return free;
    }


    public Double getCached()
    {
        return cached;
    }
}
