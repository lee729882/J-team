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
import java.awt.Color; // JTextFiled 세로 크기 변경 

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
    private JMenuBar menuBar;
    private JMenu mnNewMenu;
    private JMenuItem mntmNewMenuItem;
    private JMenu mnNewMenu_1;
    private JMenuItem mntmNewMenuItem_1;

    private final AboutDialog aboutDialog = new AboutDialog();

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
        setBounds(100, 100, 300, 450);

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
        textField.setFont(new Font("Serif", Font.BOLD, 50));
        textField.setText("0");
        textField.setHorizontalAlignment(SwingConstants.RIGHT);
        textField.setColumns(10);
        textField.setPreferredSize(new Dimension(textField.getPreferredSize().width, 100)); // JTextFiled 세로 크기 변경 
        textField.addKeyListener(new KeyListener() {
            
            // key 이벤트 추가
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (isResultShown) {
                    exp = "";
                    isResultShown = false;
                }
                if ((c >= '0' && c <= '9') || c == '+' || c == '-' || c == '*' || c == '/' ) {
                    exp = exp + c;
                    textField.setText(exp);
                } else if (c == '\b') {  // Backspace
                    if (exp.length() > 0) {
                        exp = exp.substring(0, exp.length() - 1);
                        textField.setText(exp);
                    }
                } else if (c == '\n' || c == '\r') {  // Enter
                    String postfix = lnfix2Postfix.convert(exp);
                    double value = Calc.eval(postfix);
                    prevResult = value;
                    textField.setText(String.valueOf(value));
                    isResultShown = true;
                }
            }
 
            // ESC 누를시 초기화
            public void keyPressed(KeyEvent e) {  
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    exp = "";
                    textField.setText("0");
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
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
        panel_2.setLayout(new GridLayout(4, 4, 10, 5));

        btnNewButton = new JButton("7");
        btnNewButton.setFont(new Font("Serif", Font.BOLD, 18));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!textField.getText().equals("0")) {
                    exp = exp + "7";
                    textField.setText(exp);
                } else {
                    exp = "7";
                    textField.setText(exp);
                }
            }
        });
        panel_2.add(btnNewButton);

        btnNewButton_1 = new JButton("8");
        btnNewButton_1.setFont(new Font("Serif", Font.BOLD, 18));
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!textField.getText().equals("0")) {
                    exp = exp + "8";
                    textField.setText(exp);
                } else {
                    exp = "8";
                    textField.setText(exp);
                }
            }
        });
        panel_2.add(btnNewButton_1);

        btnNewButton_2 = new JButton("9");
        btnNewButton_2.setFont(new Font("Serif", Font.BOLD, 18));
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!textField.getText().equals("0")) {
                    exp = exp + "9";
                    textField.setText(exp);
                } else {
                    exp = "9";
                    textField.setText(exp);
                }
            }
        });
        panel_2.add(btnNewButton_2);

        btnNewButton_3 = new JButton("/");
        btnNewButton_3.setForeground(new Color(0, 0, 255));
        btnNewButton_3.setFont(new Font("Serif", Font.BOLD, 18));
        btnNewButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!textField.getText().equals("0")) {
                    exp = exp + "/";
                    textField.setText(exp);
                } else {
                    exp = "/";
                    textField.setText(exp);
                }
            }
        });
        panel_2.add(btnNewButton_3);

        btnNewButton_4 = new JButton("4");
        btnNewButton_4.setFont(new Font("Serif", Font.BOLD, 18));
        btnNewButton_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!textField.getText().equals("0")) {
                    exp = exp + "4";
                    textField.setText(exp);
                } else {
                    exp = "4";
                    textField.setText(exp);
                }
            }
        });
        panel_2.add(btnNewButton_4);

        btnNewButton_5 = new JButton("5");
        btnNewButton_5.setFont(new Font("Serif", Font.BOLD, 18));
        btnNewButton_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!textField.getText().equals("0")) {
                    exp = exp + "5";
                    textField.setText(exp);
                } else {
                    exp = "5";
                    textField.setText(exp);
                }
            }
        });
        panel_2.add(btnNewButton_5);

        btnNewButton_6 = new JButton("6");
        btnNewButton_6.setFont(new Font("Serif", Font.BOLD, 18));
        btnNewButton_6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!textField.getText().equals("0")) {
                    exp = exp + "6";
                    textField.setText(exp);
                } else {
                    exp = "6";
                    textField.setText(exp);
                }
            }
        });
        panel_2.add(btnNewButton_6);

        btnNewButton_7 = new JButton("x");
        btnNewButton_7.setForeground(new Color(0, 0, 255));
        btnNewButton_7.setFont(new Font("Serif", Font.BOLD, 18));
        btnNewButton_7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!textField.getText().equals("0")) {
                    exp = exp + "*";
                    textField.setText(exp);
                } else {
                    exp = "*";
                    textField.setText(exp);
                }
            }
        });
        panel_2.add(btnNewButton_7);

        btnNewButton_8 = new JButton("1");
        btnNewButton_8.setFont(new Font("Serif", Font.BOLD, 18));
        btnNewButton_8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!textField.getText().equals("0")) {
                    exp = exp + "1";
                    textField.setText(exp);
                } else {
                    exp = "1";
                    textField.setText(exp);
                }
            }
        });
        panel_2.add(btnNewButton_8);

        btnNewButton_9 = new JButton("2");
        btnNewButton_9.setFont(new Font("Serif", Font.BOLD, 18));
        btnNewButton_9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!textField.getText().equals("0")) {
                    exp = exp + "2";
                    textField.setText(exp);
                } else {
                    exp = "2";
                    textField.setText(exp);
                }
            }
        });
        panel_2.add(btnNewButton_9);

        btnNewButton_10 = new JButton("3");
        btnNewButton_10.setFont(new Font("Serif", Font.BOLD, 18));
        btnNewButton_10.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!textField.getText().equals("0")) {
                    exp = exp + "3";
                    textField.setText(exp);
                } else {
                    exp = "3";
                    textField.setText(exp);
                }
            }
        });
        panel_2.add(btnNewButton_10);

        btnNewButton_11 = new JButton("-");
        btnNewButton_11.setForeground(new Color(0, 0, 255));
        btnNewButton_11.setFont(new Font("Serif", Font.BOLD, 18));
        btnNewButton_11.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!textField.getText().equals("0")) {
                    exp = exp + "-";
                    textField.setText(exp);
                } else {
                    exp = "-";
                    textField.setText(exp);
                }
            }
        });
        panel_2.add(btnNewButton_11);

        btnNewButton_12 = new JButton("0");
        btnNewButton_12.setFont(new Font("Serif", Font.BOLD, 18));
        btnNewButton_12.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!textField.getText().equals("0")) {
                    exp = exp + "0";
                    textField.setText(exp);
                } else {
                    exp = "0";
                    textField.setText(exp);
                }
            }
        });
        
                btnNewButton_13 = new JButton("C");
                btnNewButton_13.setForeground(new Color(255, 0, 0));
                btnNewButton_13.setFont(new Font("Serif", Font.BOLD, 18));
                btnNewButton_13.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (!textField.getText().equals("0")) {
                            exp = "";
                            textField.setText(exp);
                        } else {
                            exp = "";
                            textField.setText(exp);
                        }
                    }
                });
                panel_2.add(btnNewButton_13);
        panel_2.add(btnNewButton_12);

        btnNewButton_14 = new JButton("=");
        btnNewButton_14.setForeground(new Color(255, 0, 0));
        btnNewButton_14.setFont(new Font("Serif", Font.BOLD, 18));
        btnNewButton_14.addActionListener(new ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
            	String postfix = lnfix2Postfix.convert(exp);
	            double value = Calc.eval(postfix);
	            textField.setText(String.valueOf(value));
	            exp = String.valueOf(value);
                if (!textField.getText().equals("0")) {
	                exp = "";
	                textField.setText("0");
                }}}
            );
        panel_2.add(btnNewButton_14);

        btnNewButton_15 = new JButton("+");
        btnNewButton_15.setForeground(new Color(0, 0, 255));
        btnNewButton_15.setFont(new Font("Serif", Font.BOLD, 18));
        btnNewButton_15.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!textField.getText().equals("0")) {
                    exp = exp + "+";
                    textField.setText(exp);
                } else {
                    exp = "+";
                    textField.setText(exp);
                }
            }
        });
        panel_2.add(btnNewButton_15);
    }

    public JTextField textField() {
        return textField;
    }

    public AboutDialog getAboutDialog() {
        return aboutDialog;
    }
}