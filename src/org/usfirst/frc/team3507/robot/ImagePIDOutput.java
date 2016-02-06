package org.usfirst.frc.team3507.robot;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ImagePIDOutput implements PIDOutput {

	@Override
	public void pidWrite(double output) {
		SmartDashboard.putNumber("Turn PID Output", output);
		Robot.driveTrain.go(output, -output);
	}
}
