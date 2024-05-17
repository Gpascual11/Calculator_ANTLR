/*
 * This class implements a visitor for the Calculator language.
 * It extends the CalculatorBaseVisitor<Double> class provided by ANTLR, which allows visiting nodes of the parse tree.
 *
 * Authors: Nil Pinyol (nil.pinyol@estudiants.urv.cat), Miquel Garcia (miquel.garciam@estudiants.urv.cat), Gerard Pascual (gerard.pascual@urv.cat)
 */

package gen;

import java.util.HashMap;
import java.util.Map;

public class CalculatorVisitorImpl extends CalculatorBaseVisitor<Double> {
    // Map to store variable names and their corresponding values
    private final Map<String, Double> variables = new HashMap<>();
    // Variable to store the result of the expression evaluation
    private Double result;

    @Override
    public Double visitProgram(CalculatorParser.ProgramContext ctx) {
        // Visit each statement in the program
        for (CalculatorParser.StatementContext statementContext : ctx.statement()) {
            visit(statementContext);
        }
        return null;
    }

    @Override
    public Double visitExprStatement(CalculatorParser.ExprStatementContext ctx) {
        // Visit the expression node
        return visit(ctx.expr());
    }

    @Override
    public Double visitAssignmentStatement(CalculatorParser.AssignmentStatementContext ctx) {
        // Visit the assignment node
        return visit(ctx.assign());
    }

    @Override
    public Double visitAssignment(CalculatorParser.AssignmentContext ctx) {
        // Get the variable name
        String varName = ctx.ID().getText();
        // Evaluate the expression and store its value in the variables map
        double value = visit(ctx.expr());
        variables.put(varName, value);
        return value;
    }

    @Override
    public Double visitMulDiv(CalculatorParser.MulDivContext ctx) {
        // Evaluate multiplication and division operations
        double left = visit(ctx.term(0));
        for (int i = 1; i < ctx.term().size(); i++) {
            if (ctx.MUL(i - 1) != null) {
                left *= visit(ctx.term(i));
            } else if (ctx.DIV(i - 1) != null) {
                left /= visit(ctx.term(i));
            }
        }
        // Store the result
        result = left;
        return result;
    }

    @Override
    public Double visitAddSub(CalculatorParser.AddSubContext ctx) {
        // Evaluate addition and subtraction operations
        double left = visit(ctx.factor(0));
        for (int i = 1; i < ctx.factor().size(); i++) {
            if (ctx.ADD(i - 1) != null) {
                left += visit(ctx.factor(i));
            } else if (ctx.SUB(i - 1) != null) {
                left -= visit(ctx.factor(i));
            }
        }
        // Store the result
        result = left;
        return result;
    }

    @Override
    public Double visitInt(CalculatorParser.IntContext ctx) {
        // Return the integer value as a double
        return Double.parseDouble(ctx.INT().getText());
    }

    @Override
    public Double visitVariable(CalculatorParser.VariableContext ctx) {
        // Get the variable name
        String varName = ctx.ID().getText();
        // Check if the variable is defined in the variables map
        if (!variables.containsKey(varName)) {
            throw new RuntimeException("Variable " + varName + " not defined");
        }
        // Return the value of the variable
        return variables.get(varName);
    }

    @Override
    public Double visitParens(CalculatorParser.ParensContext ctx) {
        // Visit the expression inside the parentheses
        return visit(ctx.expr());
    }

    // Method to return the result as a string
    public String getResult() {
        return Double.toString(result);
    }
}
