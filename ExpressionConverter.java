import java.util.Stack;
import java.util.Scanner;

public class ExpressionConverter {

    // Helper to define operator strength
    static int getPrecedence(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
        }
        return -1;
    }

    // Core logic for Infix to Postfix
    public static String infixToPostfix(String exp) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);

            // If operand, add to result
            if (Character.isLetterOrDigit(c)) {
                result.append(c);
            } 
            // If '(', push to stack
            else if (c == '(') {
                stack.push(c);
            } 
            // If ')', pop until '(' is found
            else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop());
                }
                stack.pop(); // Discard '('
            } 
            // Operator encountered
            else {
                while (!stack.isEmpty() && getPrecedence(c) <= getPrecedence(stack.peek())) {
                    result.append(stack.pop());
                }
                stack.push(c);
            }
        }

        // Pop remaining operators
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }

        return result.toString();
    }

    // Logic for Infix to Prefix
    public static String infixToPrefix(String exp) {
        // 1. Reverse the infix expression
        StringBuilder revExp = new StringBuilder(exp).reverse();

        // 2. Swap brackets
        for (int i = 0; i < revExp.length(); i++) {
            if (revExp.charAt(i) == '(') {
                revExp.setCharAt(i, ')');
            } else if (revExp.charAt(i) == ')') {
                revExp.setCharAt(i, '(');
            }
        }

        // 3. Get Postfix of the modified expression
        String result = infixToPostfix(revExp.toString());

        // 4. Reverse result to get Prefix
        return new StringBuilder(result).reverse().toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Infix Expression: ");
        String infix = sc.nextLine();

        System.out.println("Postfix: " + infixToPostfix(infix));
        System.out.println("Prefix:  " + infixToPrefix(infix));
        
        sc.close();
    }
}