package de.tukl.programmierpraktikum2020.mp2;

import com.sun.jdi.request.ExceptionRequest;
import de.tukl.programmierpraktikum2020.mp2.antlr.FunctionBaseVisitor;
import de.tukl.programmierpraktikum2020.mp2.antlr.FunctionParser;
import de.tukl.programmierpraktikum2020.mp2.functions.*;

import javax.print.DocFlavor;
import javax.swing.tree.ExpandVetoException;

public class Parser extends FunctionBaseVisitor<Function> {
    @Override
    public Function visitParExpr(FunctionParser.ParExprContext ctx) {
            return this.visit(ctx.expr());

    }

    @Override
    public Function visitTrigExp(FunctionParser.TrigExpContext ctx) {

        Function f = this.visit(ctx.expr());
        if(ctx.op.getType() == FunctionParser.COS){
            return new Cos(f);
        }else{
            return new Sin(f);
        }
    }


    @Override
    public Function visitExpExpr(FunctionParser.ExpExprContext ctx) {
        Function f = this.visit(ctx.expr());
        if(ctx.op.getType() == FunctionParser.EXP){
            return new Exp(f);
       }else{
            return new Log(f);
        }
    }

    @Override
    public Function visitSgnValExpr(FunctionParser.SgnValExprContext ctx) {
        Function f = this.visit(ctx.var());
        if (ctx.sgn ==null) {
            return f;
        }
        else {
            if(ctx.sgn.getType()==FunctionParser.PLUS){
                return f;
            }
            else {
                return new Mult( f,new Const(-1.0) );
            }
        }

    }

    @Override
    public Function visitAddExpr(FunctionParser.AddExprContext ctx) {
        Function f1 = this.visit(ctx.lexpr);
        Function f2 = this.visit(ctx.rexpr);
        if (ctx.op.getType()== FunctionParser.PLUS)
            return  new Plus(f1,f2);
        else
            return new Plus(f1,new Mult(new Const(-1.0),f2));

    }

    @Override
    public Function visitPowExpr(FunctionParser.PowExprContext ctx) {
        Function f1 = this.visit(ctx.lexpr);
        Function f2 = this.visit(ctx.rexpr);
        return new Pow(f1, f2);

    }

    @Override
    public Function visitMultExpr(FunctionParser.MultExprContext ctx) {
        Function f1 = this.visit(ctx.lexpr);
        Function f2 = this.visit(ctx.rexpr);
        if (ctx.op.getType() == FunctionParser.MULT)
            return new Mult( f1, f2);
        else
            return new Div(f1,f2);
    }

    @Override
    public Function visitConstVar(FunctionParser.ConstVarContext ctx) {
        return new Const( Double.parseDouble(ctx.getText()));

    }

    @Override
    public Function visitIdVar(FunctionParser.IdVarContext ctx) {
        return new X();
    }
}
