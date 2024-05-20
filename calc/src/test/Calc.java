package test;

import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class Calc {
    public static double eval(String exp) {
        StringTokenizer st = new StringTokenizer(exp);
        Stack<Double> stack = new Stack<>();

        while (st.hasMoreTokens()) {
            String tok = st.nextToken();

            if (lnfix2Postfix.opType(tok) > 0) {
                double v1 = stack.pop();
                double v2 = stack.pop();
                double value = 0;

                switch (lnfix2Postfix.opType(tok)) {
                    case 1: // +
                        value = v2 + v1;
                        break;
                    case 2: // -
                        value = v2 - v1;
                        break;
                    case 3: // *
                        value = v2 * v1;
                        break;
                    case 4: // /
                        if (v1 == 0) throw new ArithmeticException("Division by zero");
                        value = v2 / v1;
                        break;
                    case 5: // ^
                        value = Math.pow(v2, v1);
                        break;
                }

                stack.push(value);
            } else {
                stack.push(Double.parseDouble(tok));
            }
        }

        if (stack.size() != 1) {
            throw new IllegalStateException("Invalid expression");
        }

        return stack.pop();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter infix expression:");
        String infix = sc.nextLine();

        try {
            String postfix = lnfix2Postfix.convert(infix);
            double value = eval(postfix);
            System.out.printf("%s = %.2f\n", infix, value);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}