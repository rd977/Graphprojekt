package de.tukl.programmierpraktikum2020.mp2.functions;

public class Asin implements Function {
    Function f;

    public Asin(Function f) {
        this.f = f;
    }

    @Override
    public String toString() {
        return "asin(" + f.toString() + ")";
    }

    @Override
    public double apply(double x) {
        return Math.asin(f.apply(x));
    }

    @Override
    public Function derive() {

        return new Div(f.derive(), new Sqrt(new Minus(new Const(1.0), new Pow(f,new Const(2.0)))));
    }

    //-----------Test----------------
    public static void main(String[] args) {
        Function f = new Asin(new Div(new X(), new Sin(new X())));
        System.out.println(f.apply(100));
        System.out.println(f.apply(1.0));
        System.out.println(f.toString());
        System.out.println(f.derive().toString());
    }
}