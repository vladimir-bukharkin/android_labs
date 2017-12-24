package com.example.vbukharkin.l02.converter;


import java.util.HashSet;

/**
 * Created by v.bukharkin on 24.12.2017.
 */

public class FreqConverter implements IConverter {

    private static FreqConverterValue[] freqTypes = new FreqConverterValue[5];

    private String[] spinnerElements = new String[freqTypes.length];
    {
        new HashSet<FreqConverterValue>() {{
            add(new FreqConverterValue("Герц", 1L));
            add(new FreqConverterValue("Килогерц", 1_000L));
            add(new FreqConverterValue("Мегагерц", 1_000_000L));
            add(new FreqConverterValue("Гигагерц", 1_000_000_000L));
            add(new FreqConverterValue("Терагерц", 1_000_000_000_000L));
        }}.toArray(freqTypes);

        for (int i = 0; i < freqTypes.length; i++) {
            spinnerElements[i] = freqTypes[i].getName();
        }
    }

    @Override
    public String[] getSpinnerElements() {
        return spinnerElements;
    }

    @Override
    public double calculate(float value, int in, int out) {

        FreqConverterValue inConvType = freqTypes[in];
        FreqConverterValue outConvType = freqTypes[out];
        if (inConvType == null || outConvType == null) {
            return value; //todo handle exception
        } else {
            return value * inConvType.getMultiply() / outConvType.getMultiply();
        }
    }

    private static class FreqConverterValue {

        private final String name;
        private final long multiply;

        private FreqConverterValue(String name, long multiply) {
            this.name = name;
            this.multiply = multiply;
        }

        private String getName() {
            return name;
        }

        private long getMultiply() {
            return multiply;
        }
    }
}
