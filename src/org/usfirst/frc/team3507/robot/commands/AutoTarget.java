package org.usfirst.frc.team3507.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoTarget extends Command {

	NetworkTable table;
	
    public AutoTarget() {
        table = NetworkTable.getTable("GRIP/contourReport");
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double[] defaultValue = new double[0];
    	double[] x = table.getNumberArray("centerX", defaultValue);
    	double[] y = table.getNumberArray("centerY", defaultValue);
    	SmartDashboard.putNumber("Target X", x[0]);
    	SmartDashboard.putNumber("Target Y", y[0]);
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
    	end();
    }
}
