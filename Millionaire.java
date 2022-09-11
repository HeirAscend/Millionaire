/*
    Varun Chauhan
    This program simulates the popular game show, "Who want to be a Millionaire?"
*/
import java.awt.*; // java command library
import hsa.Console; // console class files
import java.io.*; // allows File IO methods
import javax.swing.*; // allows JOptionPane method for errors

public class Millionaire // Millionaire class
{
    Console c; // Console variable
    boolean repeater = true; // state of boolean represents if the program should repeat or not
    boolean gameover = false, lifeA = true, lifeB = true, lifeC = true, quit = false, conditionA = true, conditionB = true, conditionC = true; // various boolean variables represent states of the game and allow different parts to be available and available accordingly
    Color mainPurple = new Color (120, 81, 169); // commonly used color that fits the color scheme
    char correct; // holds the correct answer to each question
    int prize = 0, winCount = 0; // prize keeps track of the winnings, and winCount keeps track of the number of wins
    String username; // holds the user for the round
    int[] rankScore = new int [10]; // holds the prizes for each run(for high scores)
    String[] names = {"", "", "", "", "", "", "", "", "", ""}; // keeps track of the various usernames(for high scores)

    private void title ()  // title and eraser method
    {
	Font header = new Font ("Corbel Light", Font.BOLD, 30); // font for the title

	c.clear (); // clears screen

	c.setColor (new Color (0, 0, 100));
	c.fillRect (0, 0, 640, 500); // eraser

	c.setFont (header);
	c.setColor (mainPurple);
	c.drawString ("Who Wants to Be a Millionaire?", 112, 32); // title
    }


    private void pauseProgram ()  // pauseProgram method
    {
	Font pause = new Font ("Segoe UI", Font.ITALIC, 25); // Font used for the notification

	c.setFont (pause);
	c.setColor (Color.pink);
	c.drawString ("Press any key to proceed", 185, 490); // notification

	char proceed = c.getChar (); // pauses until a character is inputted
    }


    private void gameScreen (int wins)  // creates the gamescreen, changing according to the number of wins and lifelines used
    {
	Font score = new Font ("Impact", Font.PLAIN, 35); // Font for the score tracker
	Font options = new Font ("Monotype Corsiva", Font.BOLD, 45); // font for the letter options of each question
	Font prizes = new Font ("SimSun", Font.PLAIN, 18); // font for the prizes in the chart that shows questions cleared
	Font letters = new Font ("NSimSun", Font.BOLD, 18); // font for the letters that indicate which letter to press for each lifeline

	c.clear (); // clears previous screen

	c.setColor (Color.black);
	c.fillRect (0, 0, 640, 500);

	c.setColor (Color.blue);
	c.drawRect (400, 0, 240, 300);
	c.drawRect (0, 50, 400, 250);

	c.drawRect (0, 315, 640, 60);
	c.drawRect (0, 390, 320, 50);
	c.drawRect (0, 440, 320, 50);
	c.drawRect (320, 390, 320, 50);
	c.drawRect (320, 440, 320, 50);

	for (int x = 20 ; x <= 240 ; x += 20)
	    c.drawLine (400, x, 640, x);

	c.setColor (Color.white);
	c.setFont (score);
	c.drawString ("Score: " + String.valueOf (prize), 20, 40);

	c.drawOval (490, 242, 60, 40);
	c.fillOval (510, 262, 20, 20);
	c.fillOval (535, 257, 10, 10);
	c.fillOval (495, 257, 10, 10);

	c.drawOval (415, 242, 60, 40);
	c.drawRect (436, 247, 18, 30);
	c.drawLine (436, 270, 454, 270);
	c.fillOval (443, 272, 4, 3);

	c.drawOval (565, 242, 60, 40);

	c.setColor (mainPurple);
	c.setFont (options);
	c.drawString ("A", 10, 430);
	c.drawString ("B", 330, 430);
	c.drawString ("C", 10, 480);
	c.drawString ("D", 330, 480);

	c.setFont (letters);
	c.drawString ("V", 515, 298);
	c.drawString ("P", 440, 298);
	c.drawString ("F", 590, 298);
	c.drawString ("50/50", 571, 270);

	c.setFont (prizes);
	c.drawString ("$500", 410, 237);
	c.drawString ("$1,000", 410, 217);
	c.drawString ("$2,000", 410, 197);
	c.drawString ("$5,000", 410, 177);
	c.drawString ("$10,000", 410, 157);
	c.drawString ("$20,000", 410, 137);
	c.drawString ("$50,000", 410, 117);
	c.drawString ("$75,000", 410, 97);
	c.drawString ("$150,000", 410, 77);
	c.drawString ("$250,000", 410, 57);
	c.drawString ("$500,000", 410, 37);
	c.drawString ("$1,000,000", 410, 17);

	//series of if statements that appear for each question cleared
	c.setColor (Color.green);
	if (wins >= 1)
	    c.drawLine (400, 230, 640, 230);
	if (wins >= 2)
	    c.drawLine (400, 210, 640, 210);
	if (wins >= 3)
	    c.drawLine (400, 190, 640, 190);
	if (wins >= 4)
	    c.drawLine (400, 170, 640, 170);
	if (wins >= 5)
	    c.drawLine (400, 150, 640, 150);
	if (wins >= 6)
	    c.drawLine (400, 130, 640, 130);
	if (wins >= 7)
	    c.drawLine (400, 110, 640, 110);
	if (wins >= 8)
	    c.drawLine (400, 90, 640, 90);
	if (wins >= 9)
	    c.drawLine (400, 70, 640, 70);
	if (wins >= 10)
	    c.drawLine (400, 50, 640, 50);
	if (wins >= 11)
	    c.drawLine (400, 30, 640, 30);

	//if statements that indicate if a lifeline has been used
	c.setColor (Color.red);
	if (lifeA != true)
	    c.fillRect (485, 257, 70, 10);
	if (lifeB != true)
	    c.fillRect (410, 257, 70, 10);
	if (lifeC != true)
	    c.fillRect (560, 257, 70, 10);
    }


    private int random (int min, int max)  // random number generator method
    {
	return min + (int) (Math.random () * ((max - min) + 1));
    }


    public void splashScreenA ()  // first splash screen method
    {
	SplashA s = new SplashA (c); // runs SplashA class in Console c
	s.start (); // starts Thread
    }


    public void splashScreenB ()  // second splash screen method
    {
	SplashB s = new SplashB (c); // runs SplashB class in Console c
	s.run (); // runs Thread

	try
	{
	    Thread.sleep (2000); // pauses for a bit before proceeding to menu
	}
	catch (Exception e)
	{
	}
    }


    public void mainMenu ()  // main menu method
    {
	Font options = new Font ("NSimSun", Font.PLAIN, 25); // font for main menu

	title ();

	c.setFont (options);
	c.setColor (Color.white);
	c.drawString ("Instructions: Press i", 180, 180);

	c.setColor (Color.green);
	c.drawString ("High Scrores: Press h", 180, 230);

	c.setColor (Color.yellow);
	c.drawString ("Play Game: Press p", 180, 280);

	c.setColor (Color.red);
	c.drawString ("Exit: Press e", 180, 330);

	c.setColor (Color.magenta);
	c.drawString ("Clear Scores: Press c", 180, 380);

	c.setColor (Color.orange);
	for (int x = 145 ; x <= 345 ; x += 50)
	    c.drawRect (175, x, 300, 50);

	while (true) // reruns if error
	{
	    char proceed = c.getChar (); // takes a character input
	    if (proceed == 'i' || proceed == 'I') // if 'i' is pressed to proceed to instructions
	    {
		instructions ();
		break;
	    }
	    else if (proceed == 'h' || proceed == 'H') // if 'h' is pressed to proceed to high scores
	    {
		highScores ();
		break;
	    }
	    else if (proceed == 'p' || proceed == 'P') // if 'p' is pressed to enter game mode
		break;
	    else if (proceed == 'e' || proceed == 'E') // if 'e' is pressed to exit
	    {
		repeater = false;
		break;
	    }
	    else if (proceed == 'c' || proceed == 'C') // if 'c' is pressed to clear high scores
	    {
		for (int x = 0 ; x < 10 ; x++)
		{
		    rankScore [x] = 0;
		    names [x] = "";
		}
	    }
	    else // if an invalid character is entered
		JOptionPane.showMessageDialog (null, "Please Enter a Valid Character", "Wrong Character", JOptionPane.ERROR_MESSAGE);
	}
    }


    public void instructions ()  // instructions method
    {
	Font instruct = new Font ("Palatino Linotype", Font.PLAIN, 22); // main instructions font
	Font lives = new Font ("NSimSun", Font.BOLD, 36); // lifeline names font
	Font explain = new Font ("SimSun", Font.PLAIN, 20); // "50/50" font

	title ();

	c.setFont (instruct);
	c.setColor (Color.white);
	c.drawString ("This is a classic \"Who Wants to Be a Millonaire\" game. You", 20, 60);
	c.drawString ("will be asked 12 multiple questions of increasing difficulty.", 20, 90);
	c.drawString ("Each question will be worth more than the last, up the max", 20, 120);
	c.drawString ("of $1,000,000. You can use lifelines to help you in questions.", 20, 150);
	c.drawString ("Phone a Friend: Friend's opinion. Press p to use.", 20, 290);
	c.drawString ("Audience Poll: Audience votes. Press v to use.", 20, 320);
	c.drawString ("Fifty-Fifty: Remove two options. Press f to use.", 20, 350);
	c.drawString ("Press 'q' to save your progress and go back to the main menu.", 20, 400);
	c.drawString ("Hint: The letters to press will be under the lifelines.", 20, 450);

	c.setFont (explain);
	c.drawString ("Audience Poll", 260, 255);
	c.drawString ("Phone a Friend", 65, 255);
	c.drawString ("Fifty-Fifty", 458, 255);

	c.setColor (Color.black);
	c.fillOval (70, 160, 120, 80);
	c.fillOval (260, 160, 120, 80);
	c.fillOval (450, 160, 120, 80);

	c.setColor (Color.white);
	c.fillOval (300, 200, 40, 40);
	c.fillOval (350, 200, 20, 20);
	c.fillOval (270, 200, 20, 20);

	c.drawRect (112, 170, 36, 60);
	c.drawLine (112, 220, 148, 220);
	c.fillOval (126, 222, 8, 6);

	c.setFont (lives);
	c.setColor (mainPurple);
	c.drawString ("50/50", 462, 220);

	pauseProgram ();

	mainMenu ();
    }


    public void highScores ()  // high scores method
    {
	Font header = new Font ("Comic Sans MS", Font.PLAIN, 40); // table headers font
	Font writing = new Font ("Comic Sans MS", Font.PLAIN, 20); // hgih scores display font

	c.setColor (Color.blue);
	c.fillRect (0, 0, 640, 500);

	c.setFont (header);
	c.setColor (Color.white);
	c.drawString ("Rankings", 240, 40);
	c.drawString ("Rank", 26, 105);
	c.drawString ("Username", 150, 105);
	c.drawString ("Prize ($)", 410, 105);

	c.drawRect (20, 60, 600, 400);
	c.drawLine (120, 60, 120, 460);
	c.drawLine (370, 60, 370, 460);
	c.drawLine (20, 120, 620, 120);

	c.setFont (writing);

	for (int x = 0 ; x < 10 ; x++) // accesses all of the scores and usernames in the high score and name arrays
	{
	    c.drawString (names [x], 127, 34 * (x + 4) + 9);
	    c.drawString (String.valueOf (rankScore [x]), 450, 34 * (x + 4) + 9);
	}

	for (int x = 154 ; x <= 460 ; x += 34) // shows rankings from 1 to 10
	{
	    c.drawLine (20, x, 620, x);
	    c.drawString (String.valueOf (x / 34 - 3), 60, x - 9);
	}

	pauseProgram ();

	mainMenu ();
    }


    private void lifeline (char choice, char answer)  // lifeline method, takes in values for the lifeline chosen and the correct answer
    {
	Font text = new Font ("Sylfaen", Font.PLAIN, 18); // lifeline text(if applicable) font

	if (choice == 'v') // if user invokes audience poll lifeline
	{
	    if (lifeA != false) // only runs if lifeline hasn't been used before
	    {
		c.setColor (Color.red);
		c.fillRect (485, 257, 70, 10);

		c.setColor (Color.black);
		c.fillRect (0, 51, 400, 248);

		c.setColor (Color.white);
		c.drawRect (50, 80, 300, 170);

		int favored = random (50, 150); // generates a rondom number that will signify the height of the correct answer's line
		int unfav1 = random (0, 150 - favored); // wrong option height
		int unfav2 = random (0, 150 - (favored + unfav1)); // wrong option height
		int unfav3 = 150 - (favored + unfav1 + unfav2); // wrong option height

		c.setFont (text);
		c.drawString ("A", 84, 275);
		c.drawString ("B", 159, 275);
		c.drawString ("C", 234, 275);
		c.drawString ("D", 309, 275);

		if (answer == 'a') // line distribution for if the correct answer is 'a'
		{
		    c.fillRect (80, 250 - favored, 21, favored);
		    c.fillRect (155, 250 - unfav1, 21, unfav1);
		    c.fillRect (230, 250 - unfav2, 21, unfav2);
		    c.fillRect (305, 250 - unfav3, 21, unfav3);
		}
		else if (answer == 'b') // line distribution for if the correct answer is 'b'
		{
		    c.fillRect (155, 250 - favored, 21, favored);
		    c.fillRect (80, 250 - unfav1, 21, unfav1);
		    c.fillRect (230, 250 - unfav2, 21, unfav2);
		    c.fillRect (305, 250 - unfav3, 21, unfav3);
		}
		else if (answer == 'c') // line distribution for if the correct answer is 'c'
		{
		    c.fillRect (230, 250 - favored, 21, favored);
		    c.fillRect (155, 250 - unfav1, 21, unfav1);
		    c.fillRect (80, 250 - unfav2, 21, unfav2);
		    c.fillRect (305, 250 - unfav3, 21, unfav3);
		}
		else if (answer == 'd') // line distribution for if the correct answer is 'd'
		{
		    c.fillRect (305, 250 - favored, 21, favored);
		    c.fillRect (155, 250 - unfav1, 21, unfav1);
		    c.fillRect (230, 250 - unfav2, 21, unfav2);
		    c.fillRect (80, 250 - unfav3, 21, unfav3);
		}

		lifeA = false; // sets the state of the lifeline to false so it can't be used again
	    }
	    else // outputs error message if the lifeline was already used once
		JOptionPane.showMessageDialog (null, "Can't use same lifeline again!", "Double Entry", JOptionPane.ERROR_MESSAGE);
	}
	else if (choice == 'p') // if user invokes the phone a friend lifeline
	{
	    if (lifeB != false) // runs if the lifeline hasn't already been used once
	    {
		c.setColor (Color.red);
		c.fillRect (410, 257, 70, 10);

		int chances = random (0, 100); // generates a random number that will define the chance of a correct answer
		char result = ' '; // holds the resulting value chosen by the lifeline

		if (answer == 'a') // if the correct answer is 'a'
		{
		    if (chances <= 70) // 70% chance of being the correct answer
			result = 'a';
		    else if (chances <= 80) // 10% chance of not being the correct answer
			result = 'b';
		    else if (chances <= 90) // 10% chance of not being the correct answer
			result = 'c';
		    else // 10% chance of not being the correct answer
			result = 'd';
		}
		else if (answer == 'b') // if the correct answer is 'b'
		{
		    if (chances <= 70) // 70% chance of being the correct answer
			result = 'b';
		    else if (chances <= 80) // 10% chance of not being the correct answer
			result = 'a';
		    else if (chances <= 90) // 10% chance of not being the correct answer
			result = 'c';
		    else // 10% chance of not being the correct answer
			result = 'd';
		}
		else if (answer == 'c') // if the correct answer is 'c'
		{
		    if (chances <= 70) // 70% chance of being the correct answer
			result = 'c';
		    else if (chances <= 80) // 10% chance of not being the correct answer
			result = 'b';
		    else if (chances <= 90) // 10% chance of not being the correct answer
			result = 'a';
		    else // 10% chance of not being the correct answer
			result = 'd';
		}
		else if (answer == 'd') // if the correct answer is 'd'
		{
		    if (chances <= 70) // 70% chance of being the correct answer
			result = 'd';
		    else if (chances <= 80) // 10% chance of not being the correct answer
			result = 'b';
		    else if (chances <= 90) // 10% chance of not being the correct answer
			result = 'c';
		    else // 10% chance of not being the correct answer
			result = 'a';
		}

		c.setColor (Color.black);
		c.fillRect (0, 51, 400, 248);

		c.setColor (Color.white);
		c.setFont (text);
		c.drawString ("Your friend's response is:", 90, 150);
		c.drawString ("\"I think the answer should be \'" + String.valueOf (result) + "\'\"", 60, 200); // displays chosen answer

		lifeB = false; // sets the state of the lifeline to false so it can't be used again
	    }
	    else // outputs error if the user tries to use the same lifeline again
		JOptionPane.showMessageDialog (null, "Can't use same lifeline again!", "Double Entry", JOptionPane.ERROR_MESSAGE);
	}
	else if (choice == 'f') // if user invokes the fifty fifty lifeline
	{
	    if (lifeC != false) // runs if lifeline hasn't been used before
	    {
		c.setColor (Color.red);
		c.fillRect (560, 257, 70, 10);

		char[] remove = new char [2]; // holds the options to remove
		int first = random (1, 9), second = random (1, 3); // generates random numbers that determine which options to remove

		if (answer == 'a') // if answer is 'a'
		{
		    // chooses first option to remove
		    if (first <= 3)
			remove [0] = 'b';
		    else if (first <= 6)
			remove [0] = 'c';
		    else
			remove [0] = 'd';

		    // chooses second option to remove given the first option
		    if (second == 1)
		    {
			if (remove [0] == 'b')
			    remove [1] = 'c';
			else
			    remove [1] = 'b';
		    }
		    if (second == 2)
		    {
			if (remove [0] == 'c')
			    remove [1] = 'd';
			else
			    remove [1] = 'c';
		    }
		    if (second == 3)
		    {
			if (remove [0] == 'd')
			    remove [1] = 'b';
			else
			    remove [1] = 'd';
		    }
		}
		else if (answer == 'b') // if the answer is 'b'
		{
		    // chooses first option to remove
		    if (first <= 3)
			remove [0] = 'a';
		    else if (first <= 6)
			remove [0] = 'c';
		    else
			remove [0] = 'd';

		    // chooses second option to remove given the first option
		    if (second == 1)
		    {
			if (remove [0] == 'a')
			    remove [1] = 'c';
			else
			    remove [1] = 'a';
		    }
		    if (second == 2)
		    {
			if (remove [0] == 'c')
			    remove [1] = 'd';
			else
			    remove [1] = 'c';
		    }
		    if (second == 3)
		    {
			if (remove [0] == 'd')
			    remove [1] = 'a';
			else
			    remove [1] = 'd';
		    }
		}
		else if (answer == 'c') // if the answer is 'c'
		{
		    // chooses first option to remove
		    if (first <= 3)
			remove [0] = 'b';
		    else if (first <= 6)
			remove [0] = 'a';
		    else
			remove [0] = 'd';

		    // chooses second option to remove given the first option
		    if (second == 1)
		    {
			if (remove [0] == 'b')
			    remove [1] = 'a';
			else
			    remove [1] = 'b';
		    }
		    if (second == 2)
		    {
			if (remove [0] == 'a')
			    remove [1] = 'd';
			else
			    remove [1] = 'a';
		    }
		    if (second == 3)
		    {
			if (remove [0] == 'd')
			    remove [1] = 'b';
			else
			    remove [1] = 'd';
		    }
		}
		else if (answer == 'd') // if the answer is 'd'
		{
		    // chooses first option to remove
		    if (first <= 3)
			remove [0] = 'b';
		    else if (first <= 6)
			remove [0] = 'c';
		    else
			remove [0] = 'a';

		    // chooses second option to remove given the first option
		    if (second == 1)
		    {
			if (remove [0] == 'b')
			    remove [1] = 'c';
			else
			    remove [1] = 'b';
		    }
		    if (second == 2)
		    {
			if (remove [0] == 'c')
			    remove [1] = 'a';
			else
			    remove [1] = 'c';
		    }
		    if (second == 3)
		    {
			if (remove [0] == 'a')
			    remove [1] = 'b';
			else
			    remove [1] = 'a';
		    }
		}

		// removes the options inside the array
		c.setColor (Color.black);
		if (remove [0] == 'a' || remove [1] == 'a')
		    c.fillRect (0, 391, 320, 48);
		if (remove [0] == 'c' || remove [1] == 'c')
		    c.fillRect (0, 441, 320, 48);
		if (remove [0] == 'b' || remove [1] == 'b')
		    c.fillRect (321, 391, 320, 48);
		if (remove [0] == 'd' || remove [1] == 'd')
		    c.fillRect (321, 441, 320, 48);

		lifeC = false; // sets the state of the lifeline to false so it can't be used again
	    }
	    else // outputs error if the user tries to use the same lifeline again
		JOptionPane.showMessageDialog (null, "Can't use same lifeline again!", "Double Entry", JOptionPane.ERROR_MESSAGE);
	}
	else // outputs error if an invalid character is inputted
	    JOptionPane.showMessageDialog (null, "Please enter a valid character!", "Invalid input", JOptionPane.ERROR_MESSAGE);
    }


    private void questions (int difficulty, int winnings)  // question generating and answer accepting method, takes in values for the difficulty and the prize of the question
    {
	Font question = new Font ("Sylfaen", Font.PLAIN, 18); // font for the question and answers
	String[] questions = new String [36], answers = new String [36]; // String arrays for the questions and answers in the file
	int[] coor = new int [2]; // coordinates for the correct answer String

	if (conditionA != false) // revives lifeline if the user saved and exited program after using a lifeline and didn't answer(if the user didn't get to use the lifeline properly)
	    lifeA = true;
	if (conditionB != false) // revives lifeline if the user saved and exited program after using a lifeline and didn't answer(if the user didn't get to use the lifeline properly)
	    lifeB = true;
	if (conditionC != false) // revives lifeline if the user saved and exited program after using a lifeline and didn't answer(if the user didn't get to use the lifeline properly)
	    lifeC = true;

	gameScreen (winCount); // creates the game screen according to the number of wins

	try
	{
	    BufferedReader br = new BufferedReader (new FileReader ("Questions.txt")); // creates a new BufferedReader object
	    for (int x = 0 ; x < 36 ; x++) // imports questions from file into the questions array
	    {
		questions [x] = br.readLine ();
	    }
	    br.close (); // closes stream
	}
	catch (IOException e)
	{
	}

	try
	{
	    BufferedReader br = new BufferedReader (new FileReader ("Answers.txt")); // creates a new BufferedReader object
	    for (int x = 0 ; x < 36 ; x++) // imports answers from file into the answers array
	    {
		answers [x] = br.readLine ();
	    }
	    br.close (); // closes stream
	}
	catch (IOException e)
	{
	}

	c.setFont (question);
	c.setColor (Color.white);

	int choice = random (difficulty * 3, difficulty * 3 + 2); // chooses one of the 3 questions of each difficulty randomly
	try // if the question is too long for the space given, it will continue on to the next lien
	{
	    c.drawString (questions [choice].substring (0, 80) + "-", 10, 340);
	    c.drawString (questions [choice].substring (80), 30, 360);
	}
	catch (StringIndexOutOfBoundsException s)  // if the question isn't too long
	{
	    c.drawString (questions [choice], 10, 340);
	}

	int optionA = random (0, 3), optionB, optionC, optionD; // randomly chooses places for the options of each question to be
	c.drawString (answers [choice].split ("_") [optionA], 50, 420); // writes one of the options in the 'a' category

	for (optionB = optionA ; optionB == optionA ; optionB += 0) // loops if the 'b' option is the same as the 'a' option
	{
	    optionB = random (0, 3); // randomly generates a value for the 'b' option
	}
	c.drawString (answers [choice].split ("_") [optionB], 370, 420); // writes the 'b' option in the 'b' category

	for (optionC = optionA ; optionC == optionA || optionC == optionB ; optionC += 0) // loops until 'c' is unique
	{
	    optionC = random (0, 3); // generates an option for 'c'
	}
	c.drawString (answers [choice].split ("_") [optionC], 50, 470); // writes 'c' option in the 'c' category

	for (optionD = optionA ; optionD == optionA || optionD == optionB || optionD == optionC ; optionD += 0) // loops until 'd' is unique
	{
	    optionD = random (0, 3); // generates an option for 'd'
	}
	c.drawString (answers [choice].split ("_") [optionD], 370, 470); // writes 'd' option in 'd' category

	if (optionA == 0) // if 'a' is the correct answer
	{
	    correct = 'a'; // sets the value of the answer to 'a'
	    coor [0] = 50; // sets the x coordinate of the correct option to the 'a' location
	    coor [1] = 420; // sets the y coordinate of the correct option to the 'a' location
	}
	if (optionB == 0) // if 'b' is the correct answer
	{
	    correct = 'b'; // sets the value of the answer to 'b'
	    coor [0] = 370; // sets the x coordinate of the correct option to the 'b' location
	    coor [1] = 420; // sets the y coordinate of the correct option to the 'b' location
	}
	if (optionC == 0) // if 'c' is the correct answer
	{
	    correct = 'c'; // sets the value of the answer to 'c'
	    coor [0] = 50; // sets the x coordinate of the correct option to the 'c' location
	    coor [1] = 470; // sets the y coordinate of the correct option to the 'c' location
	}
	if (optionD == 0) // if 'd' is the correct answer
	{
	    correct = 'd'; // sets the value of the answer to 'd'
	    coor [0] = 370; // sets the x coordinate of the correct option to the 'd' location
	    coor [1] = 470; // sets the y coordinate of the correct option to the 'd' location
	}

	while (true) // runs again if there's an invalid input or an error
	{
	    char guess = c.getChar (); // takes an input from the user for their choice(guess or lifeline)

	    if (guess == correct) // if the input is the correct answer
	    {
		c.setColor (Color.green);
		c.drawString (answers [choice].split ("_") [0], coor [0], coor [1]); // makes the answer chosen green to signify that it's correct
		prize = winnings; // the user's score changes to the value of that question
		winCount++; // the numebr of correct answers increases by 1

		if (lifeA != true) // doesn't allow to rerun lifeline they already used in this question again even if they quit
		    conditionA = false;
		if (lifeB != true) // doesn't allow to rerun lifeline they already used in this question again even if they quit
		    conditionB = false;
		if (lifeC != true) // doesn't allow to rerun lifeline they already used in this question again even if they quit
		    conditionC = false;

		break; // breaks out of the loop
	    }
	    else // if the input isn't the correct answer
	    {
		c.setColor (Color.red);
		if (guess == 'a') // if incorrect guess is 'a'
		{
		    c.drawString (answers [choice].split ("_") [optionA], 50, 420); // makes the incorrect guess red to signify it's wrong
		    c.setColor (Color.green);
		    c.drawString (answers [choice].split ("_") [0], coor [0], coor [1]); // makes the correct answer green to show that it's the correct answer
		    gameover = true; // the game is over because the user guessed wrong
		    break; // breaks out of loop
		}
		else if (guess == 'b') // if incorrect guess is 'b'
		{
		    c.drawString (answers [choice].split ("_") [optionB], 370, 420); // makes incorrect guess red to show it's wrong
		    c.setColor (Color.green);
		    c.drawString (answers [choice].split ("_") [0], coor [0], coor [1]); // makes correct answer green to show it's right
		    gameover = true; // game is over because wrong input
		    break; // breaks out of loop
		}
		else if (guess == 'c') // if incorrect guess is 'c'
		{
		    c.drawString (answers [choice].split ("_") [optionC], 50, 470); // makes guess red to show it's wrong
		    c.setColor (Color.green);
		    c.drawString (answers [choice].split ("_") [0], coor [0], coor [1]); // makes correct answer green to show it's right
		    gameover = true; // game over because wrong input
		    break; // breaks out of loop
		}
		else if (guess == 'd') // if incorrect guess is 'd'
		{
		    c.drawString (answers [choice].split ("_") [optionD], 370, 470); // the wrong guess becomes red to show it's wrong
		    c.setColor (Color.green);
		    c.drawString (answers [choice].split ("_") [0], coor [0], coor [1]); // right answer becomes green to show it's right
		    gameover = true; // the game ends because wrong input
		    break; // breaks out of loop
		}
		else if (guess == 'q') // if the user chooses to save current progress and exit back to main menu
		{
		    quit = true; // changes state to signify not to refresh everything after user re-enters game mode
		    gameover = true; // to break out of game screen it has to appear that the game has ended
		    return; // breaks out of method because there is no need to pause for a few seconds now
		}
		else // if the input is anything else
		    lifeline (guess, correct); // invokes lifeline method
	    }
	}

	try
	{
	    Thread.sleep (2000); // pauses for a few seconds, to show user what the results are
	}
	catch (Exception e)
	{
	}
    }


    public void game ()  // main game method for game flow
    {
	Font endScreen = new Font ("Garamond", Font.ITALIC, 70); // font for lose screen, win screen, or quit screen
	Font scorer = new Font ("Garamond", Font.PLAIN, 35); // font that show's the winnings of the user if they lost or if they quit
	Font askName = new Font ("Palatino Linotype", Font.PLAIN, 20); // font that asks for the username
	int[] prizeVals = {500, 1000, 2000, 5000, 10000, 20000, 50000, 75000, 150000, 250000, 500000, 1000000}; // array that holds the prizes for each level
	gameover = false; // allows game to rerun if the user quit and re-entered

	if (quit != true) // only refreshed all values if the user didn't quit
	{
	    // refreshes all variables
	    lifeA = true;
	    lifeB = true;
	    lifeC = true;
	    prize = 0;
	    winCount = 0;
	    username = "";

	    title (); // erases previous screen

	    c.setColor (Color.black);
	    c.fillRect (0, 175, 640, 40); // rectangle where the username will be

	    c.setFont (askName);
	    c.setColor (Color.white);
	    c.drawString ("Please enter a user name that is max 20 letters. Press enter to confirm:", 10, 100);
	    for (char input = c.getChar () ; input != '\n' && username.length () <= 20 ; input = c.getChar ()) // runs until either the user presses enter or until the username is max size
	    {
		c.setColor (Color.black);
		c.fillRect (0, 175, 640, 40); // eraser
		c.setColor (Color.white);
		if (input == '\b' && username.length () > 0) // if the user inputs a backspace
		    username = username.substring (0, username.length () - 1); // cuts off the last letter of the String
		else if ((input >= 'a' && input <= 'z') || (input >= 'A' && input <= 'Z')) // if the user inputs any letter
		    username += input; // adds on the letter to the String
		else // shows error if the user inputs anything other than a letter or backspace
		    JOptionPane.showMessageDialog (null, "Please enter a valid input!", "Invalid Input", JOptionPane.ERROR_MESSAGE);

		c.drawString (username + "|", 200, 200); // displays the username of the user
	    }

	    try
	    {
		Thread.sleep (2000); // pauses for a bit before proceeding to the game screen
	    }
	    catch (Exception e)
	    {
	    }
	}

	while (gameover != true && winCount < 12) // loops for each question until the user wins, loses, or quits
	{
	    quit = false; // resets the state of quit so the user can lose or win after they quit
	    questions (winCount, prizeVals [winCount]); // generates question for each difficulty
	}

	c.setColor (Color.black);
	c.fillRect (0, 0, 640, 500); // eraser

	c.setColor (Color.white);
	c.setFont (endScreen);
	if (quit != true) // if the user didn't quit
	{
	    if (winCount == 12) // if the user won
	    {
		c.drawString ("YOU WIN!", 100, 250);
		c.drawLine (400, 10, 640, 10);
	    }
	    else // if the user lost
	    {
		c.drawString ("GAME OVER...", 100, 250);

		c.setFont (scorer);
		c.drawString ("Final Winnings: $" + String.valueOf (prize), 130, 300);
	    }
	    if (prize >= rankScore [9]) // if the score is higher than the lowest value in the high scores array
	    {
		for (int x = 0 ; x < 10 ; x++) // checks every index in array
		{
		    if (prize >= rankScore [x]) // if the score is higher than the particular index in the array
		    {
			for (int y = 9 ; y > x ; y--) // bit shifts all indices of the arrays to the index where the score is higher
			{
			    rankScore [y] = rankScore [y - 1]; // bitshifts score array
			    names [y] = names [y - 1]; // but shifts names array
			}

			rankScore [x] = prize; // sets the location of the score to its valid index
			names [x] = username; // sets the location of the username to its valid index

			break; // breaks out of loop
		    }
		}
	    }
	}
	else // if the user quits
	{
	    c.drawString ("Back to Menu...", 100, 250);

	    c.setFont (scorer);
	    c.drawString ("Winnings so far: $" + String.valueOf (prize), 130, 300);
	}
	try
	{
	    Thread.sleep (3000); // pauses for a short time
	}
	catch (Exception e)
	{
	}
    }


    public void goodbye ()  // goodbye method
    {
	Font ending = new Font ("Monotype Corsiva", Font.ITALIC, 50); // font for the main text
	Font credits = new Font ("Monotype Corsiva", Font.PLAIN, 30); // font for the credits
	Color ender = new Color (212, 175, 55); // color for main text

	title (); // eraser

	c.setFont (ending);
	c.setColor (ender);
	c.drawString ("THANK YOU FOR PLAYING!", 22, 200);

	c.setFont (credits);
	c.drawString ("Made By: Varun Chauhan.", 175, 300);

	c.setColor (Color.red);
	c.drawString ("Press any key to exit.", 210, 480);

	char farewell = c.getChar (); // waits until a character is entered
	System.exit (0); // exits program
    }


    public Millionaire ()  // class constructor
    {
	c = new Console ("Who Wants to Be a Millionaire?"); // creates a new Console window called "Who Wants to be a Millionaire?"
    }


    public static void main (String[] args)  // main method
    {
	Millionaire m = new Millionaire (); // creates a new Millionaire object
	m.splashScreenA (); // executes splashScreenA
	m.splashScreenB (); // executes splashScreenB
	m.mainMenu (); // executes mainMenu
	while (m.repeater != false) // loops until user chooses to exit
	{
	    m.game (); // executes game
	    m.mainMenu (); // re-executes mainMenu
	}
	m.goodbye (); // executes goodbye
    }
}
