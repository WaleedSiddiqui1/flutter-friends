/**
 * Testing class used to test out gui aspects and the program running. Running will lead to unpredictable results.
 */

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        Random rand = new Random();
        int randomNumber = rand.nextInt(3);
        for (int i = 0; i < 300; i++) {
            randomNumber = rand.nextInt(3);
            System.out.println(randomNumber);
        }

        int test = 100;
        String spriteLocation = "Penguin1";

        JFrame frame = new JFrame();
        frame.setTitle("Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setVisible(true);


        JProgressBar bar = new JProgressBar();
        bar.setValue(test);
        bar.setBounds(10, 10, 100, 10);
        bar.setStringPainted(true);
        bar.setForeground(Color.green);

        JButton newGame = new JButton();
        newGame.setBounds(100, 100, 200, 50);
        newGame.addActionListener(e -> System.out.println("*New Game*"));
        newGame.setText("New Game");
        frame.add(newGame);
        frame.add(bar);

        ImageIcon icon = new ImageIcon(Test.class.getResource("/Sprites/"+spriteLocation+"/Normal.png"));
        JLabel imageLabel = new JLabel(icon);
        imageLabel.setBounds(300, 100, icon.getIconWidth(), icon.getIconHeight());
        frame.add(imageLabel);


        while (test > 0) {
            Thread.sleep(100);
            test = test - 1;
            if (test < 50) {
                bar.setForeground(Color.red);
            }
            bar.setValue(test);
        }

        frame = new JFrame();
        frame.setTitle("Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setVisible(true);


    }
}