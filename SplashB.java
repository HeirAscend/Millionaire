/*
    Varun Chauhan
    Ms Basaraba
    June 12, 2020
    This program is the second animation of the splash screen for Millionaire
*/
import java.awt.*; // java command libraries
import hsa.Console; // Console class files
import java.lang.*; // allows access to the Thread class

public class SplashB implements Runnable // creates SplashB class
{
    private Console c; // Console variable

    public void rightSide ()  // rightSide method
    {
	Font subText = new Font ("Gadugi", Font.PLAIN, 25); // font for all text
	Color text = new Color (255, 215, 0); // text color
	Color blue = new Color (0, 0, 100); // background
	Color mainPurple = new Color (120, 81, 169); // purple

	for (int x = 780 ; x > 230 ; x--) // loops for animation
	{
	    // erasers
	    c.setColor (Color.blue);
	    c.fillRect (0, 0, 640, 500);

	    c.setColor (mainPurple);
	    c.fillOval (170, 100, 300, 300);

	    c.setColor (Color.black);
	    c.fillOval (180, 110, 280, 280);
	    // eraser up to here

	    c.setFont (subText);
	    c.setColor (text);
	    c.drawString ("WANTS TO BE A", x, 175);
	    c.drawString ("WANTS TO BE A", x, 342);

	    // delays animation
	    try
	    {
		Thread.sleep (5);
	    }
	    catch (Exception e)
	    {
	    }
	}
    }


    public SplashB (Console con)  // allows SplashB class to be assigned to a Console variable
    {
	c = con;
    }


    public void run ()  // runs rightSide method
    {
	rightSide ();
    }
}
