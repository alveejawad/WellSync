package com.well_sync.persistence;

import com.well_sync.objects.RecipeTag;

import java.util.*;

public interface RecipeTagPersistence {
    List<RecipeTag> getAllTags();

    RecipeTag insertOneTag(RecipeTag targetTag);

    void deleteOneTag(RecipeTag targetTag);
}
