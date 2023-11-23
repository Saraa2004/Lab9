package it.unibo.mvc;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private final JFrame frame = new JFrame();

    public SimpleGUI(Controller controller) {
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        final JTextField textField = new JTextField();
        panel.add(textField, BorderLayout.NORTH);
        final JTextArea textArea = new JTextArea();
        panel.add(textArea, BorderLayout.CENTER);
        final JPanel southPanel = new JPanel(new FlowLayout());
        panel.add(southPanel, BorderLayout.SOUTH);
        final JButton print = new JButton("Print");
        southPanel.add(print);
        print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.setString(textField.getText());
                controller.printString();
            }
        });
        final JButton history = new JButton("Show history");
        southPanel.add(history);
        history.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final List<String> history = controller.getHistory();
                final StringBuilder text = new StringBuilder();
                for (final String string : history) {
                    text.append(string + "\n");                    
                }
                textArea.setText(text.toString());
            }
        });
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int)screen.getWidth();
        final int sh = (int)screen.getHeight();
        frame.setSize(sw / 2, sh / 2);
    }

    private void display() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        final SimpleGUI gui = new SimpleGUI(new SimpleController());
        gui.display();
    }
}
