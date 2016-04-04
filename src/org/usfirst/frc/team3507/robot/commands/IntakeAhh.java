package org.usfirst.frc.team3507.robot.commands;

import org.usfirst.frc.team3507.robot.Robot;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeAhh extends Command {

	private boolean backwards;
	Preferences prefs = Preferences.getInstance();
	
	private boolean speedOverride = false;
	private double overrideSpeed = 0;
	
    public IntakeAhh(boolean b) {
    	super("IntakeAhh");
    	requires(Robot.intake);
    	backwards = b;
    }
    
    public IntakeAhh(boolean b, double speed_override) {
    	super("IntakeAhh");
    	requires(Robot.intake);
    	backwards = b;
    	overrideSpeed = speed_override;
    	speedOverride = true;
    	
    }

    protected void initialize() {
    }

    protected void execute() {
    	if (!backwards) {
    		if(!speedOverride) {
    			Robot.intake.go(prefs.getDouble("Outtake Speed", 0.5));
    		} else {
    			Robot.intake.go(overrideSpeed);
    		}
    		
    	} else {
    		if(!speedOverride) {
    			Robot.intake.go(-(prefs.getDouble("Outtake Speed", 0.5)));
    		} else {
    			Robot.intake.go(-overrideSpeed);
    		}
    		
    	}
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.intake.stop();
    }

    protected void interrupted() {
    	end();
    }
}
