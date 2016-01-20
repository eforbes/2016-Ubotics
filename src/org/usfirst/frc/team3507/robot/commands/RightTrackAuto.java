package org.usfirst.frc.team3507.robot.commands;

import org.usfirst.frc.team3507.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RightTrackAuto extends Command {

	private double speed;
	
	public RightTrackAuto(int ms, double spd) {
        super("RightTrackAuto");
        requires(Robot.rightTrack);
        setTimeout(ms);
        speed = spd;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(speed > 0){
    		Robot.rightTrack.forward(speed);
    	}
    	if(speed == 0){
    		Robot.rightTrack.stop();
    	}
    	if(speed < 0){
    		Robot.rightTrack.backward(speed);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.rightTrack.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
