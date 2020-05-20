package de.tukl.programmierpraktikum2020.mp2.functions;

public class Sin implements Function {
    Function f;

    Sin(Function f) {
        this.f = f;
    }

    @Override
    public String toString() {

        return "sin(" + f.toString() + ")";
    }

    @Override
    public double apply(double x) {
        return Math.sin(x);
    }

    @Override
    public Function derive() {
        return new Mult(f.derive(), new Cos(f));
    }
    //-----------Test----------------
    public static void main(String[] args){
        Function f = new Sin(new X());
        System.out.println(f.apply (100));
        System.out.println(f.apply (1.0));
        System.out.println(f.toString ());
        System.out.println(f.derive ().toString ());
    }

}
