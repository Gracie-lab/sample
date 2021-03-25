package com.medicine.medicineProject.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class UpdateObjectHelper {
    public static void updateUserProfile(Object objectDto, Object existingObject) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Field[] fieldsInObjectDto = objectDto.getClass().getDeclaredFields();
        for (Field field: fieldsInObjectDto) {
            field.setAccessible(true);
            Object fieldValue = field.get(objectDto);
            if(fieldValue != null){
                String fieldName = field.getName();
                String setterFieldName = getSetterMethod(fieldName);

                Method method = existingObject.getClass().getDeclaredMethod(setterFieldName,field.getType());
                method.setAccessible(true);
                method.invoke(existingObject,fieldValue);
            }
        }
    }

    public static String getSetterMethod(String fieldValue) {
        String firstLetterOfFieldName = String.valueOf(fieldValue.charAt(0)).toUpperCase();
        String fieldNameWithoutFirstLetter = fieldValue.substring(1);
        String fieldName = firstLetterOfFieldName + fieldNameWithoutFirstLetter;
        String setterMethod = "set"+fieldName;
        return setterMethod;
    }
}
