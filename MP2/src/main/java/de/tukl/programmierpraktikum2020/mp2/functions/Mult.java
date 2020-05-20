package de.tukl.programmierpraktikum2020.mp2.functions;


public class Mult implements Function {
    Function f1;
    Function f2;

    Mult(Function f1, Function f2) {
        this.f1 = f1;
        this.f2 = f2;
    }

    @Override
    public String toString() {
        return "(" + f1.toString()+ " * " + f2.toString()+")" ;
    }

    @Override
    public double apply(double x) {
        return f1.apply(x) * f2.apply(x);
    }

    @Override
    public Function derive() {
        if (f1.derive().toString() == "0.0") {
            return new Mult(f1, f2.derive());
        } else {
            return new Mult(f1.derive(), f2.derive());
        }
    }
    //-----------Test----------------
    public static void main(String[] args){
        Function f = new Mult(new Const(5.0) , new X());
        System.out.println(f.apply (8));
        System.out.println(f.apply (1.0));
        System.out.println(f.toString ());
        System.out.println(f.derive ().toString ());
    }

}
