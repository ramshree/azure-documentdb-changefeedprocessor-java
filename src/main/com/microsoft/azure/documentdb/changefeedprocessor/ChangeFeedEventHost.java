/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microsoft.azure.documentdb.changefeedprocessor;

<<<<<<< HEAD
/**
 *
 * @author yoterada
 */
public class ChangeFeedEventHost {
    public ChangeFeedEventHost(String hostName, DocumentCollectionInfo documentCollectionLocation, DocumentCollectionInfo auxCollectionLocation){
//        this(hostName, documentCollectionLocation, auxCollectionLocation, new ChangeFeedOptions(), new ChangeFeedHostOptions());
=======

import com.microsoft.azure.documentdb.ChangeFeedOptions;
import com.microsoft.azure.documentdb.changefeedprocessor.internal.CancellationTokenSource;
import com.microsoft.azure.documentdb.changefeedprocessor.internal.ChangeFeedObserverFactory;
import com.microsoft.azure.documentdb.changefeedprocessor.internal.IPartitionObserver;
import com.microsoft.azure.documentdb.changefeedprocessor.internal.WorkerData;
import com.microsoft.azure.documentdb.changefeedprocessor.internal.documentleasestore.DocumentServiceLease;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Future;

public class ChangeFeedEventHost implements IPartitionObserver<DocumentServiceLease> {

    final String DefaultUserAgentSuffix = "changefeed-0.2";
    final String LeaseContainerName = "docdb-changefeed";
    final String LSNPropertyName = "_lsn";

    private DocumentCollectionInfo _collectionLocation;
    private ChangeFeedOptions _changeFeedOptions;
    private ChangeFeedHostOptions _options;
    private String _hostName;
    DocumentCollectionInfo _auxCollectionLocation;
    ConcurrentMap<String, WorkerData> _partitionKeyRangeIdToWorkerMap;

    private IChangeFeedObserverFactory _observerFactory;

    public ChangeFeedEventHost( String hostName, DocumentCollectionInfo documentCollectionLocation, DocumentCollectionInfo auxCollectionLocation){
        this(hostName, documentCollectionLocation, auxCollectionLocation, new ChangeFeedOptions(), new ChangeFeedHostOptions());
    }

    public ChangeFeedEventHost(
            String hostName,
            DocumentCollectionInfo documentCollectionLocation,
            DocumentCollectionInfo auxCollectionLocation,
            ChangeFeedOptions changeFeedOptions,
            ChangeFeedHostOptions hostOptions) {

        if (documentCollectionLocation == null) throw new IllegalArgumentException("documentCollectionLocation");
        if (documentCollectionLocation.getUri() == null) throw new IllegalArgumentException("documentCollectionLocation.getUri()");
        if (documentCollectionLocation.getDatabaseName() == null || documentCollectionLocation.getDatabaseName().isEmpty()) throw new IllegalArgumentException("documentCollectionLocation.getDatabaseName() is null or empty");
        if (documentCollectionLocation.getCollectionName() == null || documentCollectionLocation.getCollectionName().isEmpty()) throw new IllegalArgumentException("documentCollectionLocation.getCollectionName() is null or empty");
        if (hostOptions.getMinPartitionCount() > hostOptions.getMaxPartitionCount()) throw new IllegalArgumentException("hostOptions.MinPartitionCount cannot be greater than hostOptions.MaxPartitionCount");

        this._collectionLocation = CanoninicalizeCollectionInfo(documentCollectionLocation);
        this._changeFeedOptions = changeFeedOptions;
        this._options = hostOptions;
        this._hostName = hostName;
        this._auxCollectionLocation = CanoninicalizeCollectionInfo(auxCollectionLocation);
        this._partitionKeyRangeIdToWorkerMap = new ConcurrentHashMap<String, WorkerData>();


    }

    private DocumentCollectionInfo CanoninicalizeCollectionInfo(DocumentCollectionInfo collectionInfo)
    {
        DocumentCollectionInfo result = collectionInfo;
        if (result.getConnectionPolicy().getUserAgentSuffix() == null ||
                result.getConnectionPolicy().getUserAgentSuffix().isEmpty())
        {
            result = new DocumentCollectionInfo(collectionInfo);
            result.getConnectionPolicy().setUserAgentSuffix(DefaultUserAgentSuffix);
        }

        return result;
    }

    /**
     * This code used to be async
     */
    public void RegisterObserver()
    {
        this._observerFactory = new ChangeFeedObserverFactory<>();
        //this.StartAsync();
    }


    @Override
    public void OnPartitionAcquiredAsync(DocumentServiceLease documentServiceLease) {

    }

    @Override
    public void OnPartitionReleasedAsync(DocumentServiceLease documentServiceLease, ChangeFeedObserverCloseReason reason) {

>>>>>>> Event_Host
    }
}