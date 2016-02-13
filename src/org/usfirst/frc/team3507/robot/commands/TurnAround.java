package org.usfirst.frc.team3507.robot.commands;

import org.usfirst.frc.team3507.robot.Robot;
import org.usfirst.frc.team3507.robot.TurnPIDOutput;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TurnAround extends Command {

	Preferences prefs;
	private int count = 0;
	private int index = 0;
	private double sum = 0;
	private double setpoint;
	private boolean running;
	private int maxArraySize = 10;
	private PIDController turnPID;
	private double[] difs = new double[maxArraySize];
	
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
    	setpoint = Robot.ahrs.getAngle() + 180;
    	setpoint = setpoint>360?setpoint-360:setpoint;
    	turnPID.setSetpoint(setpoint);
    	turnPID.setAbsoluteTolerance(prefs.getDouble("Gyro Tolerance", 5));
    	turnPID.enable();
    	running = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (!running) {
        	setpoint = Robot.ahrs.getAngle() + 180;
        	setpoint = setpoint>360?setpoint-360:setpoint;
        	turnPID.setSetpoint(setpoint);
        	running = true;
    	}
    	SmartDashboard.putNumber("Setpoint", setpoint);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double gyroDif = Math.abs(Robot.ahrs.getAngle() - setpoint);
    	SmartDashboard.putNumber("Gyro Dif", gyroDif);
    	if (count == maxArraySize) {
    		sum -= difs[index];
    		count++;
    	}
    	difs[index] = gyroDif;
    	sum += gyroDif;
    	count++;
    	double avgDif = sum / count;
    	SmartDashboard.putNumber("Average Diff", avgDif);
    	index++;
    	if (index >= maxArraySize) {
    		index = 0;
    	}
		if(avgDif < prefs.getDouble("Gyro Tolerance", 10)) {
			return true;        	
		} else {
			return false;
		}
    	//return turnPID.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	turnPID.disable();
    	running = false;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
