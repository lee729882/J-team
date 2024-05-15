package test;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class lnfix2Postfix {
    public static String convert(String exp) {
        if (exp == null || exp.length() == 0) return null;

        // 정규표현식을 사용하여 표현식을 피연산자와 연산자로 나눔
        Pattern pattern = Pattern.compile("\\d+(\\.\\d+)?|[+\\-*/()]");
        Matcher matcher = pattern.matcher(exp);
        Stack<String> stack = new Stack<>();
        StringBuilder buf = new StringBuilder();

        boolean prevTokenIsOperator = true; // 직전 토큰이 연산자인지 여부

        while (matcher.find()) {
            String tok = matcher.group();

            // -음수 처리
            if (tok.equals("-")) {
                if (prevTokenIsOperator) {
                    // '-'가 음수 부호인 경우
                    buf.append("0 "); // 음수 부호가 있는 경우 0을 먼저 스택에 넣어줌
                } else {
                    // '-'가 연산자인 경우
                    while (!stack.isEmpty() && !stack.peek().equals("(") && hasHigherPrecedence(tok, stack.peek())) {
                        buf.append(stack.pop()).append(' ');
                    }
                    stack.push(tok);
                    prevTokenIsOperator = true;
                    continue;
                }
            } else if (opType(tok) > 0 || tok.equals("(")) {
                // 연산자인 경우
                while (!stack.empty() && !stack.peek().equals("(") && hasHigherPrecedence(tok, stack.peek())) {
                    buf.append(stack.pop()).append(' ');
                }
                stack.push(tok);
                prevTokenIsOperator = true;
            } else if (tok.equals(")")) {
                // ')'를 만나면 '('가 나올 때까지 스택의 연산자를 모두 pop하여 출력
                while (!stack.empty() && !stack.peek().equals("(")) {
                    buf.append(stack.pop()).append(' ');
                }
                stack.pop(); // '(' 제거
                prevTokenIsOperator = true;
            } else {
                // 피연산자인 경우
                buf.append(tok).append(' ');
                prevTokenIsOperator = false;
            }
        }

        while (!stack.empty()) {
            buf.append(stack.pop()).append(' ');
        }

        return buf.toString();
    }

    private static boolean hasHigherPrecedence(String op1, String op2) {
        int p1 = getPriority(op1.charAt(0));
        int p2 = getPriority(op2.charAt(0));
        return p1 < p2 || (p1 == p2 && isLeftAssociative(op1.charAt(0)));
    }

    private static boolean isLeftAssociative(char op) {
        return op == '+' || op == '-' || op == '*' || op == '/';
    }

    public static int opType(String op) {
        op = op.trim();
        if (op.length() > 1 || op.length() == 0) {
            return -1;
        }
        char c = op.charAt(0);
        switch (c) {
            case '+':
                return 1;
            case '-':
                return 2;
            case '*':
                return 3;
            case '/':
                return 4;
        }
        return -1;
    }

    private static int getPriority(char op) {
        switch (op) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return -1;
        }
    }

    public static void main(String[] args) {
        String exp = "12 +- 2.5 * 5";
        System.out.printf("%s => %s %n", exp, lnfix2Postfix.convert(exp));
    }
}