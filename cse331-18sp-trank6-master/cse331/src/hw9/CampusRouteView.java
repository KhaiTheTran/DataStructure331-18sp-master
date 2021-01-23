package hw9;


import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import hw8.*;
/**
 * Using JPanel to generate GUI for finding the shortest paths
 * 
 * @author Khai Tran
 *
 */
public class CampusRouteView extends JComponent {
	private static final long serialVersionUID = 1L;
	private int width_image;
	private int height_image;
	private double width_i_actual;
	private double height_i_actual;
	
	private CampusRouteModel model;
	
	// get shortest route
	private Map<Coordinate, Double> Shortest_route;
	
	private ArrayList<Coordinate> coor;
	
	private BufferedImage buf_I;
	/**
	 * Generate a GUI for finding shortest route
	 * @param model is the Campus Route Model
	 * @required model is not null
	 */
	public CampusRouteView (CampusRouteModel model) {
		this.model = model;
		
		width_image = 1009;
		height_image = 620;
		
		this.setPreferredSize(new Dimension(width_image, height_image));
		
		// get image
		try {
			buf_I = ImageIO.read(new File("src/hw8/data/campus_map.jpg"));
		} catch (IOException e) {
			// TODO: handle exception
			System.err.println(e);
			e.printStackTrace();
		}
		// set null for data of Map and coordinate
		Shortest_route = null;
		coor = null;
		
	}
	/**
	 * set dataset of the route to be null
	 */
	public void setNull() {
		Shortest_route = null;
		repaint();
	}
	/**
	 * Getting the shortest paths from A to B
	 * @param Start is the start building
	 * @param destination is the destination
	 * @return the distance of the shortest route
	 */
	public Double GetShortestRoute(String Start, String destination) {
		Coordinate location_start = model.getLoactionB(model.getAbbreviatedBuidingName(Start));
		Coordinate location_Dest = model.getLoactionB(model.getAbbreviatedBuidingName(destination));
		// Get date to map
		Shortest_route = model.findShortestRoute(location_start, location_Dest);
		coor = new ArrayList<Coordinate>(Shortest_route.keySet());
		Double dis = Shortest_route.get(coor.get(coor.size() - 1));
		repaint();
		return dis;
	}
	/**
	 * Repaint the simulation and requesting each item to repaint
	 * @param g is the graphics context where the painting paint
	 * 
	 */
	@Override
	public void paintComponent(Graphics g) {
	
		Graphics2D G2d= (Graphics2D)g;
		 super.paintComponent(g);
		width_image = getWidth();
		height_image = getHeight();
		// get the coordinate of the route on map
		width_i_actual = width_image / 1.0/ buf_I.getWidth() ;
		height_i_actual = height_image / 1.0/ buf_I.getHeight();
		// draw 2D map
		G2d.drawImage(buf_I, 0, 0, width_image, height_image, 0, 0, buf_I.getWidth(), buf_I.getHeight(), null);
		
		if (Shortest_route != null) {
			coor = new ArrayList<Coordinate>(Shortest_route.keySet());
			// get the coordinate x and y
			int begin_x = (int) Math.round(coor.get(0).getX()*width_i_actual);
			int begin_y = (int) Math.round(coor.get(0).getY()*height_i_actual);
			
			int curr_x = begin_x;
			int curr_y = begin_y;
			
			// drawing shortest route on the map
			BasicStroke bstroke = new BasicStroke(3);
			G2d.setStroke(bstroke);
			G2d.setColor(Color.RED);
			for (Coordinate coor : coor) {
				int dest_x = (int) Math.round(coor.getX() * width_i_actual);
				int dest_y = (int) Math.round(coor.getY()*height_i_actual);
				
				G2d.drawLine(curr_x, curr_y, dest_x, dest_y);
				
				
				// reset x and y
				
				curr_x = dest_x;
				curr_y = dest_y;
			}
			// circle the begin of buiding blue
			G2d.setColor(Color.BLUE);
			G2d.drawOval(begin_x-15, begin_y-15, 27, 27);
			
			// circle the destination building by yellow
			G2d.setColor(Color.MAGENTA);
			G2d.drawOval(curr_x-15, curr_y-15, 27, 27);
		}
		
	}
}
