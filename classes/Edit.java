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

public class Edit implements ActionListener {
    private Container c;
    private JFrame frame;
    private Cursor cursor;

    private JLabel displayName1;

    private UIStyle.ModernTextField fullField;
    private UIStyle.ModernTextField userField;
    private UIStyle.ModernTextField phoneField;

    private JLabel full;
    private JLabel user;
    private JLabel phone;

    private UIStyle.ModernButton fullnameButton;
    private UIStyle.ModernButton usernameButton;
    private UIStyle.ModernButton phoneButton;

    private UIStyle.ModernButton confirmButton1;
    private UIStyle.ModernButton confirmButton2;
    private UIStyle.ModernButton confirmButton3;
    private UIStyle.ModernButton logoutButton;
    private UIStyle.ModernButton exitButton;
    private UIStyle.ModernButton backButton;

    private int check;

    public Edit() {
        frame = new JFrame();
        frame.setBounds(50, 50, 850, 550);
        frame.setTitle("Grocery Shop Management - Edit Profile");
        frame.setLayout(null);
        frame.setVisible(true);
        c = frame.getContentPane();
        c.setBackground(UIStyle.BG_DARK);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon icon = new ImageIcon("images/market.jpg");
        frame.setIconImage(icon.getImage());
        check = 0;

        cursor = new Cursor(Cursor.HAND_CURSOR);

        // --- CENTERED CARD PANEL ---
        UIStyle.RoundedPanel card = new UIStyle.RoundedPanel(16, UIStyle.PANEL_BG, UIStyle.PANEL_BORDER);
        card.setBounds(200, 35, 450, 440);
        card.setLayout(null);
        frame.add(card);

        displayName1 = new JLabel("Edit Account Details", SwingConstants.CENTER);
        displayName1.setBounds(25, 25, 400, 30);
        displayName1.setFont(UIStyle.FONT_TITLE);
        displayName1.setForeground(UIStyle.TEXT_PRIMARY);
        card.add(displayName1);

        JLabel subTitle = new JLabel("Choose an option to modify your account information", SwingConstants.CENTER);
        subTitle.setBounds(25, 58, 400, 20);
        subTitle.setFont(UIStyle.FONT_SMALL);
        subTitle.setForeground(UIStyle.TEXT_MUTED);
        card.add(subTitle);

        JSeparator sep = new JSeparator();
        sep.setBounds(40, 90, 370, 2);
        sep.setForeground(UIStyle.PANEL_BORDER);
        card.add(sep);

        // Main Choice Buttons
        fullnameButton = new UIStyle.ModernButton("Edit Full Name", UIStyle.COLOR_PRIMARY, UIStyle.COLOR_PRIMARY_HOVER);
        fullnameButton.setBounds(60, 120, 330, 42);
        card.add(fullnameButton);

        usernameButton = new UIStyle.ModernButton("Edit Username", UIStyle.COLOR_PRIMARY, UIStyle.COLOR_PRIMARY_HOVER);
        usernameButton.setBounds(60, 180, 330, 42);
        card.add(usernameButton);

        phoneButton = new UIStyle.ModernButton("Edit Phone Number", UIStyle.COLOR_PRIMARY, UIStyle.COLOR_PRIMARY_HOVER);
        phoneButton.setBounds(60, 240, 330, 42);
        card.add(phoneButton);

        // Edit Input Fields (Initially Hidden)
        full = new JLabel("Enter New Full Name");
        full.setBounds(60, 130, 330, 20);
        full.setFont(UIStyle.FONT_BODY_BOLD);
        full.setForeground(UIStyle.TEXT_PRIMARY);
        full.setVisible(false);
        card.add(full);

        fullField = new UIStyle.ModernTextField("Enter full name");
        fullField.setBounds(60, 155, 330, 38);
        fullField.setVisible(false);
        card.add(fullField);

        confirmButton1 = new UIStyle.ModernButton("Save Full Name", UIStyle.COLOR_SUCCESS, UIStyle.COLOR_SUCCESS_HOVER);
        confirmButton1.setBounds(60, 210, 330, 42);
        confirmButton1.setVisible(false);
        card.add(confirmButton1);

        user = new JLabel("Enter New Username");
        user.setBounds(60, 130, 330, 20);
        user.setFont(UIStyle.FONT_BODY_BOLD);
        user.setForeground(UIStyle.TEXT_PRIMARY);
        user.setVisible(false);
        card.add(user);

        userField = new UIStyle.ModernTextField("Enter username");
        userField.setBounds(60, 155, 330, 38);
        userField.setVisible(false);
        card.add(userField);

        confirmButton2 = new UIStyle.ModernButton("Save Username", UIStyle.COLOR_SUCCESS, UIStyle.COLOR_SUCCESS_HOVER);
        confirmButton2.setBounds(60, 210, 330, 42);
        confirmButton2.setVisible(false);
        card.add(confirmButton2);

        phone = new JLabel("Enter New Phone Number (11 digits)");
        phone.setBounds(60, 130, 330, 20);
        phone.setFont(UIStyle.FONT_BODY_BOLD);
        phone.setForeground(UIStyle.TEXT_PRIMARY);
        phone.setVisible(false);
        card.add(phone);

        phoneField = new UIStyle.ModernTextField("01712345678");
        phoneField.setBounds(60, 155, 330, 38);
        phoneField.setVisible(false);
        card.add(phoneField);

        confirmButton3 = new UIStyle.ModernButton("Save Phone Number", UIStyle.COLOR_SUCCESS, UIStyle.COLOR_SUCCESS_HOVER);
        confirmButton3.setBounds(60, 210, 330, 42);
        confirmButton3.setVisible(false);
        card.add(confirmButton3);

        backButton = new UIStyle.ModernButton("← Back to Profile", UIStyle.COLOR_SECONDARY, UIStyle.COLOR_SECONDARY_HOVER);
        backButton.setBounds(60, 370, 330, 38);
        card.add(backButton);

        // Top Right Actions
        logoutButton = new UIStyle.ModernButton("Log Out", UIStyle.COLOR_DANGER, UIStyle.COLOR_DANGER_HOVER);
        logoutButton.setBounds(680, 15, 90, 32);
        frame.add(logoutButton);

        exitButton = new UIStyle.ModernButton("✕", UIStyle.PANEL_BG, UIStyle.COLOR_DANGER);
        exitButton.setBounds(780, 15, 30, 32);
        exitButton.setMargin(new Insets(0,0,0,0));
        frame.add(exitButton);

        usernameButton.addActionListener(this);
        phoneButton.addActionListener(this);
        fullnameButton.addActionListener(this);

        confirmButton1.addActionListener(this);
        confirmButton2.addActionListener(this);
        confirmButton3.addActionListener(this);
        logoutButton.addActionListener(this);
        backButton.addActionListener(this);
        exitButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        String name;
        boolean emptyName;

        if (e.getSource() == logoutButton) {
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

        else if (e.getSource() == fullnameButton) {
            check = 1;
            fullField.setVisible(true);
            confirmButton1.setVisible(true);
            full.setVisible(true);

            confirmButton2.setVisible(false);
            confirmButton3.setVisible(false);
            user.setVisible(false);
            phone.setVisible(false);
            userField.setVisible(false);
            phoneField.setVisible(false);

            usernameButton.setVisible(false);
            fullnameButton.setVisible(false);
            phoneButton.setVisible(false);
        }

        else if (e.getSource() == usernameButton) {
            check = 1;
            userField.setVisible(true);
            confirmButton2.setVisible(true);
            user.setVisible(true);

            confirmButton1.setVisible(false);
            confirmButton3.setVisible(false);
            full.setVisible(false);
            phone.setVisible(false);
            fullField.setVisible(false);
            phoneField.setVisible(false);

            fullnameButton.setVisible(false);
            usernameButton.setVisible(false);
            phoneButton.setVisible(false);

            name = userField.getText();
            emptyName = name.isEmpty();
        }

        else if (e.getSource() == phoneButton) {
            check = 1;
            phoneField.setVisible(true);
            confirmButton3.setVisible(true);
            phone.setVisible(true);

            confirmButton1.setVisible(false);
            confirmButton2.setVisible(false);
            full.setVisible(false);
            user.setVisible(false);
            fullField.setVisible(false);
            userField.setVisible(false);

            fullnameButton.setVisible(false);
            phoneButton.setVisible(false);
            usernameButton.setVisible(false);

            name = phoneField.getText();
            emptyName = name.isEmpty();
        }

        else if (e.getSource() == backButton) {
            if (check == 0) {
                frame.setVisible(false);
                new Profile();
            } else {
                fullnameButton.setVisible(true);
                usernameButton.setVisible(true);
                phoneButton.setVisible(true);

                confirmButton1.setVisible(false);
                confirmButton2.setVisible(false);
                confirmButton3.setVisible(false);

                full.setVisible(false);
                user.setVisible(false);
                phone.setVisible(false);

                fullField.setVisible(false);
                userField.setVisible(false);
                phoneField.setVisible(false);

                check = 0;
            }
        }

        else if (e.getSource() == confirmButton1) {
            name = fullField.getText();
            String newName;
            emptyName = name.isEmpty();

            if (emptyName) {
                showMessageDialog(null, "Please enter your name", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                newName = "Full Name : " + name;
                try {
                    FileReader fr1 = new FileReader(".\\files\\user_login.txt");
                    BufferedReader br1 = new BufferedReader(fr1);
                    String currentline1;
                    int target = 0;
                    while ((currentline1 = br1.readLine()) != null) {
                        if ((Login.fullUsername).equals(currentline1)) {
                            target = target - 1;
                            break;
                        } else {
                            target++;
                        }
                    }
                    fr1.close();
                    br1.close();

                    String tempfile = ".\\files\\temp3.txt";
                    File oldFile = new File(".\\files\\user_login.txt");
                    File newFile = new File(".\\files\\temp3.txt");
                    String currentline;

                    FileWriter fileWriter2 = new FileWriter(tempfile, false);
                    BufferedWriter bufferedWriter2 = new BufferedWriter(fileWriter2);
                    PrintWriter printWriter2 = new PrintWriter(bufferedWriter2);

                    FileReader fr = new FileReader(".\\files\\user_login.txt");
                    BufferedReader br = new BufferedReader(fr);

                    int l = 0;
                    while ((currentline = br.readLine()) != null) {
                        if (target == l) {
                            printWriter2.println(newName);
                        } else {
                            printWriter2.println(currentline);
                        }
                        l++;
                    }
                    printWriter2.flush();
                    printWriter2.close();
                    fr.close();
                    br.close();
                    bufferedWriter2.close();
                    fileWriter2.close();
                    oldFile.delete();
                    File dumb = new File(".\\files\\user_login.txt");
                    newFile.renameTo(dumb);

                    Login.fullName = newName;
                    showMessageDialog(null, "Full name updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    frame.setVisible(false);
                    new Profile();

                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        } else if (e.getSource() == confirmButton2) {
            name = userField.getText();
            String newName;
            emptyName = name.isEmpty();

            if (emptyName) {
                showMessageDialog(null, "Please enter a new username", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                newName = "User Name : " + name;

                try {
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
                        if (line.equals("User Name : " + name)) {
                            userflag = true;
                            break;
                        }
                    }

                    for (int i = 0; i < totalLines2; i++) {
                        String line = Files.readAllLines(Paths.get(".\\files\\admin_login.txt")).get(i);
                        if (line.equals("User Name : " + name)) {
                            adminflag = true;
                            break;
                        }
                    }

                    if (!adminflag && !userflag) {
                        FileReader fr1 = new FileReader(".\\files\\user_login.txt");
                        BufferedReader br1 = new BufferedReader(fr1);
                        String currentline1;
                        int target = 0;
                        while ((currentline1 = br1.readLine()) != null) {
                            if ((Login.fullUsername).equals(currentline1)) {
                                break;
                            } else {
                                target++;
                            }
                        }
                        fr1.close();
                        br1.close();

                        String tempfile = ".\\files\\temp3.txt";
                        File oldFile = new File(".\\files\\user_login.txt");
                        File newFile = new File(".\\files\\temp3.txt");
                        String currentline;

                        FileWriter fileWriter2 = new FileWriter(tempfile, false);
                        BufferedWriter bufferedWriter2 = new BufferedWriter(fileWriter2);
                        PrintWriter printWriter2 = new PrintWriter(bufferedWriter2);

                        FileReader fr = new FileReader(".\\files\\user_login.txt");
                        BufferedReader br = new BufferedReader(fr);

                        int l = 0;
                        while ((currentline = br.readLine()) != null) {
                            if (target == l) {
                                printWriter2.println(newName);
                            } else {
                                printWriter2.println(currentline);
                            }
                            l++;
                        }
                        printWriter2.flush();
                        printWriter2.close();
                        fr.close();
                        br.close();
                        bufferedWriter2.close();
                        fileWriter2.close();
                        oldFile.delete();
                        File dumb = new File(".\\files\\user_login.txt");
                        newFile.renameTo(dumb);

                        Login.fullUsername = newName;
                        showMessageDialog(null, "Username updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        frame.setVisible(false);
                        new Profile();
                    } else {
                        showMessageDialog(null, "Username already exists!", "Warning", JOptionPane.WARNING_MESSAGE);
                    }

                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        }

        else if (e.getSource() == confirmButton3) {
            name = phoneField.getText();
            String newName;
            emptyName = name.isEmpty();
            long number;
            int numcount = 0;
            try {
                number = Long.parseLong(name);
                if (name.length() != 11)
                    numcount++;
            } catch (Exception ex) {
                numcount = 1;
            }

            if (emptyName) {
                showMessageDialog(null, "Please enter a new phone number", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (numcount > 0) {
                showMessageDialog(null, "Invalid Phone Number (Must be 11 digits)", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                newName = "Phone : " + name;

                try {
                    FileReader fr1 = new FileReader(".\\files\\user_login.txt");
                    BufferedReader br1 = new BufferedReader(fr1);
                    String currentline1;
                    int target = 0;
                    while ((currentline1 = br1.readLine()) != null) {
                        if ((Login.fullUsername).equals(currentline1)) {
                            target = target + 2;
                            break;
                        } else {
                            target++;
                        }
                    }
                    fr1.close();
                    br1.close();

                    String tempfile = ".\\files\\temp3.txt";
                    File oldFile = new File(".\\files\\user_login.txt");
                    File newFile = new File(".\\files\\temp3.txt");
                    String currentline;

                    FileWriter fileWriter2 = new FileWriter(tempfile, false);
                    BufferedWriter bufferedWriter2 = new BufferedWriter(fileWriter2);
                    PrintWriter printWriter2 = new PrintWriter(bufferedWriter2);

                    FileReader fr = new FileReader(".\\files\\user_login.txt");
                    BufferedReader br = new BufferedReader(fr);

                    int l = 0;
                    while ((currentline = br.readLine()) != null) {
                        if (target == l) {
                            printWriter2.println(newName);
                        } else {
                            printWriter2.println(currentline);
                        }
                        l++;
                    }
                    printWriter2.flush();
                    printWriter2.close();
                    fr.close();
                    br.close();
                    bufferedWriter2.close();
                    fileWriter2.close();
                    oldFile.delete();
                    File dumb = new File(".\\files\\user_login.txt");
                    newFile.renameTo(dumb);

                    Login.phoneNumber = newName;
                    showMessageDialog(null, "Phone number updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    frame.setVisible(false);
                    new Profile();

                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        }
    }
}