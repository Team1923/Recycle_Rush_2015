package org.usfirst.frc.team1923.robot.commands;

import org.usfirst.frc.team1923.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

//import org.usfirst.frc.team1923.robot.subsystems.DriveTrainSubsystem;

/**
 *
 */
public class DriveWithJoyStickCommand extends Command {

    public DriveWithJoyStickCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrainSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	boolean rightTrigger = Robot.oi.rightStick.getTrigger();
    	boolean leftTrigger = Robot.oi.leftStick.getTrigger();
    	if(rightTrigger && leftTrigger){
        	Robot.driveTrainSubsystem.manualDrive(-Robot.oi.leftStick.getY(), -Robot.oi.rightStick.getY());
    	} else {
    		Robot.driveTrainSubsystem.manualDrive(-Robot.oi.leftStick.getY()/2, -Robot.oi.rightStick.getY()/2);    		
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
