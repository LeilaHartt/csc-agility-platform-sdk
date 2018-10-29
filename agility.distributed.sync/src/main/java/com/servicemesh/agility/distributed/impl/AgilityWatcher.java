package com.servicemesh.agility.distributed.impl;

import java.util.concurrent.CountDownLatch;

import org.apache.log4j.Logger;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;

import com.servicemesh.agility.distributed.sync.DistributedConfig;

/*
 * Provide a mechanism to wait for a Zoookeeper connection to completes.  This code could have been a lot
 * simpler but the use case is mainly for the class DistributedConnection where most of the methods are
 * static.
 */
public class AgilityWatcher implements Watcher
{
    private final static Logger logger = Logger.getLogger(AgilityWatcher.class);
    private final CountDownLatch connectedSignal = new CountDownLatch(1);

    public AgilityWatcher(String url)
    {
        logger.debug("Connecting to Zookeeper at URL: " + url);
    }

    public void await()
    {
        try
        {
            logger.debug("Waiting for Zookeeper connection negotiation...");
            connectedSignal.await();
            logger.debug("Zookeeper connection established.");
        }
        catch (InterruptedException ex)
        {
            logger.warn("Interruped waiting for Zookeeper conneciton negotiation: " + ex.getMessage());
        }
    }

    @Override
    public void process(WatchedEvent event)
    {
        if (event.getState() == KeeperState.Disconnected)
        {
            /**
             * The client is in the disconnected state - it is not connected to any server in the ensemble.
             */
            logger.debug("Zookeeper state: Disconnected");
        }
        else if (event.getState() == KeeperState.SyncConnected)
        {
            /**
             * The client is in the connected state - it is connected to a server in the ensemble (one of the servers specified in
             * the host connection parameter during ZooKeeper client creation).
             */
            logger.debug("Zookeeper state: SyncConnected");
            connectedSignal.countDown();
        }
        else if (event.getState() == KeeperState.AuthFailed)
        {
            /**
             * Auth failed state
             */
            logger.debug("Zookeeper state: AuthFailed");
        }
        else if (event.getState() == KeeperState.ConnectedReadOnly)
        {
            /**
             * The client is connected to a read-only server, that is the server which is not currently connected to the majority.
             * The only operations allowed after receiving this state is read operations. This state is generated for read-only
             * clients only since read/write clients aren't allowed to connect to r/o servers.
             */
            logger.debug("Zookeeper state: ConnectedReadOnly");
        }
        else if (event.getState() == KeeperState.SaslAuthenticated)
        {
            /**
             * SaslAuthenticated: used to notify clients that they are SASL-authenticated, so that they can perform Zookeeper
             * actions with their SASL-authorized permissions.
             */
            logger.debug("Zookeeper state: SaslAuthenticated");
        }
        else if (event.getState() == KeeperState.Expired)
        {
            /**
             * The serving cluster has expired this session. The ZooKeeper client connection (the session) is no longer valid. You
             * must create a new client connection (instantiate a new ZooKeeper instance) if you with to access the ensemble.
             */
            logger.debug("Zookeeper state: Expired");
            DistributedConfig.clearZookeeper();
        }
        else
        {
            logger.warn("Unknown Zookeeper state");
        }
    }
}
