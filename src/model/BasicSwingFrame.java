package model;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BasicSwingFrame extends JFrame {

    public BasicSwingFrame() {
        // Set the title of the frame
        setTitle("Basic Swing Frame");

        // Set the default close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a label
        JLabel label = new JLabel("Hello, Swing!");

        // Create a button
        JButton button = new JButton("Click Me!");

        // Add an action listener to the button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Action to perform when the button is clicked
                label.setText("Button Clicked!");
            }
        });

        // Set the layout manager (optional, defaults to BorderLayout)
        setLayout(new java.awt.FlowLayout());

        // Add components to the frame
        add(label);
        add(button);

        // Set the size of the frame
        setSize(300, 200);

        // Center the frame on the screen
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        // Create an instance of the frame
        BasicSwingFrame frame = new BasicSwingFrame();

        // Set the frame to be visible
        frame.setVisible(true);
    }
}
