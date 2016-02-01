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
	
    public IntakeAhh(boolean b) {
    	super("IntakeAhh");
    	requires(Robot.intake);
    	backwards = b;
    }

    protected void initialize() {
    }

    protected void execute() {
    	if (!backwards) {
    		Robot.intake.go(prefs.getDouble("Intake Speed", 0.5));
    	} else {
    		Robot.intake.go(-(prefs.getDouble("Intake Speed", 0.5)));
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
