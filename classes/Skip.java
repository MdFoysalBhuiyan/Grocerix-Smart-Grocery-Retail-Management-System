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

public class Skip implements ActionListener {
    private JFrame frame;
    private Container c;
    private Cursor cursor;
    private JTable table;
    private JScrollPane scroll;
    private JComboBox<String> combobox;
    private JLabel imgLabel2;
    private int tablecount;
    private UIStyle.RoundedPanel panel2;
    private JLabel lblText1, lblText2, lblText3, lblText4, lblText5, lblText6;
    private JLabel label;
    private UIStyle.ModernButton confirmButton;
    private UIStyle.ModernButton exitButton;
    private UIStyle.ModernButton backButton;
    private UIStyle.ModernButton logoutButton;

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
    private int count;
    private int count2;

    public Skip() {
        frame = new JFrame();
        frame.setBounds(50, 50, 850, 550);
        frame.setTitle("Grocery Shop Management - Available Shops Directory");
        frame.setLayout(null);
        frame.setVisible(true);
        c = frame.getContentPane();
        c.setBackground(UIStyle.BG_DARK);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon icon = new ImageIcon("images/market.jpg");
        frame.setIconImage(icon.getImage());

        cursor = new Cursor(Cursor.HAND_CURSOR);

        // Header Title
        JLabel details = new JLabel("Available Shops Directory");
        details.setBounds(25, 12, 350, 30);
        details.setFont(UIStyle.FONT_TITLE);
        details.setForeground(UIStyle.TEXT_PRIMARY);
        frame.add(details);

        // Action Buttons Top Right
        backButton = new UIStyle.ModernButton("← Back", UIStyle.COLOR_SECONDARY, UIStyle.COLOR_SECONDARY_HOVER);
        backButton.setBounds(580, 12, 90, 32);
        frame.add(backButton);
        backButton.addActionListener(this);

        logoutButton = new UIStyle.ModernButton("🚪 Log Out", UIStyle.COLOR_DANGER, UIStyle.COLOR_DANGER_HOVER);
        logoutButton.setBounds(680, 12, 100, 32);
        frame.add(logoutButton);
        logoutButton.addActionListener(this);
        if (!Login.loginFlag) {
            logoutButton.setVisible(false);
        }

        exitButton = new UIStyle.ModernButton("✕", UIStyle.PANEL_BG, UIStyle.COLOR_DANGER);
        exitButton.setBounds(790, 12, 30, 32);
        exitButton.setMargin(new Insets(0,0,0,0));
        frame.add(exitButton);
        exitButton.addActionListener(this);

        try {
            File file = new File(".\\files\\all_shops.txt");
            if (!file.exists()) {
                file.createNewFile();
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

            String[] combolist = new String[totalLines1 + 1];
            combolist[0] = "-- Select Shop --";
            int i = 0;
            int k = 1;

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
                    combolist[k] = type;
                    k++;
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
            table.setEnabled(false);
            UIStyle.styleTable(table);

            scroll = new JScrollPane(table);
            UIStyle.styleScrollPane(scroll);
            scroll.setBounds(25, 48, 795, 140);
            frame.add(scroll);

            // Controls & Dropdown
            JLabel question = new JLabel("Select Shop to Inspect");
            question.setBounds(25, 198, 200, 20);
            question.setFont(UIStyle.FONT_BODY_BOLD);
            question.setForeground(UIStyle.TEXT_PRIMARY);
            frame.add(question);

            combobox = new JComboBox<>(combolist);
            combobox.setBounds(25, 222, 210, 36);
            combobox.setFont(UIStyle.FONT_BODY);
            combobox.setBackground(UIStyle.INPUT_BG);
            combobox.setForeground(UIStyle.TEXT_PRIMARY);
            frame.add(combobox);

            label = new JLabel("Select a shop from the dropdown to view details", SwingConstants.CENTER);
            label.setBounds(250, 260, 570, 40);
            label.setForeground(UIStyle.TEXT_MUTED);
            label.setFont(UIStyle.FONT_SUBHEADER);
            frame.add(label);

            // Preview Image Label
            imgLabel2 = new JLabel();
            imgLabel2.setBounds(250, 200, 200, 200);
            imgLabel2.setVisible(false);
            frame.add(imgLabel2);

            // Details Card Panel
            panel2 = new UIStyle.RoundedPanel(12, UIStyle.PANEL_BG, UIStyle.PANEL_BORDER);
            panel2.setBounds(465, 198, 355, 290);
            panel2.setLayout(null);
            panel2.setVisible(false);
            frame.add(panel2);

            lblText1 = new JLabel();
            lblText1.setBounds(20, 15, 315, 24);
            lblText1.setFont(UIStyle.FONT_SUBHEADER);
            lblText1.setForeground(UIStyle.COLOR_PRIMARY);
            panel2.add(lblText1);

            lblText2 = new JLabel();
            lblText2.setBounds(20, 45, 315, 22);
            lblText2.setFont(UIStyle.FONT_BODY_BOLD);
            lblText2.setForeground(UIStyle.COLOR_SUCCESS);
            panel2.add(lblText2);

            lblText4 = new JLabel();
            lblText4.setBounds(20, 75, 315, 22);
            lblText4.setFont(UIStyle.FONT_BODY);
            lblText4.setForeground(UIStyle.TEXT_PRIMARY);
            panel2.add(lblText4);

            lblText6 = new JLabel();
            lblText6.setBounds(20, 105, 315, 22);
            lblText6.setFont(UIStyle.FONT_BODY);
            lblText6.setForeground(UIStyle.TEXT_PRIMARY);
            panel2.add(lblText6);

            lblText3 = new JLabel();
            lblText3.setBounds(20, 135, 315, 22);
            lblText3.setFont(UIStyle.FONT_BODY);
            lblText3.setForeground(UIStyle.TEXT_SECONDARY);
            panel2.add(lblText3);

            lblText5 = new JLabel();
            lblText5.setBounds(20, 170, 315, 20);
            lblText5.setFont(UIStyle.FONT_SMALL);
            lblText5.setForeground(UIStyle.TEXT_MUTED);
            panel2.add(lblText5);

            confirmButton = new UIStyle.ModernButton("Confirm Rent Now", UIStyle.COLOR_SUCCESS, UIStyle.COLOR_SUCCESS_HOVER);
            confirmButton.setBounds(20, 205, 315, 42);
            confirmButton.setVisible(false);
            panel2.add(confirmButton);
            confirmButton.addActionListener(this);

        } catch (Exception ex) {
            System.out.println(ex);
        }

        combobox.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == combobox) {
            String list = combobox.getSelectedItem().toString();
            boolean match = false;

            for (int i = 0; i < tablecount; i++) {
                if (list.equals(typearr[i])) {
                    match = true;
                    label.setVisible(false);
                    panel2.setVisible(true);
                    imgLabel2.setVisible(true);
                    confirmButton.setVisible(true);

                    count2 = i;
                    userList = list;
                    String line = imglinkarr[i];

                    ImageIcon rawIcon = new ImageIcon(line);
                    Image scaled = rawIcon.getImage().getScaledInstance(190, 190, Image.SCALE_SMOOTH);
                    imgLabel2.setIcon(new ImageIcon(scaled));
                    imgLabel2.setBounds(255, 230, 190, 190);

                    lblText1.setText(typearr[i]);
                    lblText2.setText("Rent: " + rentarr[i] + " TK / Month");
                    lblText4.setText("Size: " + sizearr[i] + " Sq. Ft.");
                    lblText6.setText("Shop Number: " + shoparr[i]);
                    lblText3.setText("Location: " + quantityarr[i]);
                    lblText5.setText("Click below to finalize rental agreement");
                    break;
                }
            }

            if (!match) {
                label.setVisible(true);
                panel2.setVisible(false);
                imgLabel2.setVisible(false);
                confirmButton.setVisible(false);
            }
        } else if (e.getSource() == confirmButton) {
            if (!Login.loginFlag) {
                JOptionPane.showMessageDialog(null, "Please Sign Up or Log In first to rent a shop.", "Authentication Required",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                int yesORno = JOptionPane.showConfirmDialog(null, "Are you sure you want to rent this shop?", "Confirmation",
                        JOptionPane.YES_NO_OPTION);

                if (yesORno == JOptionPane.YES_OPTION) {
                    String line = ".\\files\\" + Login.USERNAME + "_shops.txt";
                    try {
                        File file = new File(line);
                        if (!file.exists()) {
                            file.createNewFile();
                        }
                        for (int j = 0; j < tablecount; j++) {
                            if (userList.equals(typearr[j])) {
                                FileWriter fileWriter = new FileWriter(file, true);
                                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                                PrintWriter printWriter = new PrintWriter(bufferedWriter);

                                printWriter.println("Shop Details");
                                printWriter.println(typearr[j]);
                                printWriter.println(sizearr[j]);
                                printWriter.println(shoparr[j]);
                                printWriter.println(rentarr[j]);
                                printWriter.println(quantityarr[j]);
                                printWriter.println(imglinkarr[j]);
                                printWriter.println();
                                printWriter.close();

                                String tempfile = ".\\files\\temp2.txt";
                                File oldFile = new File(".\\files\\all_shops.txt");
                                File newFile = new File(".\\files\\temp2.txt.txt");
                                int l = 0;
                                String currentline;

                                FileWriter fileWriter2 = new FileWriter(tempfile, true);
                                BufferedWriter bufferedWriter2 = new BufferedWriter(fileWriter2);
                                PrintWriter printWriter2 = new PrintWriter(bufferedWriter2);

                                File rentShop = new File(".\\files\\rented_shops.txt");
                                if (!rentShop.exists()) {
                                    rentShop.createNewFile();
                                }
                                FileWriter fileWriter3 = new FileWriter(rentShop, true);
                                BufferedWriter bufferedWriter3 = new BufferedWriter(fileWriter3);
                                PrintWriter printWriter3 = new PrintWriter(bufferedWriter3);
                                printWriter3.println();

                                FileReader fr = new FileReader(".\\files\\all_shops.txt");
                                BufferedReader br = new BufferedReader(fr);

                                BufferedReader readFile3 = new BufferedReader(new FileReader(".\\files\\all_shops.txt"));
                                int totalLines3 = 0;
                                while (readFile3.readLine() != null) {
                                    totalLines3++;
                                }
                                readFile3.close();

                                for (int k = 0; k < totalLines3; k++) {
                                    String linek = Files.readAllLines(Paths.get(".\\files\\all_shops.txt")).get(k);
                                    String linek1 = Files.readAllLines(Paths.get(".\\files\\all_shops.txt")).get(k + 1);
                                    String linek2 = Files.readAllLines(Paths.get(".\\files\\all_shops.txt")).get(k + 4);
                                    String linek3 = Files.readAllLines(Paths.get(".\\files\\all_shops.txt")).get(k + 2);
                                    if (linek.equals(typearr[count2]) && linek1.equals(sizearr[count2])
                                            && linek2.equals(quantityarr[count2]) && linek3.equals(shoparr[count2])) {
                                        count = k;
                                        break;
                                    }
                                }
                                int a = count;
                                int b = count + 1;
                                int c = count + 2;
                                int d = count + 3;
                                int f = count + 4;
                                int g = count + 5;
                                int h = count + 6;
                                while ((currentline = br.readLine()) != null) {
                                    l++;
                                    if (a != l && b != l && c != l && d != l && f != l && g != l && h != l) {
                                        printWriter2.println(currentline);
                                    } else {
                                        printWriter3.println(currentline);
                                    }
                                }

                                printWriter3.println(Login.USERNAME);

                                printWriter2.flush();
                                printWriter2.close();
                                fr.close();
                                br.close();
                                bufferedWriter2.close();
                                fileWriter2.close();
                                printWriter3.flush();
                                printWriter3.close();
                                bufferedWriter3.close();
                                fileWriter3.close();

                                oldFile.delete();
                                File dumb = new File(".\\files\\temp.txt");
                                dumb.createNewFile();
                                newFile.renameTo(dumb);
                                frame.setVisible(false);
                                new Skip2();
                                break;
                            }
                        }
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                }
            }
        } else if (e.getSource() == exitButton) {
            int yesORno = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit Confirmation",
                    JOptionPane.YES_NO_OPTION);

            if (yesORno == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        } else if (e.getSource() == backButton) {
            if (Login.loginFlag) {
                frame.setVisible(false);
                new CustomerHome();
            } else {
                frame.setVisible(false);
                new Login();
            }
        } else if (e.getSource() == logoutButton) {
            frame.setVisible(false);
            new Login();
        }
    }
}
