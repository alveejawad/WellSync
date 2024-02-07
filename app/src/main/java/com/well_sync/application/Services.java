package com.well_sync.application;

import com.well_sync.persistence.MoodLogPersistence;
import com.well_sync.persistence.UserPersistence;
import com.well_sync.persistence.stubs.UserPersistenceStub;


public class Services {
    private static UserPersistence userPersistence = null;

    private static final MoodLogPersistence moodLogPersistence = null;

    public static synchronized UserPersistence getUserPersistence() {
        if (userPersistence == null) {
            userPersistence = new UserPersistenceStub();
        }
        return userPersistence;
    }

    public static synchronized MoodLogPersistence getMoodLogPersistence() {
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
