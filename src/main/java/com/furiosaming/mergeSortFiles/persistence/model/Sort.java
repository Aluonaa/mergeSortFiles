package com.furiosaming.mergeSortFiles.persistence.model;

import com.furiosaming.mergeSortFiles.persistence.enums.SortMode;
import com.furiosaming.mergeSortFiles.persistence.enums.TypeOfElements;
import lombok.Data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Data
public class Sort {
    TypeOfElements typeOfElements;
    SortMode sortMode;
    File outputFile;
    List<File> files = new ArrayList<>();
}
