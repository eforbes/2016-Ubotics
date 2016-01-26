package org.usfirst.frc.team3507.robot.subsystems;

import org.usfirst.frc.team3507.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ArmAngle extends Subsystem {

	public CANTalon motor = new CANTalon(RobotMap.armMotor);
	
	public ArmAngle() {
	}
	
    public void initDefaultCommand() {
    }
    
    public void up(double speed) {
    	motor.set(speed);
    }
    
    public void stop() {
    	motor.set(0);
    }
    
    public void down(double speed) {
    	motor.set(-speed);
    }
}

