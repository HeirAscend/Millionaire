/*
    Varun Chauhan
    Ms Basaraba
    June 12, 2020
    This program is the first animation of the splash screen for Millionaire
*/
import java.awt.*; // java command libraries
import hsa.Console; // Console class files
import java.lang.*; // allows access to the Thread class

public class SplashA extends Thread // creates SplashA class
{
    private Console c; // Console variable

    public void leftSide ()  // leftSide method
    {
	Font subText = new Font ("Gadugi", Font.PLAIN, 25); // smaller font
	Font mainText = new Font ("Gadugi", Font.PLAIN, 43); // bigger font
	Color text = new Color (255, 215, 0); // color of the text
	Color mainPurple = new Color (120, 81, 169); // color of the purple
	Color blue = new Color (0, 0, 100); // background

	for (int x = -250 ; x < 290 ; x++) // loops to animate
	{
	    // erasers
	    c.setColor (Color.blue);
	    c.fillRect (0, 0, 640, 500);

	    c.setColor (mainPurple);
	    c.fillOval (170, 100, 300, 300);

	    c.setColor (Color.black);
	    c.fillOval (180, 110, 280, 280);
	    // erasers up to here

	    c.setFont (subText);
	    c.setColor (text);
	    c.drawString ("WHO", x, 140);
	    c.drawString ("WHO", x, 380);

	    c.setFont (mainText);
	    c.drawString ("MILLIONAIRE?", x - 105, 265);

	    // delays animation
	    try
	    {
		sleep (5);
	    }
	    catch (Exception e)
	    {
	    }
	}

	//small pause for both Thread to stop before superimposing main image
	try
	{
	    Thread.sleep (100);
	}
	catch (Exception e)
	{
	}

	c.setFont (subText);
	c.setColor (text);
	c.drawString ("WHO", 290, 140);
	c.drawString ("WANTS TO BE A", 230, 175);
	c.drawString ("WANTS TO BE A", 230, 342);
	c.drawString ("WHO", 290, 380);

	c.setFont (mainText);
	c.drawString ("MILLIONAIRE?", 185, 265);

	//small pause before design shows up
	try
	{
	    Thread.sleep (10);
	}
	catch (Exception e)
	{
	}

	// creates design
	c.setColor (blue);
	for (int y = 0 ; y <= 90 ; y += 30)
	{
	    c.fillRect (2 * y, y, 640 - 4 * y, 5);
	    c.fillRect (180 - 2 * y, y + 405, 280 + 4 * y, 5);
	}

	for (int x = 0 ; x <= 160 ; x += 40)
	{
	    c.fillRect (x, x, 5, 500 - 2 * x);
	    c.fillRect (x + 475, 160 - x, 5, 180 + 2 * x);
	}

	// pause for everything to catch up
	try
	{
	    Thread.sleep (1500);
	}
	catch (Exception e)
	{
	}
    }


    public SplashA (Console con)  // allows SplashA class to be assigned to a Console variable
    {
	c = con;
    }


    public void run ()  // runs leftSide method
    {
	leftSide ();
    }
}
