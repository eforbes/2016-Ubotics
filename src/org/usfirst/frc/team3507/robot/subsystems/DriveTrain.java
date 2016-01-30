package org.usfirst.frc.team3507.robot.subsystems;

import org.usfirst.frc.team3507.robot.RobotMap;
import org.usfirst.frc.team3507.robot.commands.DriveTrainTele;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {
    
    public CANTalon masterLeft = new CANTalon(RobotMap.leftMaster);
    public CANTalon slave1Left = new CANTalon(RobotMap.leftSlave1);
    public CANTalon slave2Left = new CANTalon(RobotMap.leftSlave2);
    public CANTalon masterRight = new CANTalon(RobotMap.rightMaster);
    public CANTalon slave1Right = new CANTalon(RobotMap.rightSlave1);
    public CANTalon slave2Right = new CANTalon(RobotMap.rightSlave2);
    
    public DriveTrain() {
    }

    public void initDefaultCommand() {
    	masterRight.setInverted(true);
    	slave1Left.changeControlMode(TalonControlMode.Follower);
    	slave2Left.changeControlMode(TalonControlMode.Follower);
    	slave1Right.changeControlMode(TalonControlMode.Follower);
    	slave2Right.changeControlMode(TalonControlMode.Follower);
    	slave1Left.set(RobotMap.leftMaster);
    	slave2Left.set(RobotMap.leftMaster);
    	slave1Right.set(RobotMap.rightMaster);
    	slave2Right.set(RobotMap.rightMaster);
    	setDefaultCommand(new DriveTrainTele());
    }
    
    public void go(double left, double right){
    	masterLeft.set(left);
    	masterRight.set(right);
    }
    
    public void stop(){
    	masterLeft.set(0);
    	masterRight.set(0);
    }
}

