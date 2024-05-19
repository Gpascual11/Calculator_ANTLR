import gen.CalculatorLexer;
import gen.CalculatorParser;
import gen.CalculatorVisitorImpl;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CalculatorGUI extends JFrame {
    private final JTextArea inputArea;
    private final JTextArea outputArea;


    public CalculatorGUI() {
        setTitle("ANTLR CALCULATOR");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create components
        inputArea = new JTextArea(5, 35);
        inputArea.setFont(new Font("Arial", Font.PLAIN, 16));
        inputArea.setLineWrap(true);
        inputArea.setWrapStyleWord(true);
        outputArea = new JTextArea(5, 35);
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Arial", Font.BOLD, 16));
        outputArea.setBackground(new Color(240, 240, 240));
        JButton calculateButton = new JButton("Calculate");
        calculateButton.setBackground(new Color(50, 150, 250));
        calculateButton.setForeground(Color.WHITE);
        calculateButton.setFont(new Font("Arial", Font.BOLD, 16));
        JButton clearButton = new JButton("Clear");
        clearButton.setBackground(new Color(150, 30, 60));
        clearButton.setForeground(Color.WHITE);
        clearButton.setFont(new Font("Arial", Font.BOLD, 16));

        // Add components to the frame
        setLayout(new BorderLayout());
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add phrase above input area
        JLabel inputLabel = new JLabel("Enter your operation, remember to use \")\" ! :");
        inputLabel.setFont(new Font("Arial", Font.BOLD, 16));
        inputPanel.add(inputLabel, BorderLayout.NORTH);

        inputPanel.add(new JScrollPane(inputArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(calculateButton);
        buttonPanel.add(clearButton);
        inputPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(inputPanel, BorderLayout.CENTER);

        JPanel outputPanel = new JPanel(new BorderLayout());
        outputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        outputPanel.add(new JScrollPane(outputArea), BorderLayout.CENTER);
        add(outputPanel, BorderLayout.NORTH);

        // Add action listeners to the buttons
        calculateButton.addActionListener(_ -> calculateExpression());
        clearButton.addActionListener(_ -> clearInput());

        // Add window listener to show message on close
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                JOptionPane.showMessageDialog(CalculatorGUI.this,
                        "Thank you for using me ( ͡° ͜ʖ ͡°)",
                        "GOODBYE!",
                        JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
            }
        });
    }

    private void calculateExpression() {
        try {
            // Read input expression
            String inputExpression = inputArea.getText() + "\n";

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

    private void clearInput() {
        inputArea.setText("");
        outputArea.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculatorGUI().setVisible(true));
    }
}
