package test;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Color;

public class MyCalc extends JFrame {
    private String exp = "";
    private double prevResult = 0;
    private boolean isResultShown = false;

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JButton btnNewButton_1;
    private JButton btnNewButton;
    private JButton btnNewButton_2;
    private JButton btnNewButton_3;
    private JButton btnNewButton_4;
    private JButton btnNewButton_5;
    private JButton btnNewButton_6;
    private JButton btnNewButton_7;
    private JButton btnNewButton_8;
    private JButton btnNewButton_9;
    private JButton btnNewButton_10;
    private JButton btnNewButton_11;
    private JButton btnNewButton_12;
    private JButton btnNewButton_13;
    private JButton btnNewButton_14;
    private JButton btnNewButton_15;
    private JButton btnNewButton_16; 
    private JMenuBar menuBar;
    private JMenu mnNewMenu;
    private JMenuItem mntmNewMenuItem;
    private JMenu mnNewMenu_1;
    private JMenuItem mntmNewMenuItem_1;

    private final AboutDialog aboutDialog = new AboutDialog();
    private JButton btnNewButton_17;
    private JButton btnNewButton_18;
    private JButton btnNewButton_19;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MyCalc frame = new MyCalc();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public MyCalc() {
        setTitle("계산기");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 330, 450);

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        mnNewMenu = new JMenu("File");
        menuBar.add(mnNewMenu);

        mntmNewMenuItem = new JMenuItem("Exit");
        mntmNewMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        mnNewMenu.add(mntmNewMenuItem);

        mnNewMenu_1 = new JMenu("Help");
        menuBar.add(mnNewMenu_1);

        mntmNewMenuItem_1 = new JMenuItem("About");
        mntmNewMenuItem_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getAboutDialog().setVisible(true);
            }
        });
        mnNewMenu_1.add(mntmNewMenuItem_1);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.NORTH);
        panel.setLayout(new BorderLayout(0, 0));

        textField = new JTextField();
        textField.setEditable(false);
        textField.setFont(new Font("Serif", Font.BOLD, 40)); // 텍스트 필드 높이 줄임
        textField.setText("0");
        textField.setHorizontalAlignment(SwingConstants.RIGHT);
        textField.setColumns(10);
        textField.setPreferredSize(new Dimension(textField.getPreferredSize().width, 70)); // 텍스트 필드 높이 줄임
        
        
     // 키보드 입력 처리를 위한 KeyListener 추가
        textField.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                // 결과가 표시된 상태라면 수식을 초기화
                if (isResultShown) {
                    exp = "";
                    isResultShown = false;
                }
                // 숫자, 연산자, 소수점, 괄호 등의 입력 처리
                if ((c >= '0' && c <= '9') || c == '+' || c == '*' || c == '/' || c == '.' || c == '^' || c == '(' || c == ')') {
                    appendToExp(String.valueOf(c));
                } 
                // 백스페이스 입력 처리
                else if (c == '\b') {
                    if (exp.length() > 0) {
                        exp = exp.substring(0, exp.length() - 1);
                        textField.setText(exp);
                    }
                } 
                // Enter 키 입력 처리
                else if (c == '\n' || c == '\r') {
                    calculateResult();
                } 
                // '-' 입력 처리
                else if (c == '-') {
                    // 수식이 비어있거나 '('로 끝날 때는 음수로 처리
                    if (exp.isEmpty() || exp.endsWith("(")) {
                        appendToExp(String.valueOf(c));
                    } 
                    // 이전에 '-'나 '+', '*', '/' 또는 '^'로 끝날 때는 연산자로 처리
                    else if (exp.endsWith("-") || exp.endsWith("+") || exp.endsWith("*") || exp.endsWith("/") || exp.endsWith("^")) {
                        exp = exp.substring(0, exp.length() - 1) + String.valueOf(c);
                        textField.setText(exp);
                    } 
                    // 그 외의 경우에는 음수로 처리
                    else {
                        appendToExp(String.valueOf(c));
                    }
                }
            }

            public void keyPressed(KeyEvent e) {
                char c = e.getKeyChar();
                // '-' 입력 처리
                if (c == '-') {
                    // 결과가 표시된 상태이거나 수식이 비어있거나 특정 문자로 끝날 때는 음수로 처리
                    if (isResultShown || exp.isEmpty() || exp.endsWith("(") || exp.endsWith("+") || exp.endsWith("-") || exp.endsWith("*") || exp.endsWith("/") || exp.endsWith("^")) {
                        appendToExp(String.valueOf(c));
                    }
                }
                // ESC 키를 눌렀을 때 수식 초기화
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    exp = "";
                    textField.setText("0");
                }
            }

            public void keyReleased(KeyEvent e) {
                // 키 릴리스 처리 로직 (필요 시 구현)
            }

        
            private void calculateSquare() {
                if (!exp.isEmpty()) {
                    double number = Double.parseDouble(exp);
                    double result = Math.pow(number, 2);
                    textField.setText(String.valueOf(result));
                    exp = String.valueOf(result);
                    isResultShown = true;
                }
            }
        });
        
        

        panel.add(textField, BorderLayout.CENTER);

        JPanel panel_1 = new JPanel();
        contentPane.add(panel_1, BorderLayout.SOUTH);
        panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

        textField_1 = new JTextField();
        textField_1.setText("Status");
        textField_1.setColumns(10);
        panel_1.add(textField_1);

        JPanel panel_2 = new JPanel();
        contentPane.add(panel_2, BorderLayout.CENTER);
        panel_2.setLayout(new GridLayout(5, 4, 10, 2)); // 수직 간격을 줄임
        
     // "(" 버튼
        btnNewButton_17 = new JButton("(");
        btnNewButton_17.setFont(new Font("Serif", Font.BOLD, 18));
        btnNewButton_17.setForeground(Color.BLUE);
        panel_2.add(btnNewButton_17);

        // ")" 버튼
        btnNewButton_18 = new JButton(")");
        btnNewButton_18.setForeground(Color.BLUE);
        btnNewButton_18.setFont(new Font("Serif", Font.BOLD, 18));
        panel_2.add(btnNewButton_18);

        // "(" 버튼의 Action Listener 추가
        btnNewButton_17.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 텍스트 필드에 "(" 추가
                appendToExp("(");
            }
        });

        // ")" 버튼의 Action Listener 추가
        btnNewButton_18.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 텍스트 필드에 ")" 추가
                appendToExp(")");
            }
        });

        
     // 제곱 기능 버튼
        btnNewButton_19 = new JButton("X*X");
        btnNewButton_19.setForeground(Color.BLUE);
        btnNewButton_19.setFont(new Font("Serif", Font.BOLD, 18));
        panel_2.add(btnNewButton_19);

        // 제곱 기능 버튼의 Action Listener 추가
        btnNewButton_19.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                appendToExp("^");
                // 현재 텍스트 필드에 표시된 값 가져오기
                String currentText = textField.getText();
                // 현재 텍스트 필드의 값이 숫자인지 확인
                try {
                    double value = Double.parseDouble(currentText);
                    // 제곱 계산 후 텍스트 필드에 표시
                    textField.setText(String.valueOf(value * value));
                } catch (NumberFormatException ex) {
                    // 숫자가 아닌 경우 처리
                    textField.setText("Invalid Input");
                }
            }
        });


        btnNewButton_1 = new JButton("8");
        btnNewButton_1.setFont(new Font("Serif", Font.BOLD, 18));
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                appendToExp("8");
            }
        });
                
                        btnNewButton_3 = new JButton("/");
                        btnNewButton_3.setForeground(new Color(0, 0, 255));
                        btnNewButton_3.setFont(new Font("Serif", Font.BOLD, 18));
                        btnNewButton_3.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                appendToExp("/");
                            }
                        });
                        panel_2.add(btnNewButton_3);
        
                btnNewButton = new JButton("7");
                btnNewButton.setFont(new Font("Serif", Font.BOLD, 18));
                btnNewButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        appendToExp("7");
                    }
                });
                panel_2.add(btnNewButton);
        panel_2.add(btnNewButton_1);

        btnNewButton_2 = new JButton("9");
        btnNewButton_2.setFont(new Font("Serif", Font.BOLD, 18));
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                appendToExp("9");
            }
        });
        panel_2.add(btnNewButton_2);

        btnNewButton_5 = new JButton("5");
        btnNewButton_5.setFont(new Font("Serif", Font.BOLD, 18));
        btnNewButton_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                appendToExp("5");
            }
        });
        
                btnNewButton_4 = new JButton("4");
                btnNewButton_4.setFont(new Font("Serif", Font.BOLD, 18));
                btnNewButton_4.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        appendToExp("4");
                    }
                });
                
                        btnNewButton_7 = new JButton("x");
                        btnNewButton_7.setForeground(new Color(0, 0, 255));
                        btnNewButton_7.setFont(new Font("Serif", Font.BOLD, 18));
                        btnNewButton_7.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                appendToExp("*");
                            }
                        });
                        panel_2.add(btnNewButton_7);
                panel_2.add(btnNewButton_4);
        panel_2.add(btnNewButton_5);

        btnNewButton_6 = new JButton("6");
        btnNewButton_6.setFont(new Font("Serif", Font.BOLD, 18));
        btnNewButton_6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                appendToExp("6");
            }
        });
        panel_2.add(btnNewButton_6);

        btnNewButton_9 = new JButton("2");
        btnNewButton_9.setFont(new Font("Serif", Font.BOLD, 18));
        btnNewButton_9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                appendToExp("2");
            }
        });
        
                btnNewButton_8 = new JButton("1");
                btnNewButton_8.setFont(new Font("Serif", Font.BOLD, 18));
                btnNewButton_8.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        appendToExp("1");
                    }
                });
                
                        btnNewButton_11 = new JButton("-");
                        btnNewButton_11.setForeground(new Color(0, 0, 255));
                        btnNewButton_11.setFont(new Font("Serif", Font.BOLD, 18));
                        btnNewButton_11.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                appendToExp("-");
                            }
                        });
                        panel_2.add(btnNewButton_11);
                panel_2.add(btnNewButton_8);
        panel_2.add(btnNewButton_9);

        btnNewButton_10 = new JButton("3");
        btnNewButton_10.setFont(new Font("Serif", Font.BOLD, 18));
        btnNewButton_10.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                appendToExp("3");
            }
        });
        panel_2.add(btnNewButton_10);

        btnNewButton_14 = new JButton("=");
        btnNewButton_14.setForeground(new Color(255, 0, 0));
        btnNewButton_14.setFont(new Font("Serif", Font.BOLD, 18));
        btnNewButton_14.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateResult();
            }
        });
                
                btnNewButton_16 = new JButton(".");
                btnNewButton_16.setFont(new Font("Serif", Font.BOLD, 18));
                btnNewButton_16.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        appendToExp(".");
                    }
                });
                
                        btnNewButton_13 = new JButton("C");
                        btnNewButton_13.setForeground(new Color(255, 0, 0));
                        btnNewButton_13.setFont(new Font("Serif", Font.BOLD, 18));
                        btnNewButton_13.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                exp = "";
                                textField.setText("0");
                            }
                        });
                        
                                btnNewButton_15 = new JButton("+");
                                btnNewButton_15.setForeground(new Color(0, 0, 255));
                                btnNewButton_15.setFont(new Font("Serif", Font.BOLD, 18));
                                btnNewButton_15.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e) {
                                        appendToExp("+");
                                    }
                                });
                                panel_2.add(btnNewButton_15);
                        panel_2.add(btnNewButton_13);
                
                        btnNewButton_12 = new JButton("0");
                        btnNewButton_12.setFont(new Font("Serif", Font.BOLD, 18));
                        btnNewButton_12.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                appendToExp("0");
                            }
                        });
                        panel_2.add(btnNewButton_12);
                panel_2.add(btnNewButton_16); // 새로운 버튼 추가
        panel_2.add(btnNewButton_14);
    }

    private void appendToExp(String str) {
        // 이전 결과가 표시된 상태라면 수식을 초기화하고 결과 표시 상태를 해제
        if (isResultShown) {
            exp = "";
            isResultShown = false;
        }
        // 추가할 문자열이 "^"인 경우, 제곱 연산자를 수식에 추가하고 결과를 계산하여 표시
        if (str.equals("^")) {
            exp += "^";
            textField.setText(exp); // 텍스트 필드 업데이트
            calculateResult(); // 결과 계산
            isResultShown = true; // 결과 표시 상태 설정
        } else {
            // 그 외의 경우, 수식에 문자열을 추가하고 텍스트 필드 업데이트
            exp += str;
            textField.setText(exp);
        }
    }

    private void calculateResult() {
        try {
            // 중위 표기법을 후위 표기법으로 변환하여 수식 계산
            String postfix = lnfix2Postfix.convert(exp);
            double value = Calc.eval(postfix);
            prevResult = value; // 이전 결과 업데이트
            // 계산된 결과를 형식화하여 문자열로 변환
            String result = String.format("%.2f", value);
            textField.setText(result); // 텍스트 필드에 결과 표시
            isResultShown = true; // 결과 표시 상태 설정
        } catch (Exception e) {
            // 예외 발생 시 "Error" 메시지 표시하고 수식 초기화
            textField.setText("Error");
            exp = "";
        }
    }
    
    
    public JTextField textField() {
        return textField;
    }

    public AboutDialog getAboutDialog() {
        return aboutDialog;
    }
}