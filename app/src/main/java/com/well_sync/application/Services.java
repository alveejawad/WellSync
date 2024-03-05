package com.well_sync.application;

import com.well_sync.persistence.IDailyLogPersistence;
import com.well_sync.persistence.IUserPersistence;
import com.well_sync.persistence.stubs.UserPersistenceStub;


public class Services {
    private static IUserPersistence userPersistence = null;

    private static final IDailyLogPersistence dailyLogPersistence = null;

    public static synchronized IUserPersistence getUserPersistence() {
        if (userPersistence == null) {
            userPersistence = new UserPersistenceStub();
        }
        return userPersistence;
    }

    public static synchronized IDailyLogPersistence getDailyLogPersistence() {
        if (dailyLogPersistence == null) {
            //Need a daily log persistence stub
            //dailyLogPersistence = new DailyLogPersistenceStub() {
            //}
        }
        return dailyLogPersistence;
    }

}
