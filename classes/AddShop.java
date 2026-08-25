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

public class AddShop implements ActionListener {
    private Container c;
    private JFrame frame;
    private JLabel type;
    private JLabel size;
    private JLabel shopNo;
    private JLabel rent;
    private JLabel place;
    private JLabel max;
    private JLabel imgLabel2;

    private UIStyle.ModernTextField attach;
    private UIStyle.ModernTextField typeField;
    private UIStyle.ModernTextField rentField;
    private UIStyle.ModernTextField sizeField;
    private UIStyle.ModernTextField shopField;
    private UIStyle.ModernTextField placeField;

    private UIStyle.ModernButton exitButton;
    private UIStyle.ModernButton attachButton;
    private UIStyle.ModernButton submitButton;
    private UIStyle.ModernButton backButton;
    private UIStyle.ModernButton logoutButton;

    private UIStyle.RoundedPanel panel;
    private Cursor cursor;

    public AddShop() {
        frame = new JFrame();
        frame.setBounds(50, 50, 850, 550);
        frame.setTitle("Grocery Shop Management - Add New Shop");
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
        JLabel addTitle = new JLabel("Add New Shop Details");
        addTitle.setBounds(40, 20, 350, 32);
        addTitle.setFont(UIStyle.FONT_TITLE);
        addTitle.setForeground(UIStyle.TEXT_PRIMARY);
        frame.add(addTitle);

        // --- LEFT FORM CARD ---
        UIStyle.RoundedPanel formCard = new UIStyle.RoundedPanel(16, UIStyle.PANEL_BG, UIStyle.PANEL_BORDER);
        formCard.setBounds(40, 65, 450, 420);
        formCard.setLayout(null);
        frame.add(formCard);

        // Type
        type = new JLabel("Shop Category / Type");
        type.setBounds(25, 20, 200, 18);
        type.setFont(UIStyle.FONT_BODY_BOLD);
        type.setForeground(UIStyle.TEXT_PRIMARY);
        formCard.add(type);

        typeField = new UIStyle.ModernTextField("e.g. Super Shop");
        typeField.setBounds(25, 40, 400, 34);
        formCard.add(typeField);

        // Location / Place
        place = new JLabel("Location / Place");
        place.setBounds(25, 82, 200, 18);
        place.setFont(UIStyle.FONT_BODY_BOLD);
        place.setForeground(UIStyle.TEXT_PRIMARY);
        formCard.add(place);

        placeField = new UIStyle.ModernTextField("e.g. Level 1, Block A");
        placeField.setBounds(25, 102, 400, 34);
        formCard.add(placeField);

        // Size & Shop No in 2 columns
        size = new JLabel("Size (Sq. Ft.)");
        size.setBounds(25, 144, 180, 18);
        size.setFont(UIStyle.FONT_BODY_BOLD);
        size.setForeground(UIStyle.TEXT_PRIMARY);
        formCard.add(size);

        sizeField = new UIStyle.ModernTextField("500");
        sizeField.setBounds(25, 164, 190, 34);
        formCard.add(sizeField);

        shopNo = new JLabel("Shop Number");
        shopNo.setBounds(235, 144, 180, 18);
        shopNo.setFont(UIStyle.FONT_BODY_BOLD);
        shopNo.setForeground(UIStyle.TEXT_PRIMARY);
        formCard.add(shopNo);

        shopField = new UIStyle.ModernTextField("S-101");
        shopField.setBounds(235, 164, 190, 34);
        formCard.add(shopField);

        // Rent
        rent = new JLabel("Monthly Rent (TK)");
        rent.setBounds(25, 206, 200, 18);
        rent.setFont(UIStyle.FONT_BODY_BOLD);
        rent.setForeground(UIStyle.TEXT_PRIMARY);
        formCard.add(rent);

        rentField = new UIStyle.ModernTextField("15000");
        rentField.setBounds(25, 226, 400, 34);
        formCard.add(rentField);

        // Action buttons inside form card
        submitButton = new UIStyle.ModernButton("Save Shop Details", UIStyle.COLOR_SUCCESS, UIStyle.COLOR_SUCCESS_HOVER);
        submitButton.setBounds(25, 280, 400, 42);
        formCard.add(submitButton);
        submitButton.addActionListener(this);

        backButton = new UIStyle.ModernButton("← Back to Dashboard", UIStyle.COLOR_SECONDARY, UIStyle.COLOR_SECONDARY_HOVER);
        backButton.setBounds(25, 332, 400, 36);
        formCard.add(backButton);
        backButton.addActionListener(this);

        // --- RIGHT IMAGE ATTACHMENT CARD ---
        UIStyle.RoundedPanel rightCard = new UIStyle.RoundedPanel(16, UIStyle.PANEL_BG, UIStyle.PANEL_BORDER);
        rightCard.setBounds(510, 65, 300, 420);
        rightCard.setLayout(null);
        frame.add(rightCard);

        JLabel imgHeader = new JLabel("Shop Photo Preview", SwingConstants.CENTER);
        imgHeader.setBounds(15, 15, 270, 22);
        imgHeader.setFont(UIStyle.FONT_BODY_BOLD);
        imgHeader.setForeground(UIStyle.TEXT_PRIMARY);
        rightCard.add(imgHeader);

        panel = new UIStyle.RoundedPanel(8, UIStyle.INPUT_BG, UIStyle.INPUT_BORDER);
        panel.setBounds(30, 45, 240, 240);
        panel.setLayout(null);
        rightCard.add(panel);

        imgLabel2 = new JLabel("No Image Selected", SwingConstants.CENTER);
        imgLabel2.setFont(UIStyle.FONT_SMALL);
        imgLabel2.setForeground(UIStyle.TEXT_MUTED);
        imgLabel2.setBounds(0, 0, 240, 240);
        panel.add(imgLabel2);

        attach = new UIStyle.ModernTextField("Attachment Path");
        attach.setBounds(20, 295, 260, 34);
        attach.setEditable(false);
        rightCard.add(attach);

        attachButton = new UIStyle.ModernButton("Select Photo", UIStyle.COLOR_PRIMARY, UIStyle.COLOR_PRIMARY_HOVER);
        attachButton.setBounds(20, 340, 260, 38);
        rightCard.add(attachButton);
        attachButton.addActionListener(this);

        // Header Top Actions
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
        String type1 = typeField.getText();
        String size1 = sizeField.getText();
        String rent1 = rentField.getText();
        String shop1 = shopField.getText();
        String place1 = placeField.getText();
        String attach1 = attach.getText();

        boolean typeEmpty = type1.isEmpty();
        boolean sizeEmpty = size1.isEmpty();
        boolean rentEmpty = rent1.isEmpty();
        boolean shopEmpty = shop1.isEmpty();
        boolean placeEmpty = place1.isEmpty();
        boolean attachEmpty = attach1.isEmpty();

        if (e.getSource() == submitButton) {
            if (!typeEmpty && !sizeEmpty && !rentEmpty && !shopEmpty && !placeEmpty && !attachEmpty) {
                try {
                    int n = Integer.parseInt(rent1);
                    String line = ".\\files\\all_shops.txt";
                    try {
                        File file = new File(line);
                        if (!file.exists()) {
                            file.createNewFile();
                            FileWriter fileWriter = new FileWriter(file, true);
                            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                            PrintWriter printWriter = new PrintWriter(bufferedWriter);
                            printWriter.close();
                        }

                        BufferedReader readFile3 = new BufferedReader(new FileReader(".\\files\\all_shops.txt"));
                        int totalLines3 = 0;
                        while (readFile3.readLine() != null) {
                            totalLines3++;
                        }
                        readFile3.close();

                        boolean flag = true;
                        for (int k = 0; k < totalLines3; k++) {
                            String linek = Files.readAllLines(Paths.get(".\\files\\all_shops.txt")).get(k);
                            if (linek.equals(type1)) {
                                flag = false;
                                break;
                            }
                        }
                        if (flag) {
                            String image = "";
                            String p = attach1;
                            char ch;
                            for (int i = attach1.length() - 1; i >= 0; i--) {
                                if (p.charAt(i) == '\\') {
                                    break;
                                } else {
                                    ch = p.charAt(i);
                                    image = ch + image;
                                }
                            }
                            image = "images/" + image;
                            FileWriter fileWriter = new FileWriter(file, true);
                            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                            PrintWriter printWriter = new PrintWriter(bufferedWriter);

                            printWriter.println("Shop Details");
                            printWriter.println(type1);
                            printWriter.println(size1);
                            printWriter.println(shop1);
                            printWriter.println(rent1);
                            printWriter.println(place1);
                            printWriter.println(image);
                            printWriter.println();
                            printWriter.close();

                            showMessageDialog(null, "Shop added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            frame.setVisible(false);
                            new AdminHome();
                        } else {
                            showMessageDialog(null, "A shop with the same category already exists!", "Error",
                                    JOptionPane.WARNING_MESSAGE);
                        }
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                } catch (Exception ex) {
                    showMessageDialog(null, "Invalid Rent Field (Must be numeric)", "Error",
                            JOptionPane.WARNING_MESSAGE);
                }

            } else if (!typeEmpty && !sizeEmpty && !rentEmpty && !shopEmpty && !placeEmpty && attachEmpty) {
                showMessageDialog(null, "Please select/attach a photo for the shop.", "Error",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                showMessageDialog(null, "Please fill in all the required fields.", "Error",
                        JOptionPane.WARNING_MESSAGE);
            }
        } else if (e.getSource() == attachButton) {
            try {
                JFileChooser chooser = new JFileChooser();
                chooser.showOpenDialog(null);
                File f = chooser.getSelectedFile();
                if (f != null) {
                    String filename = f.getAbsolutePath();
                    attach.setText(filename);
                    ImageIcon rawIcon = new ImageIcon(filename);
                    Image scaled = rawIcon.getImage().getScaledInstance(240, 240, Image.SCALE_SMOOTH);
                    imgLabel2.setText("");
                    imgLabel2.setIcon(new ImageIcon(scaled));
                    imgLabel2.setBounds(0, 0, 240, 240);
                }
            } catch (Exception ex) {
                return;
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
        } else if (e.getSource() == backButton) {
            frame.setVisible(false);
            new AdminHome();
        }
    }
}