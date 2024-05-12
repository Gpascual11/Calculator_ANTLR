package gen;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Main {
    public static void main(String[] args) {
        // Create a CharStream that reads from standard input
        CharStream input = CharStreams.fromString("2+(3*4)");

        // Create a lexer that feeds off of input CharStream
        CalculatorLexer lexer = new CalculatorLexer(input);

        // Create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // Create a parser that feeds off the tokens buffer
        CalculatorParser parser = new CalculatorParser(tokens);

        // Create a parse tree
        ParseTree tree = parser.expr();

        // Create a visitor to evaluate the expressions
        CalculatorVisitorImpl visitor = new CalculatorVisitorImpl();

        // Traverse the parse tree using the visitor
        double result = visitor.visit(tree);

        // Output the result
        System.out.println("Result: " + result);
    }
}
