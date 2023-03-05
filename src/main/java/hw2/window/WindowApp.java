package hw2.window;

import javax.swing.*;

import hw2.filehandler.FileHandler;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowApp extends JFrame {
    private JButton openFileButton;
    private JButton countCharactersButton;
    private JLabel selectedFileLabel;

    private final JFileChooser fileChooser = new JFileChooser();
    private final FileHandler filehandler = new FileHandler();

    private boolean isFileOpen = false;

    public WindowApp() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setTitle("Character Counter ver. 0.1");

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        selectedFileLabel = new JLabel("No file selected");
        contentPanel.add(selectedFileLabel);

        openFileButton = new JButton("Open file");
        openFileButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, openFileButton.getPreferredSize().height));
        contentPanel.add(openFileButton);

        countCharactersButton = new JButton("Count characters");
        countCharactersButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, openFileButton.getPreferredSize().height));
        countCharactersButton.setEnabled(isFileOpen);
        contentPanel.add(countCharactersButton);

        JPanel mainPanel = new JPanel(new GridLayout(0, 1));
        mainPanel.add(contentPanel);
        
        setContentPane(mainPanel);

        openFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int result = fileChooser.showOpenDialog(WindowApp.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    filehandler.setFile(fileChooser.getSelectedFile());
                    selectedFileLabel.setText(filehandler.getAbsolutePath());
                    isFileOpen = true;
                    countCharactersButton.setEnabled(isFileOpen);
                }
            }
        });
    }
}
