package main;

import java.util.Stack;

public class PolishNotation {
    public static String ConvertInfixToPrefix(String expression) {
        var postfixInv = new StringBuilder(ConvertInfixToPostfix(ReverseString(expression)));
        return postfixInv.reverse().toString();
    }

    public static String ConvertInfixToPostfix(String expression) {
        var postfix = new StringBuilder();
        var elements = expression.split("\\s+");
        var operatorStack = new Stack<String>();

        for (var element : elements) {
            if (IsOperand(element)) {
                postfix.append(element).append(" ");
            } else if (IsOperator(element)) {
                while (!operatorStack.isEmpty() && !operatorStack.peek().equals("(") && Precedence(operatorStack.peek()) > Precedence(element))
                    postfix.append(operatorStack.pop()).append(" ");
                operatorStack.push(element);
            } else if (element.equals("(")) {
                operatorStack.push(element);
            } else if (element.equals(")")) {
                while (!operatorStack.isEmpty() && !operatorStack.peek().equals("("))
                    postfix.append(operatorStack.pop()).append(" ");
                operatorStack.pop();
            }

        }
        while (!operatorStack.isEmpty())
            postfix.append(operatorStack.pop()).append(" ");

        return postfix.toString().trim();
    }

    private static String ReverseString(String expression) {
        var reversed = new StringBuilder(expression.length());
        for (var c : expression.toCharArray()) {
            switch (c) {
                case '(':
                    reversed.append(')');
                    break;
                case ')':
                    reversed.append('(');
                    break;
                default:
                    reversed.append(c);
            }
        }
        return reversed.reverse().toString();
    }

    private static boolean IsOperator(String operator) {
        return operator.equals("+") || operator.equals("-") ||
                operator.equals("*") || operator.equals("/");
    }

    private static boolean IsOperand(String element) {
        try {
            Double.parseDouble(element);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static int Precedence(String operator) {
        switch (operator) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            default:
                throw new IllegalArgumentException();
        }
    }
}
