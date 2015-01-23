/*package org.usfirst.frc.team1923.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
*/


/*
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
/*
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
	
	// Compressor
	//public static Solenoid  solenoid  = new Solenoid(0);
	//public static Compressor compressor = new Compressor(0);
	
	// Drive Train Talon SRX
	public static CANTalon frontLeftDrive = new CANTalon(0);
	public static CANTalon rearLeftDrive = new CANTalon(1);
	public static CANTalon frontRightDrive = new CANTalon(2);
	public static CANTalon rearRightDrive = new CANTalon(3);
	
	// Elevator Talon SRX
	public static CANTalon elevatorLeftMotor = new CANTalon(4);
	public static CANTalon elevatorRightMotor = new CANTalon(5);
	
	
	
	//DriveTrain
	public static RobotDrive robotDriveTrain = new RobotDrive(frontLeftDrive, rearLeftDrive, frontRightDrive, rearRightDrive);
	
	
	// Elevator
	public static RobotDrive elevatorDrive = new RobotDrive(elevatorLeftMotor, elevatorRightMotor);
	public static DigitalInput elevatorLimit = new DigitalInput(8);
	
	public static Encoder elevatorEncoder = new Encoder(4,5,false, EncodingType.k4X);
	// Robot system initialization
	public static void init(){
		elevatorEncoder.reset();
		//compressor.setClosedLoopControl(true);
		
	}
	
	
	 
    
}
*/



package org.usfirst.frc.team1923.robot;

//import org.usfirst.frc.team1923.util.MotorGroup;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.livewindow.LiveWindow; 
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;


/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
	// Constants
	public static double ELEVATOR_POSITION_1 = 0.0;
	public static double ELEVATOR_POSITION_2 = 16.5;
	public static double ELEVATOR_POSITION_3 = 28.5;
	public static double ELEVATOR_POSITION_4 = 36.0;
	public static double ELEVATOR_POSITION_5 = 12.0;
	public static double ELEVATOR_POSITION_6 = 24.0;
	
	public static double DRIVE_DIST_1 = 0.0;
	public static double DRIVE_DIST_2 = 10.0;
	public static double DRIVE_DIST_3 = 20.0;
	public static double DRIVE_DIST_4 = 30.0;
	
	// Compressor
	public static Solenoid  solenoid  = new Solenoid(0);
	public static Compressor compressor = new Compressor(0);
	
	// Drive Train Talon SRX
	public static CANTalon frontLeftDrive = new CANTalon(0);
	public static CANTalon rearLeftDrive = new CANTalon(1);
	public static CANTalon frontRightDrive = new CANTalon(2);
	public static CANTalon rearRightDrive = new CANTalon(3);
	
	// Elevator Talon SRX
	public static CANTalon elevatorLeftMotor = new CANTalon(4);
	public static CANTalon elevatorRightMotor = new CANTalon(5);
	// Sensors
	public static Encoder driveEncoderLeft = new Encoder(0, 1, true, EncodingType.k4X);
    public static Gyro gyro = new Gyro(1);
    public static Encoder driveEncoderRight = new Encoder(2,3, false, EncodingType.k4X);
    public static AnalogInput temperature = new AnalogInput(2);
    public static Encoder elevatorEncoder = new Encoder(4,5, true, EncodingType.k4X);
    
    // Digital IO
    public static DigitalInput elevatorTopLimitSwitch = new DigitalInput(9);
    public static DigitalInput elevatorBottomLimitSwitch = new DigitalInput(8);
	
    // Spikes
    //public static Relay intakeLeftSpike = new Relay(5);
    //public static Relay intakeRightSpike = new Relay(6);
    
	//DriveTrain
	public static RobotDrive robotDriveTrain = new RobotDrive(frontLeftDrive, rearLeftDrive, frontRightDrive, rearRightDrive);
	
	
	// Elevator
	public static RobotDrive elevatorDrive = new RobotDrive(elevatorLeftMotor, elevatorRightMotor);
	
	// Motor Group for Drive
	/* Motor Group Init. */
   // public static final MotorGroup driveLeftSide = new MotorGroup(frontLeftDrive, rearLeftDrive);
    //public static final MotorGroup driveRightSide = new MotorGroup(frontRightDrive, rearRightDrive);
	
	
	
	// Robot system initialization
	public static void init(){
	compressor.setClosedLoopControl(true);
	gyro.reset();
	driveEncoderLeft.reset();
	driveEncoderRight.reset();
	elevatorEncoder.reset();
	
	
	// Test Mode --------------------
	//LiveWindow.addActuator("DriveTRainSubsystem", "frontLeftDrive",  (LiveWindowSendable) frontLeftDrive);
	//LiveWindow.addActuator("DriveTRainSubsystem", "rearLeftDrive",  (LiveWindowSendable) rearLeftDrive);
	//LiveWindow.addActuator("DriveTRainSubsystem", "frontRightDrive",  (LiveWindowSendable) frontRightDrive);
	//LiveWindow.addActuator("DriveTRainSubsystem", "rearRightDrive",  (LiveWindowSendable) rearRightDrive);
	//LiveWindow.addActuator("elevatorDrive", "elevatorLeftMotor",  (LiveWindowSendable) elevatorLeftMotor);
	//LiveWindow.addActuator("elevatorDrive", "elevatorRightMotor",  (LiveWindowSendable) elevatorRightMotor);
	
	// Live Window
    LiveWindow.addSensor("DriveTrainSubsystem", "Left Encoder", RobotMap.driveEncoderLeft);
    LiveWindow.addSensor("DriveTrainSubsystem", "Right Encoder", RobotMap.driveEncoderRight);
    //LiveWindow.addSensor("DriveTrainSubsystem", "Gyro", RobotMap.gyro);
    LiveWindow.addSensor("ElevatorSubsystem", "Elevator Encoder", RobotMap.elevatorEncoder);
	
     
		
		
	}
	
	 
	
    
}
