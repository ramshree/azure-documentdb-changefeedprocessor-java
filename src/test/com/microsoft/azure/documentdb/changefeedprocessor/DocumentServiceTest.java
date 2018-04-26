package com.microsoft.azure.documentdb.changefeedprocessor;

import com.microsoft.azure.documentdb.DocumentClientException;
import com.microsoft.azure.documentdb.PartitionKeyRange;
import com.microsoft.azure.documentdb.changefeedprocessor.internal.ConfigurationException;
import com.microsoft.azure.documentdb.changefeedprocessor.internal.ConfigurationFile;
import com.microsoft.azure.documentdb.changefeedprocessor.services.DocumentCollectionInfo;
import com.microsoft.azure.documentdb.changefeedprocessor.services.DocumentServices;
import org.junit.Assert;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ConcurrentHashMap;

public class DocumentServiceTest {

    @Test
    public void testDocumentService() throws DocumentClientException{

        ConfigurationFile config = null;
        DocumentServices client = null;

        try {
            config = new ConfigurationFile("app.secrets");
        } catch (ConfigurationException e) {
            Assert.fail(e.getMessage());
        }

        DocumentCollectionInfo docInfo = new DocumentCollectionInfo();
        try {
            docInfo.setUri(new URI(config.get("COSMOSDB_ENDPOINT")));
            docInfo.setMasterKey(config.get("COSMOSDB_SECRET"));
            docInfo.setDatabaseName(config.get("COSMOSDB_DATABASE"));
            docInfo.setCollectionName(config.get("COSMOSDB_COLLECTION"));
        } catch (URISyntaxException e) {
            Assert.fail("COSMOSDB URI FAIL: " + e.getMessage());
        } catch (ConfigurationException e) {
            Assert.fail("Configuration Error " + e.getMessage());

        }

        client = new DocumentServices(docInfo);

        Assert.assertNotNull(client);

        ConcurrentHashMap<String, PartitionKeyRange> list = client.listPartitionRanges();

        Assert.assertNotNull(list);

        Assert.assertFalse("It must have at least one partition!!", list.isEmpty());
    }

}
