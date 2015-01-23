package org.usfirst.frc.team1923.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonNoBins extends CommandGroup {
    
    public  AutonNoBins() {
    	addSequential(new DriveForwardHalfSecondCommand());
    	addParallel(new ElevatorDropCommand());
    	addSequential(new WaitSecondCommand());
    	addParallel(new ElevatorRaiseOneCommand());
    	addSequential(new WaitSecondCommand());
    	//addSequential(new TurnLeftCommand());
    	//addSequential(new ArcRightCommand());
    	//addSequential(new TurnLeftCommand());
    	addSequential(new DriveForwardCommand());

    	addParallel(new ElevatorDropCommand());
    	addSequential(new WaitSecondCommand());
    	addParallel(new ElevatorRaiseOneCommand());
    	addSequential(new WaitSecondCommand());
    	//addSequential(new TurnLeftCommand());
    	//addSequential(new ArcRightCommand());
    	//addSequential(new TurnLeftCommand());
    	addSequential(new DriveForwardCommand());

    	addParallel(new ElevatorDropCommand());
    	addSequential(new WaitSecondCommand());
    	addParallel(new ElevatorRaiseOneCommand());
    	addSequential(new WaitSecondCommand());
    	addSequential(new TurnRightCommand());
    	//addSequential(new DriveForwardCommand());
    	//addSequential(new DriveForwardCommand());
    	addSequential(new WaitSecondCommand());
    }
}
