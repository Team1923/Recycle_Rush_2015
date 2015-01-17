package org.usfirst.frc.team1923.robot;

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
    
    public static Encoder driveEncoderLeft = new Encoder(1, 2, true, EncodingType.k4X);
    public static Gyro gyro = new Gyro(1);
    public static Encoder driveEncoderRight = new Encoder(3,4, false, EncodingType.k4X);
    public static AnalogInput temperature = new AnalogInput(2);
    
    
    //DriveTrain
    public static RobotDrive robotDriveTrain = new RobotDrive(frontLeftDrive, rearLeftDrive, frontRightDrive, rearRightDrive);
    
    
    // Elevator
    public static RobotDrive elevatorDrive = new RobotDrive(elevatorLeftMotor, elevatorRightMotor);
    
    
    
    
    
    // Robot system initialization
    public static void init(){
    compressor.setClosedLoopControl(true);
    gyro.reset();
    driveEncoderLeft.reset();
    driveEncoderRight.reset();
    
    // Test Mode --------------------
    LiveWindow.addActuator("DriveTRainSubsystem", "frontLeftDrive",  (LiveWindowSendable) frontLeftDrive);
    LiveWindow.addActuator("DriveTRainSubsystem", "rearLeftDrive",  (LiveWindowSendable) rearLeftDrive);
    LiveWindow.addActuator("DriveTRainSubsystem", "frontRightDrive",  (LiveWindowSendable) frontRightDrive);
    LiveWindow.addActuator("DriveTRainSubsystem", "rearRightDrive",  (LiveWindowSendable) rearRightDrive);
    LiveWindow.addActuator("elevatorDrive", "elevatorLeftMotor",  (LiveWindowSendable) elevatorLeftMotor);
    LiveWindow.addActuator("elevatorDrive", "elevatorRightMotor",  (LiveWindowSendable) elevatorRightMotor);
    
     
        
        
    }
    
    
    
    
}