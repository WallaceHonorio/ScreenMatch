package com.walla_ho.screenmatch.model;

public enum Category {
    ACTION("Action"),
    COMEDY("Comedy"),
    CRIME("Crime"),
    DRAMA("Drama"),
    ROMANCE("Romance");

    private String categoryOmdb;

    Category(String categoryOmdb){
        this.categoryOmdb = categoryOmdb;
    }

    public static Category fromString(String text) {
        for (Category category : Category.values()) {
            if (category.categoryOmdb.equalsIgnoreCase(text)) {
                return category;
            }
        }
        throw new IllegalArgumentException("No categories found: " + text);

    }
}
