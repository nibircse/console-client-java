package io.subutai.client.console.impl.hosts;


import io.subutai.client.console.api.hosts.Quota;


public class QuotaImpl implements Quota
{
    private Double ram;
    private Double cpu;
    private Double disk;


    @Override
    public Double getRam()
    {
        return ram;
    }


    @Override
    public Double getCpu()
    {
        return cpu;
    }


    @Override
    public Double getDisk()
    {
        return disk;
    }
}
