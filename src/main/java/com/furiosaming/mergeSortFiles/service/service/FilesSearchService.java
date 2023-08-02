package com.furiosaming.mergeSortFiles.service.service;

import com.furiosaming.mergeSortFiles.persistence.model.Sort;
import com.furiosaming.mergeSortFiles.service.constants.AppConstants;
import com.furiosaming.mergeSortFiles.service.response.Response;

import java.io.File;
import java.util.regex.Pattern;

public class FilesSearchService {

    public static Response<Sort> searchFilesInCommandLineArguments(String[] args, Sort sort){

        for(String arg: args){
            if(isOutFile(arg)){
                sort.setOutputFile(new File(arg));
                }
            else if(isInputFile(arg)){
                sort.getFiles().add(new File(arg));
            }
        }
        return checkingForFiles(sort);
    }

    public static boolean isOutFile(String arg){
        File file = new File(arg);
        Pattern patternOutFile = Pattern.compile("out.txt");
        return patternOutFile.matcher(arg).matches() && file.exists() && !file.isDirectory() && file.canRead() && file.canWrite();
    }

    public static boolean isInputFile(String arg){
        File file = new File(arg);
        Pattern patternInputFile = Pattern.compile(".*.txt");
        return patternInputFile.matcher(arg).matches() && file.exists() && !file.isDirectory() && file.canRead();
    }

    public static Response<Sort> checkingForFiles(Sort sort) {
        if(sort.getOutputFile() == null){
            return new Response.Builder<Sort>().missingOutputFile().build();
        }
        if(sort.getFiles() == null || sort.getFiles().size() <= 1){
            return new Response.Builder<Sort>().missingInputFiles().build();
        }
        return new Response.Builder<Sort>().success(sort, AppConstants.success).build();
    }

}
