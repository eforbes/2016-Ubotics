package org.usfirst.frc.team3507.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class XboxController {
	public Joystick controller;
	public Button A;
	public Button B;
	public Button X;
	public Button Y;
	public Button leftBump;
	public Button rightBump;
	public Button back;
	public Button start;
	public Button leftStick;
	public Button rightStick;
	public Button leftTrigger;
	public Button rightTrigger;
	
	public XboxController(int controllerNumber) {
		controller = new Joystick(controllerNumber);
		A = new JoystickButton(controller, 1);
		B = new JoystickButton(controller, 2);
		X = new JoystickButton(controller, 3);
		Y = new JoystickButton(controller, 4);
		leftBump = new JoystickButton(controller, 5);
		rightBump = new JoystickButton(controller, 6);
		back = new JoystickButton(controller, 7);
		start = new JoystickButton(controller, 8);
		leftStick = new JoystickButton(controller, 9);
		rightStick = new JoystickButton(controller, 10);
		leftTrigger = new AnalogButton(controller, 2);
		rightTrigger = new AnalogButton(controller, 3);
	}
    
}
