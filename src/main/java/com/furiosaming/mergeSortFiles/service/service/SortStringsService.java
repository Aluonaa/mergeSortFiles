package com.furiosaming.mergeSortFiles.service.service;

import com.furiosaming.mergeSortFiles.persistence.model.Sort;
import com.furiosaming.mergeSortFiles.service.inputArgumentsCheck.FilesEmptinessCheck;
import com.furiosaming.mergeSortFiles.service.response.Response;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class SortStringsService {

    public static Response<Sort> sortFiles(Sort sort) throws IOException {

        int currentFile = 0;
        File resultFile = null;
        int sortFilesCount = sort.getFiles().size();

        currentFile = FilesEmptinessCheck.findFirstNotEmptyFile(sort, currentFile);
        if(currentFile > sortFilesCount){
            return new Response.Builder<Sort>().allFilesAreEmpty().build();
        }
        else{
            for(int i=currentFile; i<sortFilesCount; i++){
                resultFile = nextFiles(sort, i, new File("out"+(i-1)+".txt"));
            }
            Files.copy(resultFile.toPath(), sort.getOutputFile().toPath(), StandardCopyOption.REPLACE_EXISTING);
            return new Response.Builder<Sort>().success(sort,"Файлы отсортированы").build();
        }
    }


    public static File nextFiles(Sort sort, int currentFile, File previousFile){
        try(BufferedReader reader1 = new BufferedReader(new FileReader(previousFile));
            BufferedReader reader2 = new BufferedReader(new FileReader(sort.getFiles().get(currentFile)));
            FileWriter fileWriter = new FileWriter(new File("out"+currentFile+".txt"))){
            String currentMinElementOfPreviousFile;
            String currentMinElementOfCurrentFile;
            String line1;
            String line2;
            String lastElementInFile;

            while (true){
                line2 = reader2.readLine();
                if(line2 != null && line2.contains(" ")){

                }
                else {
                    currentMinElementOfCurrentFile = line2;
                    line1 = reader1.readLine();
                    currentMinElementOfPreviousFile = line1;
                    if(line2 == null){
                        fileWriter.write(currentMinElementOfPreviousFile+"\r\n");
                        line1 = reader1.readLine();
                        lastElementInFile = currentMinElementOfPreviousFile;
                        currentMinElementOfPreviousFile = line1;
                    }
                    else {
                        if(currentMinElementOfPreviousFile.compareTo(currentMinElementOfCurrentFile) >= 0){
                            fileWriter.write(currentMinElementOfCurrentFile+"\r\n");
                            line2 = reader2.readLine();
                            lastElementInFile = currentMinElementOfCurrentFile;
                            currentMinElementOfCurrentFile = line2;
                        }
                        else {
                            fileWriter.write(currentMinElementOfPreviousFile+"\r\n");
                            line1 = reader1.readLine();
                            lastElementInFile = currentMinElementOfPreviousFile;
                            currentMinElementOfPreviousFile = line1;
                        }
                    }
                    break;
                }
            }

            while (true){
                if(line2 != null && line2.contains(" ")){

                }
                else {
                    if(line1 == null && line2 == null){
                        break;
                    }
                    else if (line1 != null && lastElementInFile.compareTo(currentMinElementOfPreviousFile) > 0){
                        break;
                    }
                    else if (line2 != null && lastElementInFile.compareTo(currentMinElementOfCurrentFile) > 0){
                        break;
                    }
                    else {
                        if(line1 == null){
                            currentMinElementOfCurrentFile = line2;
                            fileWriter.write(currentMinElementOfCurrentFile+"\r\n");
                            line2 = reader2.readLine();
                            lastElementInFile = currentMinElementOfCurrentFile;
                        }
                        else if(line2 == null){
                            currentMinElementOfPreviousFile = line1;
                            fileWriter.write(currentMinElementOfPreviousFile+"\r\n");
                            line1 = reader1.readLine();
                            lastElementInFile = currentMinElementOfPreviousFile;
                        }
                        else {
                            currentMinElementOfPreviousFile = line1;
                            currentMinElementOfCurrentFile = line2;
                            if(currentMinElementOfPreviousFile.compareTo(currentMinElementOfCurrentFile) >= 0){
                                fileWriter.write(currentMinElementOfCurrentFile+"\r\n");
                                line2 = reader2.readLine();
                                lastElementInFile = currentMinElementOfCurrentFile;
                            }
                            else {
                                fileWriter.write(currentMinElementOfPreviousFile+"\r\n");
                                line1 = reader1.readLine();
                                lastElementInFile = currentMinElementOfPreviousFile;
                            }
                        }
                    }
                }
            }
            if(currentFile+2 <= sort.getFiles().size()){
                currentFile +=1;
            }
        }
        catch (IOException ioException){
            ioException.printStackTrace();
        }
        return new File("out"+currentFile+".txt");
    }
}
