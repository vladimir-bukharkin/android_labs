package com.example.vbukharkin.l02.converter;


/**
 * Created by v.bukharkin on 25.12.2017.
 */

public class DbConverter implements IConverter {

    @Override
    public String[] getSpinnerElements() {
        return DbConverterValue.getNames();
    }

    @Override
    public double calculate(float value, int in, int out) {

        if (in != out) {
            // todo обобщить
            if (DbConverterValue.get(in) == DbConverterValue.mW) {
                return 10 * Math.log10(value);
            } else {
                return Math.pow(10, value/10);
            }
        } else {
            return value;
        }
    }

    private enum DbConverterValue {

        mW(0, "мВт"),
        dBm(1, "дБм");

        private final String name;
        private final int id;

        private DbConverterValue(int id, String name) {
            this.name = name;
            this.id = id;
        }

        private static String[] getNames() {
            DbConverterValue[] dbConverterValue = DbConverterValue.values();
            String[] result = new String[dbConverterValue.length];
            for (int i = 0; i < dbConverterValue.length; i++) {
                result[i] = dbConverterValue[i].name();
            }
            return result;
        }

        private static DbConverterValue get(int id) {
            for (DbConverterValue dbConverterValue : DbConverterValue.values()) {
                if (dbConverterValue.id == id) {
                    return dbConverterValue;
                }
            }
            return null;
        }
    }
}
