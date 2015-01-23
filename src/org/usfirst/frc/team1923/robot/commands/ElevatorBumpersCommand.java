package org.usfirst.frc.team1923.robot.commands;

import org.usfirst.frc.team1923.robot.Robot;
import org.usfirst.frc.team1923.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

//import org.usfirst.frc.team1923.robot.subsystems.DriveTrainSubsystem;

/**
 *
 */
public class ElevatorBumpersCommand extends Command {

    public ElevatorBumpersCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.elevatorSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	boolean rightBumper = Robot.oi.rB.get();
    	boolean leftBumper = Robot.oi.lB.get();
    	
    	if(rightBumper && !leftBumper && !RobotMap.elevatorBottomLimitSwitch.get()){
        	Robot.elevatorSubsystem.moveElevatorDown(0.8);    		
    	} else if(!rightBumper && leftBumper){
        	Robot.elevatorSubsystem.moveElevatorUp(0.8);    		    		
    	} else{
        	Robot.elevatorSubsystem.moveElevatorDown(0.0);    		    		
    	}

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.elevatorSubsystem.moveElevatorDown(0.0);    		    		
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.elevatorSubsystem.moveElevatorDown(0.0);    		    		
    }
}
