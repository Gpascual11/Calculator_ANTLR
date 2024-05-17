/*
 * This class provides a simple example of using the Calculator language parser and visitor.
 * It demonstrates parsing an input expression, creating a parse tree, and evaluating the expression.
 */

import gen.CalculatorLexer;
import gen.CalculatorParser;
import gen.CalculatorVisitorImpl;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Main {
    public static void main(String[] args) {
        try {
            // Example input expression
            String inputExpression = "x = 2\ny = 3\nx + (y * 4)\n";

            // Create a CharStream that reads from the input string
            CharStream input = CharStreams.fromString(inputExpression);

            // Create a lexer that feeds off of input CharStream
            CalculatorLexer lexer = new CalculatorLexer(input);

            // Create a buffer of tokens pulled from the lexer
            CommonTokenStream tokens = new CommonTokenStream(lexer);

            // Create a parser that feeds off the tokens buffer
            CalculatorParser parser = new CalculatorParser(tokens);

            // Parse the input to create a parse tree
            ParseTree tree = parser.program();

            // Create a visitor to evaluate the expressions
            CalculatorVisitorImpl visitor = new CalculatorVisitorImpl();

            // Traverse the parse tree using the visitor
            visitor.visit(tree);

            // Output the result
            System.out.println("Result: " + visitor.getResult());
        } catch (RecognitionException e) {
            // Handle parsing errors
            System.err.println("Recognition error: " + e.getMessage());
        } catch (Exception e) {
            // Handle other potential errors
            System.err.println("Error: " + e.getMessage());
        }
    }
}
