
package org.usfirst.frc.team3507.robot;

import org.usfirst.frc.team3507.robot.subsystems.Arm;
import org.usfirst.frc.team3507.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3507.robot.subsystems.Flywheel;
import org.usfirst.frc.team3507.robot.subsystems.Intake;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static final DriveTrain driveTrain = new DriveTrain();
	public static final Arm arm = new Arm();
	public static final Flywheel flywheel = new Flywheel();
	public static final Intake intake = new Intake();
	
	public static OI oi;
    
	public static SendableChooser controlType;
    SendableChooser autoChoose;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI();
		
		// Autonomous Mode Selector
        autoChoose = new SendableChooser();
        //chooser.addDefault("Default Auto", new ExampleCommand());
        //chooser.addObject("My Auto", new MyAutoCommand());
        SmartDashboard.putData("Auto mode", autoChoose);
        
        // Drive Control Type Selector
        controlType = new SendableChooser();
        controlType.addDefault("Arcade Drive L-R (Default)", 0);
        controlType.addObject("Arcade Drive R-L", 1);
        controlType.addObject("Acade Drive L", 2);
        controlType.addObject("Tank Drive", 3);
        controlType.addObject("Paralyzed", 4);
        SmartDashboard.putData("Robot Control Type", controlType);
        
        SmartDashboard.putData(Scheduler.getInstance());
    }
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){

    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
    public void autonomousInit() {
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        SmartDashboard.putNumber("Left Speed", driveTrain.speedL);
        SmartDashboard.putNumber("Right Speed", driveTrain.speedR);
        SmartDashboard.putNumber("Flywheel Position", flywheel.motor.getPulseWidthPosition());
        SmartDashboard.putNumber("Flywheel Velocity", (((double)flywheel.motor.getPulseWidthRiseToRiseUs()*60000000)/4096));
        
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
