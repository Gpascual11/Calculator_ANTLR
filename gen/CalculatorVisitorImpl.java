import java.util.HashMap;
import java.util.Map;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

public class CalculatorVisitorImpl extends CalculatorBaseVisitor<Double> {
    private Map<String, Double> variables = new HashMap<>();

    @Override
    public Double visitInt(CalculatorParser.IntContext ctx) {
        return Double.parseDouble(ctx.INT().getText());
    }

    @Override
    public Double visitAddSub(CalculatorParser.AddSubContext ctx) {
        double left = visit(ctx.term(0));
        double right = visit(ctx.term(1));
        return ctx.op.getText().equals("+") ? left + right : left - right;
    }

    @Override
    public Double visitMulDiv(CalculatorParser.MulDivContext ctx) {
        double left = visit(ctx.term(0));
        double right = visit(ctx.term(1));
        return ctx.op.getText().equals("*") ? left * right : left / right;
    }

    @Override
    public Double visitParens(CalculatorParser.ParensContext ctx) {
        return visit(ctx.expr());
    }
}
