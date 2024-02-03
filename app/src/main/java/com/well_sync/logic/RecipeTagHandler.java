package com.well_sync.logic;

import com.well_sync.application.Services;
import com.well_sync.logic.exceptions.InvalidTagException;
import com.well_sync.logic.exceptions.TagNotFoundException;
import com.well_sync.objects.RecipeTag;
import com.well_sync.persistence.RecipeTagPersistence;
import android.util.Log;

import java.util.List;

public class RecipeTagHandler {
    private RecipeTagPersistence dataAccessRecipeTag;

    public RecipeTagHandler(boolean forProduction) {
        //default get recipeTagSet
        dataAccessRecipeTag = Services.getRecipeTagPersistence(forProduction);
    }

    public List<RecipeTag> getAllRecipeTags() throws InvalidTagException {
        return dataAccessRecipeTag.getAllTags();
    }

    public RecipeTag insertOneTag(RecipeTag tag)  {
        if(RecipeTagValidator.validateRecipeTag(tag)) {
            return dataAccessRecipeTag.insertOneTag(tag);
        }
        return null;
    }

    public void deleteOneTag(RecipeTag tag) throws InvalidTagException  {

        try{
            if(RecipeTagValidator.validateRecipeTag(tag)) {
                dataAccessRecipeTag.deleteOneTag(tag);
            }
        }catch(TagNotFoundException e) {
            Log.e("Tag Not found: id: " + tag.getTagID(), e.getMessage());
            e.printStackTrace();
        }
    }
}
