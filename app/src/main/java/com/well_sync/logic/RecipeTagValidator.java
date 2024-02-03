package com.well_sync.logic;

import com.well_sync.logic.exceptions.InvalidTagException;
import com.well_sync.objects.RecipeTag;

public class RecipeTagValidator {

    /* validateRecipeTag()
     *
     * Return false if tag is invalid
     */
    public static boolean validateRecipeTag(RecipeTag tag) throws InvalidTagException {
        return tag !=null;
    }
}
