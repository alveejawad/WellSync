package com.well_sync.persistence;

import com.well_sync.objects.Recipe;

import java.util.*;

public interface RecipePersistence {

    List<Recipe> getRecipeList(); //default sorted by date in ascending order

    Recipe getRecipeById(int recipeId);

    List<Recipe> getRecipeListByFavourite(boolean isFavourite);

    Recipe insertRecipe(Recipe recipe);

    Recipe updateRecipe(Recipe newRecipe);

    void deleteRecipe(Recipe recipe);

    void deleteRecipeById(int recipeId);
}
