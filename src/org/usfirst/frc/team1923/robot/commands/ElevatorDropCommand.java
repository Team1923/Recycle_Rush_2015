package org.usfirst.frc.team1923.robot.commands;

import org.usfirst.frc.team1923.robot.Robot;
import org.usfirst.frc.team1923.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

//import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorDropCommand extends Command {

	public ElevatorDropCommand() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.elevatorSubsystem);
		
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		setTimeout(2.0);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (! RobotMap.elevatorBottomLimitSwitch.get()) {
			Robot.elevatorSubsystem.moveElevatorDown(0.8);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return RobotMap.elevatorBottomLimitSwitch.get() || isTimedOut();
	}

	// Called once after isFinished returns true
	protected void end() {

	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {

	}
}
