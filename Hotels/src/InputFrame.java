import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class InputFrame extends JFrame {
    private DefaultListModel<String> model;
    private JTextField daysField;
    private JTextField mealsField;
    private JTextField costField;

    private static final int BASE_HOTEL_COST = 120;
    private static final int MEAL_COST = 30;

    public InputFrame(ArrayList<Hotel> hotels) {
        model = new DefaultListModel<>();
        JList<String> listView = new JList<>(model);

        JPanel panel = new JPanel();
        JPanel diamoniPanel = new JPanel();
        JPanel allInclusivePanel = new JPanel();
        JPanel buttonPanel = new JPanel();

        JLabel diamoniLabel = new JLabel("Residency details");
        JLabel hotelLabel = new JLabel("Hotel");
        JLabel allInclusiveLabel = new JLabel("AllInclusive");

        daysField = new JTextField("Staying days");
        mealsField = new JTextField("Number of meals (1,2,3)");
        costField = new JTextField("Total Cost");

        JButton storeButton = new JButton("Apothikeusi Kratisis");
        JButton calculateCostButton = new JButton("Ypologismos Kostous");

        model.addAll(hotels.stream().map(Hotel::getName).toArray(String[]::new));
        listView.setModel(model);

        diamoniPanel.setLayout(new BoxLayout(diamoniPanel, BoxLayout.Y_AXIS));
        diamoniLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        diamoniPanel.add(diamoniLabel);
        hotelLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        diamoniPanel.add(hotelLabel);
        diamoniPanel.add(listView);
        diamoniPanel.add(daysField);
        diamoniPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        allInclusivePanel.setLayout(new GridLayout(2, 0));
        allInclusivePanel.add(allInclusiveLabel);
        allInclusivePanel.add(mealsField);
        allInclusivePanel.setBorder(BorderFactory.createLineBorder(Color.black));

        storeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (isValidInput()) {
                    int total = calculateTotalCost();
                    Hotel hotel = new Hotel(listView.getSelectedValue(), total);
                    hotels.add(hotel);
                    System.out.println(hotel.printInfo());
                }
            }
        });

        calculateCostButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int totalCost = calculateTotalCostForSelectedHotel(hotels);
                costField.setText(listView.getSelectedValue() + ": \u20ac" + totalCost);
            }
        });

        buttonPanel.setLayout(new GridLayout(2, 0));
        buttonPanel.add(storeButton);
        buttonPanel.add(calculateCostButton);
        buttonPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        panel.add(diamoniPanel);
        panel.add(allInclusivePanel);
        panel.add(buttonPanel);
        panel.add(costField);

        this.setContentPane(panel);

        this.setVisible(true);
        this.setSize(200, 320);
        this.setLocation(200, 0);
        this.setTitle("Input");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private boolean isValidInput() {
        try {
            int days = Integer.parseInt(daysField.getText());
            int meals = Integer.parseInt(mealsField.getText());
            return !listView.isSelectionEmpty() && days >= 0 && meals >= 0;
        } catch (NumberFormatException ex) {
            System.out.println("Please enter valid numbers for days and meals.");
            return false;
        }
    }

    private int calculateTotalCost() {
        int days = Integer.parseInt(daysField.getText());
        int meals = Integer.parseInt(mealsField.getText());
        return days * (BASE_HOTEL_COST + meals * MEAL_COST);
    }

    private int calculateTotalCostForSelectedHotel(ArrayList<Hotel> hotels) {
        String selectedHotel = listView.getSelectedValue();
        return hotels.stream()
                .filter(hotel -> hotel.getName().equals(selectedHotel))
                .mapToInt(Hotel::getPrice)
                .sum();
    }

    public static void main(String[] args) {
        // Sample hotel data
        ArrayList<Hotel> hotels = new ArrayList<>();
        hotels.add(new Hotel("Hotel A", 0));
        hotels.add(new Hotel("Hotel B", 0));

        SwingUtilities.invokeLater(() -> new InputFrame(hotels));
    }
}
