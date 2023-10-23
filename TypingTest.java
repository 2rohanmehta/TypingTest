import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.*;

public class TypingTest extends JFrame {
    public String generateSentence() {
        String[] nouns = { "cat", "dog", "house", "car", "mouse", "school", "bird", "tree" };
        String[] verbs = { "ran", "jumped", "walked", "skipped", "jogged", "sprinted" };
        String[] adjectives = { "big", "small", "fast", "slow", "loud", "huge", "amazing" };
        String[] articles = { "the", "a", "one", "some" };
        String[] prepositions = { "to", "from", "over", "under", "above", "below" };

        // Generate a random sentence
        Random random = new Random();
        String sentence = "";
        sentence += articles[random.nextInt(articles.length)] + " ";
        sentence += adjectives[random.nextInt(adjectives.length)] + " ";
        sentence += nouns[random.nextInt(nouns.length)] + " ";
        sentence += verbs[random.nextInt(verbs.length)] + " ";
        sentence += prepositions[random.nextInt(prepositions.length)] + " ";
        sentence += articles[random.nextInt(articles.length)] + " ";
        sentence += nouns[random.nextInt(nouns.length)] + " and ";
        sentence += articles[random.nextInt(articles.length)] + " ";
        sentence += adjectives[random.nextInt(adjectives.length)] + " ";
        sentence += nouns[random.nextInt(nouns.length)] + " ";
        sentence += verbs[random.nextInt(verbs.length)] + " ";
        sentence += prepositions[random.nextInt(prepositions.length)] + " ";
        sentence += articles[random.nextInt(articles.length)] + " ";
        sentence += nouns[random.nextInt(nouns.length)];

        return sentence;
    }

    // The line of words to type
    private String LINE_OF_WORDS = generateSentence();

    // The text field where the user will type
    private JTextField textField;

    // The label where the timer will be displayed
    private JLabel timerLabel;
    private JPanel panel;
    private JButton resetButton = new JButton("RESET");

    private boolean enterDisplayedResult = true;

    private JLabel wordsLabel = new JLabel("");

    // The start time of the test
    private long startTime;
    private int x = 0;

    public TypingTest() {
        super("WPM Typing Test");

        // Create the text field and set the font

        wordsLabel.setFont(new Font("Times New Roman", Font.BOLD, 17));
        wordsLabel.setHorizontalAlignment(JTextField.CENTER);

        textField = new JTextField();

        textField.setEditable(false);

        textField.setFont(new Font("Monospaced", Font.PLAIN, 19));
        textField.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

                if (x == 0 && e.getKeyCode() == KeyEvent.VK_ENTER) {
                    textField.setEditable(true);
                    timerLabel.setText("Press ENTER to end");
                    wordsLabel.setText(LINE_OF_WORDS);
                    startTime = System.currentTimeMillis();

                    x = 1;
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() != KeyEvent.VK_ENTER && x != 0 && enterDisplayedResult) {
                    String s = textField.getText();
                    String y = LINE_OF_WORDS.substring(0, s.length());
                    if (!s.equals(y)) {
                        timerLabel.setText("You made a mistake!");
                        timerLabel.setForeground(Color.RED);
                    } else {
                        timerLabel.setText("Press ENTER to end");
                        timerLabel.setForeground(Color.BLACK);
                    }
                }
            }

        });
        // Add an action listener to the text field
        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Get the text from the text field
                String text = textField.getText();

                // Check if the user has typed the correct line of words
                if (text.trim().equals(LINE_OF_WORDS) && enterDisplayedResult) {
                    textField.setEditable(false);
                    // Stop the timer and display the elapsed time
                    long elapsedTime = System.currentTimeMillis() - startTime;
                    timerLabel.setText(String.format("Speed: %d WPM",
                            (int) (((double) LINE_OF_WORDS.length() / (double) 5)
                                    / ((double) (elapsedTime / 1000.0) / (double) 60))));

                    enterDisplayedResult = false;
                }

            }
        });

        // Create the timer label and set the font
        timerLabel = new JLabel("Press ENTER to start");
        timerLabel.setHorizontalAlignment(JLabel.CENTER);
        timerLabel.setFont(new Font("SansSerif", Font.BOLD, 20));

        // Create the panel and add the text field and timer label
        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));
        // panel.setLayout(new BorderLayout());
        panel.add(textField);

        panel.add(wordsLabel);
        resetButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                x = 0;
                startTime = 0;
                textField.setText("");
                textField.setEditable(false);
                timerLabel.setText("Press ENTER to start");
                timerLabel.setForeground(Color.BLACK);
                LINE_OF_WORDS = generateSentence();
                wordsLabel.setText("");
                enterDisplayedResult = true;

            }

        });

        // resetButton.setBackground(Color.BLUE);
        resetButton.setForeground(Color.BLUE);
        resetButton.setFont(new Font("Courier New", Font.BOLD, 14));
        resetButton.setFocusable(false);
        panel.add(resetButton);

        panel.add(timerLabel);

        // Add the panel to the frame
        add(panel);
        panel.setBackground(Color.GRAY);

        // Set the size and location of the frame
        setSize(950, 200);
        setLocationRelativeTo(null);
        setResizable(false);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        // Create a new Main object and set it to be visible
        TypingTest main = new TypingTest();
        main.setVisible(true);
    }
}
