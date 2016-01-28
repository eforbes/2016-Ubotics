package org.usfirst.frc.team3507.robot.subsystems;

import org.usfirst.frc.team3507.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Arm extends Subsystem {

	public CANTalon motor = new CANTalon(RobotMap.armMotor);
	
	public Arm() {
	}
	
    public void initDefaultCommand() {
    }
    
    public void go(double speed) {
    	motor.set(speed);
    }
    
    public void stop() {
    	motor.set(0);
    }
}

