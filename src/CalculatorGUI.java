import gen.CalculatorLexer;
import gen.CalculatorParser;
import gen.CalculatorVisitorImpl;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGUI extends JFrame {
    private JTextField displayField;
    private StringBuilder inputBuffer;

    public CalculatorGUI() {
        setTitle("Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false); // Prevent resizing

        inputBuffer = new StringBuilder();

        displayField = new JTextField();
        displayField.setEditable(false);
        displayField.setFont(new Font("Arial", Font.PLAIN, 24));
        displayField.setHorizontalAlignment(SwingConstants.RIGHT);
        displayField.setBackground(new Color(200, 200, 200));

        JPanel buttonPanel = new JPanel(new GridLayout(5, 4, 5, 5));
        buttonPanel.setBackground(new Color(55, 55, 55)); // Dark gray background

        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "=", "+", "Enter"
        };

        for (String label : buttonLabels) {
            JButton button = createButton(label);
            buttonPanel.add(button);
        }

        JPanel controlPanel = new JPanel(new GridLayout(1, 1, 5, 5));
        controlPanel.setBackground(new Color(55, 55, 55)); // Dark gray background
        controlPanel.add(displayField);

        setLayout(new BorderLayout());
        add(controlPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
    }

    private JButton createButton(String label) {
        JButton button = new JButton(label);
        button.setFont(new Font("Arial", Font.PLAIN, 24));
        button.setFocusPainted(false); // Remove button focus
        button.addActionListener(new ButtonClickListener());

        switch (label) {
            case "/":
            case "*":
            case "-":
            case "+":
                button.setBackground(new Color(255, 140, 0)); // Orange for operations
                button.setForeground(Color.WHITE); // White text color
                break;
            case "=":
                button.setBackground(new Color(70, 130, 180)); // Steel blue for equals
                button.setForeground(Color.WHITE); // White text color
                break;
            case "Enter":
                button.setBackground(new Color(34, 139, 34)); // Forest green for Enter
                button.setForeground(Color.WHITE); // White text color
                button.addActionListener(new EnterButtonListener());
                break;
            default:
                button.setBackground(new Color(169, 169, 169)); // Light gray for numbers
                button.setForeground(Color.BLACK); // Black text color
        }

        return button;
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            String label = source.getText();
            if (label.equals("=")) {
                calculateExpression();
            } else if (!label.equals("Enter")) {
                displayField.setText(displayField.getText() + label);
            }
        }
    }

    private class EnterButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            inputBuffer.append(displayField.getText()).append("\n");
            displayField.setText("");
        }
    }

    private void calculateExpression() {
        try {
            String inputExpression = displayField.getText();
            CharStream input = CharStreams.fromString(inputExpression);
            CalculatorLexer lexer = new CalculatorLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            CalculatorParser parser = new CalculatorParser(tokens);
            ParseTree tree = parser.program();
            CalculatorVisitorImpl visitor = new CalculatorVisitorImpl();
            visitor.visit(tree);
            displayField.setText(String.valueOf(visitor.getResult()));
        } catch (RecognitionException e) {
            displayField.setText("Error");
        } catch (Exception e) {
            displayField.setText("Error");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculatorGUI().setVisible(true));
    }
}