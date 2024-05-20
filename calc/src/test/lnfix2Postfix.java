package test;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class lnfix2Postfix {
    public static String convert(String exp) {
        if (exp == null || exp.length() == 0) return null;

        // 수식을 토큰으로 분할하기 위한 정규 표현식
        Pattern pattern = Pattern.compile("\\d+(\\.\\d+)?|[+\\-*/()]");
        Matcher matcher = pattern.matcher(exp);
        Stack<String> stack = new Stack<>(); // 연산자를 임시로 저장하는 스택
        StringBuilder buf = new StringBuilder(); // 후위 표현식을 저장하는 버퍼

        boolean prevTokenIsOperator = true; // 이전 토큰이 연산자인지 여부

        while (matcher.find()) {
            String tok = matcher.group();
            // 음수 처리: 이전 토큰이 연산자일 때만 '0'을 추가하여 음수로 처리
            if ((tok.equals("-") || tok.equals("+")) && prevTokenIsOperator) {
                buf.append("0 ");
            }

            if (isOperator(tok) || tok.equals("(")) {
                // 현재 토큰이 연산자이거나 '('일 때
                while (!stack.isEmpty() && !stack.peek().equals("(") && hasHigherPrecedence(tok, stack.peek())) {
                    // 스택의 top에 있는 연산자보다 우선순위가 높거나 같은 연산자들을 스택에서 pop하고 후위 표현식에 추가
                    buf.append(stack.pop()).append(' ');
                }
                // 현재 연산자를 스택에 push
                stack.push(tok);
                prevTokenIsOperator = true;
            } else if (tok.equals(")")) {
                // 현재 토큰이 ')'일 때
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    // 스택에서 '('가 나올 때까지 연산자를 pop하고 후위 표현식에 추가
                    buf.append(stack.pop()).append(' ');
                }
                // '('는 스택에서 제거
                stack.pop();
                prevTokenIsOperator = false;
            } else {
                // 현재 토큰이 피연산자일 때
                buf.append(tok).append(' '); // 후위 표현식에 바로 추가
                prevTokenIsOperator = false;
            }
        }

        // 스택에 남은 모든 연산자를 pop하여 후위 표현식에 추가
        while (!stack.isEmpty()) {
            buf.append(stack.pop()).append(' ');
        }

        // 후위 표현식 문자열 반환
        return buf.toString().trim();
    }

    // 두 연산자의 우선순위를 비교하는 메서드
    private static boolean hasHigherPrecedence(String op1, String op2) {
        int p1 = getPriority(op1);
        int p2 = getPriority(op2);
        return p1 < p2 || (p1 == p2 && isLeftAssociative(op1));
    }

    // 연산자의 결합성을 확인하는 메서드
    private static boolean isLeftAssociative(String op) {
        return op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/");
    }

    // 주어진 문자열이 연산자인지 확인하는 메서드
    private static boolean isOperator(String op) {
        return op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/");
    }

    // 연산자의 우선순위를 반환하는 메서드
    private static int getPriority(String op) {
        switch (op) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            default:
                return 0;
        }
    }

    public static int opType(String op) {
        if (op == null || op.length() != 1) return -1;
        switch (op) {
            case "+": return 1;
            case "-": return 2;
            case "*": return 3;
            case "/": return 4;
            case "^": return 5;
            default: return -1;
        }
    }
}