package org.usfirst.frc.team3507.robot.subsystems;

import org.usfirst.frc.team3507.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem 
{

	public CANTalon motor = new CANTalon(RobotMap.intakeMotor);

	public DigitalInput btn1;
	public DigitalInput btn2;
	
	public Intake() 
	{
		motor.enableBrakeMode(false);
    	btn1 =  new DigitalInput(0);
    	btn2 = new DigitalInput(1);
	}
	
    public void initDefaultCommand() 
    {
    }
    
    public void go(double speed)
    {
    	motor.set(speed);
    }
    
    public void stop()
    {
    	motor.set(0);
    }
}

