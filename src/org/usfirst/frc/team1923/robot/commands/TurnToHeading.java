package org.usfirst.frc.team1923.robot.commands;

import org.usfirst.frc.team1923.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnToHeading extends Command {
	private double angle;
	private double timeOut;
    public TurnToHeading(double angle,double timeOut) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.driveTrainSubsystem);
        this.angle = angle;
    	this.timeOut = timeOut;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//Robot.elevatorSubsystem.moveElevatorToPosition(position, timeOut);
    	Robot.driveTrainSubsystem.turnRobotHeading(angle, timeOut);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        //return Robot.elevatorSubsystem.reachedPosition();
        return Robot.driveTrainSubsystem.reachedTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	//Robot.elevatorSubsystem.elevatorStop();
    	Robot.driveTrainSubsystem.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	//Robot.elevatorSubsystem.elevatorStop();
    	Robot.driveTrainSubsystem.stop();
    }
}
