package org.usfirst.frc.team3507.robot.commands;

import org.usfirst.frc.team3507.robot.Robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArmAngle extends Command {
	
	private boolean backwards;
	Preferences prefs = Preferences.getInstance();
	DigitalInput btn = new DigitalInput(2);
    public ArmAngle(boolean b) {
    	super("ArmAngle");
    	requires(Robot.arm);
    	backwards = b;
    }
    
    protected void initialize() {
    }

    protected void execute() {
    	if (!backwards) {
    		Robot.arm.go(prefs.getDouble("Arm Angle Speed", 0.5));
    	} else {
    		if (btn.get()){
    			Robot.arm.stop();
    		
    		} else {
        		Robot.arm.go(-(prefs.getDouble("Arm Angle Speed", 0.5)));
    		}
    		
    	}
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.arm.stop();
    }

    protected void interrupted() {
    	end();
    }
}
