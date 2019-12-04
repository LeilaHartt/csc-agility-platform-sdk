/*
 * Copyright (C) 2016 Computer Science Corporation
 * All rights reserved.
 *
 */
package com.servicemesh.agility.api.activity.service;

import java.util.List;

import com.servicemesh.agility.api.activity.Activity;

/**
 * @author akofman Activity listener registration API to receive new or updated activities.
 */
public interface IActivityListener
{
    /**
     * Notification of a new activity generated
     *
     * @param activity
     *            created
     * @throws Exception
     */
    public void activityAdded(Activity activity) throws Exception;

    /**
     * Notification of activities generated
     *
     * @param activities
     *            created
     * @throws Exception
     */
    public void activityAdded(List<Activity> activities) throws Exception;

    /**
     * Notification of an existing activity updated
     *
     * @param activity
     *            updated
     * @throws Exception
     */
    public void activityUpdated(Activity activity) throws Exception;

    /**
     * Notification of existing activities updated
     *
     * @param activities
     *            updated
     * @throws Exception
     */
    public void activityUpdated(List<Activity> activities) throws Exception;
}
