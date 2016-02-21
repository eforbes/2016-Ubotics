package org.usfirst.frc.team3507.robot.commands;

import org.usfirst.frc.team3507.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MotorInversion extends Command {

    public MotorInversion() {
        // Use requires() here to declare subsystem dependencies
//        requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.invert();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

    }

	protected boolean isFinished() {
		return true;
	}

	protected void end() {
		
	}

	protected void interrupted() {
		
	}
}
