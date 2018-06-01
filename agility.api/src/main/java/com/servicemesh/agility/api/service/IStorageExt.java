package com.servicemesh.agility.api.service;

import com.servicemesh.agility.api.Task;
import com.servicemesh.agility.api.VolumeStorage;

//  This class is implemented by the core VolumeStorageImpl.java code to support initiating a storage extend operation from
//  the Agility API back-end.

public interface IStorageExt
{
    public Task extend(VolumeStorage storage, long newSizeGB) throws Exception;
}
