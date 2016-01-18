package org.usfirst.frc.team3507.robot.subsystems;

import org.usfirst.frc.team3507.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {
    
    public static CANTalon master;
    public static CANTalon slave1;
    public static CANTalon slave2;
    
    public DriveTrain(int x) {
    	if (x == 0) {
        	master = new CANTalon(RobotMap.leftMaster);
        	slave1 = new CANTalon(RobotMap.leftSlave1);
        	slave2 = new CANTalon(RobotMap.leftSlave2);
    	} else if (x == 1) {
        	master = new CANTalon(RobotMap.rightMaster);
        	slave1 = new CANTalon(RobotMap.rightSlave1);
        	slave2 = new CANTalon(RobotMap.rightSlave2);
    	}
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void forward(double speed){
    	master.set(speed);
    }
    public void stop(){
    	master.set(0);
    }
    public void backwards(double speed){
    	master.set(speed);
    }
}

