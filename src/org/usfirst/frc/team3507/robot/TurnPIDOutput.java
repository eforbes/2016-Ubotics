package org.usfirst.frc.team3507.robot;

import edu.wpi.first.wpilibj.PIDOutput;

public class TurnPIDOutput implements PIDOutput {

	@Override
	public void pidWrite(double output) {
		Robot.driveTrain.go(output, -output);
	}
}
