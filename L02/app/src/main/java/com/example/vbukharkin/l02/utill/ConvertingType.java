package com.example.vbukharkin.l02.utill;

/**
 * Created by v.bukharkin on 24.12.2017.
 */

public enum ConvertingType {

    FREQ(1),
    DB_LEVEL(2);

    private final int id;

    private ConvertingType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static ConvertingType get(int id) {
        for (ConvertingType convertingType : ConvertingType.values()) {
            if (convertingType.id == id)
                return convertingType;
        }
        return null;
    }
}
