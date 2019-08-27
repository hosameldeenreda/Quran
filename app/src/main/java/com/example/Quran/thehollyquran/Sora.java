package com.example.Quran.thehollyquran;

/**
 * Created by Hosam on 10/15/2018.
 */

public class Sora {
    private String name;
    private int first;
    private int end;
    private int number;
    private boolean statement;//مكية=1    مدنية=0

    public Sora(String name, int first, int number, boolean statement) {
        this.name = name;
        this.first = first;
        this.number = number;
        this.statement = statement;
    }

    public Sora(){

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isStatement() {
        return statement;
    }

    public void setStatement(boolean statement) {
        this.statement = statement;
    }
}
