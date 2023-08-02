package com.furiosaming.mergeSortFiles.app;


import com.furiosaming.mergeSortFiles.service.service.MergeSortService;


import java.io.IOException;


public class mergeSortFilesApp {
    public static void main(String[] args) throws IOException {
        System.out.println(MergeSortService.mergeSort(args).getDescription());
    }
}
