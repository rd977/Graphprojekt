package de.tukl.programmierpraktikum2020.mp2.functions;

public class X implements Function {
    double x;

    @Override
    public String toString() {
        return (x == 1.0) ? ""+ this.x :"x";
    }

    @Override
    public double apply(double x) {
        return x;
    }

    @Override
    public Function derive() {
        X c = new X();
        c.x = 1.0;
        return c;
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
