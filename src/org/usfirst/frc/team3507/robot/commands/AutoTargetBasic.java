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
public class AutoTargetBasic extends Command {

	NetworkTable table;
    PIDController turnPID;
    Preferences prefs;
	
    public AutoTargetBasic() {
        table = NetworkTable.getTable("GRIP/contourReport");
        prefs = Preferences.getInstance();
        requires(Robot.driveTrain);
        setTimeout(2);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	turnPID = new PIDController(
    			prefs.getDouble("Turn P", 0), 
    			prefs.getDouble("Turn I", 0), 
    			prefs.getDouble("Turn D", 0), 
    			new ImagePIDSource(), 
    			new ImagePIDOutput());
    	turnPID.setContinuous(false);
    	turnPID.setInputRange(0, 320);
    	turnPID.setOutputRange(-0.5, 0.5);
    	turnPID.setAbsoluteTolerance(prefs.getDouble("AutoTarget Tolerance", 10));
//    	turnPID.setToleranceBuffer(10);
    	turnPID.disable();
    	
    	SmartDashboard.putString("AutoTarget Status", "Initialized");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double[] defaultValue = new double[0];
    	double[] x = table.getNumberArray("centerX", defaultValue);
    	double[] y = table.getNumberArray("centerY", defaultValue);

    	double setpoint = prefs.getDouble("Turn Setpoint", 160);

    	SmartDashboard.putNumber("Setpoint", setpoint);
		turnPID.setSetpoint(setpoint);
    	
		turnPID.setPID(
				prefs.getDouble("Turn P", 0), 
    			prefs.getDouble("Turn I", 0), 
    			prefs.getDouble("Turn D", 0));
		
		turnPID.enable();
		
    	if (x.length > 0) SmartDashboard.putNumber("Target X", x[0]);
    	if (y.length > 0) SmartDashboard.putNumber("Target Y", y[0]);
    	
    	SmartDashboard.putNumber("AutoTarget Average Error", turnPID.getAvgError());
    	SmartDashboard.putNumber("AutoTarget PID Error", turnPID.getError());
    	SmartDashboard.putString("AutoTarget Status", "Running");
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double[] x = table.getNumberArray("centerX", new double[0]);
    	if (x.length > 0) {
    		SmartDashboard.putNumber("AUTO TARGET AVG ERR", Math.abs(turnPID.getError()));
    		return Math.abs(turnPID.getError()) < prefs.getDouble("AutoTarget Tolerance", 10) || isTimedOut();
//    		if (turnPID.onTarget()) {
//    			SmartDashboard.putString("AutoTarget Status", "Finished on target");
//    			return true;
//    		}
//    		return false;
    	} else {
    		SmartDashboard.putString("AutoTarget Status", "Finished no target");
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
    	SmartDashboard.putString("AutoTarget Status", "Interrupted");
    	end();
    }
}
