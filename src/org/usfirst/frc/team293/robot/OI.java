/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team293.robot;


import org.usfirst.frc.team293.robot.commands.AfterburnerAdjustable;
import org.usfirst.frc.team293.robot.commands.AfterburnerFullThrottle;
import org.usfirst.frc.team293.robot.commands.AfterburnerHalfThrottle;
import org.usfirst.frc.team293.robot.commands.AfterburnerRPM;
import org.usfirst.frc.team293.robot.commands.Extend_Cylinder;
import org.usfirst.frc.team293.robot.commands.FeederAngle;
import org.usfirst.frc.team293.robot.commands.FeederThrottle;
import org.usfirst.frc.team293.robot.commands.FeedtoAfterburnerShoot;
import org.usfirst.frc.team293.robot.commands.MoveServoJoystick;
import org.usfirst.frc.team293.robot.commands.Retract_Cylinder;
import org.usfirst.frc.team293.robot.commands.StopAfterburner;
import org.usfirst.frc.team293.robot.commands.StopFeeder;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public static Joystick leftStick = new Joystick(0);
	public static Joystick rightStick=new Joystick(1);
	
	public OI(){
		JoystickButton[] left= {null,new JoystickButton(leftStick,1), 
				   new JoystickButton(leftStick,2), 
				   new JoystickButton(leftStick,3), 
				   new JoystickButton(leftStick,4), 
				   new JoystickButton(leftStick,5), 
				   new JoystickButton(leftStick,6), 
				   new JoystickButton(leftStick,7), 
				   new JoystickButton(leftStick,8), 
				   new JoystickButton(leftStick,9), 
				   new JoystickButton(leftStick,10),};
		JoystickButton[] right= {null,new JoystickButton(rightStick,1), 
				   new JoystickButton(rightStick,2), 
				   new JoystickButton(rightStick,3), 
				   new JoystickButton(rightStick,4), 
				   new JoystickButton(rightStick,5), 
				   new JoystickButton(rightStick,6), 
				   new JoystickButton(rightStick,7), 
				   new JoystickButton(rightStick,8), 
				   new JoystickButton(rightStick,9), 
				   new JoystickButton(rightStick,10),};
		left[1].whileHeld(new FeederThrottle());
		left[1].whenReleased(new StopFeeder());
		left[2].toggleWhenPressed(new AfterburnerFullThrottle());
		left[2].whenReleased(new StopAfterburner());
		left[3].whileHeld(new AfterburnerAdjustable());
		left[3].whenReleased(new StopAfterburner());
		left[10].whileHeld(new AfterburnerRPM());
		//left[10].whileHeld(new AfterburnerAdjustable());
		left[10].whenReleased(new StopAfterburner());
		left[5].toggleWhenPressed(new AfterburnerHalfThrottle());
		left[5].whenReleased(new StopAfterburner());
		left[6].whenPressed(new Extend_Cylinder());
		left[6].whenReleased(new Retract_Cylinder());
		left[9].whileHeld(new FeederAngle());
		right[1].whileHeld(new MoveServoJoystick());
		
	}
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
}