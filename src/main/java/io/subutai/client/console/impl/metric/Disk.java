package io.subutai.client.console.impl.metric;


public class Disk implements io.subutai.client.console.api.metric.Disk
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
