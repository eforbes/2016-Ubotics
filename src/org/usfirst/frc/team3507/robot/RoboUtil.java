package org.usfirst.frc.team3507.robot;

public class RoboUtil {

	public static double deadzone(double side, double dead) {
		return ((side-(Math.abs(side)/side*dead))/(1-dead));
	}
}
