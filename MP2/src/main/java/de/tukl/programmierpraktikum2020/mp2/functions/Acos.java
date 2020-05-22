package de.tukl.programmierpraktikum2020.mp2.functions;

public class Acos implements Function {
    Function f;

    public Acos(Function f) {
        this.f = f;
    }

    @Override
    public String toString() {
        return "acos(" + f.toString() + ")";
    }

    @Override
    public double apply(double x) {
        return Math.acos(f.apply(x));
    }

    @Override
    public Function derive() {

        return new Mult(new Const(-1.0), new Div(f.derive(), new Sqrt(new Minus(new Const(1.0), new Pow(f,new Const(2.0))))));
    }

    //-----------Test----------------
    public static void main(String[] args) {
        Function f = new Acos(new Div(new X(), new Sin(new X())));
        System.out.println(f.apply(100));
        System.out.println(f.apply(1.0));
        System.out.println(f.toString());
        System.out.println(f.derive().toString());
    }
}