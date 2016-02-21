package org.usfirst.frc.team3507.robot.commands;

import org.usfirst.frc.team3507.robot.RoboUtil;
import org.usfirst.frc.team3507.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ArmAnglePotentiometer extends Command {

	PIDController pidController;
	Mode mode;
	double setpoint;
	
    public ArmAnglePotentiometer() {
        requires(Robot.arm);
        mode = Mode.MANUAL;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Preferences prefs = Preferences.getInstance();
    	pidController = new PIDController(
    			prefs.getDouble("Arm P", 0.2),
    			prefs.getDouble("Arm I", 0),
    			prefs.getDouble("Arm D", 0),
    			new ArmPIDSource(),
    			new ArmPIDOutput()
    			);
    	pidController.setContinuous(false);
    	pidController.setInputRange(prefs.getDouble("Arm Pot Min", 0), prefs.getDouble("Arm Pot Max", 0));
    	pidController.setOutputRange(-0.5, 0.5);
    	pidController.disable();
    }

    //TODO: make sure pot in phase with motor
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Preferences prefs = Preferences.getInstance();
    	
    	double joystickAxisValue = -RoboUtil.deadzone(Robot.oi.operator.controller.getRawAxis(1), 0.1);
    	int joystickPOV = Robot.oi.operator.controller.getPOV();
    	
    	if(Math.abs(joystickAxisValue) > 0) {
    		mode = Mode.MANUAL;
    	}
    	
    	SmartDashboard.putNumber("POV", joystickPOV);
    	
    	if(joystickPOV == 0) {
    		mode = Mode.AUTOMATIC;
    		setpoint = prefs.getDouble("Arm Position Shoot", 4);
    	}
    	if(joystickPOV > 179 && joystickPOV < 181) {
    		mode = Mode.AUTOMATIC;
    		setpoint = prefs.getDouble("Arm Position Intake", 2);
    	}
    	
//    	if(mode == Mode.MANUAL) {
    		pidController.disable();
    		Robot.arm.go(joystickAxisValue);
//    	} else {
//    		pidController.setSetpoint(setpoint);
//    		pidController.enable();
//    		pidController.setPID(prefs.getDouble("Arm P", 0.2),
//    			prefs.getDouble("Arm I", 0),
//    			prefs.getDouble("Arm D", 0));
//    		SmartDashboard.putNumber("Arm P check", pidController.getP());
//    	}
    	SmartDashboard.putString("Arm mode", mode.toString());
    	SmartDashboard.putNumber("Arm setpoint", setpoint);
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
    }
    
    public enum Mode {
    	MANUAL, AUTOMATIC;
    }
    
    class ArmPIDSource implements PIDSource {
    	
    	@Override
    	public double pidGet() {
    		return Robot.arm.pot.getVoltage();
    	}

		@Override
		public void setPIDSourceType(PIDSourceType pidSource) {
		}

		@Override
		public PIDSourceType getPIDSourceType() {
			return PIDSourceType.kDisplacement;
		}
    }
    
    class ArmPIDOutput implements PIDOutput {

		@Override
		public void pidWrite(double output) {
			Robot.arm.go(output);
			SmartDashboard.putNumber("Arm pid output", output);
		}
    	
    }
}
