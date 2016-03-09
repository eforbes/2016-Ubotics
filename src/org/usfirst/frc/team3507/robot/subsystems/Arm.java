package org.usfirst.frc.team3507.robot.subsystems;

import org.usfirst.frc.team3507.robot.RobotMap;
import org.usfirst.frc.team3507.robot.commands.ArmAnglePotentiometer;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Arm extends Subsystem {

	public CANTalon motor = new CANTalon(RobotMap.armMotor);
	DigitalInput limitUp = new DigitalInput(2);
	DigitalInput limitDown = new DigitalInput(3);
	
	public AnalogInput pot = new AnalogInput(1);
	
	public Arm() {
		motor.enableBrakeMode(true);
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new ArmAnglePotentiometer());
    }
    
    public void go(double speed) {
//    	SmartDashboard.putBoolean("arm limit up", limitUp.get());
//    	SmartDashboard.putBoolean("arm limit down", limitDown.get());
    	if(speed > 0 && limitUp.get()) {
    		stop();
    		return;
    	}
    	//TODO: when second limit switch is added to the arm
//    	if(speed < 0 && limitDown.get()) {
//    		stop();
//    		return;
//    	}
    	motor.set(speed);
    }
    
    public void stop() {
    	motor.set(0);
    }

}

