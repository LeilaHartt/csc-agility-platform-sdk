package com.servicemesh.agility.api.service;

import com.servicemesh.agility.api.Linklist;

/**
 * Exposes operations for deleting assets
 */
public interface IDelete<T>
{
    /*
     * Returns a list of assets that will be deleted as a result of the delete request.
     */
    Linklist checkDelete(T asset) throws Exception;
}
