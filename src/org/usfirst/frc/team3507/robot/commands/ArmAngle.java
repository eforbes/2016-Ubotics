package org.usfirst.frc.team3507.robot.commands;

import org.usfirst.frc.team3507.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArmAngle extends Command {
	
	private static double sped;
	
    public ArmAngle(double spd) {
    	sped = spd;
    }
    
    protected void initialize() {
    }

    protected void execute() {
    	Robot.arm.go(sped);
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
