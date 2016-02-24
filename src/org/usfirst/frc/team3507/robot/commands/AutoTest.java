package org.usfirst.frc.team3507.robot.commands;

import org.usfirst.frc.team3507.robot.subsystems.Flywheel;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoTest extends CommandGroup {
    
    public  AutoTest() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
    	
    	addSequential(new DriveTrainAutoTimedStraight2(0.5, 0.5));
    	addSequential(new DriveTrainAutoTimedStraight(0.75, 0.5, 0.5));
    	addSequential(new TurnAround(-160));
    	addSequential(new DriveTrainAutoTimedStraight(0.75, -0.5, -0.5));
    	addSequential(new AutoTargetBasic());
    	addParallel(new ChangeFlywheelState(Flywheel.State.AUTO));
    	addSequential(new Fire(false));

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
