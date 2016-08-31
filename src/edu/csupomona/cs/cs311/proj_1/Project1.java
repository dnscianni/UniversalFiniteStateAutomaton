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

import java.io.*;
import java.util.Scanner;

/**
 * Project 1 uses the Automata object to test if a certain string is located in
 * a language or not. By inputting the behavior of the FSA, it is possible to
 * build any DFSA for a language.
 * 
 * @author David Scianni
 * 
 */
public class Project1 {

	/**
	 * Main will load up a data file and check the behavior of each language in
	 * the file, and run a test on several strings to see if they are in the
	 * language. The program uses the string %% to distinguish between the
	 * previous FSA and the next.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		/**
		 * myFile will hold the file automata.dat, which holds the different
		 * FSAs
		 */
		File myFile = new File("automata.dat");

		/**
		 * fsa is the Automata object that the program uses to process the
		 * information in the automata.dat file
		 */
		Automata fsa;

		try {

			/**
			 * iFile is the scanner that will read from the file myFile, to get
			 * the information about the FSAs
			 */
			Scanner iFile = new Scanner(myFile);

			/**
			 * states, finalStates, alphabet, transition, and word will hold the
			 * information taken from the data file
			 */
			String states, finalStates, alphabet, transition, word;

			/**
			 * counter will keep track of the number of FSAs being taken from
			 * the data file
			 */
			int counter = 1;

			/**
			 * while the data file has information still in it, this loop will
			 * take all the information, including the number of states, the
			 * final states, the alphabet, and the transition information, and
			 * send it to the Automata object. It will then take in the words
			 * from the data file, and test to see if it is in the language,
			 * displaying all the information, including whether or not the word
			 * was accepted into the language.
			 */
			while (iFile.hasNext()) {
				states = iFile.nextLine();
				System.out.println("\nFinite State Automaton #" + counter++);
				System.out.println("Number of states: " + states);
				finalStates = iFile.nextLine();
				System.out.println("Final States: " + finalStates);
				alphabet = iFile.nextLine();
				System.out.println("Alphabet: " + alphabet);
				fsa = new Automata(states, finalStates, alphabet);
				System.out.println("Transitions: ");
				for (int i = 0; i < fsa.getTransitionAmount(); i++) {
					transition = iFile.nextLine();
					fsa.insertTransition(transition);
					System.out.println("\t" + transition);
				}
				System.out.println("Strings: ");
				word = iFile.nextLine();
				while (word.compareTo("%%") != 0) {
					System.out.print("\t" + word);
					if (fsa.checkWord(word)) {
						System.out.println("...............Accepted");
					} else {
						System.out.println("...............Rejected");
					}
					word = iFile.nextLine();
				}
			}

			iFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
