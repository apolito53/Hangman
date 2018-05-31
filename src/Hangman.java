/*
 * Anthony Polito
 * CSE 337
 * Assignment 1 - Hangman Game
 * September 14, 2016
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Hangman extends JFrame implements ActionListener, KeyListener {
    private Boolean blnGameStarted;     //Boolean variable used to control game
    // state - true: started, false: stopped

    private final ArrayList<String> words;
    //ArrayList containing words to be
    // selected from before game begins

    private ArrayList<LetterButton> btnArray; //ArrayList for alphabet buttons


    ////////////////////////// 26 buttons representing letters A-Z ///////////////////
    private LetterButton btnA;
    private LetterButton btnB;
    private LetterButton btnC;
    private LetterButton btnD;
    private LetterButton btnE;
    private LetterButton btnF;
    private LetterButton btnG;
    private LetterButton btnH;
    private LetterButton btnI;
    private LetterButton btnJ;
    private LetterButton btnK;
    private LetterButton btnL;
    private LetterButton btnM;
    private LetterButton btnN;
    private LetterButton btnO;
    private LetterButton btnP;
    private LetterButton btnQ;
    private LetterButton btnR;
    private LetterButton btnS;
    private LetterButton btnT;
    private LetterButton btnU;
    private LetterButton btnV;
    private LetterButton btnW;
    private LetterButton btnX;
    private LetterButton btnY;
    private LetterButton btnZ;
    ///////////////////////////////////////////////////////////////////////////////////

    private StartStopButton startStopBtn; //Button that starts and stops game,
    //action is determined by blnGameStarted

    private JPanel guessPanel;            //JPanel containing JLabel for gameplay
    private JPanel numGuessesPanel;       //JPanel containg JLabel for tracking player guesses
    private JPanel titlePanel;            //JPanel containing JLabel for title

    private JLabel lblTitle;              //JLabel for "Welcome to Hangman"
    private JLabel lblGuess;              //JLabel for playing game, displays
    //"X X X X X X" when game is in 'stopped'
    //mode, displays a combination of '-' and
    //letters during gameplay
    private JLabel lblTries;              //JLabel that keeps track of number of guesses
    //a player makes during a game

    private String strToBeGuessed;        //String variable representing word player
    //is to guess during gameplay

    private String lastWordSelected;      //String variable representing word used
    //during previous game, null during first game

    private int numGuesses;               //Counter that keeps track of player
    //guesses during current game

    ////////////////////////////// CONSTRUCTOR ///////////////////////////////////////
    public Hangman() {
        blnGameStarted = false;           //Sets game control Boolean variable to
        //false upon startup

        initComponents();                 //initComponents method creates window and
        //instantiates buttons, labels, etc

        ////////////////////// Guess words and array initialization ///////////////////////
        words = new ArrayList<>();
        words.add("SOFTWARE");
        words.add("PROGRAM");
        words.add("COMPUTER");
        words.add("CAT");
        words.add("MEMORISE");
        words.add("SHARP");
        words.add("COUGH");
        words.add("GROWTH");
        words.add("FLOWERS");
        words.add("STAY");
        words.add("DRY");
        words.add("LUNCH");
        words.add("GREEN");
        words.add("GUARANTEE");
        words.add("AGREEMENT");
        words.add("DISASTER");
        words.add("COLD");
        words.add("PHONE");
        words.add("POWER");
        words.add("COUCH");
        //////////////////////////////////////////////////////////////////////////////////

        numGuesses = 0;                              //Initialize guesses counter
    }
    ///////////////////////////// END CONSTRUCTOR ////////////////////////////////////

    //////////// Method for instantiating all components and the game window /////////
    private void initComponents() {

        //Main JFrame attributes
        setDefaultCloseOperation(EXIT_ON_CLOSE);     //Game fully exits when closed
        setResizable(false);                         //Player cannot resize window
        setVisible(true);                            //Makes window visible
        setSize(1150, 800);                          //Sets size
        setLayout(null);                             //Sets layout manager to null,
        //allows manual placement of
        //components within frame
        setLocation((int) Toolkit.getDefaultToolkit().getScreenSize().width / 5,
                (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 10);
        //Places frame in specific location

        //JPanel containing label for title label
        titlePanel = new JPanel();                   //Instantiation of panel
        add(titlePanel);                             //Panel added to JFrame
        titlePanel.setLayout(new BorderLayout());    //Sets layout manager of panel
        titlePanel.setBounds(375, 50, 400, 100);     //Sets size and location of panel
        titlePanel.add(lblTitle = new JLabel("Welcome to Hangman"), BorderLayout.CENTER);
        //Adds label to panel and sets text
        //of label
        lblTitle.setFont(lblTitle.getFont().deriveFont(32.0f));
        //Sets font size of label
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        //Sets text to be centered within label

        //JPanel containing label for gameplay
        guessPanel = new JPanel();                   //Instantiation of panel
        add(guessPanel);                             //Panel added to JFrame
        guessPanel.setLayout(new BorderLayout());    //Sets layout manager of panel
        guessPanel.setBounds(240, 175, 660, 100);    //Sets size and location of panel
        guessPanel.setBackground(Color.WHITE);       //Panel background color set to white
        guessPanel.add(lblGuess = new JLabel("X X X X X X X X X X X"));
        //Adds lblGuess to panel and sets label
        //text to "X X X X X X X X X X X"
        lblGuess.setFont(lblGuess.getFont().deriveFont((64.0f)));
        //Sets font of label

        //JPanel containing label tracking the number of guesses player has made
        numGuessesPanel = new JPanel();                  //Instantiation of panel
        add(numGuessesPanel);                            //Panel added to frame
        numGuessesPanel.setLayout(new BorderLayout());   //Sets layout manager of panel
        numGuessesPanel.setBounds(30, 30, 300, 100);     //Sets size and location of panel
        numGuessesPanel.setBackground(Color.white);      //Sets background of panel to white
        numGuessesPanel.add(lblTries = new JLabel(""));  //Adds lblTries to panel
        lblTries.setFont(lblTries.getFont().deriveFont((20.0f)));
        //Sets font size of text within panel
        lblTries.setHorizontalAlignment(SwingConstants.CENTER);
        //Centers text within panel
        numGuessesPanel.setVisible(false);               //Panel is initially invisible -
        //Only becomes visible when game is starter

        initButtons();                                   //Call initButtons() to create and instantiate
        //all buttons within frame
        getRootPane().setDefaultButton(startStopBtn);    //Sets default button to startStopButton -
        //pressing the enter key "clicks" this button
        addKeyListener(this);                            //Adds a Key Listener to this frame
    }
    ////////////////////////// END initComponents() //////////////////////////////////

    ////////////////////// Method for instantiating all buttons //////////////////////
    private void initButtons() {

        //////////////////// 26 LetterButton objects for A-Z instantiated ////////////////
        btnA = new LetterButton("A");
        btnB = new LetterButton("B");
        btnC = new LetterButton("C");
        btnD = new LetterButton("D");
        btnE = new LetterButton("E");
        btnF = new LetterButton("F");
        btnG = new LetterButton("G");
        btnH = new LetterButton("H");
        btnI = new LetterButton("I");
        btnJ = new LetterButton("J");
        btnK = new LetterButton("K");
        btnL = new LetterButton("L");
        btnM = new LetterButton("M");
        btnN = new LetterButton("N");
        btnO = new LetterButton("O");
        btnP = new LetterButton("P");
        btnQ = new LetterButton("Q");
        btnR = new LetterButton("R");
        btnS = new LetterButton("S");
        btnT = new LetterButton("T");
        btnU = new LetterButton("U");
        btnV = new LetterButton("V");
        btnW = new LetterButton("W");
        btnX = new LetterButton("X");
        btnY = new LetterButton("Y");
        btnZ = new LetterButton("Z");
        //////////////////////////////////////////////////////////////////////////////////

        btnArray = new ArrayList<>(); //Initialize ArrayList containing all LetterButtons

        //////////////////////// LetterButtons added to ArrayList ////////////////////////
        btnArray.add(btnA);
        btnArray.add(btnB);
        btnArray.add(btnC);
        btnArray.add(btnD);
        btnArray.add(btnE);
        btnArray.add(btnF);
        btnArray.add(btnG);
        btnArray.add(btnH);
        btnArray.add(btnI);
        btnArray.add(btnJ);
        btnArray.add(btnK);
        btnArray.add(btnL);
        btnArray.add(btnM);
        btnArray.add(btnN);
        btnArray.add(btnO);
        btnArray.add(btnP);
        btnArray.add(btnQ);
        btnArray.add(btnR);
        btnArray.add(btnS);
        btnArray.add(btnT);
        btnArray.add(btnU);
        btnArray.add(btnV);
        btnArray.add(btnW);
        btnArray.add(btnX);
        btnArray.add(btnY);
        btnArray.add(btnZ);
        //////////////////////////////////////////////////////////////////////////////////

        //////////////// Logic for placement of LetterButtons within main JFrame /////////
        int btnX = 30;                                //Variable for setting X location
        //of button
        int btnY = 350;                               //Variable for setting Y location
        //of button
        for(int i = 0; i < btnArray.size(); i++) {    //For each button within ArrayList...
            add(btnArray.get(i));                     //Add LetterButton to JFrame
            btnArray.get(i).addActionListener(this);  //Add ActionListener to button
            btnArray.get(i).setLocation(btnX, btnY);  //Set location of LetterButton
            btnX += 100;                              //Increase X variable by 100

            if(btnX >= this.getSize().width - 100) {  //If next button would be placed off screen...
                btnX = 30;                            //Set X variable to 30
                btnY += 100;                          //Increase Y variable by 100
                //(starts a new row of buttons)
                if(btnY == 550) {                     //For third row, offset buttons (for aesthetics)
                    btnX = 380;
                }
            }
        }
        ///////////////////// End logic for LetterButton Placement ///////////////////////

        //StartStopButton instantiation
        startStopBtn = new StartStopButton();         //Instantiate StartStopButton
        add(startStopBtn);                            //Add StartStopButton to frame
        startStopBtn.addActionListener(this);         //Add ActionListener to button
        startStopBtn.setLocation(30, 675);            //Sets location of button
        startStopBtn.setText("START GAME");           //Sets text of button
    }
    ///////////////////////////// END initButtons() //////////////////////////////////

    //////////////////////////////// MAIN METHOD /////////////////////////////////////
    public static void main(String[] args) {
        Hangman window = new Hangman();
    }
    ////////////////////////////// END MAIN METHOD ///////////////////////////////////

    /////////////// ACTIONLISTENER FOR BUTTONS AND PRIMARY GAME LOGIC ////////////////
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(startStopBtn)) {      //If button pressed is StartStopButton...
            if(blnGameStarted == true) {              //If game is currently started...
                lblGuess.setText("X X X X X X X X X X X");
                //Clear lblGuess and replace with
                //"X X X X X X X X X X X"
                blnGameStarted = false;               //Set game state to stopped
                startStopBtn.setText("START GAME");   //Change StartStopButton text to reflect
                //current game state
                lblTries.setText("");                 //Clears the text of lblTries

                numGuessesPanel.setVisible(false);  //Makes guessTrackerPanel non-visible


            } else {                                  //If game is not currently started...
                lblGuess.setText("");                 //Set text of lblGuess to ""
                blnGameStarted = true;                //Set game state to started
                strToBeGuessed = selectWord();        //Call selectWord to pick a new word to
                // be guessed

                //For loop for adding '-' to lblGuess, number of '-' is equivalent to
                //the length of the currently selected word
                for(int i = 0; i < strToBeGuessed.length(); i++) {
                    lblGuess.setText(lblGuess.getText() + "-");
                    lblGuess.setHorizontalAlignment(SwingConstants.CENTER);
                }

                startStopBtn.setText("STOP GAME");    //Sets text of StartStopButton
                numGuesses = 0;                       //Sets guess counter back to 0
                numGuessesPanel.setVisible(true);   //Panel for tracking guesses is made visible
                lblTries.setText("Number of guesses so far: " + numGuesses);
                //Updates text of guess tracker panel
            }
        }

        //This block ensures LetterButton presses only do something if the game is started
        //If game is started...
        if(blnGameStarted == true) {
            //If ActionEvent e is a LetterButton
            if(e.getSource().equals(btnA)) {
                //Call checkWord() and pass it the letter associated with the LetterButton pressed,
                // and inactivate the button
                if(checkWord('A')) {
                    
                }
            }
            if(e.getSource().equals(btnB)) {
                checkWord('B');
            }
            if(e.getSource().equals(btnC)) {
                checkWord('C');
            }
            if(e.getSource().equals(btnD)) {
                checkWord('D');
            }
            if(e.getSource().equals(btnE)) {
                checkWord('E');
            }
            if(e.getSource().equals(btnF)) {
                checkWord('F');
            }
            if(e.getSource().equals(btnG)) {
                checkWord('G');
            }
            if(e.getSource().equals(btnH)) {
                checkWord('H');
            }
            if(e.getSource().equals(btnI)) {
                checkWord('I');
            }
            if(e.getSource().equals(btnJ)) {
                checkWord('J');
            }
            if(e.getSource().equals(btnK)) {
                checkWord('K');
            }
            if(e.getSource().equals(btnL)) {
                checkWord('L');
            }
            if(e.getSource().equals(btnM)) {
                checkWord('M');
            }
            if(e.getSource().equals(btnN)) {
                checkWord('N');
            }
            if(e.getSource().equals(btnO)) {
                checkWord('O');
            }
            if(e.getSource().equals(btnP)) {
                checkWord('P');
            }
            if(e.getSource().equals(btnQ)) {
                checkWord('Q');
            }
            if(e.getSource().equals(btnR)) {
                checkWord('R');
            }
            if(e.getSource().equals(btnS)) {
                checkWord('S');
            }
            if(e.getSource().equals(btnT)) {
                checkWord('T');
            }
            if(e.getSource().equals(btnU)) {
                checkWord('U');
            }
            if(e.getSource().equals(btnV)) {
                checkWord('V');
            }
            if(e.getSource().equals(btnW)) {
                checkWord('W');
            }
            if(e.getSource().equals(btnX)) {
                checkWord('X');
            }
            if(e.getSource().equals(btnY)) {
                checkWord('Y');
            }
            if(e.getSource().equals(btnZ)) {
                checkWord('Z');
            }
            //////////////////////////////////////////////////////////////////////////////////
            lblTries.setText("Number of guesses so far: " + numGuesses);
            //Updates lblTries with the number of
            //guesses made by player after each
            //LetterButton press
        }
    }
    ////////////////////////////// END ACTIONLISTENER ////////////////////////////////

    /////// Method for checking a character against the current selected word ////////
    private boolean checkWord(char c) {
        char[] guessCharArray = lblGuess.getText().toCharArray();
        //Converts the text from lblGuess into a
        //character array called guessCharArray
        boolean blnWordIsGuessed = true;             //Tracks if word has been guessed
        //For loop that checks the user input letter against each character of selected word
        for(int i = 0; i < strToBeGuessed.length(); i++) {
            if(c == strToBeGuessed.charAt(i)) {
                guessCharArray[i] = c;               //Sets character at guessCharArray[i] to c
            }
            if(guessCharArray[i] == '-') {           //Checks guessCharArray for a '-' character
                blnWordIsGuessed = false;            //blnWordIsGuessed set to false if found
            }
        }

        String newString = new String(guessCharArray);
        //New string is constructed from
        //guessCharArray
        lblGuess.setText(newString);                 //Sets text of lblGuess to newString
        if(!blnWordIsGuessed) {                      //If player has not yet guessed the word..
            numGuesses++;                            //Increment guess counter
            return false;
        }
        return true;
    }
    ///////////////////////////////// END checkWord() ////////////////////////////////

    ///////////////// Method for selecting word for current game /////////////////////
    private String selectWord() {
        int rnd = (int) (Math.random() * words.size());
        //Random integer is selected
        //from 1 to size of array
        String selected = words.get(rnd);           //Word is selected from [rnd]
        //postion in words array
        if(selected.equals(lastWordSelected)) {     //If selected word is the same as the previous one..
            selectWord();                           //Select a new word
        }
        System.out.println(selected);               //Prints to console the selected word (for testing)
        lastWordSelected = selected;                //Sets lastWordSelected for the next time
        //selectWord() is called
        return selected;                            //Returns the selected word
    }
    //////////////////////////////// END selectWord() ////////////////////////////////

    //Added support for keyboard input, pressing a key on the keyboard does the same thing
    //as clicking a LetterButton on screen. Game checks if a typed key is a letter (i.e. not
    //a number, symbol, enter key, etc) before calling the checkWord() method.
    @Override
    public void keyTyped(KeyEvent e) {
        try {
            if(Character.isLetter(e.getKeyChar()) && blnGameStarted ) {
                checkWord(Character.toUpperCase(e.getKeyChar()));
                //Takes the char from keyboard input,
                //converts it to uppercase, and then
                //uses it as a parameter for checkWord()
                lblTries.setText("Number of guesses so far: " + numGuesses);
                //Updates lblTries with the number of
                //guesses made by player after each
                //LetterButton press
            }
        } catch(Exception ex) {
            System.out.println("Something happened");//Prints to console if a keyboard press
            //would otherwise cause an error
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {}            //Not used

    @Override
    public void keyReleased(KeyEvent e) {}           //Not used
    ///////////////////////////// END KEYBOARD SUPPORT ///////////////////////////////

    /////////////////////////// NESTED CLASS FOR StartStopButton /////////////////////
    private class StartStopButton extends JButton { //Class extends the JButton class
        //Constructor
        public StartStopButton() {
            setSize(150, 70);                       //Sets size of button
            setFocusable(false);
        }   //End Constructor
    }
    /////////////////////////// END StartStopButton //////////////////////////////////

    ////////////////////////// NESTED CLASS FOR LetterButton /////////////////////////
    private class LetterButton extends JButton{     //Class extends JButton
        //Constructor, takes a String as input because setText() doesn't take chars
        public LetterButton(String c) {
            setText(c);                             //Sets button text to String c
            setSize(80, 80);                        //Sets button size
            setFont(getFont().deriveFont(28.0f));
            setFocusable(false);
        } //End constructor
    }
    ///////////////////////////// END LetterButton ///////////////////////////////////

} //////////////////////////////// END CLASS /////////////////////////////////////////