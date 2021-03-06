
package org.usfirst.frc.team3507.robot;

import java.io.IOException;

import org.usfirst.frc.team3507.robot.commands.AutoNone;
import org.usfirst.frc.team3507.robot.commands.AutoShoot;
import org.usfirst.frc.team3507.robot.commands.AutoTargetBasic;
import org.usfirst.frc.team3507.robot.commands.AutoTest;
import org.usfirst.frc.team3507.robot.commands.DriveTrainAutoTimedStraight;
import org.usfirst.frc.team3507.robot.subsystems.Arm;
import org.usfirst.frc.team3507.robot.subsystems.DriveTrain;
import org.usfirst.frc.team3507.robot.subsystems.Flywheel;
import org.usfirst.frc.team3507.robot.subsystems.Intake;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
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

	public static SendableChooser accelType;
	public static SendableChooser controlType;
    SendableChooser autoChoose;
    
    public static AHRS ahrs;
    public static PowerDistributionPanel pdp;
    
    public static Rioduino rioduino;
    
    public static DriverStation ds;
    
    public Robot() {
    	// Gyro Stuff
    	ahrs = new AHRS(I2C.Port.kMXP);
    	pdp = new PowerDistributionPanel();
    	
    	rioduino = new Rioduino();
    	ds = DriverStation.getInstance();
    }

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI();
		
		// Autonomous Mode Selector
        autoChoose = new SendableChooser();
        autoChoose.addObject("Cross and Shoot 2", new AutoTest(160));
        autoChoose.addObject("Cross and Shoot 3", new AutoTest(220));
        autoChoose.addObject("Cross and Shoot 4", new AutoTest(181));
        autoChoose.addObject("Cross and Shoot 5", new AutoTest(-160));
        autoChoose.addDefault("Drive only (default)", new DriveTrainAutoTimedStraight(4, 0.5, 0.5));
        autoChoose.addObject("Shoot only", new AutoShoot(Flywheel.State.FAST));
        autoChoose.addObject("Auto Disabled", new AutoNone());
        SmartDashboard.putData("Auto mode", autoChoose);
        
        // Drive Control Type Selector
        controlType = new SendableChooser();
        controlType.addDefault("Arcade Drive L-R (Default)", 0);
        controlType.addObject("Arcade Drive R-L", 1);
        controlType.addObject("Acade Drive L", 2);
        controlType.addObject("Tank Drive", 3);
        controlType.addObject("Paralyzed", 4);
        SmartDashboard.putData("Robot Control Type", controlType);
        
        // Acceleration Type Selector
        accelType = new SendableChooser();
        accelType.addDefault("Linear (Default)", 0);
        accelType.addObject("Quadratic", 1);
        SmartDashboard.putData("Acceleration Type", accelType);
        
        SmartDashboard.putData(Scheduler.getInstance());
        
        SmartDashboard.putData(new AutoTargetBasic(5));
        
//    	try {
//            new ProcessBuilder("/home/lvuser/grip").inheritIO().start();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    	
    	rioduino.setLightMode(Rioduino.LIGHTS_DISABLED);
    }
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){
    	rioduino.setLightMode(Rioduino.LIGHTS_DISABLED);
    }
	
	public void disabledPeriodic() {
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
    	byte alliance = 0;
    	if(ds.getAlliance() == Alliance.Blue) {
    		alliance = 1;
    	}
    	rioduino.setLightMode(Rioduino.LIGHTS_AUTO, alliance);
    	Command autoCommand = (Command) autoChoose.getSelected();
    	autoCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        SmartDashboard.putNumber("Angle", ahrs.getAngle());
    }

    public void teleopInit() {
    	rioduino.setLightMode(Rioduino.LIGHTS_TELE);
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();

        SmartDashboard.putNumber("Flywheel Speed", flywheel.motor.getSpeed());
        SmartDashboard.putNumber("Angle", ahrs.getAngle());
        SmartDashboard.putNumber("Pitch", ahrs.getPitch());
        SmartDashboard.putNumber("Arm pot", arm.pot.getVoltage());
        
        SmartDashboard.putBoolean("Ramp limit 1", intake.btn1.get());
        SmartDashboard.putBoolean("Ramp limit 2", intake.btn2.get());
        
		NetworkTable table = NetworkTable.getTable("GRIP/contourReport");
    	double[] defaultValue = new double[0];
    	double[] x = table.getNumberArray("centerX", defaultValue);
    	double[] y = table.getNumberArray("centerY", defaultValue);
    	
    	if (x.length > 0) SmartDashboard.putNumber("Target X", x[0]); else SmartDashboard.putNumber("Target X", -1000);
    	if (y.length > 0) SmartDashboard.putNumber("Target Y", y[0]); else SmartDashboard.putNumber("Target Y", -1000);
    	
    	double matchTime = ds.getMatchTime();
    	if(matchTime < 20) {
    		if(matchTime < 5) {
    			rioduino.setLightMode(Rioduino.LIGHTS_LAST_5);
    		} else {
    			rioduino.setLightMode(Rioduino.LIGHTS_LAST_20);
    		}
    	}
    	
    	
//    	if(flywheel.currentState != Flywheel.State.OFF) {
//    		byte lightStatus = (byte) (180 * flywheel.getTargetPercent());
//    		rioduino.setLightMode(Rioduino.LIGHTS_FLYWHEEL, lightStatus);
//    		SmartDashboard.putNumber("Light byte", lightStatus);
//    	}
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
