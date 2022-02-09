import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class InputFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JPanel diamoniPanel;
	private JPanel allInclusivePanel;
	private JPanel buttonPanel;
	
	private JLabel diamoniLabel;
	private JLabel hotelLabel;
	private JLabel allInclusiveLabel;
	
	private JList listView;
	private DefaultListModel model;
	
	private JTextField daysField;
	private JTextField mealsField;

	private JButton storeButton;
	private JButton calculateCostButton;
	
	private JTextField costField;
	
	public InputFrame(ArrayList<Hotel> hotels) {

//		ArrayList<String> reservation = new ArrayList<String>();		
		ArrayList<Hotel> hotels1 = new ArrayList<Hotel>();
		
		panel = new JPanel();
		diamoniPanel = new JPanel();
		allInclusivePanel = new JPanel();
		buttonPanel = new JPanel();	
		
		listView = new JList();
		model = new DefaultListModel();
        
		
		

		for(int i=0; i<hotels.size(); i++)
		{	
			model.addElement(hotels.get(i).getName());
			
		}
		
		
		
		listView.setModel(model);
		
		diamoniLabel = new JLabel("Stoixeia Diamonis");
		hotelLabel = new JLabel("Hotel");
		allInclusiveLabel = new JLabel("AllInclusive");
		
		daysField = new JTextField("Hmeres Diamonis");
		mealsField = new JTextField("Plithos Geumatwn (1,2,3)");
		costField = new JTextField("Synoliko Kostos");
		
		storeButton = new JButton("Apothikeusi Kratisis");
		calculateCostButton = new JButton("Ypologismos Kostous");
		
		diamoniPanel.setLayout(new BoxLayout(diamoniPanel, BoxLayout.Y_AXIS));
		diamoniLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		diamoniPanel.add(diamoniLabel);
		hotelLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		diamoniPanel.add(hotelLabel);
		diamoniPanel.add(listView);
		diamoniPanel.add(daysField);
		diamoniPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		allInclusivePanel.setLayout(new GridLayout(2,0));
		allInclusivePanel.add(allInclusiveLabel);
		allInclusivePanel.add(mealsField);
		allInclusivePanel.setBorder(BorderFactory.createLineBorder(Color.black));

		storeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//apothikefsi kratisis
				//vale lista me tis kratisis j meta emfanizeta me to koumpi pou kato 
				
				
				if(daysField.getText().matches("-?\\d+") || mealsField.getText().matches("-?\\d+"))
				{
					if(!listView.isSelectionEmpty()) 
					{
						int daysNumber = Integer.valueOf(daysField.getText()).intValue();
						int mealsNumber = Integer.valueOf(mealsField.getText()).intValue();
						int total = daysNumber*(120+mealsNumber*30);
						
						
// magic tip  reservation.add(listView.getSelectedValue()+ " " + Integer.toString(total));	
						
						
						
						
//prostheto se ena neo arraylist<Hotel1> to hotel to opio dimiourgisa mazi 
// me to sinoliko poso pou tha plirosi o pelatis etsi apothikevete
//kai mporo na to kalo parakato
						
						Hotel hotel1 = new Hotel(listView.getSelectedValue().toString(),total);	
						hotels1.add(hotel1);
						
						System.out.println(hotel1.printInfo());
							
						
					}
					else {
						System.out.println("select a hotel to proceed");
					}
				}
		
				
				try
				{
					Integer.parseInt(daysField.getText());
					Integer.parseInt(mealsField.getText());
				}
				catch (NumberFormatException e1) 
				{
					System.out.println("GIVE A NUMBER");
				}
				
				
				
				
				
			}
			
		});
		calculateCostButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//ipologismos kostous
				int totalCostValue = 0 ;

						for(Hotel hott: hotels1)
						{
							if(hott.getName().contentEquals(listView.getSelectedValue().toString())) {
								
								totalCostValue += hott.getPrice();	

							}
						}
				
				System.out.println(totalCostValue);
				
				String total = Integer.toString(totalCostValue);	

				costField.setText(listView.getSelectedValue().toString()+": \u20ac" +total);

				
			}
			
		});
		
		
		
		
		buttonPanel.setLayout(new GridLayout(2,0));
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
	

	class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
		}
	}

}
