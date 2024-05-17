package gen;

import java.util.HashMap;
import java.util.Map;

public class CalculatorVisitorImpl extends CalculatorBaseVisitor<Double> {
    private Map<String, Double> variables = new HashMap<>();
    private Double result;

    @Override
    public Double visitProgram(CalculatorParser.ProgramContext ctx) {
        for (CalculatorParser.StatementContext statementContext : ctx.statement()) {
            visit(statementContext);
        }
        return null;
    }

    @Override
    public Double visitExprStatement(CalculatorParser.ExprStatementContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public Double visitAssignmentStatement(CalculatorParser.AssignmentStatementContext ctx) {
        return visit(ctx.assign());
    }

    @Override
    public Double visitAssignment(CalculatorParser.AssignmentContext ctx) {
        String varName = ctx.ID().getText();
        double value = visit(ctx.expr());
        variables.put(varName, value);
        return value;
    }

    @Override
    public Double visitMulDiv(CalculatorParser.MulDivContext ctx) {
        double left = visit(ctx.term(0));
        for (int i = 1; i < ctx.term().size(); i++) {
            if (ctx.MUL(i - 1) != null) {
                left *= visit(ctx.term(i));
            } else if (ctx.DIV(i - 1) != null) {
                left /= visit(ctx.term(i));
            }
        }
        result = left;
        return result;
    }

    @Override
    public Double visitAddSub(CalculatorParser.AddSubContext ctx) {
        double left = visit(ctx.factor(0));
        for (int i = 1; i < ctx.factor().size(); i++) {
            if (ctx.ADD(i - 1) != null) {
                left += visit(ctx.factor(i));
            } else if (ctx.SUB(i - 1) != null) {
                left -= visit(ctx.factor(i));
            }
        }
        result = left;
        return result;
    }

    @Override
    public Double visitInt(CalculatorParser.IntContext ctx) {
        return Double.parseDouble(ctx.INT().getText());
    }

    @Override
    public Double visitVariable(CalculatorParser.VariableContext ctx) {
        String varName = ctx.ID().getText();
        if (!variables.containsKey(varName)) {
            throw new RuntimeException("Variable " + varName + " not defined");
        }
        return variables.get(varName);
    }

    @Override
    public Double visitParens(CalculatorParser.ParensContext ctx) {
        return visit(ctx.expr());
    }

    public String getResult() {
        return Double.toString(result);
    }
}
