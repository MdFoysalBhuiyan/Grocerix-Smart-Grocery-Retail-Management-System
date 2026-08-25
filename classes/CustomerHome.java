package classes;

import java.util.*;
import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.BorderFactory;
import javax.swing.border.Border;
import java.io.*;
import java.nio.charset.CoderMalfunctionError;
import java.nio.file.*;
import classes.*;

public class CustomerHome implements ActionListener {
    private JFrame frame;
    private Container c;
    private Cursor cursor;

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
    private String userList;
    String file;
    String temp;
    int shopcount;
    int count;

    private JTable table;
    private JScrollPane scroll;
    private int tablecount;

    private UIStyle.ModernButton profileButton;
    private UIStyle.ModernButton buyButton;
    private UIStyle.ModernButton deleteButton;
    private UIStyle.ModernButton exitButton;
    private UIStyle.ModernButton logoutButton;
    private String newfiles;

    private String usernamenew = Login.USERNAME;

    public CustomerHome() {
        frame = new JFrame();
        frame.setBounds(50, 50, 850, 550);
        frame.setTitle("Grocery Shop Management - Customer Workspace");
        frame.setLayout(null);
        frame.setVisible(true);
        c = frame.getContentPane();
        c.setBackground(UIStyle.BG_DARK);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon icon = new ImageIcon("images/market.jpg");
        frame.setIconImage(icon.getImage());

        cursor = new Cursor(Cursor.HAND_CURSOR);

        // --- TOP BANNER ---
        UIStyle.RoundedPanel topBanner = new UIStyle.RoundedPanel(12, UIStyle.PANEL_BG, UIStyle.PANEL_BORDER);
        topBanner.setBounds(30, 20, 790, 70);
        topBanner.setLayout(null);
        frame.add(topBanner);

        JLabel bannerTitle = new JLabel("Customer Portal");
        bannerTitle.setBounds(20, 12, 300, 26);
        bannerTitle.setFont(UIStyle.FONT_TITLE);
        bannerTitle.setForeground(UIStyle.COLOR_PRIMARY);
        topBanner.add(bannerTitle);

        JLabel userBadge = new JLabel("Welcome, " + (usernamenew != null ? usernamenew : "User"));
        userBadge.setBounds(20, 40, 300, 18);
        userBadge.setFont(UIStyle.FONT_BODY_BOLD);
        userBadge.setForeground(UIStyle.TEXT_SECONDARY);
        topBanner.add(userBadge);

        // Header Action Buttons
        buyButton = new UIStyle.ModernButton("🏬 Browse & Rent", UIStyle.COLOR_SUCCESS, UIStyle.COLOR_SUCCESS_HOVER);
        buyButton.setBounds(340, 16, 140, 38);
        topBanner.add(buyButton);
        buyButton.addActionListener(this);

        profileButton = new UIStyle.ModernButton("👤 Profile", UIStyle.COLOR_PRIMARY, UIStyle.COLOR_PRIMARY_HOVER);
        profileButton.setBounds(490, 16, 120, 38);
        topBanner.add(profileButton);
        profileButton.addActionListener(this);

        logoutButton = new UIStyle.ModernButton("🚪 Log Out", UIStyle.COLOR_DANGER, UIStyle.COLOR_DANGER_HOVER);
        logoutButton.setBounds(620, 16, 120, 38);
        topBanner.add(logoutButton);
        logoutButton.addActionListener(this);

        // Exit Button (Top Right corner)
        exitButton = new UIStyle.ModernButton("✕", UIStyle.PANEL_BG, UIStyle.COLOR_DANGER);
        exitButton.setBounds(750, 16, 30, 38);
        exitButton.setMargin(new Insets(0,0,0,0));
        topBanner.add(exitButton);
        exitButton.addActionListener(this);

        // --- MAIN DATA PANEL ---
        JLabel details = new JLabel("Your Rented Shops Overview");
        details.setBounds(30, 105, 350, 30);
        details.setFont(UIStyle.FONT_HEADER);
        details.setForeground(UIStyle.TEXT_PRIMARY);
        frame.add(details);

        deleteButton = new UIStyle.ModernButton("↩ Return Selected Shop", UIStyle.COLOR_SECONDARY, UIStyle.COLOR_SECONDARY_HOVER);
        deleteButton.setBounds(600, 105, 220, 34);
        frame.add(deleteButton);
        deleteButton.addActionListener(this);

        try {
            newfiles = ".\\files\\" + usernamenew + "_shops.txt";
            File file = new File(newfiles);
            if (!file.exists()) {
                file.createNewFile();
            }

            BufferedReader readFile1 = new BufferedReader(new FileReader(newfiles));
            int totalLines1 = 0;
            int count2 = 0;
            while (readFile1.readLine() != null) {
                count2++;
            }
            readFile1.close();

            for (int j = 0; j < count2; j++) {
                String line = Files.readAllLines(Paths.get(newfiles)).get(j);
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
            BufferedReader readFile3 = new BufferedReader(new FileReader(newfiles));
            int totalLines3 = 0;
            while (readFile3.readLine() != null) {
                totalLines3++;
            }
            readFile3.close();

            for (int l = 0; l < totalLines3; l++) {
                String line = Files.readAllLines(Paths.get(newfiles)).get(l);
                if (line.equals("Shop Details")) {
                    String line2 = Files.readAllLines(Paths.get(newfiles)).get((l + 1));
                    String line3 = Files.readAllLines(Paths.get(newfiles)).get((l + 2));
                    String line4 = Files.readAllLines(Paths.get(newfiles)).get((l + 3));
                    String line5 = Files.readAllLines(Paths.get(newfiles)).get((l + 4));
                    String line6 = Files.readAllLines(Paths.get(newfiles)).get((l + 5));
                    String line7 = Files.readAllLines(Paths.get(newfiles)).get((l + 6));
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
            scroll.setBounds(30, 150, 790, 330);
            frame.add(scroll);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == deleteButton) {
            if (table == null || table.getSelectionModel().isSelectionEmpty()) {
                JOptionPane.showMessageDialog(null, "Please select a shop from the list to return.", "Warning",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                try {
                    BufferedReader readFile1 = new BufferedReader(new FileReader(newfiles));
                    int totalLines1 = 0;
                    int count2 = 0;
                    while (readFile1.readLine() != null) {
                        count2++;
                    }
                    readFile1.close();

                    for (int j = 0; j < count2; j++) {
                        String line = Files.readAllLines(Paths.get(newfiles)).get(j);
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
                        String line = Files.readAllLines(Paths.get(newfiles)).get(j);
                        if (line.equals(removeUser)) {
                            count = j;
                        }
                    }

                    String tempfile = ".\\files\\temp.txt";
                    File oldFile = new File(newfiles);
                    File newFile = new File(".\\files\\temp.txt.txt");
                    int l = 0;
                    String currentline;
                    File rentShop = new File(".\\files\\all_shops.txt");

                    FileWriter fileWriter3 = new FileWriter(rentShop, true);
                    BufferedWriter bufferedWriter3 = new BufferedWriter(fileWriter3);
                    PrintWriter printWriter3 = new PrintWriter(bufferedWriter3);
                    printWriter3.println();

                    FileWriter fileWriter2 = new FileWriter(tempfile, true);
                    BufferedWriter bufferedWriter2 = new BufferedWriter(fileWriter2);
                    PrintWriter printWriter2 = new PrintWriter(bufferedWriter2);

                    FileReader fr = new FileReader(newfiles);
                    BufferedReader br = new BufferedReader(fr);

                    BufferedReader readFile3 = new BufferedReader(new FileReader(newfiles));
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
                        } else {
                            printWriter3.println(currentline);
                        }
                    }

                    printWriter2.flush();
                    printWriter2.close();
                    printWriter3.flush();
                    printWriter3.close();
                    fr.close();
                    br.close();
                    bufferedWriter2.close();
                    fileWriter2.close();
                    bufferedWriter3.close();
                    fileWriter3.close();

                    oldFile.delete();
                    File dumb = new File(".\\files\\temp.txt");
                    dumb.createNewFile();
                    newFile.renameTo(dumb);
                    frame.setVisible(false);
                    new Delete();

                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        } else if (e.getSource() == exitButton) {
            int yesORno = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit Confirmation",
                    JOptionPane.YES_NO_OPTION);

            if (yesORno == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        } else if (e.getSource() == logoutButton) {
            frame.setVisible(false);
            new Login();
        } else if (e.getSource() == buyButton) {
            frame.setVisible(false);
            new Skip();
        } else if (e.getSource() == profileButton) {
            frame.setVisible(false);
            new Profile();
        }
    }
}
