package com.furiosaming.mergeSortFiles.service.inputArgumentsCheck;

import com.furiosaming.mergeSortFiles.persistence.enums.SortMode;
import com.furiosaming.mergeSortFiles.persistence.enums.TypeOfElements;
import com.furiosaming.mergeSortFiles.persistence.model.Sort;
import com.furiosaming.mergeSortFiles.service.constants.AppConstants;
import com.furiosaming.mergeSortFiles.service.response.Response;

import java.util.regex.Pattern;

public class SortModeSettingsCheck {
    public static Response<Sort> setSortParameters(String[] args, Sort sort){
        for(String arg: args) {
            if (isTypeOfElements(arg)) {
                sort.setTypeOfElements(stringToTypeOfElements(arg));
            } else if (isSortMode(arg)) {
                sort.setSortMode(stringToSortMode(arg));
            }
        }
        return checkSortParameters(sort);
    }

    public static boolean isTypeOfElements(String arg){
        Pattern patternTypeOfElements = Pattern.compile("-([is])");
        return patternTypeOfElements.matcher(arg).matches();
    }

    public static boolean isSortMode(String arg){
        Pattern patternSortMode = Pattern.compile("-([ad])");
        return patternSortMode.matcher(arg).matches();
    }

    public static SortMode stringToSortMode(String arg){
        for(SortMode sortMode: SortMode.values())   {
            if(sortMode.getTitle().equals(arg)){
                return sortMode;
            }
        }
        return null;
    }

    public static TypeOfElements stringToTypeOfElements(String arg){
        for(TypeOfElements typeOfElements: TypeOfElements.values()){
            if(typeOfElements.getTitle().equals(arg)){
                return typeOfElements;
            }
        }
        return null;
    }

    public static Response<Sort> checkSortParameters(Sort sort){
        if (sort.getSortMode() == null) {
            sort.setSortMode(SortMode.ASCENDING);
        }
        if (sort.getTypeOfElements() == null) {
            return new Response.Builder<Sort>().missingFilesDataType().build();
        }
        return new Response.Builder<Sort>().success(sort, AppConstants.success).build();
    }
}
