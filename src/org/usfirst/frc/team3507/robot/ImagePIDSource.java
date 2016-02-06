package org.usfirst.frc.team3507.robot;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class ImagePIDSource implements PIDSource {

	NetworkTable tables;
	
	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return null;
	}

	@Override
	public double pidGet() {
		tables = NetworkTable.getTable("GRIP/contourReport");
		double[] x = tables.getNumberArray("centerX", new double[0]);
		if (x.length > 0) {
			return x[0];
		} else {
			return 0;
		}
	}
}
