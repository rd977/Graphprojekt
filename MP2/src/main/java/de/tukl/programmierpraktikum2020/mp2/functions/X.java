package de.tukl.programmierpraktikum2020.mp2.functions;

public class X implements Function {
    double x;

    @Override
    public String toString() {
        return "x";
    }

    @Override
    public double apply(double x) {
        return x;

    }

    @Override
    public Function derive() {
        return new Const(1.0);
    }
    //-----------Test----------------
    public static void main(String[] args){
        Function f = new X();
        System.out.println(f.apply ( -42.123));
        System.out.println(f.apply (0.0));
        System.out.println(f.toString ());
        System.out.println(f.derive ().toString ());
    }

}
