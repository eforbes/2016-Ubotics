package org.usfirst.frc.team3507.robot;

import org.usfirst.frc.team3507.robot.commands.ArmAngle;
import org.usfirst.frc.team3507.robot.commands.FlywheelRun;
import org.usfirst.frc.team3507.robot.commands.IntakeAhh;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
	
    public Joystick controller = new Joystick(RobotMap.controller);
    public Button A = new JoystickButton(controller, 1),
    		B = new JoystickButton(controller, 2),
    		X = new JoystickButton(controller, 3),
    		Y = new JoystickButton(controller, 4),
    		leftBump = new JoystickButton(controller, 5),
    		rightBump = new JoystickButton(controller, 6),
    		back = new JoystickButton(controller, 7),
    		start = new JoystickButton(controller, 8),
    		leftStick = new JoystickButton(controller, 9),
    		rightStick = new JoystickButton(controller, 10);
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
    
    public OI() {
    	Preferences prefs = Preferences.getInstance();
    	double armSpeed = prefs.getDouble("Arm Angle Speed", 0.5);
    	double intakeSpeed = prefs.getDouble("Intake Speed", 0.5);
    	double flywheelSpeed = prefs.getDouble("Flywheel Speed", 1);
    	
    	leftBump.whileHeld(new ArmAngle(-armSpeed));   
    	rightBump.whileHeld(new ArmAngle(armSpeed));
    	
    	A.whileHeld(new IntakeAhh(intakeSpeed));
    	B.whileHeld(new IntakeAhh(-intakeSpeed));
    	
    	X.whileHeld(new FlywheelRun(flywheelSpeed));
    }
}

