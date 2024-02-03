package com.well_sync.logic.comparators;
import com.well_sync.objects.Recipe;
import java.util.Comparator;

public class AscendingTitleComparator implements Comparator<Recipe> {
    @Override
    public int compare(Recipe first, Recipe second) {
        return first.getRecipeTitle().compareTo(second.getRecipeTitle());
    }
}