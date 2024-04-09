package org.softeng.assignment.application;

import org.softeng.assignment.persistence.IDailyLogPersistence;
import org.softeng.assignment.persistence.IUserPersistence;
import org.softeng.assignment.persistence.hsqldb.DailyLogPersistenceHSQLDB;
import org.softeng.assignment.persistence.hsqldb.UserPersistenceHSQLDB;
import org.softeng.assignment.persistence.stubs.DailyLogPersistenceStub;
import org.softeng.assignment.persistence.stubs.UserPersistenceStub;


public class Services {
    private static IUserPersistence userPersistence = null;

    private static IDailyLogPersistence dailyLogPersistence = null;

    public static synchronized IUserPersistence getUserPersistence(boolean production) {
        if (userPersistence == null) {
            if (production)
                userPersistence = new UserPersistenceHSQLDB(Main.getDBPathName());
            else
                userPersistence = new UserPersistenceStub();
        }
        return userPersistence;
    }

    public static synchronized IDailyLogPersistence getDailyLogPersistence(boolean production) {
        if (dailyLogPersistence == null) {
            if (production)
                dailyLogPersistence = new DailyLogPersistenceHSQLDB(Main.getDBPathName());
            else
                dailyLogPersistence = new DailyLogPersistenceStub();
        }
        return dailyLogPersistence;
    }

    public static synchronized void clean() {
        userPersistence = null;
        dailyLogPersistence = null;
    }
}
