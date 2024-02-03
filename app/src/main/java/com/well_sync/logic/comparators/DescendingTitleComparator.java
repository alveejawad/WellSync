package com.well_sync.logic.comparators;
import com.well_sync.objects.Recipe;
import java.util.Comparator;

public class DescendingTitleComparator implements Comparator<Recipe> {
    @Override
    public int compare(Recipe first, Recipe second) {
        return second.getRecipeTitle().compareTo(first.getRecipeTitle());
    }
}