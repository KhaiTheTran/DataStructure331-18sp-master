package hw9;

import java.awt.event.*;
import java.awt.*;
import java.util.TreeSet;

import javax.swing.*;

import hw8.CampusRouteModel;
/**
 * Campus Route GUI is a graphic user interface controller for finding the shortest paths
 * 
 * @author Khai Tran
 *
 */
public class CampusRouteGUI extends JPanel {
	private static final long serialVersionUID = 1L;
	private CampusRouteView dataview;
	
	private JLabel dis;
	
	private JLabel beginBuilding;
	private JComboBox<String> beginBox;
	private JLabel destBuilding;
	private JComboBox<String> destBox;
	
	/**
	 * Generate GUI for finding the shortest route 
	 * 
	 * @param model is the data tool for finding the shortest route
	 * 
	 * @param dataView is a tool that using JComponent for the GUI
	 * @required model and dataView is not null
	 */
	public CampusRouteGUI(CampusRouteModel mdel, CampusRouteView dataView) {
		
		if (dataView == null) {
			throw new IllegalArgumentException("Data view can not be null");
		}
		if (mdel == null) {
			throw new IllegalArgumentException("Model can not be null");
		}
		CampusRouteModel model;
		TreeSet<String> building;
		model = mdel;
		this.dataview = dataView;
		// get the full name of buildings
		building = new TreeSet<String>(model.getbuildingName().values());
		
		JPanel panel = new JPanel();
		
		JPanel but_pan = new JPanel();
		
		beginBuilding = new JLabel("Starting building:");
		beginBox = new JComboBox<String>(building.toArray(new String[0]));
		beginBox.setPreferredSize(new Dimension(250,20));
		destBuilding = new JLabel("Destination Buidling:  ");
		destBox = new JComboBox<String>(building.toArray(new String[0]));
		destBox.setPreferredSize(new Dimension(250,20));
			
		// add buttons for action listener
		JButton but = new JButton("Go");
		but.addActionListener(new RefreshActionListener());
		JButton reset = new JButton("Reset");
		reset.addActionListener(new RefreshActionListener());
		
		// add component for view
		panel.add(beginBuilding);
		panel.add(beginBox);
		panel.add(destBuilding);
		panel.add(destBox);
		
		but_pan.add(but);
		but_pan.add(reset);
		
		// add label and button to panel
		JPanel dis_pan = new JPanel();
		JPanel dis_pan2 = new JPanel();
		
		JLabel dis_lab = new JLabel(" Total distance: ");
		beginBuilding.setForeground(Color.BLUE);
		destBuilding.setForeground(Color.MAGENTA);
		
		dis = new JLabel();
		dis_pan.add(panel);
		dis_pan2.add(but_pan);
		dis_pan2.add(dis_lab);
		dis_pan2.add(dis);
		// add panel to frame
		JPanel gitpan = new JPanel(new GridLayout(2, 1));
		gitpan.add(dis_pan);
		gitpan.add(dis_pan2);
		gitpan.setPreferredSize(new Dimension(900,70));
		this.add(gitpan);
	}
	/**
	 * Refresh ActionListener used to update the new shortest route
	 * of two building
	 *
	 */
	private class RefreshActionListener implements ActionListener{
		/**
		 * Call action performed
		 * @param ActionEvent
		 */
		@Override
		public void actionPerformed(java.awt.event.ActionEvent e) {
			String act = e.getActionCommand();
			if (act.equals("Go")) {
				
				// when click "go" finding the shortest route
				String building_start = beginBox.getSelectedItem().toString();
				String building_dest =destBox.getSelectedItem().toString();
				Double path = dataview.GetShortestRoute(building_start, building_dest);
				
				dis.setText(String.format(" %.0f feet", path));
				dis.setForeground(Color.red);
			} else {
				dataview.setNull();
				dis.setText("");
			}
		}
		
		
	}
}
