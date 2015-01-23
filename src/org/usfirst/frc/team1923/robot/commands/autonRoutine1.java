package org.usfirst.frc.team1923.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class autonRoutine1 extends CommandGroup {
    
    public  autonRoutine1() {

    	addSequential(new autonSubroutine1());
    	addSequential(new autonSubroutine1());
    	
    	addSequential(new WaitHalfSecond()); // Replace with Grab Tote
    	addSequential(new TurnRight90());
    	addSequential(new MoveForwardTwoSecond());
    }
}
