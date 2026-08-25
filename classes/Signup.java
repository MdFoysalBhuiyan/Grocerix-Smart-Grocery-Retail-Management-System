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

public class Signup implements ActionListener {
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
    private JButton exitButton;
    private UIStyle.ModernButton signup_logo;
    private JButton loginimg;

    private Cursor cursor;

    public Signup() {
        frame = new JFrame();
        frame.setBounds(50, 50, 850, 550);
        frame.setTitle("Grocery Shop Management - Sign Up");
        frame.setLayout(null);
        frame.setVisible(true);
        c = frame.getContentPane();
        c.setBackground(UIStyle.BG_DARK);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon icon = new ImageIcon("images/market.jpg");
        frame.setIconImage(icon.getImage());

        cursor = new Cursor(Cursor.HAND_CURSOR);

        // --- CENTERED SIGNUP CARD ---
        UIStyle.RoundedPanel signupCard = new UIStyle.RoundedPanel(16, UIStyle.PANEL_BG, UIStyle.PANEL_BORDER);
        signupCard.setBounds(165, 20, 520, 470);
        signupCard.setLayout(null);
        frame.add(signupCard);

        JLabel headerTitle = new JLabel("Create Account", SwingConstants.CENTER);
        headerTitle.setBounds(40, 18, 440, 30);
        headerTitle.setFont(UIStyle.FONT_TITLE);
        headerTitle.setForeground(UIStyle.TEXT_PRIMARY);
        signupCard.add(headerTitle);

        JLabel subTitle = new JLabel("Fill in your personal details to get started", SwingConstants.CENTER);
        subTitle.setBounds(40, 48, 440, 20);
        subTitle.setFont(UIStyle.FONT_SMALL);
        subTitle.setForeground(UIStyle.TEXT_MUTED);
        signupCard.add(subTitle);

        // Full Name
        fullName = new JLabel("Full Name");
        fullName.setBounds(40, 75, 200, 18);
        fullName.setFont(UIStyle.FONT_BODY_BOLD);
        fullName.setForeground(UIStyle.TEXT_PRIMARY);
        signupCard.add(fullName);

        fullField = new UIStyle.ModernTextField("John Doe");
        fullField.setBounds(40, 95, 440, 34);
        signupCard.add(fullField);

        // Username & Phone in 2 columns
        username = new JLabel("Username");
        username.setBounds(40, 135, 200, 18);
        username.setFont(UIStyle.FONT_BODY_BOLD);
        username.setForeground(UIStyle.TEXT_PRIMARY);
        signupCard.add(username);

        userField = new UIStyle.ModernTextField("username");
        userField.setBounds(40, 155, 210, 34);
        signupCard.add(userField);

        email = new JLabel("Phone Number (11 digits)");
        email.setBounds(270, 135, 210, 18);
        email.setFont(UIStyle.FONT_BODY_BOLD);
        email.setForeground(UIStyle.TEXT_PRIMARY);
        signupCard.add(email);

        emailField = new UIStyle.ModernTextField("01712345678");
        emailField.setBounds(270, 155, 210, 34);
        signupCard.add(emailField);

        // Password & Confirm Password in 2 columns
        password = new JLabel("Password");
        password.setBounds(40, 195, 200, 18);
        password.setFont(UIStyle.FONT_BODY_BOLD);
        password.setForeground(UIStyle.TEXT_PRIMARY);
        signupCard.add(password);

        passField = new UIStyle.ModernPasswordField("Password");
        passField.setBounds(40, 215, 170, 34);
        signupCard.add(passField);

        on = new ImageIcon("images/tg1.png");
        off = new ImageIcon("images/tg2.png");
        toggleButton = new JToggleButton(off);
        toggleButton.setBounds(215, 215, 34, 34);
        toggleButton.setBackground(UIStyle.INPUT_BG);
        toggleButton.setOpaque(false);
        toggleButton.setFocusPainted(false);
        toggleButton.setBorder(BorderFactory.createLineBorder(UIStyle.INPUT_BORDER, 1));
        toggleButton.setCursor(cursor);
        signupCard.add(toggleButton);

        confpassword = new JLabel("Confirm Password");
        confpassword.setBounds(270, 195, 200, 18);
        confpassword.setFont(UIStyle.FONT_BODY_BOLD);
        confpassword.setForeground(UIStyle.TEXT_PRIMARY);
        signupCard.add(confpassword);

        confpassField = new UIStyle.ModernPasswordField("Confirm Password");
        confpassField.setBounds(270, 215, 170, 34);
        signupCard.add(confpassField);

        toggleButton2 = new JToggleButton(off);
        toggleButton2.setBounds(445, 215, 34, 34);
        toggleButton2.setBackground(UIStyle.INPUT_BG);
        toggleButton2.setOpaque(false);
        toggleButton2.setFocusPainted(false);
        toggleButton2.setBorder(BorderFactory.createLineBorder(UIStyle.INPUT_BORDER, 1));
        toggleButton2.setCursor(cursor);
        signupCard.add(toggleButton2);

        // Sign Up Action Button
        signup_logo = new UIStyle.ModernButton("Register Account", UIStyle.COLOR_PRIMARY, UIStyle.COLOR_PRIMARY_HOVER);
        signup_logo.setBounds(40, 270, 440, 42);
        signupCard.add(signup_logo);

        // Already have an account row
        JLabel loginLabel = new JLabel("Already have an account?", SwingConstants.RIGHT);
        loginLabel.setBounds(100, 330, 180, 25);
        loginLabel.setFont(UIStyle.FONT_BODY);
        loginLabel.setForeground(UIStyle.TEXT_SECONDARY);
        signupCard.add(loginLabel);

        loginimg = new JButton("Sign In Here");
        loginimg.setBounds(290, 330, 120, 25);
        loginimg.setFont(UIStyle.FONT_BODY_BOLD);
        loginimg.setBorder(BorderFactory.createEmptyBorder());
        loginimg.setOpaque(false);
        loginimg.setForeground(UIStyle.COLOR_PRIMARY);
        loginimg.setBackground(new Color(0, 0, 0, 0));
        loginimg.setCursor(cursor);
        signupCard.add(loginimg);

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

        exitButton.addActionListener(this);
        loginimg.addActionListener(this);
        toggleButton.addActionListener(this);
        toggleButton2.addActionListener(this);
        signup_logo.addActionListener(this);
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

        if (e.getSource() == signup_logo) {
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
                        showMessageDialog(null, "Account created successfully! Please sign in.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        frame.setVisible(false);
                        new Login();
                    } else {
                        showMessageDialog(null, "Username already taken!", "Warning", JOptionPane.WARNING_MESSAGE);
                    }

                    printWriter.close();
                } catch (Exception ex) {
                    System.out.print(ex);
                }
            }
        }

        else if (e.getSource() == toggleButton) {
            if (toggleButton.isSelected()) {
                toggleButton.setIcon(on);
                passField.setEchoChar((char) 0);
            } else {
                toggleButton.setIcon(off);
                passField.setEchoChar('•');
            }
        }

        else if (e.getSource() == toggleButton2) {
            if (toggleButton2.isSelected()) {
                toggleButton2.setIcon(on);
                confpassField.setEchoChar((char) 0);
            } else {
                toggleButton2.setIcon(off);
                confpassField.setEchoChar('•');
            }
        }

        else if (e.getSource() == loginimg) {
            frame.setVisible(false);
            new Login();
        }

        else if (e.getSource() == exitButton) {
            int yesORno = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit Confirmation",
                    JOptionPane.YES_NO_OPTION);

            if (yesORno == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }
}