package org.usfirst.frc.team3507.robot;

public class RoboUtil {

	public static double deadzone(double side, double dead) {
		double blub = side;
    	if (side > dead || side < -dead) {
    		blub = side;
    	}
		return ((blub-(Math.abs(blub)/blub*dead))/(1-dead));
	}
}
