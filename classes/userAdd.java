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
import java.time.*;
import java.time.format.*;
import classes.*;

public class userAdd implements ActionListener {
    private Container c;
    private JFrame frame;
    private JLabel username;
    private JLabel password;
    private JLabel confpassword;
    private JLabel email;
    private UIStyle.ModernTextField userField;
    private UIStyle.ModernTextField emailField;
    private UIStyle.ModernPasswordField passField;
    private UIStyle.ModernPasswordField confpassField;
    private JLabel fullName;
    private UIStyle.ModernTextField fullField;

    private ImageIcon on;
    private ImageIcon off;
    private JToggleButton toggleButton;
    private JToggleButton toggleButton2;
    private UIStyle.ModernButton exitButton;
    private UIStyle.ModernButton useradd;
    private UIStyle.ModernButton backButton;

    private Cursor cursor;

    public userAdd() {
        frame = new JFrame();
        frame.setBounds(50, 50, 850, 550);
        frame.setTitle("Grocery Shop Management - Add User");
        frame.setLayout(null);
        frame.setVisible(true);
        c = frame.getContentPane();
        c.setBackground(UIStyle.BG_DARK);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon icon = new ImageIcon("images/market.jpg");
        frame.setIconImage(icon.getImage());

        cursor = new Cursor(Cursor.HAND_CURSOR);

        // --- CENTERED FORM CARD ---
        UIStyle.RoundedPanel card = new UIStyle.RoundedPanel(16, UIStyle.PANEL_BG, UIStyle.PANEL_BORDER);
        card.setBounds(165, 30, 520, 450);
        card.setLayout(null);
        frame.add(card);

        JLabel headerTitle = new JLabel("Add New User", SwingConstants.CENTER);
        headerTitle.setBounds(40, 20, 440, 30);
        headerTitle.setFont(UIStyle.FONT_TITLE);
        headerTitle.setForeground(UIStyle.TEXT_PRIMARY);
        card.add(headerTitle);

        JLabel subTitle = new JLabel("Admin user creation interface", SwingConstants.CENTER);
        subTitle.setBounds(40, 50, 440, 20);
        subTitle.setFont(UIStyle.FONT_SMALL);
        subTitle.setForeground(UIStyle.TEXT_MUTED);
        card.add(subTitle);

        // Full Name
        fullName = new JLabel("Full Name");
        fullName.setBounds(40, 80, 200, 18);
        fullName.setFont(UIStyle.FONT_BODY_BOLD);
        fullName.setForeground(UIStyle.TEXT_PRIMARY);
        card.add(fullName);

        fullField = new UIStyle.ModernTextField("Full Name");
        fullField.setBounds(40, 100, 440, 34);
        card.add(fullField);

        // Username & Phone
        username = new JLabel("Username");
        username.setBounds(40, 140, 200, 18);
        username.setFont(UIStyle.FONT_BODY_BOLD);
        username.setForeground(UIStyle.TEXT_PRIMARY);
        card.add(username);

        userField = new UIStyle.ModernTextField("Username");
        userField.setBounds(40, 160, 210, 34);
        card.add(userField);

        email = new JLabel("Phone Number");
        email.setBounds(270, 140, 210, 18);
        email.setFont(UIStyle.FONT_BODY_BOLD);
        email.setForeground(UIStyle.TEXT_PRIMARY);
        card.add(email);

        emailField = new UIStyle.ModernTextField("01712345678");
        emailField.setBounds(270, 160, 210, 34);
        card.add(emailField);

        // Password & Confirm
        password = new JLabel("Password");
        password.setBounds(40, 200, 200, 18);
        password.setFont(UIStyle.FONT_BODY_BOLD);
        password.setForeground(UIStyle.TEXT_PRIMARY);
        card.add(password);

        passField = new UIStyle.ModernPasswordField("Password");
        passField.setBounds(40, 220, 170, 34);
        card.add(passField);

        on = new ImageIcon("images/tg1.png");
        off = new ImageIcon("images/tg2.png");
        toggleButton = new JToggleButton(off);
        toggleButton.setBounds(215, 220, 34, 34);
        toggleButton.setBackground(UIStyle.INPUT_BG);
        toggleButton.setOpaque(false);
        toggleButton.setFocusPainted(false);
        toggleButton.setBorder(BorderFactory.createLineBorder(UIStyle.INPUT_BORDER, 1));
        toggleButton.setCursor(cursor);
        card.add(toggleButton);

        confpassword = new JLabel("Confirm Password");
        confpassword.setBounds(270, 200, 200, 18);
        confpassword.setFont(UIStyle.FONT_BODY_BOLD);
        confpassword.setForeground(UIStyle.TEXT_PRIMARY);
        card.add(confpassword);

        confpassField = new UIStyle.ModernPasswordField("Confirm Password");
        confpassField.setBounds(270, 220, 170, 34);
        card.add(confpassField);

        toggleButton2 = new JToggleButton(off);
        toggleButton2.setBounds(445, 220, 34, 34);
        toggleButton2.setBackground(UIStyle.INPUT_BG);
        toggleButton2.setOpaque(false);
        toggleButton2.setFocusPainted(false);
        toggleButton2.setBorder(BorderFactory.createLineBorder(UIStyle.INPUT_BORDER, 1));
        toggleButton2.setCursor(cursor);
        card.add(toggleButton2);

        // Action buttons
        useradd = new UIStyle.ModernButton("Create User Account", UIStyle.COLOR_SUCCESS, UIStyle.COLOR_SUCCESS_HOVER);
        useradd.setBounds(40, 280, 440, 42);
        card.add(useradd);
        useradd.addActionListener(this);

        backButton = new UIStyle.ModernButton("← Back to Dashboard", UIStyle.COLOR_SECONDARY, UIStyle.COLOR_SECONDARY_HOVER);
        backButton.setBounds(40, 332, 440, 36);
        card.add(backButton);
        backButton.addActionListener(this);

        // Exit Button (Top Right corner)
        exitButton = new UIStyle.ModernButton("✕", UIStyle.PANEL_BG, UIStyle.COLOR_DANGER);
        exitButton.setBounds(795, 10, 30, 30);
        exitButton.setMargin(new Insets(0,0,0,0));
        frame.add(exitButton);
        exitButton.addActionListener(this);

        toggleButton.addActionListener(this);
        toggleButton2.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        String user = userField.getText();
        String pass = new String(passField.getPassword());
        String confpass = new String(confpassField.getPassword());
        String name = fullField.getText();
        String em = emailField.getText();
        boolean userEmpty = user.isEmpty();
        boolean passEmpty = pass.isEmpty();
        boolean confEmpty = confpass.isEmpty();
        boolean nameEmpty = name.isEmpty();
        boolean emailEmpty = em.isEmpty();
        boolean check = pass.equals(confpass);
        long number = 0;
        int numcount = 0;

        try {
            number = Long.parseLong(em);
            if (em.length() != 11)
                numcount++;
        } catch (Exception ex) {
            numcount = 1;
        }

        if (e.getSource() == useradd) {
            if (userEmpty || passEmpty || confEmpty || nameEmpty || emailEmpty) {
                showMessageDialog(null, "Please fill in all of the fields.", "Error", JOptionPane.WARNING_MESSAGE);
            } else if (numcount > 0) {
                showMessageDialog(null, "Invalid Phone Number (Must be 11 digits)", "Error", JOptionPane.WARNING_MESSAGE);
            } else if (!check) {
                showMessageDialog(null, "Passwords do not match!", "Error", JOptionPane.WARNING_MESSAGE);
            } else {
                try {
                    File file = new File(".\\files\\user_login.txt");
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    FileWriter fileWriter = new FileWriter(file, true);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    PrintWriter printWriter = new PrintWriter(bufferedWriter);

                    LocalDateTime myDateObj = LocalDateTime.now();
                    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm a, dd/MM/yyyy");
                    String timeAndDate = myDateObj.format(myFormatObj);

                    int totalLines = 0;
                    BufferedReader readFile = new BufferedReader(new FileReader(".\\files\\user_login.txt"));
                    while (readFile.readLine() != null) {
                        totalLines++;
                    }
                    readFile.close();

                    BufferedReader adminFile = new BufferedReader(new FileReader(".\\files\\admin_login.txt"));
                    int totalLines2 = 0;
                    while (adminFile.readLine() != null) {
                        totalLines2++;
                    }
                    adminFile.close();

                    boolean userflag = false;
                    boolean adminflag = false;

                    for (int i = 0; i < totalLines; i++) {
                        String line = Files.readAllLines(Paths.get(".\\files\\user_login.txt")).get(i);
                        if (line.equals("User Name : " + user)) {
                            userflag = true;
                            break;
                        }
                    }

                    for (int i = 0; i < totalLines2; i++) {
                        String line = Files.readAllLines(Paths.get(".\\files\\admin_login.txt")).get(i);
                        if (line.equals("User Name : " + user)) {
                            adminflag = true;
                            break;
                        }
                    }

                    if (!userflag && !adminflag) {
                        printWriter.println("===============================================");
                        printWriter.println("Full Name : " + name);
                        printWriter.println("User Name : " + user);
                        printWriter.println("Password : " + pass);
                        printWriter.println("Phone : " + em);
                        printWriter.println("Time & Date : " + timeAndDate);
                        printWriter.println("===============================================");
                        showMessageDialog(null, "User added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        frame.setVisible(false);
                        new AdminHome();
                    } else {
                        showMessageDialog(null, "Username already taken!", "Warning", JOptionPane.WARNING_MESSAGE);
                    }

                    printWriter.close();
                } catch (Exception ex) {
                    System.out.print(ex);
                }
            }
        } else if (e.getSource() == toggleButton) {
            if (toggleButton.isSelected()) {
                toggleButton.setIcon(on);
                passField.setEchoChar((char) 0);
            } else {
                toggleButton.setIcon(off);
                passField.setEchoChar('•');
            }
        } else if (e.getSource() == toggleButton2) {
            if (toggleButton2.isSelected()) {
                toggleButton2.setIcon(on);
                confpassField.setEchoChar((char) 0);
            } else {
                toggleButton2.setIcon(off);
                confpassField.setEchoChar('•');
            }
        } else if (e.getSource() == backButton) {
            frame.setVisible(false);
            new AdminHome();
        } else if (e.getSource() == exitButton) {
            int yesORno = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit Confirmation",
                    JOptionPane.YES_NO_OPTION);

            if (yesORno == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }
}