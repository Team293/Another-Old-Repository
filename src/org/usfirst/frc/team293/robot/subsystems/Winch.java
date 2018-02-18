package org.usfirst.frc.team293.robot.subsystems;

import org.usfirst.frc.team293.robot.RobotMap;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Winch extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
		private Spark Climber1;
		private Spark Climber2;
	public Winch(){
		Climber1 = new Spark(RobotMap.climbMotors[0]);
		Climber2 = new Spark(RobotMap.climbMotors[1]);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void move(double pwm){
    	Climber1.set(pwm);
    	Climber2.set(pwm);
    }
}


