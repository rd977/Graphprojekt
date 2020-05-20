package de.tukl.programmierpraktikum2020.mp2.functions;

public class Exp implements Function {

    Function f;

    Exp(Function f) {
        this.f = f;
    }

    @Override
    public String toString() {

        return "\"" +"exp(" + f.toString().substring(1, f.toString().length()-1) + "\"";
    }

    @Override
    public double apply(double x) {
        return Math.exp(x);
    }

    @Override
    public Function derive() {

        return new Mult(f.derive(), new Exp(f));
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
