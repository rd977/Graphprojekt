package de.tukl.programmierpraktikum2020.mp2.functions;

import java.io.PipedWriter;

public class Acot implements Function {
    Function f;

    public Acot(Function f) {
        this.f = f;
    }

    @Override
    public String toString() {
        return "acot(" + f.toString() + ")";
    }

    @Override
    public double apply(double x) {
        return Math.atan(f.apply(1/x));
    }

    @Override
    public Function derive() {
        return new Mult(new Const(-1.0), new Div(f.derive(), new Plus(new Const(1.0), new Pow(f,new Const(2.0)))));
    }

    //-----------Test----------------
    public static void main(String[] args) {
        Function f = new Acot(new Div(new X(), new Sin(new X())));
        System.out.println(f.apply(100));
        System.out.println(f.apply(1.0));
        System.out.println(f.toString());
        System.out.println(f.derive().toString());
    }
}