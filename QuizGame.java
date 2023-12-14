import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

class Quiz extends JFrame implements ActionListener {
    JPanel panel;
    JRadioButton choice1;
    JRadioButton choice2;
    JRadioButton choice3;
    JRadioButton choice4;
    ButtonGroup bg;
    JLabel lblmess;
    JButton btnext;
    String[][] qpa;
    String[][] qca;
    int qaid;
    HashMap<Integer, String> map;

    Quiz() {
        initializedata();
        setTitle("Quiz Program");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(430, 350);
        setLocation(300, 100);
        setResizable(false);
        Container cont = getContentPane();
        cont.setLayout(null);
        cont.setBackground(Color.GRAY);
        bg = new ButtonGroup();
        choice1 = new JRadioButton("Choice1", true);
        choice2 = new JRadioButton("Choice2", false);
        choice3 = new JRadioButton("Choice3", false);
        choice4 = new JRadioButton("Choice4", false);
        bg.add(choice1);
        bg.add(choice2);
        bg.add(choice3);
        bg.add(choice4);
        lblmess = new JLabel("Choose a correct answer");
        lblmess.setForeground(Color.BLUE);
        lblmess.setFont(new Font("Arial", Font.BOLD, 11));
        btnext = new JButton("Next");
        btnext.setForeground(Color.BLUE);
        btnext.addActionListener(this);
        panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setLocation(10, 10);
        panel.setSize(400, 300);
        panel.setLayout(new GridLayout(6, 2));
        panel.add(lblmess);
        panel.add(choice1);
        panel.add(choice2);
        panel.add(choice3);
        panel.add(choice4);
        panel.add(btnext);
        cont.add(panel);
        setVisible(true);
        qaid = 0;
        readqa(qaid);
    }

    public void actionPerformed(ActionEvent e) {
        if (btnext.getText().equals("Next")) {
            if (qaid < 5) {
                map.put(qaid, getSelection());
                qaid++;
                readqa(qaid);
            } else {
                map.put(qaid, getSelection());
                btnext.setText("Show answers");
            }
        } else if (btnext.getText().equals("Show answers")) {
            new Report();
        }
    }

    public void initializedata() {

        qpa = new String[6][5];

        qpa[0][0] = "What does HTML stand for?";
        qpa[0][1] = "Hyperlink and Text Markup Language";
        qpa[0][2] = "HyperText Markup Language";
        qpa[0][3] = "High-Level Text Markup Language";
        qpa[0][4] = "Hyperlinking Text and Multimedia Language";

        qpa[1][0] = "Which data type is used to store decimal numbers in Java?";
        qpa[1][1] = "int";
        qpa[1][2] = "float";
        qpa[1][3] = "char";
        qpa[1][4] = "double";

        qpa[2][0] = "What is the use of the println method?";
        qpa[2][1] = "It is used to print text on the screen.";
        qpa[2][2] = "It is used to print text on the screen with the line break.";
        qpa[2][3] = "It is used to read text from keyboard.";
        qpa[2][4] = "It is used to read text from a file.";

        qpa[3][0] = "What is the result of the expression 3 + 7 * 2?";
        qpa[3][1] = "20";
        qpa[3][2] = "17";
        qpa[3][3] = "13";
        qpa[3][4] = "27";

        qpa[4][0] = "What is the purpose of the break statement in a loop?";
        qpa[4][1] = "Skip the next iteration";
        qpa[4][2] = "Exit the loop";
        qpa[4][3] = "Start the loop again";
        qpa[4][4] = "Continue to the next iteration";

        qpa[5][0] = "What is the value of x? int x=3>>2";
        qpa[5][1] = "1";
        qpa[5][2] = "0";
        qpa[5][3] = "3";
        qpa[5][4] = "-3";


        qca = new String[6][2];

        qca[0][0] = "What does HTML stand for?";
        qca[0][1] = "HyperText Markup Language";

        qca[1][0] = "Which data type is used to store decimal numbers in Java?";
        qca[1][1] = "double";

        qca[2][0] = "What is the use of the println method?";
        qca[2][1] = "It is used to print text on the screen with the line break";

        qca[3][0] = "What is the result of the expression 3 + 7 * 2?";
        qca[3][1] = "17";

        qca[4][0] = "What is the purpose of the break statement in a loop?";
        qca[4][1] = "Exit the loop";

        qca[5][0] = "What is the value of x? int x=3>>2?";
        qca[5][1] = "0";

        map = new HashMap<>();
    }

    public String getSelection() {
        String selectedChoice = null;
        Enumeration<AbstractButton> buttons = bg.getElements();
        while (buttons.hasMoreElements()) {
            JRadioButton temp = (JRadioButton) buttons.nextElement();
            if (temp.isSelected()) {
                selectedChoice = temp.getText();
            }
        }
        return (selectedChoice);
    }

    public void readqa(int qid) {
        lblmess.setText("  " + qpa[qid][0]);
        choice1.setText(qpa[qid][1]);
        choice2.setText(qpa[qid][2]);
        choice3.setText(qpa[qid][3]);
        choice4.setText(qpa[qid][4]);
        choice1.setSelected(true);
    }

    public void reset() {
        qaid = 0;
        map.clear();
        readqa(qaid);
        btnext.setText("Next");
    }

    public int calCorrectAnswer() {
        int qnum = 6;
        int count = 1;
        for (int qid = 0; qid < qnum; qid++) {
            if (qca[qid][1].equals(map.get(qid))) {
                count++;
            }
        }
        return count;
    }

    public class Report extends JFrame {
        Report() {
            setTitle("Answers");
            setSize(850, 550);
            setBackground(Color.WHITE);
            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    dispose();
                    reset();
                }
            });
            Draw d = new Draw();
            add(d);
            setVisible(true);
        }

        class Draw extends Canvas {
            public void paint(Graphics g) {
                int qnum = 6;
                int x = 10;
                int y = 20;
                for (int i = 0; i < qnum; i++) {
                    g.setColor(Color.BLUE);
                    g.setFont(new Font("Arial", Font.BOLD, 12));
                    g.drawString(i + 1 + ". " + qca[i][0], x, y);
                    y += 30;
                    g.setFont(new Font("Arial", Font.PLAIN, 12));
                    g.drawString("      Correct answer:" + qca[i][1], x, y);
                    y += 30;
                    g.drawString("      Your answer:" + map.get(i), x, y);
                    y += 30;
                    if (y > 400) {
                        y = 20;
                        x = 450;
                    }
                }

                int numc = calCorrectAnswer();
                g.setColor(Color.MAGENTA);
                g.setFont(new Font("Arial", Font.BOLD, 14));
                g.drawString("Number of correct answers:" + numc, 300, 500);
            }
        }
    }
}

public class QuizGame {
    public static void main(String args[]) {
        new Quiz();
    }
}
