package de.tukl.programmierpraktikum2020.mp1;

public class Pair {
    private String fst; //KEY
    private Integer sec; //VALUE

    public Pair(String fst, Integer sec) {
        this.fst = fst;
        this.sec = sec;
    }

    public void setSecond(Integer sec) {
        this.sec = sec;
    }

    public String getFirst() {
        return fst;
    }

    public Integer getSecond() {
        return sec;
    }
}

