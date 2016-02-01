package org.usfirst.frc.team3507.robot.subsystems;

import org.usfirst.frc.team3507.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Arm extends Subsystem {

	public CANTalon motor = new CANTalon(RobotMap.armMotor);
	
	public Arm() {
		motor.enableBrakeMode(true);
	}
	
    public void initDefaultCommand() {
    }
    
    public void go(double speed) {
    	SmartDashboard.putNumber("Arm Speed", speed);
    	motor.set(speed);
    }
    
    public void stop() {
    	motor.set(0);
    }
}

