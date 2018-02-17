package org.usfirst.frc.team293.robot.subsystems;

import org.usfirst.frc.team293.robot.RobotMap;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ClimberRelease extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private Servo Release;
	
	public ClimberRelease(){
		Release = new Servo(RobotMap.Release);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void move(double angle){
    	Release.set(angle);
    }
}

