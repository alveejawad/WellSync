package com.well_sync.logic.exceptions;

public class InvalidRecipeTitle extends InvalidRecipeException{
    public InvalidRecipeTitle(String error) {
        super("The title is empty:\n" + error);
    }
}
