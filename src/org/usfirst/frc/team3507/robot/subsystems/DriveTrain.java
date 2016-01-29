package org.usfirst.frc.team3507.robot.subsystems;

import org.usfirst.frc.team3507.robot.Robot;
import org.usfirst.frc.team3507.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {
    
    public CANTalon master;
    public CANTalon slave1;
    public CANTalon slave2;
    
    public DriveTrain(int x) {
    	if (x == 0) {
        	master = new CANTalon(RobotMap.leftMaster);
        	slave1 = new CANTalon(RobotMap.leftSlave1);
        	slave2 = new CANTalon(RobotMap.leftSlave2);
        	Robot.leftTrack.slave1.changeControlMode(TalonControlMode.Follower);
        	Robot.leftTrack.slave2.changeControlMode(TalonControlMode.Follower);
        	Robot.leftTrack.slave1.set(RobotMap.leftMaster);
        	Robot.leftTrack.slave2.set(RobotMap.leftMaster);
    	} else if (x == 1) {
        	master = new CANTalon(RobotMap.rightMaster);
        	slave1 = new CANTalon(RobotMap.rightSlave1);
        	slave2 = new CANTalon(RobotMap.rightSlave2);
        	Robot.rightTrack.slave1.changeControlMode(TalonControlMode.Follower);
        	Robot.rightTrack.slave2.changeControlMode(TalonControlMode.Follower);
        	Robot.rightTrack.slave1.set(RobotMap.rightMaster);
        	Robot.rightTrack.slave2.set(RobotMap.rightMaster);
    	}
    }

    public void initDefaultCommand() {
    	setDriveTrainTele(new DriveTrainTele());
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void forward(double speed, double deadzone){
    	double speedScale = ((speed-(Math.abs(speed)/speed*deadzone))/(1-deadzone));
    	master.set(speedScale);
    }
    public void stop(){
    	master.set(0);
    }
    public void backward(double speed, double deadzone){
    	double speedScale = ((speed-(Math.abs(speed)/speed*deadzone))/(1-deadzone));
    	master.set(speedScale);
    }
}

