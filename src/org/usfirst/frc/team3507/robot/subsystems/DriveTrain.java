package org.usfirst.frc.team3507.robot.subsystems;

import org.usfirst.frc.team3507.robot.RobotMap;

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
    }
    
    public void go(double left, double right, double deadzone){
    	double scaleLeft = ((left-(Math.abs(left)/left*deadzone))/(1-deadzone));
    	double scaleRight = ((right-(Math.abs(right)/right*deadzone))/(1-deadzone));
    	masterLeft.set(scaleLeft);
    	masterRight.set(scaleRight);
    }
    
    public void stop(){
    	masterLeft.set(0);
    	masterRight.set(0);
    }
}

