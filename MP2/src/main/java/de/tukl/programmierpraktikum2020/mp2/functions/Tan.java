package de.tukl.programmierpraktikum2020.mp2.functions;


public class Tan implements Function {
    Function f;

    public Tan(Function f) {
        this.f = f;
    }

    @Override
    public String toString() {
        return "tan(" + f.toString() + ")";
    }

    @Override
    public double apply(double x) {
        return Math.tan(f.apply(x));
    }

    @Override
    public Function derive() {

        return new Div(f.derive(), new Pow(new Cos(f),new Const(2.0)));
    }
}