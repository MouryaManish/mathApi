package com.sojern.models;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class Data {
    @NotBlank
    @Pattern(regexp = "[0-9]+((,[0-9]+)?)+")
    String list;

    @Pattern(regexp = "[0-9]+")
    String q = String.valueOf(Integer.MAX_VALUE);

    public Data() {}

    public void setList(String data){
        list = data;
    }

    public String getList() {
        return this.list;
    }

    public void setQ(String data) {
        if(data!=null && data.length()>0)
            q = data;
    }

    public String getQ() {
        return this.q;
    }

}