package com.example.xxxx.thehollyquran;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Hosam on 10/15/2018.
 */

public class Goz2 {
    private int number;
    private ArrayList<Sora> soraArrayList;
    private int first;
    private String name;
    public Goz2() {
    }

    public Goz2(int first, String name) {
        this.first = first;
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public ArrayList<Sora> getSoraArrayList() {
        return soraArrayList;
    }

    public void setSoraArrayList(ArrayList<Sora> soraArrayList) {
        this.soraArrayList = soraArrayList;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
