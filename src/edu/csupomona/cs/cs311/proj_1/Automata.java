/**
 * CS 311: Language Translation and Automata
 * Professor: Daisy Sang
 *
 * Programming Assignment #1
 *
 * To run this project, open up Eclipse, and click file -> import.
 * From the import page, open up the General folder, and select 
 * "Existing Projects into Workspace", and click next.  From this 
 * page, select browse next to the "Select root directory: " option, 
 * and select the "UniversalFiniteStateAutomaton" folder, to import 
 * the whole project. On the left side is the Package Explorer, open 
 * up the UniversalFiniteStateAutomaton folder, then the src folder, 
 * then the edu.csupomona.cs.cs311.proj_1 package.  From there open 
 * up the Project1.java file. To compile the code and run it, from 
 * the Project1 file, click the Run button in the top list of options, 
 * and click the run option, which should have a green circle with a 
 * white arrow icon next to it.
 *
 * David Scianni
 */
package edu.csupomona.cs.cs311.proj_1;

/**
 * The Automata object will create a generic FSA, with the capability to create
 * any DFSA possible, as long as the behavior of the DFSA is sent to the object.
 * 
 * @author David Scianni
 * 
 */
public class Automata {

	/**
	 * states holds the number of states in the FSA
	 */
	private int states;

	/**
	 * Final states is a boolean array of size states, that will be true in
	 * index i if i represents a state that is a final state
	 */
	private boolean[] finalStates;

	/**
	 * alphabet is an array of characters that represent the alphabet of the
	 * language. Each index holds an element of the alphabet
	 */
	private char[] alphabet;

	/**
	 * transitions is a table that holds the transition behavior of the FSA. It
	 * is defined by tranitions[i][j], where i represents the current state, j
	 * represents the character from the alphabet that is the next character in
	 * the word we are checking, and transitions[i][j] holds the next state that
	 * these two choices lead to.
	 */
	private String[][] transitions;

	/**
	 * The Automata constructor will take in three strings, representing the
	 * number of states, the set of final states, and the set containing the
	 * alphabet. to hold the number of states, the program parses the integer,
	 * and stores it in states. finalStates is initialized as an array of size
	 * states, and changes the index of the string fs to true. It then fills
	 * alphabet with the characters in a. Lastly it initializes transitions as a
	 * new 2d array of size alphabet.length by states.
	 * 
	 * @param s
	 *            Holds the number of states
	 * @param fs
	 *            Holds the set of final states in the form "a b c"
	 * @param a
	 *            Holds the set of alphabet characters in the form "a b c d"
	 */
	public Automata(String s, String fs, String a) {
		states = Integer.parseInt(s);

		finalStates = new boolean[states];
		fs = fs.replaceAll("\\s", "");
		char[] temp = fs.toCharArray();
		for (int i = 0; i < temp.length; i++) {
			finalStates[Integer.parseInt(Character.toString(temp[i]))] = true;
		}

		a = a.replaceAll("\\s", "");
		alphabet = a.toCharArray();

		transitions = new String[alphabet.length][states];
	}

	/**
	 * checkWord will take in the word we are checking and replace it as an
	 * array of characters, so as to check each character individually. It will
	 * first check to see if the counter has gone past the length of the array,
	 * meaning that we reached the end of the word. If we have then it will
	 * return the result of finalState[state], which will return true if the
	 * last state we were in is a final state or false if it is not. after this
	 * check it will check to see if the next symbol is in the alphabet, and if
	 * it is not, it will return false. If it is in the alphabet, then the state
	 * will change to the next state, depending on the transition behavior. It
	 * will then check to see if it has gone into a trap state, and if so, it
	 * will return false.
	 * 
	 * @param word
	 *            the word we are testing to see if it is in the language
	 * @return true if the word is in the language, and false if it is not
	 */
	public boolean checkWord(String word) {
		int state = 0;
		int counter = 0;
		int symbol;
		char[] charWord = word.toCharArray();

		while (true) {
			if (counter >= charWord.length) {
				return finalStates[state];
			}
			symbol = inAlphabet(charWord[counter]);
			counter++;
			if (symbol >= 0) {
				state = nextState(state, symbol);
				if (state == states) {
					return false;
				}
			} else {
				return false;
			}
		}
	}

	/**
	 * The NextState method will return the integer representation of the next
	 * state given the current state and the current symbol given.
	 * 
	 * @param state
	 *            the current state
	 * @param symbol
	 *            the current symbol in the word
	 * @return the integer representation of the next state
	 */
	private int nextState(int state, int symbol) {

		return Integer.parseInt(transitions[symbol][state]);
	}

	/**
	 * The inAlphabet method will search the alphabet array to see if the
	 * character c is in the alphabet
	 * 
	 * @param c
	 *            the character we are testing
	 * @return the index of the alphabet array that the character is located in,
	 *         or -1 if it is not in the alphabet
	 */
	private int inAlphabet(char c) {
		for (int i = 0; i < alphabet.length; i++) {
			if (c == alphabet[i]) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * The insertTransition method will take the transition string in the form
	 * "(a b c)", and will get rid of the parentheses, and the spaces and will
	 * take the value c and insert it into the array transitions[b][a]
	 * 
	 * @param t
	 *            the string that holds the transition behavior
	 */
	public void insertTransition(String t) {
		t = t.replaceAll("\\(", "");
		t = t.replaceAll("\\)", "");
		String[] temp = t.split("\\s");

		transitions[Integer.parseInt(temp[1])][Integer.parseInt(temp[0])] = temp[2];
	}

	/**
	 * Gets the amount of transitions
	 * 
	 * @return the length of transitions multiplied by the length of each 2nd
	 *         dimension of the array.
	 */
	public int getTransitionAmount() {
		return transitions.length * transitions[0].length;
	}
}
