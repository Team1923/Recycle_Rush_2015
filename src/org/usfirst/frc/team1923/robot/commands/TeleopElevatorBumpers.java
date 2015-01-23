package org.usfirst.frc.team1923.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TeleopElevatorBumpers extends CommandGroup {
    
    public  TeleopElevatorBumpers() {
    	addParallel(new ElevatorBumpersCommand());
    	addSequential(new DriveWithJoyStickCommand());
    }
}
