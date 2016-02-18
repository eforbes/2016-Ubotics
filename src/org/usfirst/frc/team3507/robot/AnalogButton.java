package org.usfirst.frc.team3507.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.Button;

public class AnalogButton extends Button {

	private GenericHID joy;
	private int axis;
	public AnalogButton(GenericHID joystick, int axis) {
		joy = joystick;
		this.axis = axis;
		
	}
	@Override
	public boolean get() {
		// TODO Auto-generated method stub
		return joy.getRawAxis(axis) > 0.1;
	}
	

}
