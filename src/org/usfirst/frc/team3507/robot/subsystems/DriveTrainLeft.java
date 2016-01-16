package org.usfirst.frc.team3507.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrainLeft extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void forward(){
    	//moves the left track forwards, accepts a value between 0 and 1
    }
    public void stop(){
    	//stops the left track, value is 0
    }
    public void backwards(){
    	//moves the left track in reverse, accepts a value between 0 and -1
    }
}

