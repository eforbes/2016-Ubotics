package org.usfirst.frc.team3507.robot;

import org.usfirst.frc.team3507.robot.commands.DriveTrainPID;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;

public class ImagePIDOutput implements PIDOutput {

	@Override
	public void pidWrite(double output) {
		Command d = new DriveTrainPID(output, -output);
	}
}
