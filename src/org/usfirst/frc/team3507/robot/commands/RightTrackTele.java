package org.usfirst.frc.team3507.robot.commands;

import org.usfirst.frc.team3507.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RightTrackTele extends Command {
	double deadzone = 0.02;

    public RightTrackTele() {
    	super("RightTrackTele");
        requires(Robot.rightTrack);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double jAxis = Robot.oi.controller.getRawAxis(5);
    	
    	if (jAxis > 0) {
    		Robot.rightTrack.forward(jAxis);
    	} else if (jAxis == 0) {
    		Robot.rightTrack.stop();
    	} else if (jAxis < 0) {
    		Robot.rightTrack.backward(jAxis);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
