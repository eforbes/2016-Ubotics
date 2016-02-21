package org.usfirst.frc.team3507.robot.commands;

import org.usfirst.frc.team3507.robot.Robot;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveTrainAutoDistance extends Command {

	private double distance;
	
	private double tolerance;
	
    public DriveTrainAutoDistance(double distance) {
        requires(Robot.driveTrain);
        Preferences prefs = Preferences.getInstance();
        
        this.distance = distance;
        this.tolerance = prefs.getDouble("Drivetrain Tolerance", 10);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    
    	
    	Robot.driveTrain.masterLeft.setPosition(0);
    	Robot.driveTrain.masterRight.setPosition(0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Preferences prefs = Preferences.getInstance();
    	Robot.driveTrain.masterLeft.changeControlMode(TalonControlMode.Position);
    	Robot.driveTrain.masterRight.changeControlMode(TalonControlMode.Position);
    	Robot.driveTrain.masterLeft.setPID(prefs.getDouble("Drive P", 0.2), 
    			prefs.getDouble("Drive I", 0.2), 
    			prefs.getDouble("Drive D", 0.2));
    	
    	Robot.driveTrain.masterRight.setPID(prefs.getDouble("Drive P", 0.2), 
    			prefs.getDouble("Drive I", 0.2), 
    			prefs.getDouble("Drive D", 0.2));
    	
    	Robot.driveTrain.masterLeft.set(distance);
    	Robot.driveTrain.masterRight.set(-distance);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.driveTrain.masterLeft.get() < tolerance 
        		&& Robot.driveTrain.masterRight.getError() < tolerance;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.masterLeft.changeControlMode(TalonControlMode.PercentVbus);
    	Robot.driveTrain.masterRight.changeControlMode(TalonControlMode.PercentVbus);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
