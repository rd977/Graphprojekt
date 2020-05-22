package de.tukl.programmierpraktikum2020.mp2.functions;

public class Minus implements Function {
    Function f1;
    Function f2;


    public Minus(Function f1, Function f2) {
        this.f1 = f1;
        this.f2 = f2;
    }

    @Override
    public String toString() {
        return  f1 + "-" + f2 ;
    }

    @Override
    public double apply(double x) {
        return f1.apply(x) - f2.apply(x);
    }

    @Override
    public Function derive()
    {
        return new Minus(f1.derive(), f2.derive());
    }
}
