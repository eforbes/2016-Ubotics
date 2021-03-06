package org.usfirst.frc.team3507.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
	
	public static int driver = 0;
	public static int operator = 1;
	
	public static int leftMaster = 1;
	public static int leftSlave1 = 2;
	public static int leftSlave2 = 3;
	public static int rightMaster = 4;
	public static int rightSlave1 = 5;
	public static int rightSlave2 = 6;

	public static int armMotor = 9;
	public static int intakeMotor = 8;
	public static int flywheelMotor = 7;
	
	public static int gyroPort = 0;
}
