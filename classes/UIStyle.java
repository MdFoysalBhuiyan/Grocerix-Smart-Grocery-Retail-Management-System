package classes;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public class UIStyle {

    // Color Palette - Classic Modern Dark Theme
    public static final Color BG_DARK = Color.decode("#0F172A");       // Main background (Deep Slate)
    public static final Color PANEL_BG = Color.decode("#1E293B");      // Card/Panel background
    public static final Color PANEL_BORDER = Color.decode("#334155");  // Panel border outline
    public static final Color INPUT_BG = Color.decode("#0F172A");      // Input field background
    public static final Color INPUT_BORDER = Color.decode("#475569");  // Input border outline
    public static final Color INPUT_FOCUS = Color.decode("#3B82F6");   // Input focus border ring

    public static final Color COLOR_PRIMARY = Color.decode("#2563EB");  // Vibrant Sapphire Blue
    public static final Color COLOR_PRIMARY_HOVER = Color.decode("#1D4ED8");
    public static final Color COLOR_SECONDARY = Color.decode("#475569");
    public static final Color COLOR_SECONDARY_HOVER = Color.decode("#334155");
    public static final Color COLOR_SUCCESS = Color.decode("#10B981");  // Emerald
    public static final Color COLOR_SUCCESS_HOVER = Color.decode("#059669");
    public static final Color COLOR_DANGER = Color.decode("#EF4444");   // Red
    public static final Color COLOR_DANGER_HOVER = Color.decode("#DC2626");
    public static final Color COLOR_WARNING = Color.decode("#F59E0B");  // Amber
    public static final Color COLOR_ACCENT = Color.decode("#8B5CF6");   // Purple accent

    public static final Color TEXT_PRIMARY = Color.decode("#F8FAFC");    // Bright Crisp White
    public static final Color TEXT_SECONDARY = Color.decode("#94A3B8");  // Soft Silver
    public static final Color TEXT_MUTED = Color.decode("#64748B");      // Slate Muted

    // Typography
    public static final Font FONT_TITLE = new Font("Segoe UI", Font.BOLD, 22);
    public static final Font FONT_HEADER = new Font("Segoe UI", Font.BOLD, 18);
    public static final Font FONT_SUBHEADER = new Font("Segoe UI", Font.BOLD, 15);
    public static final Font FONT_BODY = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font FONT_BODY_BOLD = new Font("Segoe UI", Font.BOLD, 13);
    public static final Font FONT_SMALL = new Font("Segoe UI", Font.PLAIN, 11);
    public static final Font FONT_BUTTON = new Font("Segoe UI", Font.BOLD, 13);

    // Global Initialization
    public static void init() {
        System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("swing.aatext", "true");
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception ignored) {}
    }

    // Custom Anti-aliased Rounded Panel
    public static class RoundedPanel extends JPanel {
        private int cornerRadius;
        private Color backgroundColor;
        private Color borderColor;

        public RoundedPanel(int radius, Color bg) {
            this(radius, bg, null);
        }

        public RoundedPanel(int radius, Color bg, Color border) {
            super();
            this.cornerRadius = radius;
            this.backgroundColor = bg;
            this.borderColor = border;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            // Draw background
            g2.setColor(backgroundColor != null ? backgroundColor : getBackground());
            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);

            // Draw border if set
            if (borderColor != null) {
                g2.setColor(borderColor);
                g2.setStroke(new BasicStroke(1.2f));
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);
            }
            g2.dispose();
        }
    }

    // Custom Anti-aliased Modern Button
    public static class ModernButton extends JButton {
        private Color normalBg;
        private Color hoverBg;
        private Color pressedBg;
        private boolean isHovered = false;
        private boolean isPressed = false;
        private int cornerRadius = 8;

        public ModernButton(String text) {
            this(text, COLOR_PRIMARY, COLOR_PRIMARY_HOVER);
        }

        public ModernButton(String text, Color bg, Color hover) {
            super(text);
            this.normalBg = bg;
            this.hoverBg = hover;
            this.pressedBg = hover.darker();
            setFont(FONT_BUTTON);
            setForeground(TEXT_PRIMARY);
            setFocusPainted(false);
            setBorderPainted(false);
            setContentAreaFilled(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    isHovered = true;
                    repaint();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    isHovered = false;
                    isPressed = false;
                    repaint();
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    isPressed = true;
                    repaint();
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    isPressed = false;
                    repaint();
                }
            });
        }

        public void setCornerRadius(int radius) {
            this.cornerRadius = radius;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            Color currentBg = normalBg;
            if (isPressed) {
                currentBg = pressedBg;
            } else if (isHovered) {
                currentBg = hoverBg;
            }

            g2.setColor(currentBg);
            g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius));

            g2.dispose();
            super.paintComponent(g);
        }
    }

    // Custom Anti-aliased Modern Text Field
    public static class ModernTextField extends JTextField {
        private String placeholder = "";
        private boolean isFocused = false;
        private int cornerRadius = 8;

        public ModernTextField() {
            this("");
        }

        public ModernTextField(String placeholder) {
            this.placeholder = placeholder;
            setFont(FONT_BODY);
            setForeground(TEXT_PRIMARY);
            setCaretColor(TEXT_PRIMARY);
            setOpaque(false);
            setBorder(new EmptyBorder(6, 12, 6, 12));

            addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    isFocused = true;
                    repaint();
                }

                @Override
                public void focusLost(FocusEvent e) {
                    isFocused = false;
                    repaint();
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Fill background
            g2.setColor(INPUT_BG);
            g2.fill(new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius));

            // Draw border outline
            g2.setColor(isFocused ? INPUT_FOCUS : INPUT_BORDER);
            g2.setStroke(new BasicStroke(isFocused ? 1.5f : 1.0f));
            g2.draw(new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius));

            g2.dispose();
            super.paintComponent(g);

            // Paint placeholder text if empty
            if (getText().isEmpty() && !placeholder.isEmpty() && !isFocused) {
                Graphics2D gPlaceholder = (Graphics2D) g.create();
                gPlaceholder.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                gPlaceholder.setColor(TEXT_MUTED);
                gPlaceholder.setFont(FONT_BODY);
                FontMetrics fm = gPlaceholder.getFontMetrics();
                int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
                gPlaceholder.drawString(placeholder, 12, y);
                gPlaceholder.dispose();
            }
        }
    }

    // Custom Anti-aliased Modern Password Field
    public static class ModernPasswordField extends JPasswordField {
        private String placeholder = "";
        private boolean isFocused = false;
        private int cornerRadius = 8;

        public ModernPasswordField() {
            this("");
        }

        public ModernPasswordField(String placeholder) {
            this.placeholder = placeholder;
            setFont(FONT_BODY);
            setForeground(TEXT_PRIMARY);
            setCaretColor(TEXT_PRIMARY);
            setOpaque(false);
            setEchoChar('•');
            setBorder(new EmptyBorder(6, 12, 6, 12));

            addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    isFocused = true;
                    repaint();
                }

                @Override
                public void focusLost(FocusEvent e) {
                    isFocused = false;
                    repaint();
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Fill background
            g2.setColor(INPUT_BG);
            g2.fill(new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius));

            // Draw border outline
            g2.setColor(isFocused ? INPUT_FOCUS : INPUT_BORDER);
            g2.setStroke(new BasicStroke(isFocused ? 1.5f : 1.0f));
            g2.draw(new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius));

            g2.dispose();
            super.paintComponent(g);

            // Paint placeholder text if empty
            if (getPassword().length == 0 && !placeholder.isEmpty() && !isFocused) {
                Graphics2D gPlaceholder = (Graphics2D) g.create();
                gPlaceholder.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                gPlaceholder.setColor(TEXT_MUTED);
                gPlaceholder.setFont(FONT_BODY);
                FontMetrics fm = gPlaceholder.getFontMetrics();
                int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
                gPlaceholder.drawString(placeholder, 12, y);
                gPlaceholder.dispose();
            }
        }
    }

    // Modern JTable Styling
    public static void styleTable(JTable table) {
        table.setBackground(PANEL_BG);
        table.setForeground(TEXT_PRIMARY);
        table.setFont(FONT_BODY);
        table.setRowHeight(36);
        table.setGridColor(PANEL_BORDER);
        table.setShowGrid(true);
        table.setSelectionBackground(COLOR_PRIMARY);
        table.setSelectionForeground(TEXT_PRIMARY);

        // Header style
        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.decode("#182234"));
        header.setForeground(TEXT_PRIMARY);
        header.setFont(FONT_BODY_BOLD);
        header.setReorderingAllowed(false);
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 38));

        // Alternating row renderer
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable t, Object val, boolean isSelected, boolean hasFocus, int row, int col) {
                Component c = super.getTableCellRendererComponent(t, val, isSelected, hasFocus, row, col);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? PANEL_BG : Color.decode("#182234"));
                    c.setForeground(TEXT_PRIMARY);
                } else {
                    c.setBackground(COLOR_PRIMARY);
                    c.setForeground(TEXT_PRIMARY);
                }
                setBorder(new EmptyBorder(0, 10, 0, 10));
                return c;
            }
        };

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }
    }

    // Modern ScrollPane Styling
    public static void styleScrollPane(JScrollPane scrollPane) {
        scrollPane.getViewport().setBackground(PANEL_BG);
        scrollPane.setBorder(BorderFactory.createLineBorder(PANEL_BORDER, 1));
        scrollPane.getVerticalScrollBar().setBackground(PANEL_BG);
        scrollPane.getHorizontalScrollBar().setBackground(PANEL_BG);
    }
}
