package Autonomouses;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * ReplayFile reads in the desired autonomous file number and extracts
 * recorded motor. controls, encoders, and AHRS sensor data for autonomous mode.
 * NOTE: This is a "home grown" CSV reader that wants to see a SPECIFIC FORMAT,
 * which appends a comma and space after each double, then appends a newline at the end of
 * each row of data.  The newline is read in separately as an explicit character to avoid 
 * getting "fancy" with the options for parsing, or re-writing the logger routine.  Sorry.
 * We are building this for speed, since the file I/O has to happen in real-time autonomous!
 */
public class ReplayFile {

	Scanner scanner;
	File fp;
	long startTime;

	boolean onTime = true;
	double nextDouble;
	double timeHack;

	public double chanValues[] = {.1, .1, .1, .1, .1, .1 };

	public ReplayFile() {
	}
	
	public void init( int fnum ) throws FileNotFoundException {
		// Opens up file for reading, and records current time
		// fnum is autonomous file number

		// autoFile is the playback file name
		String autoFile = new String("/home/lvuser/recordedAuto" + fnum + ".csv");
		System.out.println("Looking for file: " + autoFile);
		// scanner is the object for extracting the values
		try {
			fp = new File( autoFile );
		} catch (Error e) {
			System.out.println("**************************************");
			System.out.println("**************************************");
			System.out.println("**************************************");
			System.out.println("**************************************");
			System.out.println("     Issues with opening file???");
			System.out.println("**************************************");
			System.out.println("**************************************");
			System.out.println("**************************************");
			System.out.println("**************************************");
		}
		scanner = new Scanner(fp);

		// Let scanner know that the numbers are separated by a comma or a newline, as it is a .csv file
//		scanner.useDelimiter(",|\\n|\\r");
		scanner.useDelimiter(", "); // comma then space
		// First read in the two lines of text to get to the numerics
		scanner.nextLine(); // the channel names
		scanner.nextLine(); // the channel units
		// Lets set start time to the current time you begin autonomous
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("  Got the auto file - now processing!");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		startTime = System.currentTimeMillis();	
	}
	
	public int playBack() {
		
		double t_delta;
		double tempDouble;
		int EOF = 0; // flag for end of file (returned from call)
		int numChannels = 6; // current files have t, then 6 channels
		
		//if recordedAutoXX.csv has a double to read next, then read it
//		if ((scanner != null) && (scanner.hasNextDouble()))
//		if (scanner != null) 
		if (scanner.hasNextDouble())
		{	
			EOF = 0; // more to read!
			// if we have waited the recorded amount of time assigned to each
			// respective motor value, then move on to the next time value
			// prevents the macro playback from getting ahead of itself and writing
			// different motor values too quickly.  HOWEVER, we don't trap for being
			// too slow - yet.  That would have us keep reading the file to catch up.
			if(onTime)
			{
				//take next time value
				timeHack = scanner.nextDouble();
				System.out.println("**************************************");
				System.out.println("**************************************");
				System.out.println("**************************************");
				System.out.println("**************************************");
				System.out.println("    File time : " + timeHack );
				System.out.println("**************************************");
				System.out.println("**************************************");
				System.out.println("**************************************");
				System.out.println("**************************************");
			}
			
			// Get time recorded for values minus how far into replaying it we are
			// If not negative or zero, hold up, as we have "future" data
			t_delta = timeHack - (System.currentTimeMillis()-startTime)/1000.;
			
			//if we are on time, then set motor values
			if (t_delta <= 0)
			{
				// Get the remaining doubles on this line of the file
				for ( int i=0; i<numChannels; i++) {
					chanValues[i] = scanner.nextDouble();
				}
				try {
					scanner.nextLine(); // read in the EOL character!
				} catch (Error e) {
					// do nothing, this is EOF!
				}
//				System.out.print("\n");
				// All the channels available, so make it dance!

/*			for (int i=0;i<6;i++) {
				System.out.print(" " + chanValues[i]);
			}
			System.out.print("\n");;
*/
				
				//go to next time point
				onTime = true;
			}
			//else don't change the values of the motors until we are "onTime"
			else
			{
				onTime = false;
			}
		}
		//end play, there are no more values to find
		else
		{
			if (scanner != null) 
			{
				EOF = 1;
				scanner.close();
//				scanner = null;
				// Maybe set final channel values to zero??
				for ( int i=0; i<numChannels; i++) {
					chanValues[i] = 0.0;
				}				
			}
		}
		return EOF;
	}
	
	public void close() {
		scanner.close();
		scanner = null;
	}
}
