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

public class Profile implements ActionListener {
    private Container c;
    private JFrame frame;
    private Cursor cursor;
    private UIStyle.RoundedPanel card;

    private JLabel fullName1;
    private JLabel displayName1;
    private JLabel phoneNumber1;
    private JLabel userName1;

    private UIStyle.ModernButton editButton;
    private UIStyle.ModernButton logoutButton;
    private UIStyle.ModernButton exitButton;
    private UIStyle.ModernButton backButton;

    public Profile() {
        frame = new JFrame();
        frame.setBounds(50, 50, 850, 550);
        frame.setTitle("Grocery Shop Management - User Profile");
        frame.setLayout(null);
        frame.setVisible(true);
        c = frame.getContentPane();
        c.setBackground(UIStyle.BG_DARK);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon icon = new ImageIcon("images/market.jpg");
        frame.setIconImage(icon.getImage());

        cursor = new Cursor(Cursor.HAND_CURSOR);

        // --- CENTERED PROFILE CARD ---
        card = new UIStyle.RoundedPanel(16, UIStyle.PANEL_BG, UIStyle.PANEL_BORDER);
        card.setBounds(200, 35, 450, 440);
        card.setLayout(null);
        frame.add(card);

        // Avatar Image
        ImageIcon profile = new ImageIcon("images/profile.png");
        Image img = profile.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        JLabel imgLabel = new JLabel(new ImageIcon(img));
        imgLabel.setBounds(180, 25, 90, 90);
        card.add(imgLabel);

        displayName1 = new JLabel("User Profile", SwingConstants.CENTER);
        displayName1.setBounds(25, 125, 400, 28);
        displayName1.setFont(UIStyle.FONT_TITLE);
        displayName1.setForeground(UIStyle.TEXT_PRIMARY);
        card.add(displayName1);

        JSeparator sep = new JSeparator();
        sep.setBounds(40, 160, 370, 2);
        sep.setForeground(UIStyle.PANEL_BORDER);
        card.add(sep);

        // Info Labels
        JLabel nameLabel = new JLabel("Full Name");
        nameLabel.setBounds(40, 175, 370, 16);
        nameLabel.setFont(UIStyle.FONT_SMALL);
        nameLabel.setForeground(UIStyle.TEXT_MUTED);
        card.add(nameLabel);

        fullName1 = new JLabel(Login.fullName != null ? Login.fullName : "Full Name");
        fullName1.setBounds(40, 193, 370, 24);
        fullName1.setFont(UIStyle.FONT_BODY_BOLD);
        fullName1.setForeground(UIStyle.COLOR_PRIMARY);
        card.add(fullName1);

        JLabel userLabel = new JLabel("Username");
        userLabel.setBounds(40, 225, 370, 16);
        userLabel.setFont(UIStyle.FONT_SMALL);
        userLabel.setForeground(UIStyle.TEXT_MUTED);
        card.add(userLabel);

        userName1 = new JLabel(Login.fullUsername != null ? Login.fullUsername : "Username");
        userName1.setBounds(40, 243, 370, 24);
        userName1.setFont(UIStyle.FONT_BODY_BOLD);
        userName1.setForeground(UIStyle.TEXT_PRIMARY);
        card.add(userName1);

        JLabel phoneLabel = new JLabel("Phone Number");
        phoneLabel.setBounds(40, 275, 370, 16);
        phoneLabel.setFont(UIStyle.FONT_SMALL);
        phoneLabel.setForeground(UIStyle.TEXT_MUTED);
        card.add(phoneLabel);

        phoneNumber1 = new JLabel(Login.phoneNumber != null ? Login.phoneNumber : "Phone");
        phoneNumber1.setBounds(40, 293, 370, 24);
        phoneNumber1.setFont(UIStyle.FONT_BODY_BOLD);
        phoneNumber1.setForeground(UIStyle.TEXT_PRIMARY);
        card.add(phoneNumber1);

        // Action Buttons
        editButton = new UIStyle.ModernButton("✏ Edit Profile Info", UIStyle.COLOR_PRIMARY, UIStyle.COLOR_PRIMARY_HOVER);
        editButton.setBounds(40, 335, 370, 38);
        card.add(editButton);
        editButton.addActionListener(this);

        backButton = new UIStyle.ModernButton("← Back to Workspace", UIStyle.COLOR_SECONDARY, UIStyle.COLOR_SECONDARY_HOVER);
        backButton.setBounds(40, 382, 370, 36);
        card.add(backButton);
        backButton.addActionListener(this);

        // Top Right Actions
        logoutButton = new UIStyle.ModernButton("Log Out", UIStyle.COLOR_DANGER, UIStyle.COLOR_DANGER_HOVER);
        logoutButton.setBounds(680, 15, 90, 32);
        frame.add(logoutButton);
        logoutButton.addActionListener(this);

        exitButton = new UIStyle.ModernButton("✕", UIStyle.PANEL_BG, UIStyle.COLOR_DANGER);
        exitButton.setBounds(780, 15, 30, 32);
        exitButton.setMargin(new Insets(0,0,0,0));
        frame.add(exitButton);
        exitButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == logoutButton) {
            frame.setVisible(false);
            new Login();
        } else if (e.getSource() == editButton) {
            frame.setVisible(false);
            new Edit();
        } else if (e.getSource() == backButton) {
            frame.setVisible(false);
            new CustomerHome();
        } else if (e.getSource() == exitButton) {
            int yesORno = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit Confirmation",
                    JOptionPane.YES_NO_OPTION);

            if (yesORno == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }
}
