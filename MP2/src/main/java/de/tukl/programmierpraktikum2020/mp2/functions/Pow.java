package de.tukl.programmierpraktikum2020.mp2.functions;


public class Pow implements Function {
    Function f1;
    Function f2;

    Pow(Function f1, Function f2) {
        this.f1 = f1;
        this.f2 = f2;
    }

    @Override
    public String toString() {
        return "(" + f1.toString() + ")^(" + f2.toString() + ")";
    }

    @Override
    public double apply(double x) {
        return Math.pow(f1.apply(x), f2.apply(x));
    }

    @Override
    public Function derive() {

        return new Mult(new Pow(f1,f2), new Plus(new Mult(f2.derive(),new Log(f1)) , new Mult(new Mult(f2 , new Div(new Const(1.0),f1)),f1.derive())));
    }
    //-----------Test----------------
    public static void main(String[] args){
        Function f = new Pow(new X(),new X());
        System.out.println(f.apply ( 4));
        System.out.println(f.apply (1));
        System.out.println(f.toString ());
        System.out.println(f.derive ().toString ());
    }

}
