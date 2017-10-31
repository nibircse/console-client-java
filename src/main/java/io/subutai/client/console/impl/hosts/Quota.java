package io.subutai.client.console.impl.hosts;


public class Quota implements io.subutai.client.console.api.hosts.Quota
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
