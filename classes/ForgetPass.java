package classes;

import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.io.*;
import java.nio.file.*;
import classes.*;

public class ForgetPass implements ActionListener {
    private JFrame frame;
    private JLabel username;
    private UIStyle.ModernTextField userField;
    private UIStyle.ModernButton next;
    private JButton exitButton;
    private UIStyle.ModernButton backButton;
    private Container c;
    private Cursor cursor;
    protected static boolean loginFlag;
    protected static int deleteLine;

    public ForgetPass() {
        frame = new JFrame();
        frame.setBounds(50, 50, 850, 550);
        frame.setTitle("Grocery Shop Management - Password Recovery (Step 1)");
        frame.setLayout(null);
        frame.setVisible(true);
        c = frame.getContentPane();
        c.setBackground(UIStyle.BG_DARK);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon icon = new ImageIcon("images/market.jpg");
        frame.setIconImage(icon.getImage());

        cursor = new Cursor(Cursor.HAND_CURSOR);

        // --- WIZARD CARD ---
        UIStyle.RoundedPanel card = new UIStyle.RoundedPanel(16, UIStyle.PANEL_BG, UIStyle.PANEL_BORDER);
        card.setBounds(180, 40, 490, 430);
        card.setLayout(null);
        frame.add(card);

        JLabel stepBadge = new JLabel("STEP 1 OF 3", SwingConstants.CENTER);
        stepBadge.setBounds(190, 25, 110, 22);
        stepBadge.setOpaque(true);
        stepBadge.setBackground(UIStyle.COLOR_PRIMARY);
        stepBadge.setForeground(UIStyle.TEXT_PRIMARY);
        stepBadge.setFont(UIStyle.FONT_SMALL);
        card.add(stepBadge);

        JLabel title = new JLabel("Reset Your Password", SwingConstants.CENTER);
        title.setBounds(30, 55, 430, 30);
        title.setFont(UIStyle.FONT_TITLE);
        title.setForeground(UIStyle.TEXT_PRIMARY);
        card.add(title);

        JLabel sub = new JLabel("Enter your registered username to verify your account", SwingConstants.CENTER);
        sub.setBounds(30, 88, 430, 20);
        sub.setFont(UIStyle.FONT_SMALL);
        sub.setForeground(UIStyle.TEXT_MUTED);
        card.add(sub);

        // Graphic logo
        ImageIcon forgotUser = new ImageIcon("images/forgot_user.png");
        Image img = forgotUser.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel imgLabel = new JLabel(new ImageIcon(img));
        imgLabel.setBounds(195, 120, 100, 100);
        card.add(imgLabel);

        username = new JLabel("Username");
        username.setBounds(70, 235, 200, 20);
        username.setFont(UIStyle.FONT_BODY_BOLD);
        username.setForeground(UIStyle.TEXT_PRIMARY);
        card.add(username);

        userField = new UIStyle.ModernTextField("Enter your username");
        userField.setBounds(70, 260, 350, 38);
        card.add(userField);

        next = new UIStyle.ModernButton("Next Step →", UIStyle.COLOR_PRIMARY, UIStyle.COLOR_PRIMARY_HOVER);
        next.setBounds(70, 320, 350, 42);
        card.add(next);

        backButton = new UIStyle.ModernButton("← Back to Login", UIStyle.COLOR_SECONDARY, UIStyle.COLOR_SECONDARY_HOVER);
        backButton.setBounds(70, 372, 350, 36);
        card.add(backButton);

        // Exit Button (Top Right corner)
        exitButton = new JButton("✕");
        exitButton.setBounds(795, 10, 30, 30);
        exitButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        exitButton.setForeground(UIStyle.TEXT_MUTED);
        exitButton.setOpaque(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setBorder(BorderFactory.createEmptyBorder());
        exitButton.setCursor(cursor);
        frame.add(exitButton);

        next.addActionListener(this);
        backButton.addActionListener(this);
        exitButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        String user = "User Name : " + userField.getText();
        String user1 = userField.getText();
        boolean userEmpty = user1.isEmpty();
        boolean yes = false;
        int totalLines = 0;

        if (e.getSource() == next) {
            try {
                File userfile = new File(".\\files\\user_login.txt");
                if (userfile.exists()) {
                    BufferedReader readFile = new BufferedReader(new FileReader(".\\files\\user_login.txt"));
                    while (readFile.readLine() != null) {
                        totalLines++;
                    }
                    readFile.close();
                }

                if (userEmpty) {
                    showMessageDialog(null, "Please enter your username.", "Warning", JOptionPane.WARNING_MESSAGE);
                    yes = false;
                } else {
                    for (int i = 0; i < totalLines; i++) {
                        String line = Files.readAllLines(Paths.get(".\\files\\user_login.txt")).get(i);
                        if (line.equals(user)) {
                            deleteLine = i;
                            yes = true;
                            break;
                        }
                    }
                    if (yes) {
                        frame.setVisible(false);
                        new ForgetPass2();
                    } else {
                        showMessageDialog(null, "Username not found!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (Exception ex) {
                showMessageDialog(null, "Username not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == exitButton) {
            int yesORno = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit Confirmation",
                    JOptionPane.YES_NO_OPTION);

            if (yesORno == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        } else if (e.getSource() == backButton) {
            frame.setVisible(false);
            new Login();
        }
    }
}