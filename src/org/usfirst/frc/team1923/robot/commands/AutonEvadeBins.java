package org.usfirst.frc.team1923.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonEvadeBins extends CommandGroup {
    
    public  AutonEvadeBins() {
    	//addParallel(new ElevatorDropCommand());
    	addSequential(new WaitSecondCommand());
    	//addParallel(new ElevatorRaiseOneCommand());
    	addSequential(new WaitSecondCommand());
    	addSequential(new DriveBackwardForArcCommand());
    	addSequential(new TurnLeftCommand());
    	addSequential(new ArcRightCommand());
    	addSequential(new TurnLeftCommand());
    	addSequential(new DriveForwardHalfSecondCommand());
    	
    	//addParallel(new ElevatorDropCommand());
    	addSequential(new WaitSecondCommand());
    	//addParallel(new ElevatorRaiseOneCommand());
    	addSequential(new WaitSecondCommand());
    	addSequential(new DriveBackwardForArcCommand());
    	addSequential(new TurnLeftCommand());
    	addSequential(new ArcRightCommand());
    	addSequential(new TurnLeftCommand());
    	addSequential(new DriveForwardHalfSecondCommand());
    	
    }
}
