package org.usfirst.frc.team3507.robot;

import org.usfirst.frc.team3507.robot.commands.ArmAngle;
import org.usfirst.frc.team3507.robot.commands.ChangeFlywheelState;
import org.usfirst.frc.team3507.robot.commands.Fire;
import org.usfirst.frc.team3507.robot.commands.IntakeAhh;
import org.usfirst.frc.team3507.robot.commands.MotorInversion;
import org.usfirst.frc.team3507.robot.commands.SmartIntake;
import org.usfirst.frc.team3507.robot.commands.TurnAround;
import org.usfirst.frc.team3507.robot.subsystems.Flywheel;

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
    
    public XboxController driver = new XboxController(RobotMap.driver);
    public XboxController operator =  new XboxController(RobotMap.operator);
    
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
//    	operator.leftBump.whileHeld(new ArmAngle(false));
//    	operator.leftTrigger.whileHeld(new ArmAngle(true));
    	
    	driver.rightTrigger.whenPressed(new SmartIntake());
    	driver.leftTrigger.whileHeld(new IntakeAhh(false));
    	
    	driver.Y.whenPressed(new MotorInversion());
    	
    	driver.A.whenPressed(new TurnAround());
    	operator.A.whenPressed(new ChangeFlywheelState(Flywheel.State.AUTO));
    	operator.B.whenPressed(new ChangeFlywheelState(Flywheel.State.SLOW));
    	operator.X.whenPressed(new ChangeFlywheelState(Flywheel.State.FAST));
    	operator.Y.whenPressed(new ChangeFlywheelState(Flywheel.State.OFF));
    	
    	operator.rightTrigger.whenPressed(new Fire(false));
    	operator.rightBump.whenPressed(new Fire(true));
    }
}

