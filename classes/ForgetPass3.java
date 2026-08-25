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

public class ForgetPass3 implements ActionListener {
    private JFrame frame;
    private JLabel newPasslabel;
    private JLabel confpasslabel;
    private UIStyle.ModernPasswordField newPass;
    private UIStyle.ModernPasswordField confpass;
    private UIStyle.ModernButton next2;
    private JButton exitButton;
    private UIStyle.ModernButton backButton;
    private JToggleButton toggleButton;
    private JToggleButton toggleButton2;
    private ImageIcon on;
    private ImageIcon off;
    private Container c;
    private Cursor cursor;
    protected static boolean loginFlag;
    protected static String USERNAME;
    private int totalLines;

    public ForgetPass3() {
        frame = new JFrame();
        frame.setBounds(50, 50, 850, 550);
        frame.setTitle("Grocery Shop Management - Password Recovery (Step 3)");
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
        card.setBounds(180, 30, 490, 450);
        card.setLayout(null);
        frame.add(card);

        JLabel stepBadge = new JLabel("STEP 3 OF 3", SwingConstants.CENTER);
        stepBadge.setBounds(190, 20, 110, 22);
        stepBadge.setOpaque(true);
        stepBadge.setBackground(UIStyle.COLOR_SUCCESS);
        stepBadge.setForeground(UIStyle.TEXT_PRIMARY);
        stepBadge.setFont(UIStyle.FONT_SMALL);
        card.add(stepBadge);

        JLabel title = new JLabel("Set New Password", SwingConstants.CENTER);
        title.setBounds(30, 48, 430, 30);
        title.setFont(UIStyle.FONT_TITLE);
        title.setForeground(UIStyle.TEXT_PRIMARY);
        card.add(title);

        JLabel sub = new JLabel("Create a new secure password for your account", SwingConstants.CENTER);
        sub.setBounds(30, 78, 430, 20);
        sub.setFont(UIStyle.FONT_SMALL);
        sub.setForeground(UIStyle.TEXT_MUTED);
        card.add(sub);

        // Graphic logo
        ImageIcon forgotUser = new ImageIcon("images/forgot_user3.png");
        Image img = forgotUser.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        JLabel imgLabel = new JLabel(new ImageIcon(img));
        imgLabel.setBounds(200, 105, 90, 90);
        card.add(imgLabel);

        on = new ImageIcon("images/tg1.png");
        off = new ImageIcon("images/tg2.png");

        // New Password
        newPasslabel = new JLabel("New Password");
        newPasslabel.setBounds(60, 202, 200, 18);
        newPasslabel.setFont(UIStyle.FONT_BODY_BOLD);
        newPasslabel.setForeground(UIStyle.TEXT_PRIMARY);
        card.add(newPasslabel);

        newPass = new UIStyle.ModernPasswordField("New Password");
        newPass.setBounds(60, 222, 320, 36);
        card.add(newPass);

        toggleButton2 = new JToggleButton(off);
        toggleButton2.setBounds(385, 222, 36, 36);
        toggleButton2.setBackground(UIStyle.INPUT_BG);
        toggleButton2.setOpaque(false);
        toggleButton2.setFocusPainted(false);
        toggleButton2.setBorder(BorderFactory.createLineBorder(UIStyle.INPUT_BORDER, 1));
        toggleButton2.setCursor(cursor);
        card.add(toggleButton2);

        // Confirm Password
        confpasslabel = new JLabel("Confirm New Password");
        confpasslabel.setBounds(60, 265, 200, 18);
        confpasslabel.setFont(UIStyle.FONT_BODY_BOLD);
        confpasslabel.setForeground(UIStyle.TEXT_PRIMARY);
        card.add(confpasslabel);

        confpass = new UIStyle.ModernPasswordField("Confirm New Password");
        confpass.setBounds(60, 285, 320, 36);
        card.add(confpass);

        toggleButton = new JToggleButton(off);
        toggleButton.setBounds(385, 285, 36, 36);
        toggleButton.setBackground(UIStyle.INPUT_BG);
        toggleButton.setOpaque(false);
        toggleButton.setFocusPainted(false);
        toggleButton.setBorder(BorderFactory.createLineBorder(UIStyle.INPUT_BORDER, 1));
        toggleButton.setCursor(cursor);
        card.add(toggleButton);

        next2 = new UIStyle.ModernButton("Save New Password", UIStyle.COLOR_SUCCESS, UIStyle.COLOR_SUCCESS_HOVER);
        next2.setBounds(60, 335, 360, 42);
        card.add(next2);

        backButton = new UIStyle.ModernButton("← Back to Step 2", UIStyle.COLOR_SECONDARY, UIStyle.COLOR_SECONDARY_HOVER);
        backButton.setBounds(60, 385, 360, 36);
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

        toggleButton.addActionListener(this);
        toggleButton2.addActionListener(this);
        next2.addActionListener(this);
        backButton.addActionListener(this);
        exitButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == next2) {
            try {
                File userfile = new File(".\\files\\user_login.txt");
                if (userfile.exists()) {
                    BufferedReader readFile = new BufferedReader(new FileReader(".\\files\\user_login.txt"));
                    while (readFile.readLine() != null) {
                        totalLines++;
                    }
                    readFile.close();
                }

                String newpass1 = new String(newPass.getPassword());
                boolean newpass3 = newpass1.isEmpty();

                String confpass1 = new String(confpass.getPassword());
                String confpass2 = "Password : " + confpass1;
                boolean confpass3 = confpass1.isEmpty();
                boolean check = newpass1.equals(confpass1);

                if (newpass3 || confpass3) {
                    showMessageDialog(null, "Please enter and confirm your new password.", "Error", JOptionPane.WARNING_MESSAGE);
                } else if (!check) {
                    showMessageDialog(null, "Passwords do not match!", "Error", JOptionPane.WARNING_MESSAGE);
                } else {
                    String tempfile = ".\\files\\temp.txt";
                    File oldFile = new File(".\\files\\user_login.txt");
                    File newFile = new File(".\\files\\temp.txt");
                    int l = 0;
                    String currentline;

                    FileWriter fileWriter = new FileWriter(tempfile, true);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    PrintWriter printWriter = new PrintWriter(bufferedWriter);

                    FileReader fr = new FileReader(".\\files\\user_login.txt");
                    BufferedReader br = new BufferedReader(fr);

                    int n = (ForgetPass.deleteLine) + 2;
                    while ((currentline = br.readLine()) != null) {
                        l++;
                        if (n != l) {
                            printWriter.println(currentline);
                        } else {
                            printWriter.println(confpass2);
                        }
                    }
                    printWriter.flush();
                    printWriter.close();
                    fr.close();
                    br.close();
                    bufferedWriter.close();
                    fileWriter.close();

                    oldFile.delete();
                    File dumb = new File(".\\files\\user_login.txt");
                    newFile.renameTo(dumb);
                    showMessageDialog(null, "Password reset successfully! Please sign in.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    frame.setVisible(false);
                    new Login();
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        } else if (e.getSource() == exitButton) {
            int yesORno = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit Confirmation",
                    JOptionPane.YES_NO_OPTION);

            if (yesORno == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        } else if (e.getSource() == backButton) {
            frame.setVisible(false);
            new ForgetPass2();
        } else if (e.getSource() == toggleButton) {
            if (toggleButton.isSelected()) {
                toggleButton.setIcon(on);
                confpass.setEchoChar((char) 0);
            } else {
                toggleButton.setIcon(off);
                confpass.setEchoChar('•');
            }
        } else if (e.getSource() == toggleButton2) {
            if (toggleButton2.isSelected()) {
                toggleButton2.setIcon(on);
                newPass.setEchoChar((char) 0);
            } else {
                toggleButton2.setIcon(off);
                newPass.setEchoChar('•');
            }
        }
    }
}
