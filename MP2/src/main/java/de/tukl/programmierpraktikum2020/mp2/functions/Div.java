package de.tukl.programmierpraktikum2020.mp2.functions;


public class Div implements Function {
    Function f1;
    Function f2;

    Div(Function f1, Function f2) {
        this.f1 = f1;
        this.f2 = f2;
    }

    @Override
    public String toString() {
        return "(" + f1.toString() + " / " + f2.toString() + ")";
    }

    @Override
    public double apply(double x) {
        return f1.apply(x) / f2.apply(x);
    }

    @Override
    public Function derive() {

        return new Div(new Plus(new Mult(f1.derive(), f2), new Mult(new Mult(new Const(-1.0), f2.derive()), f1)),
                new Pow(f2, new Const(2.0)));
    }
    //-----------Test----------------
    public static void main(String[] args){
        Function f = new Div(new X() , new X());
        System.out.println(f.apply (8.0));
        System.out.println(f.apply (2.0));
        System.out.println(f.toString ());
        System.out.println(f.derive ().toString ());
    }


}