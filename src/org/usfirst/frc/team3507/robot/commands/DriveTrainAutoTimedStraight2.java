package org.usfirst.frc.team3507.robot.commands;

import org.usfirst.frc.team3507.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrainAutoTimedStraight2 extends Command {

	private double speedRight;
	private double speedLeft;
	private double startAngle;
	private double tolerance = 3;
	private boolean flag = false;
	private int flat = 0;
	
	public DriveTrainAutoTimedStraight2(double spdLft, double spdRgt) {
        super("DriveTrainAuto");
        requires(Robot.driveTrain);
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
    	double currentPitch = Robot.ahrs.getPitch();
    	double mod = 0;
    	if (startAngle - currentAngle < -tolerance) {
    		mod = 0.1;
    	}
    	if (startAngle - currentAngle > tolerance) {
    		mod = -0.1;
    	}
    	if (Math.abs(currentPitch) > tolerance && !flag) {
    		flag = true;
    	}
    	Robot.driveTrain.go(speedRight+mod, speedLeft-mod);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (flag && Math.abs(Robot.ahrs.getPitch()) < 5) {
    		flat++;
    	} else {
    		flat--;
    		if(flat<0) flat=0;
    	}
//    	SmartDashboard.putNumber("Flatness", flat);
        return flat > 3;
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
