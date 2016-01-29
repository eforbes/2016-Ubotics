package org.usfirst.frc.team3507.robot.subsystems;

import org.usfirst.frc.team3507.robot.RoboUtil;
import org.usfirst.frc.team3507.robot.RobotMap;
import org.usfirst.frc.team3507.robot.commands.DriveTrainTele;

import edu.wpi.first.wpilibj.CANTalon;
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
    	setDefaultCommand(new DriveTrainTele());
    }
    
    public void go(double left, double right, double deadzone){
    	double scaleLeft = RoboUtil.deadzone(left, deadzone);
    	double scaleRight = RoboUtil.deadzone(right, deadzone);
    	masterLeft.set(scaleLeft);
    	masterRight.set(scaleRight);
    }
    
    public void stop(){
    	masterLeft.set(0);
    	masterRight.set(0);
    }
}

