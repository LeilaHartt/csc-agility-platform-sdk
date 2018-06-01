package com.servicemesh.agility.sdk.cloud.spi;

import com.servicemesh.agility.sdk.cloud.msgs.StorageExpandRequest;
import com.servicemesh.agility.sdk.cloud.msgs.StorageResponse;
import com.servicemesh.core.async.ResponseHandler;

//  Note: This interface is used by the async bridge/cloud SDK and implemented by async cloud adapters
//  that support extending storage in the cloud.

public interface IStorageExt
{

    /**
     * Called to extend storage in the cloud
     *
     * @param request
     *            Specifies the parameters of the storage volume to extend
     * @param handler
     *            Interface to asynchronously signal completion (or error) of the requested operation.
     * @return An instance of ICancellable which can be used by the platform to cancel the pending operation.
     */
    public ICancellable extend(StorageExpandRequest request, ResponseHandler<StorageResponse> handler);

}
