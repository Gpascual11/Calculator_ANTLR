package gen;

import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

public class CalculatorVisitorImpl extends CalculatorBaseVisitor<Double> {

    @Override
    public Double visitInt(CalculatorParser.IntContext ctx) {
        return Double.parseDouble(ctx.INT().getText());
    }

    @Override
    public Double visitAddSub(CalculatorParser.AddSubContext ctx) {
        double left = visit(ctx.term(0));
        double right = visit(ctx.term(1));
        String operator = ctx.getChild(1).getText(); // Get the operator from the second child
        return operator.equals("+") ? left + right : left - right;
    }

    @Override
    public Double visitMulDiv(CalculatorParser.MulDivContext ctx) {
        double left = visit(ctx.term(0));
        double right = visit(ctx.term(1));
        String operator = ctx.getChild(1).getText(); // Get the operator from the second child
        return operator.equals("*") ? left * right : left / right;
    }

    @Override
    public Double visitParens(CalculatorParser.ParensContext ctx) {
        return visit(ctx.expr());
    }
}
