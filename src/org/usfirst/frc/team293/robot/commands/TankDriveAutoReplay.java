package org.usfirst.frc.team293.robot.commands;

import java.io.FileNotFoundException;

import org.usfirst.frc.team293.robot.OI;
import org.usfirst.frc.team293.robot.Robot;

import Autonomouses.ReplayFile;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TankDriveAutoReplay extends Command {
	private double left;
	private double right;
	private double outleft;
	private double outright;
	
	ReplayFile replay;
	int fileEOF = 0;
	
    public TankDriveAutoReplay() {
    	requires(Robot.TrainofDriving);
    	replay = new ReplayFile();    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	try {
    		replay.init(3);
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
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
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	fileEOF = replay.playBack(); // gets next line in opened file, copied to chanValues[]

    	System.out.println("************************************");
    	System.out.println(" LeftRead: " + replay.chanValues[0] + " RightRead: " + replay.chanValues[1]);
    	System.out.println("************************************");
    	this.stickCooker(replay.chanValues[0], replay.chanValues[1]);
    	right = outright;
    	left = outleft;
    	
    	//Robot.TrainofDriving.tankdrive((-1*left), (-1*right));
    	System.out.println("************************************");
    	System.out.println(" Left: " + left + " Right: " + right);
    	System.out.println("************************************");
    	Robot.TrainofDriving.feedForwardEncoderDrive((-1*left), (-1*right));
    	//Robot.TrainofDriving.encoderDrive(left, right);
//    	Robot.TrainofDriving.encoderDrive(-1*(outright), -1*(outleft));
    	
    }
    
    void stickCooker(double vleft, double vright) {
    	// Performs selected sensitivity on stick displacements
    	// UGLY define of limit points
    	double in1 = 0.35;
    	double out1 = 0.25;
    	// Get signs of each
    	double ls = Math.signum(vleft);
    	double rs = Math.signum(vright);
    	// Check each stick if below threshold
    	if ( Math.abs(vright) < in1 ) {
    		outright = vright * (out1 / in1 ); // low gain range
    	} else { 
    		outright = rs * (out1 + ( (rs * vright)- in1 )*(1-out1)/(1-in1));
    	}
    	// Same as left side
    	if ( Math.abs(vleft) < in1 ) {
    		outleft = vleft * (out1 / in1 ); // low gain range
    	} else { 
    		outleft = ls * (out1 + ( (ls * vleft)- in1 )*(1-out1)/(1-in1));
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double cumsum;
    	cumsum = 0.;
/*    	for (int i=0; i<replay.chanValues.length; i++) {
    		cumsum += replay.chanValues[i];
    	}
    	if (0.0 >= cumsum) {
    		return false;
    	} else {
    		return true;
    	}
*/
    	if ( 1 == fileEOF ) {
    		return true;
    	} else {
    		return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
