
import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    private JTextField weightField, heightField;
    private JLabel resultValueLabel, statusLabel;

    public Main() {

        setTitle("kalkulator");
        setSize(750, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(10, 10, 10, 10));


        add(new JLabel("waga"));
        weightField = new JTextField();
        add(weightField);

        add(new JLabel("wzrost"));
        heightField = new JTextField();
        add(heightField);

        JButton calculateButton = new JButton("Oblicz");
        add(calculateButton);

        resultValueLabel = new JLabel("---", SwingConstants.CENTER);
        resultValueLabel.setFont(new Font("calibri", Font.BOLD, 14));
        add(resultValueLabel);

        add(new JLabel("status"));
        statusLabel = new JLabel("");
        add(statusLabel);


        calculateButton.addActionListener(e -> calculateBMI());

        getRootPane().setDefaultButton(calculateButton);
    }

    private void calculateBMI() {
        try {
            double weight = Double.parseDouble(weightField.getText().replace(",", "."));
            double heightM = Double.parseDouble(heightField.getText().replace(",", ".")) / 100.0;

            if (heightM <= 0 || weight <= 0) throw new NumberFormatException();

            double bmi = weight / (heightM * heightM);
            resultValueLabel.setText(String.format("%.2f", bmi));
            interpretBMI(bmi);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Wpisz poprawne dane", "blad", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void interpretBMI(double bmi) {
        String status;
        Color color;

        if (bmi < 18.5) {
            status = "Niedowaga";
            color = Color.BLUE;
        } else if (bmi < 25) {
            status = "Norma";
            color = new Color(0, 150, 0);
        } else if (bmi < 30) {
            status = "Nadwaga";
            color = Color.ORANGE;
        } else {
            status = "Otyłość";
            color = Color.RED;
        }

        statusLabel.setText(status);
        statusLabel.setForeground(color);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}