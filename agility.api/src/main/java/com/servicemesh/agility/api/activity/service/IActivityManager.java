/*
 * Copyright (C) 2016 Computer Science Corporation
 * All rights reserved.
 *
 */
package com.servicemesh.agility.api.activity.service;

/**
 * Activity Manager administration API for activity messaging service.
 */
public interface IActivityManager
{
    /**
     * Register as a listener for generated activity.
     *
     * @param activityListener
     *            to add to a list of activity listeners
     * @throws Exception
     */
    public void insertActivityListener(IActivityListener activityListener) throws Exception;

    /**
     * Unregister an activity listener
     *
     * @param activityListener
     *            to remove from registered list of activity listeners
     * @throws Exception
     */
    public void removeActivityListener(IActivityListener activityListener) throws Exception;
}
