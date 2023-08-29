import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class ForestManagementSystemGUI extends JFrame {
    private JButton payParkingButton;
    private JButton recordHikeButton;
    private JTextField visitorIDField;
    
    // Database connection details
    private final String dbUrl = "jdbc:mysql://localhost/ForestManagement";
    private final String dbUser = "your_db_username";
    private final String dbPassword = "your_db_password";

    public ForestManagementSystemGUI() {
        setTitle("Forest Management System");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2));

        add(new JLabel("Visitor ID:"));
        visitorIDField = new JTextField();
        add(visitorIDField);

        payParkingButton = new JButton("Pay for Parking");
        payParkingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int visitorID = Integer.parseInt(visitorIDField.getText());
                payForParking(visitorID);
            }
        });
        add(payParkingButton);

        recordHikeButton = new JButton("Record Hike");
        recordHikeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int visitorID = Integer.parseInt(visitorIDField.getText());
                recordHike(visitorID);
            }
        });
        add(recordHikeButton);

        setVisible(true);
    }

    private void payForParking(int visitorID) {
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            String query = "INSERT INTO PaymentHistory (user_id, payment_type, amount, payment_date) VALUES (?, ?, ?, NOW())";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, visitorID);
                preparedStatement.setString(2, "Parking");
                preparedStatement.setDouble(3, 10.00);  // Sample parking fee
                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(this, "Parking payment recorded.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void recordHike(int visitorID) {
        // Similar logic to payForParking, but with hike details
        // You can implement recording hike information in a similar manner
    }
}

public class ForestManagementSystem {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ForestManagementSystemGUI();
        });
    }
}
