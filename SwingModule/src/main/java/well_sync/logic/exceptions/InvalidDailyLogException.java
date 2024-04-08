package well_sync.logic.exceptions;

public class InvalidDailyLogException extends InvalidDataException
{
    public InvalidDailyLogException(String error) {
        super("Invalid data entered into log:\n" + error);
    }
}
