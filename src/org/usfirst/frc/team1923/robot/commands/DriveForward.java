package org.usfirst.frc.team1923.robot.commands;

import java.security.InvalidParameterException;

import org.usfirst.frc.team1923.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * A command that drives the robot forward for the given time at the given speed
 * @author Christopher Cushman
 * @since 1/14/2015
 * 
 */
public class DriveForward extends Command {
	
	private Timer timer;
	
	private double speed, duration;

	/**
	 * Constructs an object of type DriveForward
	 * @param speed		the speed at which to drive forward at
	 * @param duration	how long to drive forward.
	 * Precondition: the speed must be 0<speed<1 inclusive and duration must be greater than zero.
	 * @throws InvalidParameterException 	if the precondition is not met
	 * 						
	 */
    public DriveForward(double speed, double duration) {
    	if(!(0 <= speed && speed <= 1) || duration < 0)
    		throw new InvalidParameterException();
        this.speed = speed;
        this.duration = duration;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer = new Timer();
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrainSubsystem.manualDrive(speed, speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return timer.get() >= duration;
    }

    // Called once after isFinished returns true
    protected void end() {
    	timer.stop();
    	Robot.driveTrainSubsystem.manualDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.driveTrainSubsystem.manualDrive(0, 0);
    }
}