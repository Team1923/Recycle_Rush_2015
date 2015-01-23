
package org.usfirst.frc.team1923.robot;


import org.usfirst.frc.team1923.robot.commands.AutonEvadeBins;
import org.usfirst.frc.team1923.robot.commands.DriveDistanceCommand;
import org.usfirst.frc.team1923.robot.commands.DriveForwardCommand;
import org.usfirst.frc.team1923.robot.commands.DriveWithJoyStickCommand;
import org.usfirst.frc.team1923.robot.commands.AutonNoBins;
import org.usfirst.frc.team1923.robot.commands.ElevatorSetHomeCommand;
import org.usfirst.frc.team1923.robot.commands.MoveElevatorToPositionCommand;
import org.usfirst.frc.team1923.robot.commands.TeleopElevatorBumpers;
import org.usfirst.frc.team1923.robot.commands.TurnToHeading;
import org.usfirst.frc.team1923.robot.subsystems.DriveTrainSubsystem;
import org.usfirst.frc.team1923.robot.subsystems.ElevatorSubsystem;
import org.usfirst.frc.team1923.robot.subsystems.PIDriveTrainSubsystem;
import org.usfirst.frc.team1923.robot.subsystems.PIElevatorSubsystem;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	//public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	public static OI oi;
	public static PIDriveTrainSubsystem driveTrainSubsystem = new PIDriveTrainSubsystem();
    //public static Elevator elevator
    public static PIElevatorSubsystem elevatorSubsystem = new PIElevatorSubsystem();
    public CommandGroup autonomousCommand;
    public CommandGroup teleopCommand;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI();
		// Initialize Robot
		RobotMap.init();
		//CommandBase.init();
        // instantiate the command used for the autonomous period
		
		SmartDashboard.putData(driveTrainSubsystem);
		SmartDashboard.putData(elevatorSubsystem);
		addCommandsToDashboard();
		autonomousCommand = new AutonNoBins();
		teleopCommand = new TeleopElevatorBumpers();
		
		
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    	log();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        if (teleopCommand != null) teleopCommand.start();
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){
        if (autonomousCommand != null) autonomousCommand.cancel();
        if (teleopCommand != null) teleopCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    	log();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }

    public void log(){
    	// Log values at Dashboard
    	SmartDashboard.putNumber("Left Distance", driveTrainSubsystem.getLeftEncoderDistance());
    	SmartDashboard.putNumber("Right Distance", driveTrainSubsystem.getRightEncoderDistance());
    	SmartDashboard.putNumber("Elevator Position", elevatorSubsystem.getElevatorEncoderPosition());
    	SmartDashboard.putNumber("Gyro Angle", driveTrainSubsystem.getGyroAngle());

    }
 
    public void addCommandsToDashboard(){
    	//SmartDashboard.putData("Drive DRIVE_DIST_1", new DriveDistanceCommand(RobotMap.DRIVE_DIST_1,5.0));
    	//SmartDashboard.putData("Drive DRIVE_DIST_2", new DriveDistanceCommand(RobotMap.DRIVE_DIST_2,5.0));
    	SmartDashboard.putData("Move Elevator ELEVATOR_POSITION_1", new MoveElevatorToPositionCommand(RobotMap.ELEVATOR_POSITION_1,5.0));
    	SmartDashboard.putData("Move Elevator ELEVATOR_POSITION_2", new MoveElevatorToPositionCommand(RobotMap.ELEVATOR_POSITION_2,5.0));
    	SmartDashboard.putData("Move Elevator ELEVATOR_POSITION_3", new MoveElevatorToPositionCommand(RobotMap.ELEVATOR_POSITION_3,5.0));
    	SmartDashboard.putData("Move Elevator ELEVATOR_POSITION_4", new MoveElevatorToPositionCommand(RobotMap.ELEVATOR_POSITION_4,5.0));
    	SmartDashboard.putData("Move Gyro Angle 0", new TurnToHeading(0,5.0));
    	SmartDashboard.putData("Move Gyro Angle 90", new TurnToHeading(-90,5.0));
    	SmartDashboard.putData("Set Up Home Position", new ElevatorSetHomeCommand());
    	
    }
    
}

