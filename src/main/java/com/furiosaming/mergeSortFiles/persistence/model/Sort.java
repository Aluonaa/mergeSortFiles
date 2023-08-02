package com.furiosaming.mergeSortFiles.persistence.model;

import com.furiosaming.mergeSortFiles.persistence.enums.SortMode;
import com.furiosaming.mergeSortFiles.persistence.enums.TypeOfElements;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Data
@Schema(name = "Sort", description = "Сортировка")
public class Sort {
    @Schema(name = "typeOfElements", description = "Тип элементов в файлах")
    TypeOfElements typeOfElements;
    @Schema(name = "sortMode", description = "Режим сортировки (возрастающая или убывающая)")
    SortMode sortMode;
    @Schema(name = "outputFile", description = "Выходной файл для записи результата сортировки")
    File outputFile;
    @Schema(name = "files", description = "Входные файлы для сортировки")
    List<File> files = new ArrayList<>();
}
