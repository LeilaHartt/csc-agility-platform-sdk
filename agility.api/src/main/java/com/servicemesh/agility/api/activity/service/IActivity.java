package com.servicemesh.agility.api.activity.service;

import java.util.List;

import com.servicemesh.agility.api.activity.Activity;
import com.servicemesh.agility.api.activity.ActivityList;
import com.servicemesh.agility.api.activity.SearchCriteria;

/**
 * Activity API for generating and querying activities.
 *
 * @author akofman
 */
public interface IActivity
{
    /**
     * Activity source property containing task id which corresponds to activity Task id
     */
    public static final String SOURCE_PROP_TASK_ID = "taskId";
    /**
     * Activity source property having indicating whether task has any children, valid values: true/false
     */
    public static final String SOURCE_PROP_TASK_HAS_CHILD = "taskHasChild";

    /**
     * Generate activity message for processing by an activity service.
     *
     * @param activity
     *            new or updated activity message
     * @throws Exception
     */
    public void push(Activity activity) throws Exception;

    /**
     * Generate activity messages for processing by an activity service.
     *
     * @param activities
     *            new or updated activity messages
     * @throws Exception
     */
    public void push(List<Activity> activities) throws Exception;

    /**
     * Retrieve activity message by activity identifier.
     *
     * @param id
     *            activity identifier
     * @return activity matching specified identifier
     * @throws Exception
     *             if activity not visible to a current user unless it is an admin user
     */
    public Activity get(int id) throws Exception;

    /**
     * Query for activity messages by search criteria
     *
     * @param searchCriteria
     *            activity search criteria
     * @return activity list as per specified by an activity search criteria. Activity list is filtered based on calling user
     *         permissions.
     * @throws Exception
     */
    public ActivityList search(SearchCriteria searchCriteria) throws Exception;

    /**
     * Initialize use of service
     * 
     * @param login
     *            user name of the user on behalf on which activity api executed
     * @throws Exception
     */
    public void init(String login) throws Exception;

}
