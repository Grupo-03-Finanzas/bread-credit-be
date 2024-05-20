package com.finanzas.breadcredit.utility;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class UtilityDto {
    public static <T, U> U convertTo(T source, Class<U> targetClass) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(source, targetClass);
    }
    public static <T, U> List<U> convertToList(List<T> sourceList, Class<U> targetClass) {
        List<U> targetList = new ArrayList<U>();
        for (T source : sourceList) {
            U converted = convertTo(source, targetClass);
            targetList.add(converted);
        }
        return targetList;
    }
}
