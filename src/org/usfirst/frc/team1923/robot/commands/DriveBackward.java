package org.usfirst.frc.team1923.robot.commands;

import java.security.InvalidParameterException;

import org.usfirst.frc.team1923.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * A command that drives the robot backward for the given time at the given speed
 * @author Christopher Cushman
 * @since 1/15/2015
 * 
 */
public class DriveBackward extends Command {
	
	private Timer timer;
	
	private double speed, duration;

	/**
	 * Constructs an object of type DriveBackward
	 * @param speed		the speed at which to drive backward at
	 * @param duration	how long to drive backward.
	 * Precondition: the speed must be 0<speed<1 inclusive and duration must be greater than zero.
	 * @throws InvalidParameterException 	if the precondition is not met
	 * 						
	 */
    public DriveBackward(double speed, double duration) {

    	requires(Robot.driveTrainSubsystem);
    	if(speed < 0){
    		speed = 0;  		
    	}
    	else if(speed > 1){
    		speed = 1;   		
    	}
        this.speed = -speed; //go in the other direction
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
