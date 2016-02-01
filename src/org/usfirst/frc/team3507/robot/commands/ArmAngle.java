package org.usfirst.frc.team3507.robot.commands;

import org.usfirst.frc.team3507.robot.Robot;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArmAngle extends Command {
	
	Preferences prefs = Preferences.getInstance();
	
    public ArmAngle() {
    	super("ArmAngle");
    	requires(Robot.arm);
    }
    
    protected void initialize() {
    }

    protected void execute() {
    	Robot.arm.go(prefs.getDouble("Arm Angle Speed", 0.5));
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
