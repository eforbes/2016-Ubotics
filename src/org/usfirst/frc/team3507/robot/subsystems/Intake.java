package org.usfirst.frc.team3507.robot.subsystems;

import org.usfirst.frc.team3507.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem 
{

	public CANTalon motor = new CANTalon(RobotMap.intakeMotor);
	
	public Intake() 
	{
	}
	
    public void initDefaultCommand() 
    {
    }
    
    public void in(double speed)
    {
    	motor.set(speed);
    }
    
    public void stop()
    {
    	motor.set(0);
    }
    
    public void out(double speed)
    {
    	motor.set(speed);
    }
}

