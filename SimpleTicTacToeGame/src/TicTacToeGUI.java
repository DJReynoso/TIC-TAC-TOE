import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUI extends JFrame {
    private int moveCounter = 1;
    private JLabel titleName;
    private JPanel titlePanel;
    private int returnValue;
    public TicTacToeGUI() {
        guiContent();
    }
    public void guiContent() {
        int maxCount = 9;
        this.setTitle("Tic-Tac-Toe");
        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout());

        titleName = new JLabel("X Turn");
        titleName.setFont(new Font("Times New Roman", Font.BOLD, 55));

        titlePanel.add(titleName);

        titlePanel.setBackground(Color.CYAN);

        container.add(titlePanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3,3));
        JButton[] buttons = new JButton[9];
        for(int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton();
            buttons[i].setFont(new Font("Times New Roman", Font.BOLD, 45));
            buttons[i].setPreferredSize(new Dimension(200,200));
            buttonPanel.add(buttons[i]);
        }
        container.add(buttonPanel, BorderLayout.CENTER);

        this.setVisible(true);
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        buttonSelect(maxCount, buttons);
    }
    public void buttonSelect (int maxCount, JButton[] buttons) {
        for(int i = 0; i < buttons.length; i++) {
            int buttonIndex = i;

            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(buttons[buttonIndex].getText().isEmpty()) {
                        if (moveCounter % 2 == 0) {
                            titlePanel.setBackground(Color.CYAN);
                            titleName.setForeground(Color.BLACK);
                            titleName.setText("X turn");

                            buttons[buttonIndex].setText("O");
                        }
                        else {
                            titlePanel.setBackground(Color.RED);
                            titleName.setText("O turn");
                            titleName.setForeground(Color.WHITE);

                            buttons[buttonIndex].setText("X");
                        }
                        moveCounter++;
                        if (Xwin(buttons)) {
                            titleName.setText("X WIN!");
                            JOptionPane.showMessageDialog(null, "X WIN!");
                            playAgain();
                        }
                        else if (Owin(buttons)) {
                            titleName.setText("O WIN!");
                            JOptionPane.showMessageDialog(null, "O WIN!");
                            playAgain();
                        }
                        else if (checkGameOver(buttons)) {
                            titleName.setText("Game Over! It's a Draw!");
                            titlePanel.setBackground(Color.WHITE);
                            titleName.setForeground(Color.RED);
                            terminate();
                            playAgain();
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Invalid move!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
        }
    }
    public void terminate() {
        JOptionPane.showMessageDialog(null, "Game Over! It's a Draw!");
    }
    public boolean checkGameOver(JButton[] buttons) {
        for (int i = 0; i < buttons.length; i++) {
            if(buttons[i].getText().isEmpty()) {
                return false;
            }
        }
        return true;
    }
    public boolean Xwin(JButton[] buttons) {
        if((buttons[0].getText().equals("X") && buttons[1].getText().equals("X") && buttons[2].getText().equals("X")) ||
                (buttons[3].getText().equals("X") && buttons[4].getText().equals("X") && buttons[5].getText().equals("X")) ||
                (buttons[6].getText().equals("X") && buttons[7].getText().equals("X") && buttons[8].getText().equals("X")) ||
                (buttons[0].getText().equals("X") && buttons[3].getText().equals("X") && buttons[6].getText().equals("X")) ||
                (buttons[1].getText().equals("X") && buttons[4].getText().equals("X") && buttons[7].getText().equals("X")) ||
                (buttons[6].getText().equals("X") && buttons[7].getText().equals("X") && buttons[8].getText().equals("X")) ||
                (buttons[0].getText().equals("X") && buttons[4].getText().equals("X") && buttons[8].getText().equals("X")) ||
                (buttons[2].getText().equals("X") && buttons[4].getText().equals("X") && buttons[6].getText().equals("X")) ){
            return true;
        }
        return false;
    }
    public boolean Owin(JButton[] buttons) {
        if((buttons[0].getText().equals("O") && buttons[1].getText().equals("O") && buttons[2].getText().equals("O")) ||
                (buttons[3].getText().equals("O") && buttons[4].getText().equals("O") && buttons[5].getText().equals("O")) ||
                (buttons[6].getText().equals("O") && buttons[7].getText().equals("O") && buttons[8].getText().equals("O")) ||
                (buttons[0].getText().equals("O") && buttons[3].getText().equals("O") && buttons[6].getText().equals("O")) ||
                (buttons[1].getText().equals("O") && buttons[4].getText().equals("O") && buttons[7].getText().equals("O")) ||
                (buttons[6].getText().equals("O") && buttons[7].getText().equals("O") && buttons[8].getText().equals("O")) ||
                (buttons[0].getText().equals("O") && buttons[4].getText().equals("O") && buttons[8].getText().equals("O")) ||
                (buttons[2].getText().equals("O") && buttons[4].getText().equals("O") && buttons[6].getText().equals("O")) ){
            return true;
        }
        return false;
    }
    public void play() {
        int confirm = JOptionPane.showConfirmDialog(null, "Do you want to play TIC-TAC-TOE?", "", JOptionPane.YES_OPTION);
        if(confirm == JOptionPane.YES_OPTION) {
            titlePanel.setBackground(Color.CYAN);
            titleName.setForeground(Color.BLACK);
            titleName.setText("X Turn");
        }
        else {
            super.dispose();
        }
    }
    public void playAgain() {
        returnValue = JOptionPane.showConfirmDialog(null, "Do you want to play again?", "", JOptionPane.YES_OPTION);
        if(returnValue == JOptionPane.YES_OPTION) {
            super.dispose();
            new TicTacToeGUI();
        }
        else {
            super.dispose();
        }
    }

    public static void main(String[] args) {
        TicTacToeGUI newGame = new TicTacToeGUI();
        newGame.play();
    }
}
