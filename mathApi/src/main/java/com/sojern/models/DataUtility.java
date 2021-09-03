package com.sojern.models;

public class DataUtility {
    private Double[] list;
    private Double quantifier;

    public DataUtility() {}

    public Double [] convertToList(String input) {
        String [] temp = input.split(",");
        list = new Double[temp.length];
        for(int i=0;i<list.length;i++)
            list[i] = Double.parseDouble(temp[i]);
        return list;
    }

    public double convertToQuantifier(String input) {
        this.quantifier = Double.parseDouble(input);
        return this.quantifier;
    }
}
