package io.subutai.client.console.impl.metric;


import io.subutai.client.console.api.metric.Disk;


public class DiskImpl implements Disk
{
    private Double total = 0D;
    private Double used = 0D;


    public Double getTotal()
    {
        return total;
    }


    public Double getUsed()
    {
        return used;
    }
}
