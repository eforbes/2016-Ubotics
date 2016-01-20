package org.usfirst.frc.team3507.robot.commands;

import org.usfirst.frc.team3507.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class LeftTrackTele extends Command {
	
	double deadzone = 0.02;

    public LeftTrackTele() {
    	super("LeftTrackTele");
        requires(Robot.leftTrack);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double jAxis = Robot.oi.controller.getRawAxis(1);
    	
    	if (jAxis > deadzone) {
    		Robot.leftTrack.forward(jAxis);
    	} else if (jAxis > -deadzone && jAxis < deadzone) {
    		Robot.leftTrack.stop();
    	} else if (jAxis < -deadzone) {
    		Robot.leftTrack.backward(jAxis);
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
