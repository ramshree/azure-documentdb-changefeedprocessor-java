/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.microsoft.azure.documentdb.changefeedprocessor.internal;

/**
 *
 * @author fcatae
 */
<<<<<<< HEAD

enum SubStatusCode
{
    // <summary>
    // 410: partition key range is gone
    // </summary>

    PartitionKeyRangeGone (1002),

    /// <summary>
    /// 410: partition splitting
    /// </summary>
    Splitting (1007),

    /// <summary>
    /// 404: LSN in session token is higher
    /// </summary>
    ReadSessionNotAvailable (1002);

    private final int value;

    SubStatusCode(int subcode) {
        this.value = subcode;
    }
}
=======
public enum SubStatusCode {
    /**
     * 410: partition key range is gone
     */

    PartitionKeyRangeGone(1002),
    /**
     * 410: partition splitting
     */
    Splitting (1007),
    /**
     * 404: LSN in session token is higher
     */
    ReadSessionNotAvailable(1002);
    private int value;

    private SubStatusCode(int value) {
        this.value = value;
    }
}
>>>>>>> Event_Host