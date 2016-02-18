package org.usfirst.frc.team3507.robot.commands;

import org.usfirst.frc.team3507.robot.Robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SmartIntake extends Command {
	
	private int state;

	Preferences prefs = Preferences.getInstance();
	DigitalInput btn1 = new DigitalInput(0);
	DigitalInput btn2 = new DigitalInput(1);
	
    public SmartIntake() {
    	super("SmartIntake");
    	requires(Robot.intake);
    }

    protected void initialize() {
    	state = 0;
    }

    protected void execute() {
		switch(state) {
		case 0:
			if (!btn1.get() || !btn2.get()) {
				Robot.intake.go(-(prefs.getDouble("Intake Speed", 1)));		
			} else {
				state = 1;
			}
			break;
		case 1:
			if (btn1.get()) {
				Robot.intake.go(prefs.getDouble("Intake Speed", 1) / 2);
			} else {
				state = 2;
			}
			break;
		default:
			Robot.intake.stop();
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
