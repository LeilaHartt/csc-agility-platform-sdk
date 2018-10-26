package com.servicemesh.agility.distributed.impl;

import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;

/*
 * Provide a mechanism to wait for a Zoookeeper connection to completes.  This code could have been a lot
 * simpler but the use case is mainly for the class DistributedConnection where most of the methods are
 * static.
 */
public class AgilityWatcher implements Watcher
{
    private final CountDownLatch connectedSignal = new CountDownLatch(1);

    public AgilityWatcher()
    {
    }

    public void await()
    {
        try
        {
            connectedSignal.await();
        }
        catch (InterruptedException ex)
        {
        }
    }

    @Override
    public void process(WatchedEvent event)
    {
        if (event.getState() == KeeperState.SyncConnected)
        {
            connectedSignal.countDown();
        }

    }
}
