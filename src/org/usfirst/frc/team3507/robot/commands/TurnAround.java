package org.usfirst.frc.team3507.robot.commands;

import org.usfirst.frc.team3507.robot.Robot;
import org.usfirst.frc.team3507.robot.TurnPIDOutput;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnAround extends Command {

	Preferences prefs;
	PIDController turnPID;
	
    public TurnAround() {
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	prefs = Preferences.getInstance();
    	turnPID = new PIDController(prefs.getDouble("FlipP", 0.0), prefs.getDouble("FlipI", 0.0), prefs.getDouble("FlipD", 0.0), Robot.ahrs, new TurnPIDOutput());
    	turnPID.setContinuous(true);
    	turnPID.setInputRange(0, 360);
    	turnPID.setOutputRange(-1, 1);
    	double setpoint = Robot.ahrs.getAngle() + 180;
    	turnPID.setSetpoint(setpoint>360?setpoint-180:setpoint);
    	turnPID.setAbsoluteTolerance(prefs.getDouble("Gyro Tolerance", 5));
    	turnPID.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return turnPID.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.stop();
    	turnPID.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
