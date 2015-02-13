package org.usfirst.frc.team1923.robot.commands;

import org.usfirst.frc.team1923.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveBackwardForArcCommand extends Command {
	//TODO
	// Use Arc Drive command instead. DELETE THIS CLASS

	private Timer timer;

	private double speed = 0.4;

	public DriveBackwardForArcCommand() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.driveTrainSubsystem);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		timer = new Timer();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		// System.out.println("ex");
		double currtime = timer.get();
		if (currtime < 0.1) {
			Robot.driveTrainSubsystem.manualDrive(-speed * currtime * 10,
					-speed * currtime * 10);
		} else {
			Robot.driveTrainSubsystem.manualDrive(-speed, -speed);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return timer.get() >= 0.3;
		// return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		timer.stop();
		Robot.driveTrainSubsystem.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
