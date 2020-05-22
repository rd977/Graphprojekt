package de.tukl.programmierpraktikum2020.mp2.functions;

public class Plus implements Function {
    Function f1;
    Function f2;


    public Plus(Function f1, Function f2) {
        this.f1 = f1;
        this.f2 = f2;
    }

    @Override
    public String toString() {
        return  f1 + "+" + f2 ;
    }

    @Override
    public double apply(double x) {
        return f1.apply(x) + f2.apply(x);
    }

    @Override
    public Function derive()
    {
        return new Plus(f1.derive(), f2.derive());
    }
    //-----------Test----------------
    public static void main(String[] args){
        Function f = new Plus(new Const(2),new X());
        System.out.println(f.apply (0.0));
        System.out.println(f.apply (1.0));
        System.out.println(f.toString ());
        System.out.println(f.derive ().toString ());
    }

}
