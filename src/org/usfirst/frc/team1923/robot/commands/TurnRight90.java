package org.usfirst.frc.team1923.robot.commands;

import org.usfirst.frc.team1923.robot.Robot;
import org.usfirst.frc.team1923.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnRight90 extends Command {

	private Timer timer = new Timer();
    public TurnRight90() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrainSubsystem);
    	
    	this.initialize();
    	
    	while(!this.isFinished()){
    		this.execute();
    	}
    	
    	this.end();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.start();
    	Robot.driveTrainSubsystem.manualDrive(1,-1);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrainSubsystem.manualDrive(1,-1);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return timer.get() >= 0.5;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrainSubsystem.manualDrive(0,0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.driveTrainSubsystem.manualDrive(0,0);
    }
}
