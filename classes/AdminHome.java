package classes;

import java.lang.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

import java.awt.*;
import java.awt.event.*;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.io.*;
import java.nio.file.*;
import classes.*;

public class AdminHome implements ActionListener {
    private Container c;
    private JFrame frame;
    private Cursor cursor;
    private UIStyle.ModernButton userInfo, shopInfo, selfInfo, logoutButton, exitButton;
    private UIStyle.ModernButton userAdd, shopAdd, shopDlt;
    private JLabel details, details1, details3;
    private JTable table1;
    private JScrollPane scroll1;

    private JTable table, table3;
    private JScrollPane scroll, scroll3;
    private int tablecount;
    private String type;
    private String typearr[];
    private String size;
    private String sizearr[];
    private String rent;
    private String rentarr[];
    private String quantity;
    private String quantityarr[];
    private String shopno;
    private String shoparr[];
    private String imglink;
    private String imglinkarr[];
    String file;
    String temp;
    int count, shopcount;
    private String userList;

    public AdminHome() {
        frame = new JFrame();
        frame.setBounds(50, 50, 850, 550);
        frame.setTitle("Grocery Shop Management - Admin Dashboard");
        frame.setLayout(null);
        frame.setVisible(true);
        c = frame.getContentPane();
        c.setBackground(UIStyle.BG_DARK);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        file = ".\\files\\all_shops.txt";
        temp = ".\\files\\temp.txt";

        ImageIcon icon = new ImageIcon("images/market.jpg");
        frame.setIconImage(icon.getImage());

        cursor = new Cursor(Cursor.HAND_CURSOR);

        // --- LEFT SIDEBAR PANEL ---
        UIStyle.RoundedPanel sidebar = new UIStyle.RoundedPanel(0, UIStyle.PANEL_BG, UIStyle.PANEL_BORDER);
        sidebar.setBounds(0, 0, 220, 550);
        sidebar.setLayout(null);
        frame.add(sidebar);

        JLabel brand = new JLabel("ADMIN PANEL", SwingConstants.CENTER);
        brand.setBounds(10, 25, 200, 28);
        brand.setFont(UIStyle.FONT_HEADER);
        brand.setForeground(UIStyle.COLOR_PRIMARY);
        sidebar.add(brand);

        JLabel brandSub = new JLabel("Management Dashboard", SwingConstants.CENTER);
        brandSub.setBounds(10, 52, 200, 18);
        brandSub.setFont(UIStyle.FONT_SMALL);
        brandSub.setForeground(UIStyle.TEXT_MUTED);
        sidebar.add(brandSub);

        JSeparator sep = new JSeparator();
        sep.setBounds(20, 80, 180, 2);
        sep.setForeground(UIStyle.PANEL_BORDER);
        sidebar.add(sep);

        // Navigation Buttons
        shopInfo = new UIStyle.ModernButton("🏬 Shops Info", UIStyle.COLOR_PRIMARY, UIStyle.COLOR_PRIMARY_HOVER);
        shopInfo.setBounds(15, 100, 190, 40);
        sidebar.add(shopInfo);
        shopInfo.addActionListener(this);

        userInfo = new UIStyle.ModernButton("👥 Users Info", UIStyle.COLOR_SECONDARY, UIStyle.COLOR_SECONDARY_HOVER);
        userInfo.setBounds(15, 150, 190, 40);
        sidebar.add(userInfo);
        userInfo.addActionListener(this);

        selfInfo = new UIStyle.ModernButton("📋 Rented Shops", UIStyle.COLOR_SECONDARY, UIStyle.COLOR_SECONDARY_HOVER);
        selfInfo.setBounds(15, 200, 190, 40);
        sidebar.add(selfInfo);
        selfInfo.addActionListener(this);

        logoutButton = new UIStyle.ModernButton("🚪 Log Out", UIStyle.COLOR_DANGER, UIStyle.COLOR_DANGER_HOVER);
        logoutButton.setBounds(15, 440, 190, 40);
        sidebar.add(logoutButton);
        logoutButton.addActionListener(this);

        // Exit Button (Top Right corner)
        exitButton = new UIStyle.ModernButton("✕", UIStyle.PANEL_BG, UIStyle.COLOR_DANGER);
        exitButton.setBounds(795, 10, 30, 30);
        exitButton.setMargin(new Insets(0,0,0,0));
        frame.add(exitButton);
        exitButton.addActionListener(this);

        // --- MAIN CONTENT AREA HEADERS ---
        details = new JLabel("Shops Information");
        details.setBounds(250, 20, 300, 32);
        details.setFont(UIStyle.FONT_TITLE);
        details.setForeground(UIStyle.TEXT_PRIMARY);
        frame.add(details);

        details1 = new JLabel("Users Information");
        details1.setBounds(250, 20, 300, 32);
        details1.setFont(UIStyle.FONT_TITLE);
        details1.setForeground(UIStyle.TEXT_PRIMARY);
        frame.add(details1);
        details1.setVisible(false);

        details3 = new JLabel("Rented Shops Overview");
        details3.setBounds(250, 20, 350, 32);
        details3.setFont(UIStyle.FONT_TITLE);
        details3.setForeground(UIStyle.TEXT_PRIMARY);
        frame.add(details3);
        details3.setVisible(false);

        // Action Buttons for Shops
        shopAdd = new UIStyle.ModernButton("+ Add Shop", UIStyle.COLOR_SUCCESS, UIStyle.COLOR_SUCCESS_HOVER);
        shopAdd.setBounds(250, 450, 140, 38);
        frame.add(shopAdd);
        shopAdd.addActionListener(this);

        shopDlt = new UIStyle.ModernButton("🗑 Delete Shop", UIStyle.COLOR_DANGER, UIStyle.COLOR_DANGER_HOVER);
        shopDlt.setBounds(400, 450, 140, 38);
        frame.add(shopDlt);
        shopDlt.addActionListener(this);

        // Action Button for Users
        userAdd = new UIStyle.ModernButton("+ Add User", UIStyle.COLOR_SUCCESS, UIStyle.COLOR_SUCCESS_HOVER);
        userAdd.setBounds(250, 450, 140, 38);
        frame.add(userAdd);
        userAdd.addActionListener(this);
        userAdd.setVisible(false);

        // --- LOAD USER TABLE ---
        try {
            File fileUser = new File(".\\files\\user_login.txt");
            if (!fileUser.exists()) {
                fileUser.createNewFile();
            }

            BufferedReader reader = new BufferedReader(new FileReader(".\\files\\user_login.txt"));
            int totalLines = 0;
            while (reader.readLine() != null)
                totalLines++;
            reader.close();

            int totalLines1 = 0;
            for (int j = 0; j < totalLines; j++) {
                String line = Files.readAllLines(Paths.get(".\\files\\user_login.txt")).get(j);
                if (line.equals("===============================================")) {
                    totalLines1++;
                }
            }
            totalLines1 = totalLines1 / 2;

            String[] titleCol1 = { "Full Name", "User Name", "Password", "Phone" };
            String[][] titleRow1 = new String[totalLines1][4];

            int k = 0;
            char ch;
            for (int i = 0; i < totalLines; i++) {
                int m = 0;
                String line2 = Files.readAllLines(Paths.get(".\\files\\user_login.txt")).get((i));
                if (line2.length() > 0 && line2.charAt(0) == 'F') {
                    String fullname = "";
                    for (int f = 0; f < line2.length(); f++) {
                        if (line2.charAt(f) == ':' && line2.charAt(f + 1) == ' ') {
                            for (int p = f + 2; p < line2.length(); p++) {
                                ch = line2.charAt(p);
                                fullname = fullname + ch;
                            }
                            break;
                        }
                    }
                    titleRow1[k][m] = fullname;
                    m++;

                    String usernameStr = "";
                    String line3 = Files.readAllLines(Paths.get(".\\files\\user_login.txt")).get((i + 1));
                    for (int f = 0; f < line3.length(); f++) {
                        if (line3.charAt(f) == ':' && line3.charAt(f + 1) == ' ') {
                            for (int p = f + 2; p < line3.length(); p++) {
                                ch = line3.charAt(p);
                                usernameStr = usernameStr + ch;
                            }
                            break;
                        }
                    }
                    titleRow1[k][m] = usernameStr;
                    m++;

                    String line4 = Files.readAllLines(Paths.get(".\\files\\user_login.txt")).get((i + 2));
                    String passwordStr = "";
                    for (int f = 0; f < line4.length(); f++) {
                        if (line4.charAt(f) == ':' && line4.charAt(f + 1) == ' ') {
                            for (int p = f + 2; p < line4.length(); p++) {
                                ch = line4.charAt(p);
                                passwordStr = passwordStr + ch;
                            }
                            break;
                        }
                    }
                    titleRow1[k][m] = passwordStr;
                    m++;

                    String line5 = Files.readAllLines(Paths.get(".\\files\\user_login.txt")).get((i + 3));
                    String phoneStr = "";
                    for (int f = 0; f < line5.length(); f++) {
                        if (line5.charAt(f) == ':' && line5.charAt(f + 1) == ' ') {
                            for (int p = f + 2; p < line5.length(); p++) {
                                ch = line5.charAt(p);
                                phoneStr = phoneStr + ch;
                            }
                            break;
                        }
                    }
                    titleRow1[k][m] = phoneStr;
                    k++;
                    i += 5;
                }
            }
            table1 = new JTable(titleRow1, titleCol1);
            UIStyle.styleTable(table1);

            scroll1 = new JScrollPane(table1);
            UIStyle.styleScrollPane(scroll1);
            scroll1.setBounds(250, 65, 560, 360);
            frame.add(scroll1);
        } catch (Exception exa) {
            System.out.println(exa);
        }
        if (scroll1 != null) scroll1.setVisible(false);

        // --- LOAD SHOPS TABLE ---
        try {
            File file1 = new File(".\\files\\all_shops.txt");
            if (!file1.exists()) {
                file1.createNewFile();
            }

            BufferedReader readFile1 = new BufferedReader(new FileReader(".\\files\\all_shops.txt"));
            int totalLines1 = 0;
            int count2 = 0;
            while (readFile1.readLine() != null) {
                count2++;
            }
            readFile1.close();

            for (int j = 0; j < count2; j++) {
                String line = Files.readAllLines(Paths.get(".\\files\\all_shops.txt")).get(j);
                if (line.equals("Shop Details")) {
                    totalLines1++;
                }
            }
            tablecount = totalLines1;

            String titleCol[] = { "Type", "Size (sq ft)", "Shop No", "Rent (TK)", "Place" };
            String titleRow[][] = new String[totalLines1][5];

            typearr = new String[totalLines1];
            sizearr = new String[totalLines1];
            rentarr = new String[totalLines1];
            quantityarr = new String[totalLines1];
            shoparr = new String[totalLines1];
            imglinkarr = new String[totalLines1];

            int i = 0;
            BufferedReader readFile3 = new BufferedReader(new FileReader(".\\files\\all_shops.txt"));
            int totalLines3 = 0;
            while (readFile3.readLine() != null) {
                totalLines3++;
            }
            readFile3.close();

            for (int l = 0; l < totalLines3; l++) {
                String line = Files.readAllLines(Paths.get(".\\files\\all_shops.txt")).get(l);
                if (line.equals("Shop Details")) {
                    String line2 = Files.readAllLines(Paths.get(".\\files\\all_shops.txt")).get((l + 1));
                    String line3 = Files.readAllLines(Paths.get(".\\files\\all_shops.txt")).get((l + 2));
                    String line4 = Files.readAllLines(Paths.get(".\\files\\all_shops.txt")).get((l + 3));
                    String line5 = Files.readAllLines(Paths.get(".\\files\\all_shops.txt")).get((l + 4));
                    String line6 = Files.readAllLines(Paths.get(".\\files\\all_shops.txt")).get((l + 5));
                    String line7 = Files.readAllLines(Paths.get(".\\files\\all_shops.txt")).get((l + 6));
                    int j = 0;
                    type = line2;
                    titleRow[i][j] = type;
                    typearr[i] = type;
                    ++j;

                    size = line3;
                    titleRow[i][j] = size;
                    sizearr[i] = size;
                    ++j;

                    shopno = line4;
                    titleRow[i][j] = shopno;
                    shoparr[i] = shopno;
                    ++j;

                    rent = line5;
                    titleRow[i][j] = rent;
                    rentarr[i] = rent;
                    ++j;

                    quantity = line6;
                    titleRow[i][j] = quantity;
                    quantityarr[i] = quantity;
                    ++j;

                    imglink = line7;
                    imglinkarr[i] = imglink;
                    i++;
                    l += 5;
                }
            }

            table = new JTable(titleRow, titleCol);
            UIStyle.styleTable(table);

            scroll = new JScrollPane(table);
            UIStyle.styleScrollPane(scroll);
            scroll.setBounds(250, 65, 560, 360);
            frame.add(scroll);

        } catch (Exception ex) {
            System.out.println(ex);
        }

        // --- LOAD RENTED SHOPS TABLE ---
        try {
            File fileRented = new File(".\\files\\rented_shops.txt");
            if (!fileRented.exists()) {
                fileRented.createNewFile();
            }

            BufferedReader readFile1 = new BufferedReader(new FileReader(".\\files\\rented_shops.txt"));
            int totalLines1 = 0;
            int count2 = 0;
            while (readFile1.readLine() != null) {
                count2++;
            }
            readFile1.close();

            for (int j = 0; j < count2; j++) {
                String line = Files.readAllLines(Paths.get(".\\files\\rented_shops.txt")).get(j);
                if (line.equals("Shop Details")) {
                    totalLines1++;
                }
            }

            String titleCol[] = { "Type", "Size (sq ft)", "Shop No", "Rent (TK)", "Place" };
            String titleRow[][] = new String[totalLines1][5];

            int i = 0;
            BufferedReader readFile3 = new BufferedReader(new FileReader(".\\files\\rented_shops.txt"));
            int totalLines3 = 0;
            while (readFile3.readLine() != null) {
                totalLines3++;
            }
            readFile3.close();

            for (int l = 0; l < totalLines3; l++) {
                String line = Files.readAllLines(Paths.get(".\\files\\rented_shops.txt")).get(l);
                if (line.equals("Shop Details")) {
                    String line2 = Files.readAllLines(Paths.get(".\\files\\rented_shops.txt")).get((l + 1));
                    String line3 = Files.readAllLines(Paths.get(".\\files\\rented_shops.txt")).get((l + 2));
                    String line4 = Files.readAllLines(Paths.get(".\\files\\rented_shops.txt")).get((l + 3));
                    String line5 = Files.readAllLines(Paths.get(".\\files\\rented_shops.txt")).get((l + 4));
                    String line6 = Files.readAllLines(Paths.get(".\\files\\rented_shops.txt")).get((l + 5));
                    int j = 0;
                    titleRow[i][j++] = line2;
                    titleRow[i][j++] = line3;
                    titleRow[i][j++] = line4;
                    titleRow[i][j++] = line5;
                    titleRow[i][j++] = line6;
                    i++;
                    l += 5;
                }
            }

            table3 = new JTable(titleRow, titleCol);
            UIStyle.styleTable(table3);

            scroll3 = new JScrollPane(table3);
            UIStyle.styleScrollPane(scroll3);
            scroll3.setBounds(250, 65, 560, 360);
            frame.add(scroll3);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        if (scroll3 != null) scroll3.setVisible(false);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == userInfo) {
            if (scroll != null) scroll.setVisible(false);
            shopAdd.setVisible(false);
            shopDlt.setVisible(false);
            details.setVisible(false);
            details3.setVisible(false);
            if (scroll3 != null) scroll3.setVisible(false);

            if (scroll1 != null) scroll1.setVisible(true);
            details1.setVisible(true);
            userAdd.setVisible(true);

            shopInfo.setBackground(UIStyle.COLOR_SECONDARY);
            userInfo.setBackground(UIStyle.COLOR_PRIMARY);
            selfInfo.setBackground(UIStyle.COLOR_SECONDARY);

        } else if (e.getSource() == shopInfo) {
            if (scroll != null) scroll.setVisible(true);
            shopAdd.setVisible(true);
            shopDlt.setVisible(true);
            details.setVisible(true);

            if (scroll1 != null) scroll1.setVisible(false);
            details1.setVisible(false);
            userAdd.setVisible(false);
            details3.setVisible(false);
            if (scroll3 != null) scroll3.setVisible(false);

            shopInfo.setBackground(UIStyle.COLOR_PRIMARY);
            userInfo.setBackground(UIStyle.COLOR_SECONDARY);
            selfInfo.setBackground(UIStyle.COLOR_SECONDARY);

        } else if (e.getSource() == selfInfo) {
            if (scroll != null) scroll.setVisible(false);
            shopAdd.setVisible(false);
            shopDlt.setVisible(false);
            details.setVisible(false);
            if (scroll1 != null) scroll1.setVisible(false);
            details1.setVisible(false);
            userAdd.setVisible(false);

            if (scroll3 != null) scroll3.setVisible(true);
            details3.setVisible(true);

            shopInfo.setBackground(UIStyle.COLOR_SECONDARY);
            userInfo.setBackground(UIStyle.COLOR_SECONDARY);
            selfInfo.setBackground(UIStyle.COLOR_PRIMARY);
        }

        else if (e.getSource() == shopAdd) {
            frame.setVisible(false);
            new AddShop();
        } else if (e.getSource() == userAdd) {
            frame.setVisible(false);
            new userAdd();
        }

        else if (e.getSource() == shopDlt) {
            if (table == null || table.getSelectionModel().isSelectionEmpty()) {
                JOptionPane.showMessageDialog(null, "Please select a shop row from the table to delete.", "Warning",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                try {
                    BufferedReader readFile1 = new BufferedReader(new FileReader(".\\files\\all_shops.txt"));
                    int totalLines1 = 0;
                    int count2 = 0;
                    while (readFile1.readLine() != null) {
                        count2++;
                    }
                    readFile1.close();

                    for (int j = 0; j < count2; j++) {
                        String line = Files.readAllLines(Paths.get(".\\files\\all_shops.txt")).get(j);
                        if (line.equals("Shop Details")) {
                            totalLines1++;
                        }
                    }
                    tablecount = totalLines1;
                    String removeUser = table.getModel().getValueAt(table.getSelectedRow(), 0).toString();

                    for (int i = 0; i < typearr.length; i++) {
                        if (typearr[i] != null && typearr[i].equals(removeUser)) {
                            count2 = i;
                            break;
                        }
                    }

                    userList = removeUser;
                    for (int j = 0; j < count2; j++) {
                        String line = Files.readAllLines(Paths.get(".\\files\\all_shops.txt")).get(j);
                        if (line.equals(removeUser)) {
                            count = j;
                        }
                    }

                    String tempfile = ".\\files\\temp.txt";
                    File oldFile = new File(".\\files\\all_shops.txt");
                    File newFile = new File(".\\files\\temp.txt.txt");
                    int l = 0;
                    String currentline;

                    FileWriter fileWriter2 = new FileWriter(tempfile, true);
                    BufferedWriter bufferedWriter2 = new BufferedWriter(fileWriter2);
                    PrintWriter printWriter2 = new PrintWriter(bufferedWriter2);

                    FileReader fr = new FileReader(".\\files\\all_shops.txt");
                    BufferedReader br = new BufferedReader(fr);

                    BufferedReader readFile3 = new BufferedReader(new FileReader(".\\files\\all_shops.txt"));
                    int totalLines3 = 0;
                    while (readFile3.readLine() != null) {
                        totalLines3++;
                    }
                    readFile3.close();

                    int a = count - 1;
                    int b = count;
                    int c = count + 1;
                    int d = count + 2;
                    int f = count + 3;
                    int g = count + 4;
                    int h = count + 5;
                    int j = count + 6;
                    while ((currentline = br.readLine()) != null) {
                        l++;
                        if (a != l && b != l && c != l && d != l && f != l && g != l && h != l && j != l) {
                            printWriter2.println(currentline);
                        }
                    }

                    printWriter2.flush();
                    printWriter2.close();
                    fr.close();
                    br.close();
                    bufferedWriter2.close();
                    fileWriter2.close();

                    oldFile.delete();
                    File dumb = new File(".\\files\\temp.txt");
                    dumb.createNewFile();
                    newFile.renameTo(dumb);
                    frame.setVisible(false);
                    new ShopDlt();
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        }

        else if (e.getSource() == exitButton) {
            int yesORno = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit Confirmation",
                    JOptionPane.YES_NO_OPTION);

            if (yesORno == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        } else if (e.getSource() == logoutButton) {
            frame.setVisible(false);
            new Login();
        }
    }
}
