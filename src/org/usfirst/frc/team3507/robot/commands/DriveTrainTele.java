package org.usfirst.frc.team3507.robot.commands;

import org.usfirst.frc.team3507.robot.RoboUtil;
import org.usfirst.frc.team3507.robot.Robot;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveTrainTele extends Command {
	
	double deadzone;

    public DriveTrainTele() {
    	super("DriveTrainTele");
        requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Preferences prefs = Preferences.getInstance();
    	deadzone = prefs.getDouble("Deadzone", 0.08);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double jAxisRight;
    	double jAxisLeft;
    	double max;
    	double sum;
    	double dif;
    	double L;
    	double R;
    	
    	if (Robot.controlType.getSelected().equals(0)) {
        	jAxisRight = RoboUtil.deadzone(Robot.oi.controller.getRawAxis(4), deadzone);
        	jAxisLeft = RoboUtil.deadzone(Robot.oi.controller.getRawAxis(1), deadzone);
        	
        	max = Math.abs(jAxisRight);
        	if (Math.abs(jAxisLeft) > max) max = Math.abs(jAxisLeft);
        	sum = jAxisRight + jAxisLeft;
        	dif = jAxisRight - jAxisLeft;
        	
        	if (jAxisLeft <= 0) {
        		if (jAxisRight >= 0) {
        			L = max;
        			R = -sum;
        		} else {
        			L = dif;
        			R = max;
        		}
        	} else {
        		if (jAxisRight >= 0) {
        			L = dif;
        			R = -max;
        		} else {
        			L = -max;
        			R = -sum;
        		}
        	}
        	Robot.driveTrain.go(L, R);
    	} else if (Robot.controlType.getSelected().equals(1)) {
        	jAxisRight = RoboUtil.deadzone(Robot.oi.controller.getRawAxis(5), deadzone);
        	jAxisLeft = RoboUtil.deadzone(Robot.oi.controller.getRawAxis(0), deadzone);
        	
        	max = Math.abs(jAxisLeft);
        	if (Math.abs(jAxisRight) > max) max = Math.abs(jAxisRight);
        	sum = jAxisLeft + jAxisRight;
        	dif = jAxisLeft - jAxisRight;
        	
        	if (jAxisRight <= 0) {
        		if (jAxisLeft >= 0) {
        			L = max;
        			R = -sum;
        		} else {
        			L = dif;
        			R = max;
        		}
        	} else {
        		if (jAxisLeft >= 0) {
        			L = dif;
        			R = -max;
        		} else {
        			L = -max;
        			R = -sum;
        		}
        	}
        	Robot.driveTrain.go(L, R);
    	} else if (Robot.controlType.getSelected().equals(2)) {
        	jAxisRight = Robot.oi.controller.getRawAxis(5);
        	jAxisLeft = Robot.oi.controller.getRawAxis(1);
        	Robot.driveTrain.go(RoboUtil.deadzone(jAxisLeft, deadzone), RoboUtil.deadzone(jAxisRight, deadzone));
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
