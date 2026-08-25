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

public class Login implements ActionListener {
    private JFrame frame;
    private JLabel username;
    private JLabel password;
    private UIStyle.ModernTextField userField;
    private UIStyle.ModernPasswordField passField;
    private UIStyle.ModernButton loginButton;
    private JButton forgot;
    private UIStyle.ModernButton signup;
    private JButton exitButton;
    private JButton skipButton;
    private ImageIcon on;
    private ImageIcon off;
    private JToggleButton toggleButton;
    private Container c;
    private Cursor cursor;
    protected static boolean loginFlag;
    public static String USERNAME;
    protected static String fullName;
    protected static String oldPassword;
    protected static String phoneNumber;
    protected static String fullUsername;

    public Login() {
        loginFlag = false;
        frame = new JFrame();
        frame.setBounds(50, 50, 850, 550);
        frame.setTitle("Grocery Shop Management - Login");
        frame.setLayout(null);
        frame.setVisible(true);
        c = frame.getContentPane();
        c.setBackground(UIStyle.BG_DARK);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon icon = new ImageIcon("images/market.jpg");
        frame.setIconImage(icon.getImage());

        cursor = new Cursor(Cursor.HAND_CURSOR);

        // --- LEFT BRANDING CARD ---
        UIStyle.RoundedPanel leftCard = new UIStyle.RoundedPanel(16, UIStyle.PANEL_BG, UIStyle.PANEL_BORDER);
        leftCard.setBounds(40, 40, 360, 430);
        leftCard.setLayout(null);
        frame.add(leftCard);

        ImageIcon welcome = new ImageIcon("images/login logo2.png");
        Image scaledImage = welcome.getImage().getScaledInstance(180, 160, Image.SCALE_SMOOTH);
        JLabel imgLabel = new JLabel(new ImageIcon(scaledImage));
        imgLabel.setBounds(90, 30, 180, 160);
        leftCard.add(imgLabel);

        JLabel brandTitle = new JLabel("GROCERY SHOP", SwingConstants.CENTER);
        brandTitle.setBounds(20, 210, 320, 32);
        brandTitle.setFont(UIStyle.FONT_TITLE);
        brandTitle.setForeground(UIStyle.COLOR_PRIMARY);
        leftCard.add(brandTitle);

        JLabel brandSub = new JLabel("Management System", SwingConstants.CENTER);
        brandSub.setBounds(20, 245, 320, 24);
        brandSub.setFont(UIStyle.FONT_SUBHEADER);
        brandSub.setForeground(UIStyle.TEXT_SECONDARY);
        leftCard.add(brandSub);

        JLabel descLabel = new JLabel("<html><center>Manage inventory, users, shops and sales effortlessly with modern tools.</center></html>", SwingConstants.CENTER);
        descLabel.setBounds(30, 280, 300, 50);
        descLabel.setFont(UIStyle.FONT_BODY);
        descLabel.setForeground(UIStyle.TEXT_MUTED);
        leftCard.add(descLabel);

        skipButton = new JButton("Guest Mode (Skip) >>");
        skipButton.setBounds(80, 360, 200, 30);
        skipButton.setFont(UIStyle.FONT_BODY_BOLD);
        skipButton.setBorder(BorderFactory.createEmptyBorder());
        skipButton.setOpaque(false);
        skipButton.setForeground(UIStyle.TEXT_SECONDARY);
        skipButton.setBackground(new Color(0, 0, 0, 0));
        skipButton.setCursor(cursor);
        leftCard.add(skipButton);

        // --- RIGHT LOGIN CARD ---
        UIStyle.RoundedPanel rightCard = new UIStyle.RoundedPanel(16, UIStyle.PANEL_BG, UIStyle.PANEL_BORDER);
        rightCard.setBounds(430, 40, 370, 430);
        rightCard.setLayout(null);
        frame.add(rightCard);

        JLabel loginHeader = new JLabel("Sign In");
        loginHeader.setBounds(35, 30, 200, 32);
        loginHeader.setFont(UIStyle.FONT_TITLE);
        loginHeader.setForeground(UIStyle.TEXT_PRIMARY);
        rightCard.add(loginHeader);

        JLabel loginSub = new JLabel("Enter your credentials to access your account");
        loginSub.setBounds(35, 62, 300, 20);
        loginSub.setFont(UIStyle.FONT_SMALL);
        loginSub.setForeground(UIStyle.TEXT_MUTED);
        rightCard.add(loginSub);

        // Username Field
        username = new JLabel("Username");
        username.setBounds(35, 100, 200, 20);
        username.setFont(UIStyle.FONT_BODY_BOLD);
        username.setForeground(UIStyle.TEXT_PRIMARY);
        rightCard.add(username);

        userField = new UIStyle.ModernTextField("Enter username");
        userField.setBounds(35, 125, 300, 36);
        rightCard.add(userField);

        // Password Field
        password = new JLabel("Password");
        password.setBounds(35, 175, 200, 20);
        password.setFont(UIStyle.FONT_BODY_BOLD);
        password.setForeground(UIStyle.TEXT_PRIMARY);
        rightCard.add(password);

        passField = new UIStyle.ModernPasswordField("Enter password");
        passField.setBounds(35, 200, 255, 36);
        rightCard.add(passField);

        on = new ImageIcon("images/tg1.png");
        off = new ImageIcon("images/tg2.png");
        toggleButton = new JToggleButton(off);
        toggleButton.setBounds(298, 200, 36, 36);
        toggleButton.setBackground(UIStyle.INPUT_BG);
        toggleButton.setOpaque(false);
        toggleButton.setFocusPainted(false);
        toggleButton.setBorder(BorderFactory.createLineBorder(UIStyle.INPUT_BORDER, 1));
        toggleButton.setCursor(cursor);
        rightCard.add(toggleButton);

        // Forgot password
        forgot = new JButton("Forgot Password?");
        forgot.setBounds(205, 240, 130, 22);
        forgot.setFont(UIStyle.FONT_SMALL);
        forgot.setBorder(BorderFactory.createEmptyBorder());
        forgot.setOpaque(false);
        forgot.setForeground(UIStyle.COLOR_PRIMARY);
        forgot.setBackground(new Color(0, 0, 0, 0));
        forgot.setCursor(cursor);
        rightCard.add(forgot);

        // Login Button
        loginButton = new UIStyle.ModernButton("Sign In", UIStyle.COLOR_PRIMARY, UIStyle.COLOR_PRIMARY_HOVER);
        loginButton.setBounds(35, 275, 300, 42);
        rightCard.add(loginButton);

        // Divider
        JLabel divider = new JLabel("------------------ OR ------------------", SwingConstants.CENTER);
        divider.setBounds(35, 325, 300, 20);
        divider.setFont(UIStyle.FONT_SMALL);
        divider.setForeground(UIStyle.TEXT_MUTED);
        rightCard.add(divider);

        // Signup Button
        signup = new UIStyle.ModernButton("Create New Account", UIStyle.COLOR_SUCCESS, UIStyle.COLOR_SUCCESS_HOVER);
        signup.setBounds(35, 355, 300, 38);
        rightCard.add(signup);

        // Exit Button (Top Right corner of frame)
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
        signup.addActionListener(this);
        exitButton.addActionListener(this);
        loginButton.addActionListener(this);
        skipButton.addActionListener(this);
        forgot.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        String user = userField.getText();
        String pass = new String(passField.getPassword());
        boolean userEmpty = user.isEmpty();
        boolean passEmpty = pass.isEmpty();

        if (e.getSource() == toggleButton) {
            if (toggleButton.isSelected()) {
                toggleButton.setIcon(on);
                passField.setEchoChar((char) 0);
            } else {
                toggleButton.setIcon(off);
                passField.setEchoChar('•');
            }
        }

        else if (e.getSource() == signup) {
            frame.setVisible(false);
            new Signup();
        }

        else if (e.getSource() == skipButton) {
            frame.setVisible(false);
            new Skip();
        }

        else if (e.getSource() == exitButton) {
            int yesORno = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit Confirmation",
                    JOptionPane.YES_NO_OPTION);

            if (yesORno == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }

        else if (e.getSource() == loginButton) {
            if (userEmpty || passEmpty) {
                showMessageDialog(null, "Please fill in both username and password.", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                boolean userbool = false;
                boolean adminbool = false;
                try {
                    File file = new File(".\\files\\admin_login.txt");
                    if (!file.exists()) {
                        file.createNewFile();
                        FileWriter fileWriter = new FileWriter(file, true);
                        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                        PrintWriter printWriter = new PrintWriter(bufferedWriter);

                        printWriter.println("===============================================");
                        printWriter.println("User Name : admin");
                        printWriter.println("Password : admin");
                        printWriter.println("===============================================");
                        printWriter.close();
                    }

                    String uname = "User Name : " + user;
                    String pin = "Password : " + pass;

                    // for admin
                    BufferedReader readFile1 = new BufferedReader(new FileReader(".\\files\\admin_login.txt"));
                    int totalLines1 = 0;
                    while (readFile1.readLine() != null) {
                        totalLines1++;
                    }
                    readFile1.close();

                    for (int i = 0; i < totalLines1; i++) {
                        String line = Files.readAllLines(Paths.get(".\\files\\admin_login.txt")).get(i);
                        if (line.equals(uname)) {
                            String line2 = Files.readAllLines(Paths.get(".\\files\\admin_login.txt")).get((i + 1));
                            if (line2.equals(pin)) {
                                frame.setVisible(false);
                                new AdminHome();
                                loginFlag = true;
                                adminbool = true;
                                USERNAME = user;
                                break;
                            } else {
                                adminbool = false;
                            }
                        } else {
                            adminbool = false;
                        }
                    }

                    // for user
                    if (!adminbool) {
                        File userfile = new File(".\\files\\user_login.txt");
                        if (userfile.exists()) {
                            BufferedReader readFile = new BufferedReader(new FileReader(".\\files\\user_login.txt"));
                            int totalLines = 0;
                            while (readFile.readLine() != null) {
                                totalLines++;
                            }
                            readFile.close();

                            for (int i = 0; i < totalLines; i++) {
                                String line = Files.readAllLines(Paths.get(".\\files\\user_login.txt")).get(i);
                                if (line.equals(uname)) {
                                    String line2 = Files.readAllLines(Paths.get(".\\files\\user_login.txt")).get((i + 1));
                                    if (line2.equals(pin)) {
                                        loginFlag = true;
                                        userbool = true;
                                        USERNAME = user;

                                        fullName = Files.readAllLines(Paths.get(".\\files\\user_login.txt")).get(i - 1);
                                        phoneNumber = Files.readAllLines(Paths.get(".\\files\\user_login.txt")).get(i + 2);
                                        oldPassword = Files.readAllLines(Paths.get(".\\files\\user_login.txt")).get(i + 1);
                                        fullUsername = uname;

                                        File file3 = new File(".\\files\\" + USERNAME + "_shops.txt");
                                        if (!file3.exists()) {
                                            file3.createNewFile();
                                        }

                                        frame.setVisible(false);
                                        new CustomerHome();
                                        break;
                                    } else {
                                        userbool = false;
                                    }
                                } else {
                                    userbool = false;
                                }
                            }
                        } else {
                            userbool = false;
                        }
                    }
                    if (!userbool && !adminbool) {
                        showMessageDialog(null, "Invalid Username or Password!", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (Exception ex) {
                    if (!userbool && !adminbool) {
                        showMessageDialog(null, "Invalid Username or Password!", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        } else if (e.getSource() == forgot) {
            frame.setVisible(false);
            new ForgetPass();
        }
    }

    public static boolean getloginFlag() {
        return loginFlag;
    }
}