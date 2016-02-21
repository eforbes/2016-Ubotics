package org.usfirst.frc.team3507.robot.commands;

import org.usfirst.frc.team3507.robot.Robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SmartIntake extends Command {
	
	private int state = 0;

	DigitalInput btn1;
	DigitalInput btn2;
	
    public SmartIntake() {
    	super("SmartIntake");
    	requires(Robot.intake);
    	btn1 =  new DigitalInput(0);
    	btn2 = new DigitalInput(1);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Preferences prefs = Preferences.getInstance();
    	SmartDashboard.putBoolean("ramp limit top", btn1.get());
    	SmartDashboard.putBoolean("ramp limit bot", btn2.get());
		switch(state) {
		case 0:
			if (btn1.get() || btn2.get()) {
				Robot.intake.go(-(prefs.getDouble("Intake Speed", 1)));		
			} else {
				state = 1;
			}
			break;
		case 1:
			if (!btn1.get()) {
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
        return state >= 2;
    }

    protected void end() {
    	Robot.intake.stop();
    	state = 0;
    }

    protected void interrupted() {
    	end();
    }
}
