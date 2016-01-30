package org.usfirst.frc.team3507.robot.commands;

import org.usfirst.frc.team3507.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArmAngleR extends Command {

	public static double sped;
	
    public ArmAngleR(double spd) {
    	super("ArmAngleR");
    	requires(Robot.arm);
    	sped = spd;
    }
    
    protected void initialize() {
    }

    protected void execute() {
    	Robot.arm.go(-0.2);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.arm.stop();
    }

    protected void interrupted() {
    	end();
    }
}
