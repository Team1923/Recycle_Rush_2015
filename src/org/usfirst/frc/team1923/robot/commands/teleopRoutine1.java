package org.usfirst.frc.team1923.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class teleopRoutine1 extends CommandGroup {
    
    public  teleopRoutine1() {
    	addSequential(new DriveWithJoyStickCommand());
    }
}
