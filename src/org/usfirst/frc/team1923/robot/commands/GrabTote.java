package org.usfirst.frc.team1923.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GrabTote extends CommandGroup {
    
    public  GrabTote() {
    	addSequential(new MoveElevatorToPositionCommand(0,4));
    	addSequential(new MoveElevatorToPositionCommand(5,2));
    }
}
