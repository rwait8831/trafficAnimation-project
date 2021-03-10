import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;

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
        
        g.setColor(Color.black);
        g.fillRect(0, (height/3), width, (height/3));

        g.setColor(Color.yellow);
        g.drawLine(0, (height/3) + height/6, width, (height/3) + height/6);
        g.drawLine(0, (height/3) + height/6 + 1, width, (height/3) + height/6 +1);
        g.drawLine(0, (height/3) + height/6 - 1, width, (height/3) + height/6 - 1);
		// Calculate the new xOffset position of the moving object.
		xOffset  = (xOffset + stepSize) % (width);

		xOffset2 = (xOffset2 - stepSize) % width;

		// TODO: Use width, height, and xOffset to draw your scalable objects
		// at their new positions on the screen

		// This draws a green square. Replace it with your own object.
		int squareSide = height / 7;
		int squareY = height / 2 + height/14 - squareSide / 2;
		
		g.setColor(Color.red);
		g.fillRect(xOffset, squareY, squareSide, squareSide);
		g.fillRect(xOffset2 + width, squareY - height/6, squareSide, squareSide);
		
		g.setColor(Color.white);
		g.fillOval(width/3,  height/ 2 + height/4, height/12, height/12);
		g.drawLine(width/3 + 16,  height/ 2 + height/4 + 16, width/3 + 16, height/ 2 + height/3 + 16);
		g.setColor(Color.gray);
		g.fillOval(xOffset, squareY + (squareSide), squareSide/3, squareSide/3);
		g.fillOval(xOffset + squareSide - squareSide/3, squareY + (squareSide), squareSide/3, squareSide/3);
		g.fillOval(xOffset2, squareY- height/6 + (squareSide), squareSide/3, squareSide/3);

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