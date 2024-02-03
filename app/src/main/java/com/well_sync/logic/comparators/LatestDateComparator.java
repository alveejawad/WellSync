package com.well_sync.logic.comparators;
import com.well_sync.objects.Recipe;
import java.util.Comparator;
public class LatestDateComparator implements Comparator<Recipe> {
    @Override
    public int compare(Recipe first, Recipe second) {
        if (second.getRecipeDate().before(first.getRecipeDate())) {
            return -1;
        } else if (second.getRecipeDate().after(first.getRecipeDate())) {
            return 1;
        } else {
            return 0;
        }
    }
}