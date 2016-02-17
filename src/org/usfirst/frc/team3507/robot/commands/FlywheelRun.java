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
    	if (!Robot.flywheelMode.getSelected().equals(0)) {
    		if (Robot.flywheelMode.getSelected().equals(1)) {
        		if (!backwards) {
        			Robot.flywheel.go(prefs.getDouble("Flywheel Slow Speed", 4500));
        		} else {
        			Robot.flywheel.go(-(prefs.getDouble("Flywheel Slow Speed", 4500)));
        		}
    		}
    		if (Robot.flywheelMode.getSelected().equals(2)) {
        		if (!backwards) {
        			Robot.flywheel.go(prefs.getDouble("Flywheel Fast Speed", 6150));
        		} else {
        			Robot.flywheel.go(-(prefs.getDouble("Flywheel Fast Speed", 6150)));
        		}
    		}
    		if (Robot.flywheelMode.getSelected().equals(3)) {
        		if (!backwards) {
        			Robot.flywheel.go(prefs.getDouble("Flywheel Auto Speed", 6000));
        		} else {
        			Robot.flywheel.go(-(prefs.getDouble("Flywheel Auto Speed", 6000)));
        		}
    		}
    	} else {
    		Robot.flywheel.stop();
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
