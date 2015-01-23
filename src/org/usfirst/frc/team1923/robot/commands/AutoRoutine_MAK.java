package org.usfirst.frc.team1923.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRoutine_MAK extends CommandGroup {
    
    public  AutoRoutine_MAK() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

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
    	addParallel(new MoveElevatorToPositionCommand(20,3));
    	addSequential(new WaitHalfSecond()); // Replace with Grab Tote
    	addSequential(new WaitHalfSecond()); // Replace with Elevate Tote
    	addSequential(new TurnLeft90());
    	addSequential(new ArcRight180());
    	addSequential(new TurnLeft90());
    	addSequential(new MoveForwardOneSecond()); //Replace with Put Tote on Other Tote
    	

    	addSequential(new WaitHalfSecond()); // Replace with Grab Tote
    	addSequential(new WaitHalfSecond()); // Replace with Elevate Tote
    	addSequential(new TurnLeft90());
    	addSequential(new ArcRight180());
    	addSequential(new TurnLeft90());
    	addSequential(new MoveForwardOneSecond()); //Replace with Put Tote on Other Tote
    	
    	addSequential(new WaitHalfSecond()); // Replace with Grab Tote
    	addSequential(new TurnRight90());
    	addSequential(new MoveForwardTwoSecond());
    }
}
