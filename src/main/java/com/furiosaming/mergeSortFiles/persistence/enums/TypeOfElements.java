package com.furiosaming.mergeSortFiles.persistence.enums;

public enum TypeOfElements {
    STRING ("-s"),
    INTEGER ("-i");

    private String title;

    TypeOfElements(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
