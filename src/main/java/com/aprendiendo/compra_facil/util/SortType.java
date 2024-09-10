package com.aprendiendo.compra_facil.util;

import org.springframework.stereotype.Component;

public enum SortType {
    NOMBRE,
    APELLIDO,
    NONE;

    public static SortType fromString(String sortType) {
        SortType result;
        switch (sortType.toUpperCase()) {
            case "NOMBRE":
                result = NOMBRE;
                break;
            case "APELLIDO":
                result = APELLIDO;
                break;
            default:
                result = NONE;
                break;
        }
        return result;
    }

}
