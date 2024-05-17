import gen.*;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
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

    @org.junit.Test
    public void testSimpleAddition() {
        String expression = "3 + 2\n";
        double expected = 5.0;
        double result = evaluateExpression(expression);
        assertEquals(expected, result, "3 + 2 should equal 5");
    }

    @org.junit.Test
    public void testSimpleSubtraction() {
        String expression = "10 - 4\n";
        double expected = 6.0;
        double result = evaluateExpression(expression);
        assertEquals(expected, result, "10 - 4 should equal 6");
    }

    @org.junit.Test
    public void testSimpleMultiplication() {
        String expression = "3 * 4\n";
        double expected = 12.0;
        double result = evaluateExpression(expression);
        assertEquals(expected, result, "3 * 4 should equal 12");
    }

    @org.junit.Test
    public void testSimpleDivision() {
        String expression = "20 / 5\n";
        double expected = 4.0;
        double result = evaluateExpression(expression);
        assertEquals(expected, result, "20 / 5 should equal 4");
    }

    @org.junit.Test
    public void testVariableAssignment() {
        String expression = "x = 5\ny = 3\nx + y\n";
        double expected = 8.0;
        double result = evaluateExpression(expression);
        assertEquals(expected, result, "x + y with x=5 and y=3 should equal 8");
    }

    @org.junit.Test
    public void testComplexExpression() {
        String expression = "a = 4\nb = 2\na * (b + 3)\n";
        double expected = 20.0;
        double result = evaluateExpression(expression);
        assertEquals(expected, result, "a * (b + 3) with a=4 and b=2 should equal 20");
    }

    @org.junit.Test
    public void testNestedExpressions() {
        String expression = "x = 3\ny = x + 2\nz = y * (x + 4)\nz\n";
        double expected = 35.0;
        double result = evaluateExpression(expression);
        assertEquals(expected, result, "z = y * (x + 4)with x=3 and y=5 should equal 35.0");
    }

    @org.junit.Test
    public void testExpressionWithoutVariables() {
        String expression = "(3 + 2) * (7 - 5)\n";
        double expected = 10.0;
        double result = evaluateExpression(expression);
        assertEquals(expected, result, "(3 + 2) * (7 - 5) should equal 10");
    }

    @org.junit.Test
    public void testExpressionWithMultipleVariables() {
        String expression = "x = 3\ny = x + 2\nz = y * (x + 4)\nv=z+5\nv\n";
        double expected = 40.0;
        double result = evaluateExpression(expression);
        assertEquals(expected, result, "z = y * (x + 4) with x=3 and y= x + 2 should equal 40.0");
    }

    @org.junit.Test
    public void testExpressionWithMultipleVariablesAndComplexOperations() {
        String expression = "x = 3\ny = x * 2\nz = y / (x - 1)\nv = z + (y * 2)\nv\n";
        double expected = 15.0;
        double result = evaluateExpression(expression);
        assertEquals(expected, result, "Expression with multiple variables and complex operations should equal 12");
    }

    @org.junit.Test
    public void testExpressionWithMultipleNestedVariables() {
        String expression = "x = 2\ny = x + 3\nz = y * (x + 1)\na = z / y\nb = a * (z + y)\nc = b - (a + x)\nc\n";
        double expected = 55.0;
        double result = evaluateExpression(expression);
        assertEquals(expected, result, "Expression with multiple nested variables should equal 11");
    }

    @org.junit.Test
    public void testExpressionWithDivisionByZero() {
        String expression = "x = 5\ny = x - 5\nz = 10 / y\nz\n";
        double expected = Double.POSITIVE_INFINITY; // Division by zero should result in positive infinity
        double result = evaluateExpression(expression);
        assertEquals(expected, result, "Expression with division by zero should result in positive infinity");
    }

    @org.junit.Test
    public void testExpressionWithNegativeResult() {
        String expression = "x = 5\ny = 3 - x\nz = y * (x + 1)\nz\n";
        double expected = -12.0;
        double result = evaluateExpression(expression);
        assertEquals(expected, result, "Expression with negative result should equal -16");
    }

    @org.junit.Test
    public void testExpressionWithBiggerNumbersAndTwoOperations() {
        String expression = "x = 100\ny = x * 3\nz = y / (x - 20)\nv = z + (y * 2)\nw = v - (x / 2)\nw\n";
        double expected = 553.75;
        double result = evaluateExpression(expression);
        assertEquals(expected, result, "Expression with bigger numbers and two operations should equal 750");
    }

    @org.junit.Test
    public void testExpressionWithBiggerNumbersAndTwoOperations2() {
        String expression = "a = 500\nb = a + 300\nc = b - (a * 2)\nd = c / 5\ne = d * 2\ne\n";
        double expected = -80.0;
        double result = evaluateExpression(expression);
        assertEquals(expected, result, "Expression with bigger numbers and two operations should equal 600");
    }

    @org.junit.Test
    public void testExpressionWithBiggerNumbersAndMultipleExpressions() {
        String expression = "a = 1000\nb = a * 3\nc = b / 2\nd = c - (a / 5)\ne = d * 4\nf = e / 10\ng = f + (a * 2)\nh = g * 3\ni = h / 2\nj = i + 100\nk = j - 500\nl = k / 4\nm = l + 2000\nn = m * 2\no = n / 5\np = o - 1000\nq = p * 3\nr = q / 2\ns = r + 1000\nt = s - 500\nu = t / 4\nv = u + 2000\nw = v * 2\nx = w / 5\ny = x + 1000\nz = y - 500\nz\n";
        double expected = 1370.7;
        double result = evaluateExpression(expression);
        assertEquals(expected, result, "Expression with bigger numbers and multiple expressions should equal 5600");
    }

    @org.junit.Test
    public void testExpressionMoreOperations() {
        String expression = "x = 7\ny = 5\nz=(x+y/4)\nz\n";
        double expected = 1.75;
        double result = evaluateExpression(expression);
        assertEquals(expected, result, "Expression with bigger numbers and multiple expressions should equal 5600");
    }

}
