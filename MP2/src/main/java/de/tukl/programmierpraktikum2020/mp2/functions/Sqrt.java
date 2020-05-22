package de.tukl.programmierpraktikum2020.mp2.functions;

public class Sqrt implements Function{
    Function f;
    public Sqrt(Function f){
        this.f = f;
    }
    @Override
    public String toString() {
        return "sqrt(" + f.toString() + ")";
    }

    @Override
    public double apply(double x) {
        return Math.sqrt(f.apply(x));
    }

    @Override
    public Function derive() {
        return new Pow(f , new Const(0.5)).derive();
    }
}
