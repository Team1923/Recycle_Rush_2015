package org.usfirst.frc.team1923.robot.commands;

import org.usfirst.frc.team1923.robot.Robot;
import org.usfirst.frc.team1923.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

//import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorRaiseOneCommand extends Command {

	private Timer timer = new Timer();
	
    public ElevatorRaiseOneCommand() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.elevatorSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.stop();
    	timer.reset();
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        	Robot.elevatorSubsystem.moveElevatorUp(0.8);    		
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return timer.get() > 1.3;
    }

    // Called once after isFinished returns true
    protected void end() {
    	timer.stop();
    	Robot.elevatorSubsystem.moveElevatorDown(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	timer.stop();
    	Robot.elevatorSubsystem.moveElevatorDown(0.0);
    }
}

