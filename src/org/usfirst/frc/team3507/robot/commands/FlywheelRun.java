package org.usfirst.frc.team3507.robot.commands;

import org.usfirst.frc.team3507.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FlywheelRun extends Command {

	private static double sped;
	
    public FlywheelRun(double spd) {
    	super("FlywheelRun");
    	sped = spd;
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.flywheel.go(sped);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.flywheel.stop();
    }

    protected void interrupted() {
    	end();
    }
}
