package well_sync.persistence;

import well_sync.objects.DailyLog;
import well_sync.objects.Patient;

import java.util.Date;
import java.util.List;

public interface IDailyLogPersistence {
    void setDailyLog(Patient patient, DailyLog dailyLog);
    void setMedication(Patient patient, DailyLog dailyLog);
    void setSymptoms(Patient patient, DailyLog dailyLog);
    void setSubstances(Patient patient, DailyLog dailyLog);

    DailyLog getDailyLog(Patient patient, Date date);

    List<DailyLog> getAllDailyLogs(Patient patient);
}
