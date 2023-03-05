package hw2.window;

import javax.swing.*;

import hw2.filehandler.FileHandler;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * A window application for counting the number of characters in a text file.
 */
public class WindowApp extends JFrame {
    private JButton openFileButton;
    private JButton countCharactersButton;
    private JLabel selectedFileLabel;
    private JLabel doneLabel;

    private final JFileChooser fileChooser = new JFileChooser();
    private final FileHandler fileHandler = new FileHandler();
    private final ImageIcon icon = new ImageIcon("./src/main/java/hw2/icon/icon.png");

    private boolean isFileOpen = false;

    /**
     * Constructor for the WindowApp class. Sets up the GUI and sets event listeners
     * for buttons.
     */
    public WindowApp() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setTitle("Character Counter ver. 0.2");
        setIconImage(icon.getImage());

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        selectedFileLabel = new JLabel("No file selected");
        contentPanel.add(selectedFileLabel);

        openFileButton = new JButton("Open file");
        openFileButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, openFileButton.getPreferredSize().height));
        contentPanel.add(openFileButton);

        countCharactersButton = new JButton("Count characters");
        countCharactersButton
                .setMaximumSize(new Dimension(Integer.MAX_VALUE, openFileButton.getPreferredSize().height));
        countCharactersButton.setEnabled(isFileOpen);
        contentPanel.add(countCharactersButton);

        doneLabel = new JLabel("Done");
        doneLabel.setVisible(false);
        contentPanel.add(doneLabel);

        JPanel mainPanel = new JPanel(new GridLayout(0, 1));
        mainPanel.add(contentPanel);

        setContentPane(mainPanel);

        /**
         * Performs an action when the Open file button is clicked. Opens a JFileChooser
         * dialog, sets the selected
         * file, and enables the Count characters button.
         * 
         * @param e The ActionEvent triggered by the button click.
         */
        openFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                doneLabel.setVisible(false);
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir") + "/src/main/java/hw2/files"));

                int result = fileChooser.showOpenDialog(WindowApp.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();

                    if (!file.exists()) {
                        System.out.println("incorrect path");
                        return;
                    }

                    fileHandler.setFile(file);
                    selectedFileLabel.setText(fileHandler.getAbsolutePath());
                    isFileOpen = true;
                    countCharactersButton.setEnabled(isFileOpen);
                }
            }
        });

        /**
         * Performs an action when the Count characters button is clicked. Counts the
         * characters in the selected file
         * and writes the counts to a new file.
         * 
         * @param e The ActionEvent triggered by the button click.
         */
        countCharactersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fileHandler.countCharacters();

                fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir") + "/src/main/java/hw2/files"));
                int result = fileChooser.showOpenDialog(WindowApp.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();

                    fileHandler.writeCounts(file);

                    doneLabel.setVisible(true);
                }
            }
        });
    }
}
