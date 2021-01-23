package hw9;

import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

import hw3.Box;
import hw8.CampusParser.MalformedDataException;
import hw8.CampusRouteModel;
/**
 * This class allow for user using the model-view-controller (MVC)
 * to find the shortest path from point A to point B on campus. 
 * @author Khai Tran
 *
 */
public class CampusPathsMain {
	/**
	 * Using MVC model to create an interface GUI for user to interact with program
	 * to find the shortest path..
	 * @param agrs
	 * @throws MalformedDataException
	 */
	
	public static void main(String[] agrs) throws MalformedDataException {
		CampusRouteModel model = new CampusRouteModel("campus_buildings.dat", "campus_paths.dat");
		if (model != null) {
			PathGUI(model);
		}
	}
	/**
	 * Generate the Graphic User Interface
	 * 
	 * @param model is the Campus Route Model
	 */
	public static void PathGUI(CampusRouteModel model) {
		if (model == null) {
			throw new IllegalArgumentException("Model should not null!");
		}
		final String winName = "Finding the Shortest Paths!";
		CampusRouteModel mdel = model;
		
		// Generate window
		JFrame win = new JFrame(winName);
		win.setPreferredSize(new Dimension(1024, 768));
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// set layout manager
		win.setLayout(new BoxLayout(win.getContentPane(), BoxLayout.Y_AXIS));
		
		// call map data view
		CampusRouteView dataView = new CampusRouteView(mdel);
		
		// call controller
		CampusRouteGUI controller = new CampusRouteGUI(mdel, dataView);
		controller.setPreferredSize(new Dimension(1024, 60));
		
		// add to window
		win.add(controller);
		win.add(dataView);
		
		// set visible frame
		win.pack();
		win.setVisible(true);	
	}
}
