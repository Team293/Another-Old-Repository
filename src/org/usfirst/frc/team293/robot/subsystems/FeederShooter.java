package org.usfirst.frc.team293.robot.subsystems;

import org.usfirst.frc.team293.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class FeederShooter extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	private TalonSRX L_motor, R_motor;
	private TalonSRX Angle_motor;
	private double position;
	//private TalonSRX L_motor, R_motor;
	
	public FeederShooter(){
		L_motor = new TalonSRX(0);
		L_motor.setSensorPhase(true);
		R_motor = new TalonSRX(1);
		R_motor.setSensorPhase(true);
		Angle_motor = new TalonSRX(5);
		L_motor.config_kF(0, .08, 10);
		L_motor.config_kP(0, 0.1, 10);
		L_motor.config_kI(0, 0.05 , 10);
		L_motor.config_kD(0, 0.5, 10);
		
		L_motor.config_IntegralZone(0, 20, 10);
		L_motor.configMaxIntegralAccumulator(0, 400, 10);
		
		R_motor.config_kF(0, .084, 10);
		R_motor.config_kP(0, 0.1, 10);
		R_motor.config_kI(0, .05 , 10);
		R_motor.config_kD(0, .5, 10);
		
		R_motor.config_IntegralZone(0, 20, 10);
		R_motor.configMaxIntegralAccumulator(0, 400, 10);
		
		//Set up angle motor for closed loop position control
		Angle_motor.config_kF(0, 0.0, 10);
		Angle_motor.config_kP(0, 0.8, 10);
		Angle_motor.config_kI(0, 0.0, 10);
		Angle_motor.config_kD(0, 0.0, 10);
		
		Angle_motor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0,
				10);
		
		Angle_motor.configAllowableClosedloopError(0, 0, 10);
		
		Angle_motor.setSensorPhase(false);
		Angle_motor.setInverted(false);

		Angle_motor.configNominalOutputForward(0, 10);
		Angle_motor.configNominalOutputReverse(0, 10);
		Angle_motor.configPeakOutputForward(1, 10);
		Angle_motor.configPeakOutputReverse(-1, 10);

		int absolutePosition = Angle_motor.getSensorCollection().getPulseWidthPosition();
		/* mask out overflows, keep bottom 12 bits */
		absolutePosition &= 0xFFF;
		/*if (false)
			absolutePosition *= -1;
		if (false)
			absolutePosition *= -1;
			*/
		/* set the quadrature (relative) sensor to match absolute */
		Angle_motor.setSelectedSensorPosition(absolutePosition, 0, 10);

	}
	
		
	

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new ExampleCommand());
	}
	public void shoot(double power){
		L_motor.set(ControlMode.PercentOutput, power);
		R_motor.set(ControlMode.PercentOutput, (-1)*power);
		//L_motor.set(ControlMode.PercentOutput, power);
		//R_motor.set(ControlMode.PercentOutput, (-1)*power);
	}
	public void moveAngular(double position){
		Angle_motor.set(ControlMode.Position, position);
		//SmartDashboard.putNumber("", value)
	}
	public boolean isInPosition(){
		return(Math.abs(Angle_motor.getSelectedSensorPosition(0)-position)<=20);
	}
	public void moverpm(double rpm){
		L_motor.set(ControlMode.Velocity, rpm);
		R_motor.set(ControlMode.Velocity, (-1)*rpm);
	}
}