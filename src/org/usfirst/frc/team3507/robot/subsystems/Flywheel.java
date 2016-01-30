package org.usfirst.frc.team3507.robot.subsystems;

import org.usfirst.frc.team3507.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Flywheel extends Subsystem 
{	
	public CANTalon motor = new CANTalon(RobotMap.flywheelMotor);
	
	public Flywheel()
	{
		motor.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Absolute);
	}
	
    public void initDefaultCommand() 
    {
    }
    
    public void go(double speed)
    {
    	motor.set(-1);
    }
    
    public void stop()
    {
    	motor.set(0);
    }
}

