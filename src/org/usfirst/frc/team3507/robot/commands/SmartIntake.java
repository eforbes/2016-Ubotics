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
	
    public SmartIntake() {
    	super("SmartIntake");
    	requires(Robot.intake);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Preferences prefs = Preferences.getInstance();
    	SmartDashboard.putBoolean("ramp limit top", Robot.intake.btn1.get());
    	SmartDashboard.putBoolean("ramp limit bot", Robot.intake.btn2.get());
		switch(state) {
		case 0:
			if (Robot.intake.btn1.get() || Robot.intake.btn2.get()) {
				Robot.intake.go(-(prefs.getDouble("Intake Speed", 1)));		
			} else {
				state = 1;
			}
			break;
//		case 1:
//			if (!btn1.get()) {
//				Robot.intake.go(prefs.getDouble("Intake Speed", 1) / 2);
//			} else {
//				state = 2;
//			}
//			break;
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
