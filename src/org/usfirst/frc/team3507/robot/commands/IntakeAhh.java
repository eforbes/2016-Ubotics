package org.usfirst.frc.team3507.robot.commands;

import org.usfirst.frc.team3507.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeAhh extends Command {

	private static double sped;
	
    public IntakeAhh(double spd) {
    	super("IntakeAhh");
    	sped = spd;
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.intake.go(sped);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.intake.stop();
    }

    protected void interrupted() {
    	end();
    }
}
