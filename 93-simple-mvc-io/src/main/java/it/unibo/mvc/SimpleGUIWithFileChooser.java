package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {
    private static final int PROPORTION = 2;

    private final JFrame frame = new JFrame();

    public SimpleGUIWithFileChooser(Controller controller) {
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());
        final JTextArea textArea = new JTextArea();
        panel1.add(textArea, BorderLayout.CENTER);
        final JButton save = new JButton("Save");
        panel1.add(save, BorderLayout.SOUTH);
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    controller.write(textArea.getText());
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(frame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }                
            }
        });
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());
        panel1.add(panel2, BorderLayout.NORTH);
        final JTextField textField = new JTextField(controller.getCurrentFilePath());
        textField.setEditable(false);
        panel2.add(textField, BorderLayout.CENTER);
        final JButton browse = new JButton("Browse...");
        panel2.add(browse, BorderLayout.LINE_END);
        browse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFileChooser chooser = new JFileChooser();
                chooser.setSelectedFile(controller.getFile());
                final int result = chooser.showSaveDialog(frame);
                switch (result) {
                    case JFileChooser.APPROVE_OPTION :
                        final File newFile = chooser.getSelectedFile();
                        controller.setFile(newFile);
                        textField.setText(newFile.getPath());
                        break;
                    case JFileChooser.CANCEL_OPTION :
                        break;
                    default :
                        JOptionPane.showMessageDialog(frame, result, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int)screen.getWidth();
        final int sh = (int)screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        frame.setLocationByPlatform(true);
    }

    private void display() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        final SimpleGUIWithFileChooser gui = new SimpleGUIWithFileChooser(new Controller());
        gui.display();
    }

}
