package org.usfirst.frc.team3507.robot.subsystems;

import org.usfirst.frc.team3507.robot.Robot;
import org.usfirst.frc.team3507.robot.RobotMap;
import org.usfirst.frc.team3507.robot.commands.DriveTrainTele;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {
    
    public CANTalon masterLeft = new CANTalon(RobotMap.leftMaster);
    public CANTalon slave1Left = new CANTalon(RobotMap.leftSlave1);
    public CANTalon slave2Left = new CANTalon(RobotMap.leftSlave2);
    public CANTalon masterRight = new CANTalon(RobotMap.rightMaster);
    public CANTalon slave1Right = new CANTalon(RobotMap.rightSlave1);
    public CANTalon slave2Right = new CANTalon(RobotMap.rightSlave2);
    
    public double speedL;
    public double speedR;
    
    public DriveTrain() {
    	masterRight.setInverted(true);
    	slave1Left.changeControlMode(TalonControlMode.Follower);
    	slave2Left.changeControlMode(TalonControlMode.Follower);
    	slave1Right.changeControlMode(TalonControlMode.Follower);
    	slave2Right.changeControlMode(TalonControlMode.Follower);
    	slave1Left.set(RobotMap.leftMaster);
    	slave2Left.set(RobotMap.leftMaster);
    	slave1Right.set(RobotMap.rightMaster);
    	slave2Right.set(RobotMap.rightMaster);
    	masterLeft.enableBrakeMode(true);
    	slave1Left.enableBrakeMode(true);
    	slave2Left.enableBrakeMode(true);
    	masterRight.enableBrakeMode(true);
    	slave1Right.enableBrakeMode(true);
    	slave2Right.enableBrakeMode(true);
    }

    public void initDefaultCommand() {
    	setDefaultCommand(new DriveTrainTele());
    }
    
    public void go(double left, double right){
    	Preferences prefs = Preferences.getInstance();
    	double fact = prefs.getDouble("Drive Factor", 1);
    	speedL = left;
    	speedR = right;
    	
    	if (Robot.accelType.getSelected().equals(0)) {
    		masterLeft.set(left*fact);
    		masterRight.set(right*fact);
    	} else {
    		if (left < 0) {
    			masterLeft.set(-(Math.pow(left, 2)*fact));
    		} else {
    			masterLeft.set((Math.pow(left, 2)*fact));
    		}
    		if (right < 0) {
    			masterRight.set(-(Math.pow(right, 2)*fact));
    		} else {
    			masterRight.set((Math.pow(right, 2)*fact));
    		}
    	}
    }
    
    public void stop(){
    	masterLeft.set(0);
    	masterRight.set(0);
    }
}

