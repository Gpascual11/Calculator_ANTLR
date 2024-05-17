import gen.CalculatorLexer;
import gen.CalculatorParser;
import gen.CalculatorVisitorImpl;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import javax.swing.*;
import java.awt.*;

public class CalculatorGUI extends JFrame {
    private final JTextArea inputArea;
    private final JTextArea outputArea;

    public CalculatorGUI() {
        setTitle("Calculator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create components
        inputArea = new JTextArea(5, 30);
        inputArea.setFont(new Font("Arial", Font.PLAIN, 14));
        inputArea.setLineWrap(true);
        inputArea.setWrapStyleWord(true);
        outputArea = new JTextArea(5, 30);
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Arial", Font.BOLD, 14));
        outputArea.setBackground(new Color(240, 240, 240));
        JButton calculateButton = new JButton("Calculate");
        calculateButton.setBackground(new Color(50, 150, 250));
        calculateButton.setForeground(Color.WHITE);
        calculateButton.setFont(new Font("Arial", Font.BOLD, 14));

        // Add components to the frame
        setLayout(new BorderLayout());
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.add(new JScrollPane(inputArea), BorderLayout.CENTER);
        inputPanel.add(calculateButton, BorderLayout.SOUTH);
        add(inputPanel, BorderLayout.CENTER);
        JPanel outputPanel = new JPanel(new BorderLayout());
        outputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        outputPanel.add(new JScrollPane(outputArea), BorderLayout.CENTER);
        add(outputPanel, BorderLayout.NORTH);

        // Add action listener to the button
        calculateButton.addActionListener(_ -> calculateExpression());
    }

    private void calculateExpression() {
        try {
            // Read input expression
            String inputExpression = inputArea.getText();

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
            outputArea.setText("Result: " + visitor.getResult());
        } catch (RecognitionException e) {
            // Handle parsing errors
            outputArea.setText("Recognition error: " + e.getMessage());
        } catch (Exception e) {
            // Handle other potential errors
            outputArea.setText("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculatorGUI().setVisible(true));
    }
}
