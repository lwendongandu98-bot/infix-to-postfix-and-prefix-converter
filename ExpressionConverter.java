import java.util.Stack;
import java.util.Scanner;

public class ExpressionConverter {

    // 1. Define which operators are stronger
    static int strength(char op) {
        if (op == '+' || op == '-') return 1;
        if (op == '*' || op == '/') return 2;
        return 0;
    }

    // 2. The main logic 
    public static String convertToPostfix(String input) {
        Stack<Character> stack = new Stack<>();
        StringBuilder result = new StringBuilder();

        for (char c : input.toCharArray()) {
            // If it's a letter or number
            if (Character.isLetterOrDigit(c)) {
                result.append(c);
            } 
            // If it's an opening bracket
            else if (c == '(') {
                stack.push(c);
            } 
            // If it's a closing bracket
            else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop());
                }
                stack.pop(); // Remove the '('
            } 
            // If it's an operator
            else {
                while (!stack.isEmpty() && strength(stack.peek()) >= strength(c)) {
                    result.append(stack.pop());
                }
                stack.push(c);
            }
        }

        // Empty the stack at the end
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }
        return result.toString();
    }

    // 3. The Prefix logic using the shortcut
    public static String convertToPrefix(String input) {
        // Step 1: Reverse and swap brackets
        StringBuilder reversed = new StringBuilder(input).reverse();
        for (int i = 0; i < reversed.length(); i++) {
            if (reversed.charAt(i) == '(') reversed.setCharAt(i, ')');
            else if (reversed.charAt(i) == ')') reversed.setCharAt(i, '(');
        }

        // Step 2: Get postfix of the reversed version
        String postfix = convertToPostfix(reversed.toString());

        // Step 3: Reverse back to get Prefix
        return new StringBuilder(postfix).reverse().toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your expression: ");
        String expression = scanner.nextLine();

        System.out.println("--- Results ---");
        System.out.println("Postfix: " + convertToPostfix(expression));
        System.out.println("Prefix:  " + convertToPrefix(expression));
        
        scanner.close();
    }
}
