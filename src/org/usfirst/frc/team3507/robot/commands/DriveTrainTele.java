package org.usfirst.frc.team3507.robot.commands;

import org.usfirst.frc.team3507.robot.RoboUtil;
import org.usfirst.frc.team3507.robot.Robot;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveTrainTele extends Command {
	
	private double L;
	private double R;
	
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
    	
    	if (Robot.controlType.getSelected().equals(0)) {
        	jAxisRight = RoboUtil.deadzone(Robot.oi.driver.controller.getRawAxis(4), deadzone);
        	jAxisLeft = RoboUtil.deadzone(Robot.oi.driver.controller.getRawAxis(1), deadzone);
        	
        	arcade(jAxisRight, jAxisLeft);
        	
        	Robot.driveTrain.go(L, R);
    	} else if (Robot.controlType.getSelected().equals(1)) {
        	jAxisRight = RoboUtil.deadzone(Robot.oi.driver.controller.getRawAxis(5), deadzone);
        	jAxisLeft = RoboUtil.deadzone(Robot.oi.driver.controller.getRawAxis(0), deadzone);
        	
        	arcade(jAxisLeft, jAxisRight);
        	
        	Robot.driveTrain.go(L, R);
    	} else if (Robot.controlType.getSelected().equals(2)) {
        	jAxisRight = RoboUtil.deadzone(Robot.oi.driver.controller.getRawAxis(0), deadzone);
        	jAxisLeft = RoboUtil.deadzone(Robot.oi.driver.controller.getRawAxis(1), deadzone);
        	
        	arcade(jAxisRight, jAxisLeft);
        	
        	Robot.driveTrain.go(L, R);
    	} else if (Robot.controlType.getSelected().equals(3)) {
        	jAxisRight = Robot.oi.driver.controller.getRawAxis(5);
        	jAxisLeft = Robot.oi.driver.controller.getRawAxis(1);
        	Robot.driveTrain.go(-RoboUtil.deadzone(jAxisLeft, deadzone), -RoboUtil.deadzone(jAxisRight, deadzone));
    	} else if (Robot.controlType.getSelected().equals(4)) {
    		Robot.driveTrain.go(0, 0);
    	}
    }
    
    public void arcade(double jR, double jL) {
    	double max;
    	double sum;
    	double dif;
    	
    	max = Math.abs(jR);
    	if (Math.abs(jL) > max) max = Math.abs(jL);
    	sum = jR + jL;
    	dif = jR - jL;
    	
    	if (jL <= 0) {
    		if (jR >= 0) {
    			L = max;
    			R = -sum;
    		} else {
    			L = dif;
    			R = max;
    		}
    	} else {
    		if (jR >= 0) {
    			L = dif;
    			R = -max;
    		} else {
    			L = -max;
    			R = -sum;
    		}
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
