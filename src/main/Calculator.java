package main;

import javax.swing.*;
import java.awt.*;

public class Calculator extends JFrame {
    JButton[] digits = {
            new JButton(" 0 "),
            new JButton(" 1 "),
            new JButton(" 2 "),
            new JButton(" 3 "),
            new JButton(" 4 "),
            new JButton(" 5 "),
            new JButton(" 6 "),
            new JButton(" 7 "),
            new JButton(" 8 "),
            new JButton(" 9 ")
    };

    JButton[] operators = {
            new JButton(" ( "),
            new JButton(" ) "),
            new JButton(" + "),
            new JButton(" - "),
            new JButton(" * "),
            new JButton(" / "),
            new JButton(" = "),
            new JButton(" C ")
    };

    String[] oper_values = {" ( ", " ) " ," + ", " - ", " * ", " / ", " = ", ""};

    String value;
    char operator;

    JTextArea area = new JTextArea(3, 5);

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.setSize(230, 250);
        calculator.setTitle(" Java-Calc, PP Lab1 ");
        calculator.setResizable(false);
        calculator.setVisible(true);
        calculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public Calculator() {
        add(new JScrollPane(area), BorderLayout.NORTH);
        JPanel buttonpanel = new JPanel();
        buttonpanel.setLayout(new FlowLayout());

        for (int i = 0; i < digits.length; i++)
            buttonpanel.add(digits[i]);

        for (int i = 0; i < operators.length; i++)
            buttonpanel.add(operators[i]);

        add(buttonpanel, BorderLayout.CENTER);
        area.setForeground(Color.BLACK);
        area.setBackground(Color.WHITE);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setEditable(false);

        for (int i = 0; i < digits.length; i++) {
            int finalI = i;
            digits[i].addActionListener(actionEvent -> area.append(Integer.toString(finalI)));
        }

        for (int i = 0; i < operators.length; i++) {
            int finalI = i;
            operators[i].addActionListener(actionEvent -> {
                if (finalI == 7)
                    area.setText("");
                else if (finalI == 6) {
                    try {
                        var expressionTree = new ExpressionTree(area.getText());
                        area.append(" = " + expressionTree.EvaluateTree(expressionTree.head));
                    } catch (Exception e) {
                        area.setText(" !!!Probleme!!! ");
                    }
                } else {
                    area.append(oper_values[finalI]);
                    operator = oper_values[finalI].charAt(0);
                }
            });
        }
    }
}