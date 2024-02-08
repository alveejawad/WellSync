package com.well_sync.application;

import com.well_sync.persistence.iMoodLogPersistence;
import com.well_sync.persistence.iUserPersistence;
import com.well_sync.persistence.stubs.UserPersistenceStub;


public class Services {
    private static iUserPersistence userPersistence = null;

    private static final iMoodLogPersistence moodLogPersistence = null;

    public static synchronized iUserPersistence getUserPersistence() {
        if (userPersistence == null) {
            userPersistence = new UserPersistenceStub();
        }
        return userPersistence;
    }

    public static synchronized iMoodLogPersistence getMoodLogPersistence() {
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
