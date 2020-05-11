package de.tukl.programmierpraktikum2020.mp1;

public class Pair {
    private String fst; //first member of pair
    private Integer sec; //second member of pair

    public Pair(String fst, Integer sec) {
        this.fst = fst;
        this.sec = sec;
    }

    public void setFirst(String fst) {
        this.fst = fst;
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

