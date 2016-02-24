package org.usfirst.frc.team3507.robot.commands;

import org.usfirst.frc.team3507.robot.Robot;
import org.usfirst.frc.team3507.robot.subsystems.Flywheel;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ChangeFlywheelState extends Command {
	Flywheel.State state;
	Preferences prefs;
	
	boolean done = false;
	
    public ChangeFlywheelState(Flywheel.State state) {
    	requires(Robot.flywheel);
    	requires(Robot.intake);
    	this.state = state;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	prefs = Preferences.getInstance();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putString("Flystate", state.toString());
    	if (state != Flywheel.State.OFF && !Robot.intake.btn1.get()) {
			Robot.intake.go(prefs.getDouble("Intake Speed", 1) / 2);
			done = false;
    	} else {
    		Robot.intake.stop();
    		Robot.flywheel.setState(state);
    		done = true;
    	}
    	if(state == Flywheel.State.OFF) {
        	Robot.flywheel.setState(state);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return done;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
