package com.example.vbukharkin.l02.converter;

/**
 * Created by v.bukharkin on 24.12.2017.
 */

public interface IConverter {

    public String[] getSpinnerElements();

    public double calculate(float value, int in, int out);
}
