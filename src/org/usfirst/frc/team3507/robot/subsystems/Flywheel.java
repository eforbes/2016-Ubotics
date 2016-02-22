package org.usfirst.frc.team3507.robot.subsystems;

import org.usfirst.frc.team3507.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Flywheel extends Subsystem 
{	
	public CANTalon motor = new CANTalon(RobotMap.flywheelMotor);

	public State currentState;
	public Flywheel()
	{
		motor.changeControlMode(TalonControlMode.Speed);
		motor.setF(0.026759);
		motor.setP(0.15); //.1705
		motor.setI(0);
		motor.setD(0);
		motor.setVoltageRampRate(8);
		motor.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Absolute);
		motor.enableBrakeMode(false);
		currentState = State.OFF;
	}
	
    public void initDefaultCommand() 
    {
    }
    
    public void setState(State newState)
    {
    	Preferences prefs = Preferences.getInstance();
    	currentState = newState;
    	switch(newState) {
    		case OFF: 
    			motor.set(0);
    			break;
    		case SLOW:
    			motor.set(prefs.getDouble("Flywheel Slow Speed", 4500));
    			break;
    		case FAST:
    			motor.set(prefs.getDouble("Flywheel Fast Speed", 6150));
    			break;
    		case AUTO:
    			motor.set(prefs.getDouble("Flywheel Auto Speed", 6000));
    			break;
    	}
    }
    
    public void stop()
    {
    	motor.set(0);
    }
    
    public boolean isAtSpeed() {
    	//return Math.abs(motor.getSpeed() - motor.getSetpoint()) < 100;
    	return motor.getClosedLoopError() < 2;
    }
    
    public double getTargetPercent() {
    	if(currentState == State.OFF) {
    		return 0;
    	}
    	return Math.abs(((double) motor.getSpeed()) / ((double) motor.getSetpoint()));
    }
    
    public enum State {
    	OFF, SLOW, FAST, AUTO;
    }
}

