package org.usfirst.frc.team1923.robot.commands;

import org.usfirst.frc.team1923.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveDistanceCommand extends Command {
	private double dist;
	private double timeOut;
    public DriveDistanceCommand(double dist, double timeOut) {
        // Use requires() here to declare subsystem dependencies
    	requires(Robot.driveTrainSubsystem);
    	this.dist = dist;
    	this.timeOut = timeOut;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrainSubsystem.driveStrightUsingEncoder(dist, timeOut);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Robot.driveTrainSubsystem.reachedDistance();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrainSubsystem.disable();
    	Robot.driveTrainSubsystem.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.driveTrainSubsystem.disable();
    	Robot.driveTrainSubsystem.stop();
    }
}