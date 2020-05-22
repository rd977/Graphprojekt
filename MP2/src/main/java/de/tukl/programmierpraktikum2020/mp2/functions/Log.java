package de.tukl.programmierpraktikum2020.mp2.functions;


public class Log implements Function {
    Function f;

    public Log(Function f) {
        this.f = f;
    }

    @Override
    public String toString() {

        return  "log(" + f.toString() + ")";
    }

    @Override
    public double apply(double x) {
        return Math.log(f.apply(x));
    }

    @Override
    public Function derive() {
        return new Div(f.derive(), f);
    }
    //-----------Test----------------
    public static void main(String[] args){
        Function f = new Log(new X());
        System.out.println(f.apply (100));
        System.out.println(f.apply (1.0));
        System.out.println(f.toString ());
        System.out.println(f.derive ().toString ());
    }

}
