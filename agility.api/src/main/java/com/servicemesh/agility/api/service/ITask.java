package com.servicemesh.agility.api.service;

import java.util.Map;

import com.servicemesh.agility.api.Task;
import com.servicemesh.agility.api.Tasklist;

public interface ITask
{

    public Task signal(Task task, String event_name, Object event_value) throws Exception;

    public Task complete(Task task, long workItemId, Map<String, Object> results) throws Exception;

    public Task cancel(Task task) throws Exception;

    public Task resume(Task task) throws Exception;

    public Task recover(Task task) throws Exception;

    public Tasklist getRunningTasks(boolean leafOnly) throws Exception;

}
