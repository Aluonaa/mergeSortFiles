package com.furiosaming.mergeSortFiles.service.service;

import com.furiosaming.mergeSortFiles.persistence.model.Sort;
import com.furiosaming.mergeSortFiles.service.inputArgumentsCheck.FilesExistenceCheck;
import com.furiosaming.mergeSortFiles.service.inputArgumentsCheck.SortModeSettingsCheck;
import com.furiosaming.mergeSortFiles.service.response.Response;

import java.io.IOException;

public class MergeSortService {
    public static Response<Sort> mergeSort(String[] args) throws IOException {
        Sort sort = new Sort();

        Response<Sort> responseWithSortMode = SortModeSettingsCheck.setSortParameters(new String[] {args[0], args[1]}, sort);
        if(!responseWithSortMode.isStatus()){
            return responseWithSortMode;
        }

        Response<Sort> responseWithFiles = FilesExistenceCheck.searchFilesInCommandLineArguments(args, sort);
        if(!responseWithFiles.isStatus()){
            return responseWithFiles;
        }

        return SortStringsService.sortFiles(responseWithFiles.getResult());
    }
}
