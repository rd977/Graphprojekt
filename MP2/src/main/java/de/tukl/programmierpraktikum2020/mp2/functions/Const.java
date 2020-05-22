package de.tukl.programmierpraktikum2020.mp2.functions;

public class Const implements Function {
    final double number;

    public Const(double number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }

    @Override
    public double apply(double x) {
        return number;
    }

    @Override
    public Function derive() {
        return new Const(0.0);
    }



    //-----------Test----------------
    public static void main(String[] args){
        Function f = new Const(3.14);
        System.out.println(f.apply ( -42.123));
        System.out.println(f.apply (0.0));
        System.out.println(f.toString ());
        System.out.println(f.derive ().toString ());
    }

}