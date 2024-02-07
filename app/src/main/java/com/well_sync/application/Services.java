package com.well_sync.application;

import com.well_sync.persistence.UserPersistence;
import com.well_sync.persistence.stubs.UserPersistenceStub;


public class Services {
    private static UserPersistence userPersistence = null;

    public static synchronized UserPersistence getUserPersistence() {
        if (userPersistence == null) {
            userPersistence = new UserPersistenceStub();
        }
        return userPersistence;
    }

    /**
     * clean
     * Reset all services so to be reloaded from scratch next time they are referenced
     */
   public static synchronized void clean() {
        userPersistence = null;
    }
}
