package org.usfirst.frc.team3507.robot.commands;

import org.usfirst.frc.team3507.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveTrainAutoTimedStraight extends Command {

	private double speedRight;
	private double speedLeft;
	private double startAngle;
	private double tolerance = 3;
	
	public DriveTrainAutoTimedStraight(double sec, double spdLft, double spdRgt) {
        super("DriveTrainAuto");
        requires(Robot.driveTrain);
        setTimeout(sec);
        speedRight = spdRgt;
        speedLeft = spdLft;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startAngle = Robot.ahrs.getAngle();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double currentAngle = Robot.ahrs.getAngle();
    	double mod = 0;
    	if (startAngle - currentAngle < -tolerance) {
    		mod = 0.1;
    	}
    	if (startAngle - currentAngle > tolerance) {
    		mod = -0.1;
    	}
    	Robot.driveTrain.go(speedRight+mod, speedLeft-mod);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
