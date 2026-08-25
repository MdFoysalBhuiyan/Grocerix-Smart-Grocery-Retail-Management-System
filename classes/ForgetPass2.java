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

public class ForgetPass2 implements ActionListener {
    private JFrame frame;
    private JLabel phone;
    private UIStyle.ModernTextField phoneField;
    private JLabel hintphn;
    private UIStyle.ModernButton next2;
    private JButton exitButton;
    private UIStyle.ModernButton backButton;
    private Container c;
    private Cursor cursor;
    protected static boolean loginFlag;

    public ForgetPass2() {
        frame = new JFrame();
        frame.setBounds(50, 50, 850, 550);
        frame.setTitle("Grocery Shop Management - Password Recovery (Step 2)");
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

        JLabel stepBadge = new JLabel("STEP 2 OF 3", SwingConstants.CENTER);
        stepBadge.setBounds(190, 25, 110, 22);
        stepBadge.setOpaque(true);
        stepBadge.setBackground(UIStyle.COLOR_PRIMARY);
        stepBadge.setForeground(UIStyle.TEXT_PRIMARY);
        stepBadge.setFont(UIStyle.FONT_SMALL);
        card.add(stepBadge);

        JLabel title = new JLabel("Verify Phone Number", SwingConstants.CENTER);
        title.setBounds(30, 55, 430, 30);
        title.setFont(UIStyle.FONT_TITLE);
        title.setForeground(UIStyle.TEXT_PRIMARY);
        card.add(title);

        JLabel sub = new JLabel("Enter your registered phone number for verification", SwingConstants.CENTER);
        sub.setBounds(30, 88, 430, 20);
        sub.setFont(UIStyle.FONT_SMALL);
        sub.setForeground(UIStyle.TEXT_MUTED);
        card.add(sub);

        // Graphic logo
        ImageIcon forgotUser = new ImageIcon("images/forgot_user2.png");
        Image img = forgotUser.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel imgLabel = new JLabel(new ImageIcon(img));
        imgLabel.setBounds(195, 115, 100, 100);
        card.add(imgLabel);

        // Phone Hint
        String line2 = "";
        try {
            int k = ((ForgetPass.deleteLine) + 2);
            String line1 = Files.readAllLines(Paths.get(".\\files\\user_login.txt")).get(k);
            line2 = line2 + line1.charAt(16) + line1.charAt(17) + line1.charAt(18);
        } catch (Exception e) {
            line2 = "***";
        }

        hintphn = new JLabel("Phone Hint: xxxxxxxx" + line2, SwingConstants.CENTER);
        hintphn.setBounds(30, 222, 430, 20);
        hintphn.setForeground(UIStyle.COLOR_WARNING);
        hintphn.setFont(UIStyle.FONT_BODY_BOLD);
        card.add(hintphn);

        phone = new JLabel("Phone Number");
        phone.setBounds(70, 245, 200, 20);
        phone.setFont(UIStyle.FONT_BODY_BOLD);
        phone.setForeground(UIStyle.TEXT_PRIMARY);
        card.add(phone);

        phoneField = new UIStyle.ModernTextField("Enter full phone number");
        phoneField.setBounds(70, 268, 350, 38);
        card.add(phoneField);

        next2 = new UIStyle.ModernButton("Verify & Continue →", UIStyle.COLOR_PRIMARY, UIStyle.COLOR_PRIMARY_HOVER);
        next2.setBounds(70, 320, 350, 42);
        card.add(next2);

        backButton = new UIStyle.ModernButton("← Back to Step 1", UIStyle.COLOR_SECONDARY, UIStyle.COLOR_SECONDARY_HOVER);
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

        next2.addActionListener(this);
        backButton.addActionListener(this);
        exitButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        String user = "Phone : " + phoneField.getText();
        String user1 = phoneField.getText();
        boolean userEmpty = user1.isEmpty();
        boolean yes = false;

        if (e.getSource() == next2) {
            try {
                if (userEmpty) {
                    showMessageDialog(null, "Please enter your phone number.", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    int n = ((ForgetPass.deleteLine) + 2);
                    BufferedReader readFile = new BufferedReader(new FileReader(".\\files\\user_login.txt"));
                    String line = Files.readAllLines(Paths.get(".\\files\\user_login.txt")).get(n);
                    if (line.equals(user)) {
                        yes = true;
                    }
                    readFile.close();

                    if (!yes) {
                        showMessageDialog(null, "Phone number does not match record!", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        frame.setVisible(false);
                        new ForgetPass3();
                    }
                }
            } catch (Exception ex) {
                showMessageDialog(null, "Phone number verification failed!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == exitButton) {
            int yesORno = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit Confirmation",
                    JOptionPane.YES_NO_OPTION);

            if (yesORno == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        } else if (e.getSource() == backButton) {
            frame.setVisible(false);
            new ForgetPass();
        }
    }
}
