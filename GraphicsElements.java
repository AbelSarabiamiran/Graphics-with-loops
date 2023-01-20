import java.awt.Color;
import java.util.*;
import java.awt.Point;
import uwcse.io.*;
import uwcse.graphics.*;
import javax.swing.*;

/**
 * A class to create and manipulate graphics elements stored in an ArrayList
 Author: @Abel Sarabia Miranda*/

public class GraphicsElements { 

	/** Maximum number of wedges in a pie */
	public static final int MAXIMUM_NUMBER_OF_PIE_WEDGES = 100;

	/** Maximum number of stripes of one color in the pattern of stripes */
	public static final int MAXIMUM_NUMBER_OF_STRIPES = 15;

	/** Maximum number of divisions in a Koch snow flake */
	public static final int MAXIMUM_NUMBER_OF_DIVISIONS = 5;

	// The window is 400 pixels wide and 300 pixels high

	/**
	 * Create the view of a pie Use filled arcs. The color of each arc is
	 * random. The pie should fill the window. Store the arcs in an ArrayList
	 * and return that ArrayList. The number of pie wedges (= arcs) is given by
	 * the user (use a dialog box). If that number is less than or equal to 0 or
	 * greater than MAXIMUM_NUMBER_OF_PIE_WEDGES, display an error message (use
	 * JOptionPane.showMessageDialog)and ask for it again. Make sure that no gap
	 * appears in the pie when drawing it.
	 */
	public ArrayList<Arc> createAPie() {
		ArrayList<Arc> graphicsList = new ArrayList<Arc>();
		Input in = new Input();
		int input = in.readIntDialog("Enter number of arc you want");
		if (input > MAXIMUM_NUMBER_OF_PIE_WEDGES || input <= 0) {
			JOptionPane.showMessageDialog(null, "Invalid input, value not in range", "Error", JOptionPane.INFORMATION_MESSAGE);
		}else {
			int InitialAngle = 0;
			int pieGap = 360 % input;
			int arcAngle = 360 / input;
			for (int i = 0; i < input; i++) {
				if (i < input - pieGap) {
					int r = (int)(Math.random()*256);
					int g = (int)(Math.random()*256);
					int b= (int)(Math.random()*256);
					Color random = new Color(r, g, b);
					graphicsList.add(new Arc(50, 0, 300, 300, InitialAngle, arcAngle, random, true));
					InitialAngle += arcAngle;
				}else{
					int r = (int)(Math.random()*256);
					int g = (int)(Math.random()*256);
					int b= (int)(Math.random()*256);
					Color random = new Color(r, g, b);
					graphicsList.add(new Arc(50, 0, 300, 300, InitialAngle, arcAngle + 1, random, true));
					InitialAngle += arcAngle + 1;
				}
			}
		}
		return graphicsList;
	}

	/**
	 * Create a pattern of stripes as shown in the homework instructions. Store
	 * the triangles in an ArrayList and return that ArrayList. Use two colors
	 * only to paint the triangles. The pattern should cover most of the window.
	 * The number of stripes (of one color) is given by the user (use a dialog
	 * box). If that number is less than or equal to 0 or greater than
	 * MAXIMUM_NUMBER_OF_STRIPES, display an error message (use
	 * JOptionPane.showMessageDialog)and ask for it again.
	 */
	public ArrayList<Triangle> createStripes() {
		ArrayList<Triangle> graphicsList = new ArrayList<Triangle>();
		Input in = new Input();
		int InputStripe = in.readIntDialog("Enter number of arc you want");
		if (InputStripe > MAXIMUM_NUMBER_OF_STRIPES || InputStripe <= 0) {
			JOptionPane.showMessageDialog(null, "Invalid input, value not in range", "Error", JOptionPane.INFORMATION_MESSAGE);
		}else {
			int z = 300 / InputStripe; 
			int x = 50;
			int y = 0;
			for (int i = 1; i <= InputStripe; i++) {
				if(i % 2 == 0) {
					for(int j = 1; j <= InputStripe; j++)
						if(j % 2 == 0) {
							graphicsList.add(new Triangle(x, y, x + z, y, x + z, y + z, Color.BLUE, true)); 
							graphicsList.add(new Triangle(x, y, x, y + z, x + z, y + z, Color.YELLOW, true)); 
							x += z;	
						}else {
							graphicsList.add(new Triangle(x, y, x + z, y, x + z, y + z, Color.YELLOW, true)); 
							graphicsList.add(new Triangle(x, y, x, y + z, x + z, y + z, Color.BLUE, true)); 
							x += z;	
						}
					x = 50;
					y += z;
				}else {
					for(int j = 1; j <= InputStripe; j++)
						if(j % 2 == 0) {
							graphicsList.add(new Triangle(x, y, x + z, y, x + z, y + z, Color.YELLOW, true)); 
							graphicsList.add(new Triangle(x, y, x, y + z, x + z, y + z, Color.BLUE, true)); 
							x += z;	
						}else {
							graphicsList.add(new Triangle(x, y, x + z, y, x + z, y + z, Color.BLUE, true)); 
							graphicsList.add(new Triangle(x, y, x, y + z, x + z, y + z, Color.YELLOW, true)); 
							x += z;	
						}
					x = 50;
					y += z;
					
				}
			}
		}
		return graphicsList;
	}

	/**
	 * Create a Koch snow flake. Use the class java.awt.Point. Store the Points
	 * in an ArrayList and return that ArrayList. Note that you can't set the
	 * color of a point. The initial color of the lines making up the snow flake
	 * is black. But, you can change the color in the method
	 * changeColorOfSnowFlake below. The snow flake should cover most of the
	 * window, and be always visible. The number of divisions of the initial
	 * triangle is given by the user (use a dialog box). If that number is less
	 * than 0 or greater than MAXIMUM_NUMBER_OF_DIVISIONS, display an error
	 * message (use JOptionPane.showMessageDialog)and ask for it again.
	 */
	public ArrayList<Point> createASnowFlake() {
        ArrayList<Point> graphicsList = new ArrayList<Point>();
        // Add your code here
        Input in = new Input();
        int nD = in.readIntDialog("Enter number of arc you want");
        if (nD > MAXIMUM_NUMBER_OF_DIVISIONS || nD < 0) {
            JOptionPane.showMessageDialog(null, "Invalid input, value not in range", "Error", JOptionPane.INFORMATION_MESSAGE);
        }else {
            Point a1 = new Point(100, 0);
            Point b2 = new Point(350, 150);
            Point c3 = new Point(100, 300);
            //makes initial triangle starting point
            graphicsList.add(a1);
            graphicsList.add(b2);
            graphicsList.add(c3);
            for (int div = 0; div < nD; div++){  // code for running multiple divisions
                int numberOfSides = graphicsList.size();
                for (int side = numberOfSides; side > 0 ; side--){
                    Point p;
                    Point q;
                    if (side != numberOfSides){
                        p = graphicsList.get(side - 1);
                        q = graphicsList.get(side); 
                    } else {
                        p = graphicsList.get(numberOfSides - 1); 
                        q = graphicsList.get(0);
                    }
                    Point a = new Point((int)(p.x + (q.x - p.x)/3.0), (int)(p.y + (q.y - p.y)/3.0));
                    Point c = new Point((int)(p.x + 2 * (q.x - p.x)/3.0), (int)(p.y + 2 * (q.y - p.y)/3.0));
                    Point b = new Point();
                    b.x = (int)(a.x + (c.x - a.x) * Math.cos(Math.PI / 3.0) + (c.y - a.y) * Math.sin(Math.PI / 3.0));
                    b.y = (int)(a.y - (c.x - a.x) * Math.sin(Math.PI / 3.0) + (c.y - a.y) * Math.cos(Math.PI / 3.0));

                    graphicsList.add(side, c);
                    graphicsList.add(side, b);
                    graphicsList.add(side, a);
                }
            }
        }
        return graphicsList;
    }
	/**
	 * Rotate the colors in the pie, in a clockwise direction. Precondition:
	 * graphicsList describes a pie Return the updated ArrayList
	 * @return 
	 */
	public ArrayList<Arc> rotateColorsInPie(ArrayList<Arc> graphicsList) {
		
		Color first = graphicsList.get(0).getColor();
		for(int i = 0; i <= graphicsList.size() - 1; i++) {
			Arc Pie = graphicsList.get(i);
			if (i == graphicsList.size() - 1) {
				Pie.setColor(first);
			}else {
				Pie.setColor(graphicsList.get(i + 1).getColor()); //makes color rotate
			}
		}

		return graphicsList;
	}

	/**
	 * Flip the 2 colors of the pattern of stripes Precondition: graphicsList
	 * describes a pattern of stripes Return the updated ArrayList
	 * @return 
	 */
	public ArrayList<Triangle> flipColorsInStripes(ArrayList<Triangle> graphicsList) {
		// Add your code here
		Color first = graphicsList.get(0).getColor();
		Color second = graphicsList.get(1).getColor();
		//if statement allows the colors to just be swapped when pressed
		for(int i = 0; i <= graphicsList.size() - 1; i++) {
			Triangle halve = graphicsList.get(i);
			if (halve.getColor() == first) {
				halve.setColor(second);
			}else {
				halve.setColor(first);
			}
		}
		return graphicsList;
	}

	/**
	 * Return the new color of the snow flake (select a new random color) Use
	 * the Random class (in java.util) to select the new random color. The color
	 * that you create and return in this method will automatically be assigned
	 * to the snow flake.
	 */
	public Color changeColorOfSnowFlake() {
		//changes color of lines on snowflake
		int r = (int)(Math.random()*256);
		int g = (int)(Math.random()*256);
		int b= (int)(Math.random()*256);
		Color random = new Color(r, g, b);
		return random;
	}
}