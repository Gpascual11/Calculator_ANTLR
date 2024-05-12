package gen;

import gen.CalculatorVisitorImpl;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Main {
    public static void main(String[] args) {
        // Create a CharStream that reads from standard input
        CharStream input = CharStreams.fromString("x = 2\ny = 3\nx+(y*4)\n");

        // Create a lexer that feeds off of input CharStream
        CalculatorLexer lexer = new CalculatorLexer(input);

        // Create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // Create a parser that feeds off the tokens buffer
        CalculatorParser parser = new CalculatorParser(tokens);

        // Create a parse tree
        ParseTree tree = parser.program();

        // Create a visitor to evaluate the expressions
        CalculatorVisitorImpl visitor = new CalculatorVisitorImpl();

        // Traverse the parse tree using the visitor
        visitor.visit(tree);

        // Output the result
        System.out.println("Result: " + visitor.getResult());
    }
}