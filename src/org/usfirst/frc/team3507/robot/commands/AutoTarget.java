package org.usfirst.frc.team3507.robot.commands;

import org.usfirst.frc.team3507.robot.ImagePIDOutput;
import org.usfirst.frc.team3507.robot.ImagePIDSource;
import org.usfirst.frc.team3507.robot.Robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoTarget extends Command {

	private int count = 0;
	
	NetworkTable table;
    PIDController turnPID;
    Preferences prefs;
	
    public AutoTarget() {
        table = NetworkTable.getTable("GRIP/contourReport");
        prefs = Preferences.getInstance();
        requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	count = 0;
    	double[] x = table.getNumberArray("centerX", new double[0]);
    	double deltaAngle = (x[0] - prefs.getDouble("Turn Setpoint", 160)) * prefs.getDouble("K", 0);
    	turnPID = new PIDController(prefs.getDouble("Turn P", 0), prefs.getDouble("Turn I", 0), prefs.getDouble("Turn D", 0), Robot.ahrs, new ImagePIDOutput());
    	turnPID.setContinuous(true);
    	turnPID.setInputRange(0, 360);
    	turnPID.setOutputRange(-0.5, 0.5);
    	turnPID.setSetpoint(fix(Robot.ahrs.getAngle() + deltaAngle));
    	SmartDashboard.putNumber("Setpoint", fix(Robot.ahrs.getAngle() + deltaAngle));
    	turnPID.setAbsoluteTolerance(prefs.getDouble("Gyro Tolerance", 5));
    	turnPID.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double[] defaultValue = new double[0];
    	double[] x = table.getNumberArray("centerX", defaultValue);
    	double[] y = table.getNumberArray("centerY", defaultValue);
    	double deltaAngle;
    	
    	if(Math.abs(Robot.ahrs.getAngle() - turnPID.getSetpoint()) < prefs.getDouble("Gyro Tolerance", 1)) {
    		SmartDashboard.putNumber("Recalcs", ++count);
        	if (x.length > 0) {
        		deltaAngle = (x[0] - prefs.getDouble("Turn Setpoint", 160)) * prefs.getDouble("K", 0);
        	} else {
        		deltaAngle = 0;
        	}
        	SmartDashboard.putNumber("Setpoint", fix(Robot.ahrs.getAngle() + deltaAngle));
    		turnPID.setSetpoint(fix(Robot.ahrs.getAngle() + deltaAngle));
    	}
    	
    	if (x.length > 0) SmartDashboard.putNumber("Target X", x[0]);
    	if (y.length > 0) SmartDashboard.putNumber("Target Y", y[0]);
    	
    	SmartDashboard.putNumber("Turn PID Status", turnPID.getAvgError());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double[] x = table.getNumberArray("centerX", new double[0]);
    	if (x.length > 0) {
    		double pixelDif = Math.abs(x[0] - prefs.getDouble("Turn Setpoint", 160));
    		if(pixelDif < prefs.getDouble("Pixel Tolerance", 10)) {
    			return true;        	
    		} else {
    			return false;
    		}
    	} else {
    		return true;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	turnPID.disable();
    	Robot.driveTrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
    
    private double fix(double b) {
    	if (b > 360) {
    		return b - 360;
    	} else if (b < 0) {
    		return b + 360;
    	} else {
    		return b;
    	}
    }
}
