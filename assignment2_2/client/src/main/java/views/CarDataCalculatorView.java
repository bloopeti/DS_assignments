package views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;

public class CarDataCalculatorView extends JFrame {

    private JPanel contentPane;
    private JTextField textYear;
    private JTextField textEngineCapacity;
    private JTextField textPurchasingPrice;
    private JButton btnTax;
    private JButton btnSellingPrice;
    private JTextArea textArea;


    public CarDataCalculatorView() {
        setTitle("Car Data Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300, 100, 450, 350);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblInsertNewStudent = new JLabel("Insert car data");
        lblInsertNewStudent.setBounds(10, 10, 120, 14);
        contentPane.add(lblInsertNewStudent);

        JLabel lblYear = new JLabel("Manufacturing year");
        lblYear.setBounds(10, 35, 200, 15);
        contentPane.add(lblYear);

        textYear = new JTextField();
        textYear.setBounds(10, 60, 200, 20);
        contentPane.add(textYear);
        textYear.setColumns(10);

        JLabel lblEngineCapacity = new JLabel("Engine capacity");
        lblEngineCapacity.setBounds(10, 85, 200, 15);
        contentPane.add(lblEngineCapacity);

        textEngineCapacity = new JTextField();
        textEngineCapacity.setBounds(10, 110, 200, 20);
        contentPane.add(textEngineCapacity);
        textEngineCapacity.setColumns(10);

        JLabel lblPurchasingPrice = new JLabel("Purchasing Price");
        lblPurchasingPrice.setBounds(10, 135, 200, 15);
        contentPane.add(lblPurchasingPrice);

        textPurchasingPrice = new JTextField();
        textPurchasingPrice.setBounds(10, 160, 200, 20);
        contentPane.add(textPurchasingPrice);
        textPurchasingPrice.setColumns(10);

        btnTax = new JButton("Calculate Tax");
        btnTax.setBounds(10, 190, 200, 23);
        contentPane.add(btnTax);

        btnSellingPrice = new JButton("Calculate Selling Price");
        btnSellingPrice.setBounds(10, 220, 200, 23);
        contentPane.add(btnSellingPrice);

        textArea = new JTextArea();
        textArea.setBounds(235, 10, 171, 250);
        contentPane.add(textArea);
    }

    public void addBtnTaxActionListener(ActionListener e) {
        btnTax.addActionListener(e);
    }

    public void addBtnSellingPriceActionListener(ActionListener e) {
        btnSellingPrice.addActionListener(e);
    }

    public String getCarYear() {
        return textYear.getText();
    }

    public String getCarEngineCapacity() {
        return textEngineCapacity.getText();
    }

    public String getCarPurchasingPrice() {
        return textPurchasingPrice.getText();
    }

    public void printResult(String s) {
        textArea.append(s + "\n");
    }
}
