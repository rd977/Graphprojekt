package de.tukl.programmierpraktikum2020.mp2.functions;


public class Cos implements Function {
    Function f;

    Cos(Function f) {
        this.f = f;
    }

    @Override
    public String toString() {
        return "cos(" + f.toString() + ")";
    }

    @Override
    public double apply(double x) {
        return Math.cos(x);
    }

    @Override
    public Function derive() {

        return new Mult(f.derive(), new Mult(new Const(-1.0), new Sin(f)));
    }
    //-----------Test----------------
    public static void main(String[] args){
        Function f = new Cos(new X());
        System.out.println(f.apply (100));
        System.out.println(f.apply (1.0));
        System.out.println(f.toString ());
        System.out.println(f.derive ().toString ());
    }

}
