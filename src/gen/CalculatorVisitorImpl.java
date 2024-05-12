package gen;

import java.util.HashMap;
import java.util.Map;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class CalculatorVisitorImpl extends CalculatorBaseVisitor<Double> {
    private Map<String, Double> variables = new HashMap<>();
    private Double result; // Afegim una variable per emmagatzemar el resultat

    @Override
    public Double visitAssign(CalculatorParser.AssignContext ctx) {
        String varName = ctx.ID().getText();
        double value = visit(ctx.expr());
        variables.put(varName, value);
        return value;
    }

    @Override
    public Double visitInt(CalculatorParser.IntContext ctx) {
        return Double.parseDouble(ctx.getText());
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
    public Double visitAddSub(CalculatorParser.AddSubContext ctx) {
        double left = visit(ctx.expr(0));
        double right = visit(ctx.expr(1));
        if (ctx.ADD() != null) {
            result = left + right; // Assignem el resultat a la variable result
            return result;
        } else {
            result = left - right; // Assignem el resultat a la variable result
            return result;
        }
    }

    @Override
    public Double visitMulDiv(CalculatorParser.MulDivContext ctx) {
        double left = visit(ctx.term(0));
        double right = visit(ctx.term(1));
        if (ctx.MUL() != null) {
            result = left * right; // Assignem el resultat a la variable result
            return result;
        } else {
            result = left / right; // Assignem el resultat a la variable result
            return result;
        }
    }



    // Add this method
    @Override
    public Double visitProgram(CalculatorParser.ProgramContext ctx) {
        // Visit each statement in the program
        for (CalculatorParser.StatementContext statementContext : ctx.statement()) {
            visit(statementContext);
        }
        return null;
    }

    public String getResult() {
        return Double.toString(result);
    }
}

