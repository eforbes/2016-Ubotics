package org.usfirst.frc.team3507.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrainRight extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void forward() {
    	//moves forward...full speed being 1.
    	
    }
    
    public void reverse() {
    	//moves backwards...full speed being -1.
    }
    
    public void stop() {
    	//stops all movement...value being 0.
    }
}

