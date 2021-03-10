import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * CS 121 Project 1: Traffic Animation
 *
 * Animates a [put your description here]
 *
 * @author BSU CS 121 Instructors
 * @author [put your name here]
 */
@SuppressWarnings("serial")
public class TrafficAnimation extends JPanel
{
	// This is where you declare constants and variables that need to keep their
	// values between calls	to paintComponent(). Any other variables should be
	// declared locally, in the method where they are used.

	/**
	 * A constant to regulate the frequency of Timer events.
	 * Note: 100ms is 10 frames per second - you should not need
	 * a faster refresh rate than this
	 */
	private final int DELAY = 100; //milliseconds

	/**
	 * The anchor coordinate for drawing / animating. All of your vehicle's
	 * coordinates should be relative to this offset value.
	 */
	private int xOffset = 0;

	private int xOffset2 = 0;

	/**
	 * The number of pixels added to xOffset each time paintComponent() is called.
	 */
	private int stepSize = 10;

	private final Color BACKGROUND_COLOR = new Color(100, 221, 40);

	/* This method draws on the panel's Graphics context.
	 * This is where the majority of your work will be.
	 *
	 * (non-Javadoc)
	 * @see java.awt.Container#paint(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g)
	{
		// Get the current width and height of the window.
		int width = getWidth(); // panel width
		int height = getHeight(); // panel height

        // Fill the graphics page with the background color.
		g.setColor(BACKGROUND_COLOR);
        g.fillRect(0, 0, width, height);
		
		//drawing the road
        g.setColor(Color.black);
        g.fillRect(0, (height/3), width, (height/3));
		
		//drawing the center line
        g.setColor(Color.yellow);
        g.drawLine(0, (height/3) + height/6, width, (height/3) + height/6);
        g.drawLine(0, (height/3) + height/6 + 1, width, (height/3) + height/6 +1);
        g.drawLine(0, (height/3) + height/6 - 1, width, (height/3) + height/6 - 1);
		// Calculate the new xOffset position of the moving object.
		int carHeight = height / 9;
		int carWidth = width / 5;
		xOffset  = (xOffset + stepSize) % (width);

		//The x offset for the car traveling in the opposite direction.
		xOffset2 = (xOffset2 - stepSize) % width;

		// TODO: Use width, height, and xOffset to draw your scalable objects
		// at their new positions on the screen

		// This draws a red and blue rectangle that is the body for the cars

		int carY = height / 2 + height/14 - carHeight / 2;
		
		g.setColor(Color.red);
		g.fillRect(xOffset, carY, carWidth, carHeight);
		g.setColor(Color.blue);
		g.fillRect(xOffset2 + width, carY - height/6, carWidth, carHeight);

		//drawing the wheels for the cars and basing them off of the x offset
		g.setColor(Color.gray);
		g.fillOval(xOffset, carY + (carHeight), carHeight/3, carHeight/3);
		g.fillOval(xOffset + carWidth - carHeight/3, carY + (carHeight), carHeight/3, carHeight/3);
		g.fillOval(xOffset2 + width, carY- height/6 + (carHeight), carHeight/3, carHeight/3);
		g.fillOval(xOffset2 + width + carWidth - carHeight/3, carY- height/6 + (carHeight), carHeight/3, carHeight/3);
		

		//drawing the observer
		int size = (height+width)/2;
		int headSize = size/12;
		int manWidth = size/100;
		int manHeight = size/7;
		g.setColor(Color.white);
		g.fillOval(width/3,  height/ 2 + height/4, headSize, headSize);
		g.fillRect(width/3 + (headSize/2 - manWidth/3),  height/ 2 + height/4 + headSize/2, manWidth, manHeight);
		int legY = height/2 + height/4 + headSize/2 + manHeight;
		int hipLength = manWidth * 3;
		g.fillRect(width/3 + hipLength/2, legY, hipLength, manHeight/10);

		//Adding some text to one of the cars
		g.setFont(new Font("Arial", Font.BOLD, carHeight/3));
		g.drawString("GOT MILK?", xOffset + carWidth/6, carY + carHeight/3);

		
		// Put your code above this line. This makes the drawing smoother.
		Toolkit.getDefaultToolkit().sync();
	}


	//==============================================================
	// You don't need to modify anything beyond this point.
	//==============================================================


	/**
	 * Starting point for this program. Your code will not go in the
	 * main method for this program. It will go in the paintComponent
	 * method above.
	 *
	 * DO NOT MODIFY this method!
	 *
	 * @param args unused
	 */
	public static void main (String[] args)
	{
		// DO NOT MODIFY THIS CODE.
		JFrame frame = new JFrame ("Traffic Animation");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new TrafficAnimation());
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * Constructor for the display panel initializes necessary variables.
	 * Only called once, when the program first begins. This method also
	 * sets up a Timer that will call paint() with frequency specified by
	 * the DELAY constant.
	 */
	public TrafficAnimation()
	{
		// Do not initialize larger than 800x600. I won't be able to
		// grade your project if you do.
		int initWidth = 600;
		int initHeight = 400;
		setPreferredSize(new Dimension(initWidth, initHeight));
		this.setDoubleBuffered(true);

		//Start the animation - DO NOT REMOVE
		startAnimation();
	}

	/**
	 * Create an animation thread that runs periodically.
	 * DO NOT MODIFY this method!
	 */
	private void startAnimation()
	{
		ActionListener timerListener = new TimerListener();
		Timer timer = new Timer(DELAY, timerListener);
		timer.start();
	}

	/**
	 * Repaints the graphics panel every time the timer fires.
	 * DO NOT MODIFY this class!
	 */
	private class TimerListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) {
			repaint();
		}
	}
}