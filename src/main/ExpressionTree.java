package main;

import java.util.Stack;

public class ExpressionTree {
    public Node head;

    public ExpressionTree(String infix) {
        head = BuildTreeFromPrefix(PolishNotation.ConvertInfixToPrefix(infix));
        System.out.println(PolishNotation.ConvertInfixToPrefix(infix));
    }

    public Node BuildTreeFromPrefix(String prefix) {
        var elements = prefix.split("\\s+");
        var stack = new Stack<Node>();
        for (int i = elements.length - 1; i >= 0; --i) {
            var el = elements[i];
            if (IsOperator(el)) {
                Node left = stack.empty() ? null : stack.pop();
                Node right = stack.empty() ? null : stack.pop();
                Node node = new Node(el, left, right);
                stack.push(node);
            } else {
                stack.push(new Node(el));
            }

        }
        return stack.empty() ? null : stack.pop();
    }

    public double EvaluateTree(Node head) {
        switch (head.data) {
            case "+":
                return EvaluateTree(head.left) + EvaluateTree(head.right);
            case "-":
                return EvaluateTree(head.left) - EvaluateTree(head.right);
            case "*":
                return EvaluateTree(head.left) * EvaluateTree(head.right);
            case "/":
                return EvaluateTree(head.left) / EvaluateTree(head.right);
            default:
                return Double.parseDouble(head.data);
        }
    }

    private boolean IsOperator(String operator) {
        return operator.equals("+") || operator.equals("-") ||
                operator.equals("*") || operator.equals("/");
    }
}