// Programmer Identifier: Rein Alexander A. Vocal 25-1823-197
// Student Record System - Java Swing (Chrome Hearts Fire Theme)

import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Main extends JFrame {
    private DefaultTableModel model;
    private JTable table;
    private JTextField idField, nameField, gradeField;

    public Main() {
        this.setTitle("Records - Rein Alexander A. Vocal 25-1823-197 ");
        this.setSize(700, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        model = new DefaultTableModel(new String[]{"ID", "Name", "Grade"}, 0);
        table = new JTable(model);
        table.setBackground(Color.BLACK);
        table.setForeground(Color.ORANGE);
        table.setFont(new Font("Serif", Font.BOLD, 14));
        loadCSV();

        idField = new JTextField(5);
        nameField = new JTextField(10);
        gradeField = new JTextField(5);

        JButton addBtn = new JButton("🔥 Add");
        JButton delBtn = new JButton("✖ Delete");

        addBtn.addActionListener(e -> {
            if (!idField.getText().isEmpty() && !nameField.getText().isEmpty() && !gradeField.getText().isEmpty()) {
                model.addRow(new Object[]{idField.getText(), nameField.getText(), gradeField.getText()});
                idField.setText(""); nameField.setText(""); gradeField.setText("");
            }
        });

        delBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) model.removeRow(row);
        });

        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(Color.BLACK);
        inputPanel.add(new JLabel("ID:")); inputPanel.add(idField);
        inputPanel.add(new JLabel("Name:")); inputPanel.add(nameField);
        inputPanel.add(new JLabel("Grade:")); inputPanel.add(gradeField);
        inputPanel.add(addBtn); inputPanel.add(delBtn);

        this.add(new JScrollPane(table), BorderLayout.CENTER);
        this.add(inputPanel, BorderLayout.SOUTH);
    }

    private void loadCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader("MOCK_DATA.csv"))) {
            String line; br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    String id = parts[0];
                    String name = parts[1] + " " + parts[2];
                    String grade = parts[3]; // LAB WORK 1
                    model.addRow(new Object[]{id, name, grade});
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading CSV: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}