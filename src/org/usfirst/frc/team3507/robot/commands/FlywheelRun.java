package org.usfirst.frc.team3507.robot.commands;

import org.usfirst.frc.team3507.robot.Robot;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FlywheelRun extends Command {

	private boolean backwards;
	Preferences prefs = Preferences.getInstance();
	
    public FlywheelRun(boolean b) {
    	super("FlywheelRun");
    	requires(Robot.flywheel);
    	backwards = b;
    }

    protected void initialize() {
    }

    protected void execute() {
    	if (!backwards) {
    		Robot.flywheel.go(prefs.getDouble("Flywheel Speed", 0.5));
    	} else {

    		Robot.flywheel.go(-(prefs.getDouble("Flywheel Speed", 0.5)));
    	}
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.flywheel.stop();
    }

    protected void interrupted() {
    	end();
    }
}
