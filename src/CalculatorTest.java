import gen.*;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    private double evaluateExpression(String inputExpression) {
        CharStream input = CharStreams.fromString(inputExpression);
        CalculatorLexer lexer = new CalculatorLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CalculatorParser parser = new CalculatorParser(tokens);
        ParseTree tree = parser.program();
        CalculatorVisitorImpl visitor = new CalculatorVisitorImpl();
        visitor.visit(tree);
        return Double.parseDouble(visitor.getResult());
    }

    @Test
    public void testSimpleAddition() {
        String expression = "3 + 2\n";
        double expected = 5.0;
        double result = evaluateExpression(expression);
        assertEquals(expected, result, "3 + 2 should equal 5");
    }

    @Test
    public void testSimpleSubtraction() {
        String expression = "10 - 4\n";
        double expected = 6.0;
        double result = evaluateExpression(expression);
        assertEquals(expected, result, "10 - 4 should equal 6");
    }

    @Test
    public void testSimpleMultiplication() {
        String expression = "3 * 4\n";
        double expected = 12.0;
        double result = evaluateExpression(expression);
        assertEquals(expected, result, "3 * 4 should equal 12");
    }

    @Test
    public void testSimpleDivision() {
        String expression = "20 / 5\n";
        double expected = 4.0;
        double result = evaluateExpression(expression);
        assertEquals(expected, result, "20 / 5 should equal 4");
    }

    @Test
    public void testVariableAssignment() {
        String expression = "x = 5\ny = 3\nx + y\n";
        double expected = 8.0;
        double result = evaluateExpression(expression);
        assertEquals(expected, result, "x + y with x=5 and y=3 should equal 8");
    }

    @Test
    public void testComplexExpression() {
        String expression = "a = 4\nb = 2\na * (b + 3) / 2\n";
        double expected = 10.0;
        double result = evaluateExpression(expression);
        assertEquals(expected, result, "a * (b + 3) / 2 with a=4 and b=2 should equal 10");
    }

    @Test
    public void testNestedExpressions() {
        String expression = "x = 3\ny = x + 2\nz = y * (x + 4) / 2\nz\n";
        double expected = 12.5;
        double result = evaluateExpression(expression);
        assertEquals(expected, result, "z = y * (x + 4) / 2 with x=3 and y=5 should equal 12.5");
    }

    @Test
    public void testExpressionWithoutVariables() {
        String expression = "(3 + 2) * (7 - 5)\n";
        double expected = 10.0;
        double result = evaluateExpression(expression);
        assertEquals(expected, result, "(3 + 2) * (7 - 5) should equal 10");
    }

    @Test
    public void testComplexExpressionWithVariables() {
        String expression = "a = 2\nb = 3\nc = a + b * 2\nc * (a + b)\n";
        double expected = 28.0;
        double result = evaluateExpression(expression);
        assertEquals(expected, result, "c * (a + b) with a=2, b=3, c=8 should equal 28");
    }
}
