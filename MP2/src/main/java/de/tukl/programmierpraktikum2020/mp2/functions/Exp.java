package de.tukl.programmierpraktikum2020.mp2.functions;

public class Exp implements Function {

    Function f;

    public Exp(Function f) {
        this.f = f;
    }

    @Override
    public String toString() {

        return "exp(" + f.toString()+ ")" ;
    }

    @Override
    public double apply(double x) {
        return Math.exp(f.apply(x));
    }

    @Override
    public Function derive() {

        return new Mult(new Exp(f) , f.derive());
    }
    //-----------Test----------------
    public static void main(String[] args){
        Function f = new Exp(new X());
        System.out.println(f.apply (0.0));
        System.out.println(f.apply (1.0));
        System.out.println(f.toString ());
        System.out.println(f.derive ().toString ());
    }

}
