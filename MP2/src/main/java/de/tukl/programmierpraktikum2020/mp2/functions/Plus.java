package de.tukl.programmierpraktikum2020.mp2.functions;

public class Plus implements Function {
    Function c;
    Function v;
    double b;

    Plus(Function c, Function x) {
        this.c = c;
        this.v = x;
    }

    @Override
    public String toString() {
        return "(" + c.toString() + " + " + v.toString() + ")";
    }

    @Override
    public double apply(double x) {
        return c.apply(x) + v.apply(x);
    }

    @Override
    public Function derive() {
        return new Plus(c.derive(), v.derive());
    }
    //-----------Test----------------
    public static void main(String[] args){
        Function f = new Plus(new Const(2),new X());
        System.out.println(f.apply (0.0));
        System.out.println(f.apply (1.0));
        System.out.println(f.toString ());
        System.out.println(f.derive ().toString ());
    }

}
