package de.tukl.programmierpraktikum2020.mp2.functions;
public class Abs implements Function {
    Function f;

    public Abs(Function f) {
        this.f = f;
    }

    @Override
    public String toString() {

        return "abs(" + f.toString() + ")";
    }

    @Override
    public double apply(double x) {
        return Math.abs(f.apply(x));
    }

    @Override
    public Function derive() {
        return new Div(new Abs(f), f);
    }
}