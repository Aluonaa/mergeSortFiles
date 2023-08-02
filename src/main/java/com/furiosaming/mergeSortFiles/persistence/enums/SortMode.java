package com.furiosaming.mergeSortFiles.persistence.enums;

public enum SortMode {
    ASCENDING ("-a"),
    DESCENDING ("-d");

    private String title;

    SortMode(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
