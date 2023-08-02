package com.furiosaming.mergeSortFiles.service.inputArgumentsCheck;

import com.furiosaming.mergeSortFiles.persistence.model.Sort;

import java.io.*;

public class FilesEmptinessCheck {
    public static Integer findFirstNotEmptyFile(Sort sort, int currentFile){
        try(BufferedReader reader = new BufferedReader(new FileReader(sort.getFiles().get(currentFile)));
            FileWriter fileWriter = new FileWriter(new File("out"+currentFile+".txt"))){
            String currentMaxElement;
            String line;
            while (true){
                line = reader.readLine();
                //Если файл пустой, проверяем следующий файл на наличие элементов
                if (line == null){
                    if(currentFile+1 > sort.getFiles().size()){
                        return currentFile+1;
                    }
                    currentFile +=1;
                    findFirstNotEmptyFile(sort, currentFile);
                }
                else {
                    if(line.contains(" ")){
                        continue;
                    }
                    fileWriter.write(line+"\r\n");
                    // Текущий максимальный элемент это первая строка файла
                    currentMaxElement = line;
                    break;
                }
            }

            while (true){
                line = reader.readLine();
                if(line == null || currentMaxElement.compareTo(line) > 0){
                    break;
                }
                else {
                    fileWriter.write(line+"\r\n");
                }
            }
        }
        catch (IOException ioException){
            ioException.printStackTrace();
        }
        return currentFile+1;
    }
}
