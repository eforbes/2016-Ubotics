package org.usfirst.frc.team3507.robot.commands;

import org.usfirst.frc.team3507.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveTrainTele extends Command {
	
	double deadzone = 0.02;

    public DriveTrainTele() {
    	super("DriveTrainTele");
        requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double jAxisRight = Robot.oi.controller.getRawAxis(5);
    	double jAxisLeft = Robot.oi.controller.getRawAxis(1);
    	double left = 0;
    	double right = 0;
    	if (jAxisRight > deadzone) {
    		right = jAxisRight;
    	}
    	else if (jAxisRight < -deadzone) {
    		right = jAxisRight;
    	}
    	if (jAxisLeft > deadzone) {
    		left = jAxisLeft;
    	}
    	else if (jAxisLeft < -deadzone) {
    		left = jAxisLeft;
    	}
    	Robot.driveTrain.go(left, right, deadzone);
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
