package org.softeng.assignment.logic.exceptions;

public class InvalidNotesException extends InvalidDataException {
    public InvalidNotesException (String error) {
        super("Invalid notes field:\n" + error);
    }
}
