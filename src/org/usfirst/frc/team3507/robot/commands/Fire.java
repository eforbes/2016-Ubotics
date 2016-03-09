package org.usfirst.frc.team3507.robot.commands;

import org.usfirst.frc.team3507.robot.Rioduino;
import org.usfirst.frc.team3507.robot.Robot;
import org.usfirst.frc.team3507.robot.subsystems.Flywheel;

import edu.wpi.first.wpilibj.Joystick.RumbleType;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Fire extends Command {

	private boolean immediate;
	private int state = 0;
	private long timeFired;
	
	private long WAIT_AFTER_FIRE = 500; //ms
	
    public Fire(boolean immediate) {
        requires(Robot.intake);
        requires(Robot.flywheel); //TODO: is this actually required?
        
        this.immediate = immediate;
        if(immediate) {
        	state = 1;
        }
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	switch(state) {
    	case 0:
    		if(Robot.flywheel.isAtSpeed()) {
    			state = 1;
    			Robot.oi.operator.controller.setRumble(RumbleType.kRightRumble, (float) 0.5);
    		}
    		break;
    	case 1:
    		Robot.intake.go(-0.75);
    		Robot.rioduino.setLightMode(Rioduino.LIGHTS_FIRE);
    		timeFired = System.currentTimeMillis();
    		state = 2;
    		break;
    	case 2:
    		long currentTime = System.currentTimeMillis();
    		if(currentTime-timeFired > WAIT_AFTER_FIRE) {
    			state = 3;
    			Robot.intake.stop();
        		Robot.flywheel.setState(Flywheel.State.OFF);
    		}
    		break;
    	default:
    		Robot.intake.stop();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return state >= 3;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.oi.operator.controller.setRumble(RumbleType.kLeftRumble, (float) 0.25);
    	Robot.intake.stop();
    	if(immediate) {
    		state = 1;
    	} else {
    		state = 0;
    	}
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
