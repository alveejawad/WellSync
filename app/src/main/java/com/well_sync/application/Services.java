package com.well_sync.application;

import com.well_sync.persistence.IMoodLogPersistence;
import com.well_sync.persistence.IUserPersistence;
import com.well_sync.persistence.stubs.UserPersistenceStub;


public class Services {
    private static IUserPersistence userPersistence = null;

    private static final IMoodLogPersistence moodLogPersistence = null;

    public static synchronized IUserPersistence getUserPersistence() {
        if (userPersistence == null) {
            userPersistence = new UserPersistenceStub();
        }
        return userPersistence;
    }

    public static synchronized IMoodLogPersistence getMoodLogPersistence() {
        if (moodLogPersistence == null) {
            //Need a mood log persistence stub
            //moodLogPersistence = new MoodLogPersistenceStub() {
            //}
        }
        return moodLogPersistence;
    }

    /**
     * clean
     * Reset all services so to be reloaded from scratch next time they are referenced
     */
    public static synchronized void clean() {
        userPersistence = null;
    }
}
