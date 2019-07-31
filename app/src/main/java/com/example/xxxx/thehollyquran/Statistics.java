package com.example.xxxx.thehollyquran;

import java.util.Date;

/**
 * Created by Hosam on 10/15/2018.
 */

public class Statistics {
    private Date day;
    private int numOfPages;
    private int numOfSora;
    private int numOfGoz2;

    public Statistics() {
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public int getNumOfPages() {
        return numOfPages;
    }

    public void setNumOfPages(int numOfPages) {
        this.numOfPages = numOfPages;
    }

    public int getNumOfSora() {
        return numOfSora;
    }

    public void setNumOfSora(int numOfSora) {
        this.numOfSora = numOfSora;
    }

    public int getNumOfGoz2() {
        return numOfGoz2;
    }

    public void setNumOfGoz2(int numOfGoz2) {
        this.numOfGoz2 = numOfGoz2;
    }
}
