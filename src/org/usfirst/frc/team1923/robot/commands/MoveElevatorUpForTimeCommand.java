package org.usfirst.frc.team1923.robot.commands;

import org.usfirst.frc.team1923.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveElevatorUpForTimeCommand extends Command {
	private double timeOut;
    public MoveElevatorUpForTimeCommand(double timeOut) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.elevatorSubsystem);
        this.timeOut = timeOut;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(this.timeOut);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.elevatorSubsystem.moveElevatorUp(0.5);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.elevatorSubsystem.elevatorStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
