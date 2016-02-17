package org.usfirst.frc.team3507.robot.commands;

import org.usfirst.frc.team3507.robot.Robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SmartIntake extends Command {

	Preferences prefs = Preferences.getInstance();
	DigitalInput btn1 = new DigitalInput(0);
	DigitalInput btn2 = new DigitalInput(1);
	
    public SmartIntake() {
    	super("SmartIntake");
    	requires(Robot.intake);
    }

    protected void initialize() {
    }

    protected void execute() {
    	if (Robot.oi.controller.getRawAxis(3) > 0.05) {
    		if (!btn1.get() || !btn2.get()) {
    			Robot.intake.go(prefs.getDouble("Intake Speed", 1));
    		} else {
    			end();
    		}
    	}
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
