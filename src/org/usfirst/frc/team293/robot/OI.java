package org.usfirst.frc.team293.robot;


import org.usfirst.frc.team293.robot.commands.AfterburnerAdjustable;
import org.usfirst.frc.team293.robot.commands.AfterburnerFullThrottle;
import org.usfirst.frc.team293.robot.commands.AfterburnerRPM;
import org.usfirst.frc.team293.robot.commands.AfterburnerShoot;
import org.usfirst.frc.team293.robot.commands.CalibrateFeeder;
import org.usfirst.frc.team293.robot.commands.Climb;
import org.usfirst.frc.team293.robot.commands.Extend_Cylinder;
import org.usfirst.frc.team293.robot.commands.Feed;
import org.usfirst.frc.team293.robot.commands.FeedToExchange;
import org.usfirst.frc.team293.robot.commands.FeedToUpperPosition;
import org.usfirst.frc.team293.robot.commands.FeederAngle;
import org.usfirst.frc.team293.robot.commands.FeederFullDown;
import org.usfirst.frc.team293.robot.commands.FeederFullUp;
import org.usfirst.frc.team293.robot.commands.FeederRPM;
import org.usfirst.frc.team293.robot.commands.FeederRelease;
import org.usfirst.frc.team293.robot.commands.FeederThrottle;
import org.usfirst.frc.team293.robot.commands.FeedtoAfterburnerShoot;
import org.usfirst.frc.team293.robot.commands.Retract_Cylinder;
//import org.usfirst.frc.team293.robot.commands.MoveServoJoystick;
import org.usfirst.frc.team293.robot.commands.StopAfterburner;
import org.usfirst.frc.team293.robot.commands.StopClimbing;
import org.usfirst.frc.team293.robot.commands.StopFeeder;
import org.usfirst.frc.team293.robot.commands.Unclimb;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public static Joystick leftStick = new Joystick(0);
	public static Joystick rightStick = new Joystick(1);
	public static Joystick launchpad=new Joystick(2);
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
		JoystickButton button1=new JoystickButton(launchpad, 1);
		JoystickButton button2=new JoystickButton(launchpad, 2);
		JoystickButton button3=new JoystickButton(launchpad, 3);
		JoystickButton button4=new JoystickButton(launchpad, 4);
		JoystickButton button5=new JoystickButton(launchpad, 5);
		JoystickButton button6=new JoystickButton(launchpad, 6);
		JoystickButton button7=new JoystickButton(launchpad, 7);
		JoystickButton button8=new JoystickButton(launchpad, 8);
		JoystickButton button9=new JoystickButton(launchpad, 9);	
		JoystickButton button10=new JoystickButton(launchpad, 10);
		JoystickButton button11=new JoystickButton(launchpad, 11);
		JoystickButton button12=new JoystickButton(launchpad, 12);
		//left[1].whenReleased(new StopFeeder());
		//left[2].toggleWhenPressed(new AfterburnerFullThrottle());
		//left[2].whenReleased(new StopAfterburner());
		//left[3].whileHeld(new AfterburnerAdjustable());
		//left[3].whenReleased(new StopAfterburner());
		//left[4].whileHeld(new AfterburnerRPM(3574.2));
		//left[4].whenReleased(new StopAfterburner());
		//button7.whileHeld(new Climb());
		//left[5].whenPressed(new CalibrateFeeder());
		//button1.whenPressed(new FeedToExchange());
		//button7.toggleWhenPressed(new AfterburnerFullThrottle());
		button4.whenPressed(new FeederAngle(0));
		button6.whenPressed(new FeederFullUp());
		button2.whenPressed(new FeederAngle(60));
		//button3.whileHeld(new FeedToUpperPosition());
		button3.whileHeld(new FeederThrottle(1));
		button5.whileHeld(new FeederRelease(-1));
		button5.whenReleased(new StopFeeder());
		button3.whenReleased(new StopFeeder());
		button1.whileHeld(new Extend_Cylinder());
		button1.whenReleased(new Retract_Cylinder());
		button9.whenPressed(new Feed());
		//button9.whenReleased(new FeedtoAfterburnerShoot());
		//button10.whenPressed(new AfterburnerShoot());
		button10.whenPressed(new AfterburnerRPM(1));
		button10.whenReleased(new StopAfterburner());
		//button11.whenPressed(new LEDsTest());
		button11.whenReleased(new StopAfterburner());
		button12.whenPressed(new AfterburnerRPM(.4));
		button12.whenReleased(new StopAfterburner());
		button8.whileHeld(new Climb());
		button8.whenReleased(new StopClimbing());
		button7.whileHeld(new Unclimb());
		button7.whenReleased(new StopClimbing());
		
		
		//right[1].whileHeld(new MoveServoJoystick());
		
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
