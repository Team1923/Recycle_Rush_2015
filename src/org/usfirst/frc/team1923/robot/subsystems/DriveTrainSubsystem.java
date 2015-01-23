package org.usfirst.frc.team1923.robot.subsystems;



import org.usfirst.frc.team1923.robot.RobotMap;
import org.usfirst.frc.team1923.robot.commands.*;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrainSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public double cLeft = 0;
	public double cRight = 0;
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new DriveWithJoyStickCommand()); // TBD for Commandbased programming
    }
    public void coalesceLeft(double left){
    	if (left < cLeft - 0.02){
    		cLeft = cLeft - 0.02;
    	} else if (left > cLeft + 0.02) {
    		cLeft = cLeft + 0.02;
    	} else {
    		cLeft = left;
    	}    	
    }

    
    
    // Manual Drive 
    public void manualDrive(double left, double right) {
        //disablePID();
        //mode = MANUAL_MODE;
    	RobotMap.robotDriveTrain.tankDrive(left, right);
        
        
    }
    
}

