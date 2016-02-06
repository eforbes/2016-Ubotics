package org.usfirst.frc.team3507.robot.commands;

import org.usfirst.frc.team3507.robot.ImagePIDOutput;
import org.usfirst.frc.team3507.robot.ImagePIDSource;
import org.usfirst.frc.team3507.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoTarget extends Command {

	NetworkTable table;
    PIDController turnPID;
    Preferences prefs;
	
    public AutoTarget() {
        table = NetworkTable.getTable("GRIP/contourReport");
        prefs = Preferences.getInstance();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	turnPID = new PIDController(prefs.getDouble("TurnP", 0), prefs.getDouble("TurnI", 0), prefs.getDouble("TurnD", 0), new ImagePIDSource(), new ImagePIDOutput());
    	turnPID.setInputRange(0, 640);
    	turnPID.setOutputRange(-0.5, 0.5);
    	turnPID.setSetpoint(prefs.getDouble("turnSetpoint", 320));
    	turnPID.setPercentTolerance(prefs.getDouble("turnTolerance", 10));
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	turnPID.enable();
    	double[] defaultValue = new double[0];
    	double[] x = table.getNumberArray("centerX", defaultValue);
    	double[] y = table.getNumberArray("centerY", defaultValue);
    	
    	if (x.length > 0) SmartDashboard.putNumber("Target X", x[0]);
    	if (y.length > 0) SmartDashboard.putNumber("Target Y", y[0]);
    	
    	SmartDashboard.putNumber("Turn PID Status", turnPID.getAvgError());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return turnPID.onTarget();
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
