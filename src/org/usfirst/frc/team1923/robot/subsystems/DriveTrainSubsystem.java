package org.usfirst.frc.team1923.robot.subsystems;



import org.usfirst.frc.team1923.robot.RobotMap;
import org.usfirst.frc.team1923.robot.commands.*;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
//import edu.wpi.first.wpilibj.PIDController;
//import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
//import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 *
 */
public class DriveTrainSubsystem extends PIDSubsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	// distance per pulse of a drive-wheel encoder, in inches. [CHANGE THESE VALUES!!!!!!!!!!]
	// Declare variable for the robot drive system
    private RobotDrive robotDriveTrain = RobotMap.robotDriveTrain;
    private Timer timer;
    private double timeOut = 2.0;
    // Drive Wheel Encoders
    private Encoder driveEncoderLeft = RobotMap.driveEncoderLeft;
    private Encoder driveEncoderRight = RobotMap.driveEncoderRight;
    // gyro.
    private Gyro gyro = RobotMap.gyro;
	
	
	private static final double NUM_CLICKS = 256, //distance per pulse = 0.0491"/pulse
    							GEAR_RATIO = 1.0/1.0, 
					            WHEEL_CIRCUMFERENCE = 12.56,   // 4 inches wheels
					            Pg = 0.1, Ig = 0.005, Dg = 0.0,     // LEAVE THESE CONSTANTS ALONE!
					            Pe = 8.0, Ie = 0.01, De = 0.0,      // LEAVE THESE CONSTANTS ALONE!
					            PID_LOOP_TIME = .05, 
					            gyroTOLERANCE = 0.3,            // 0.2778% error ~= 0.5 degrees...?
					            encoderTOLERANCE = 2.0;         // +/- 2" tolarance
	
	
	
	public DriveTrainSubsystem() {
        super(Pe, Ie, De);
        this.init();
        this.disable();
	}
	
	
public void init(){
	// TODO
	// Set distance per pulse for each encoder
    this.driveEncoderLeft.setDistancePerPulse(GEAR_RATIO*WHEEL_CIRCUMFERENCE/NUM_CLICKS);
    this.driveEncoderRight.setDistancePerPulse(GEAR_RATIO*WHEEL_CIRCUMFERENCE/NUM_CLICKS);
    // Set PID source parameter to Distance...
    this.driveEncoderLeft.setPIDSourceParameter(Encoder.PIDSourceParameter.kDistance);
    this.driveEncoderRight.setPIDSourceParameter(Encoder.PIDSourceParameter.kDistance);
    // PID tolerance
    this.setAbsoluteTolerance(encoderTOLERANCE);
    
    
    
    gyro.reset();
   
    
    // Timer        
    timer = new Timer();
    timer.reset();
    timer.stop();
    
    // Live Window
    LiveWindow.addSensor("DriveTrainSubsystem", "Left Encoder", this.driveEncoderLeft);
    LiveWindow.addSensor("DriveTrainSubsystem", "Right Encoder", this.driveEncoderRight);
    
}
   
    
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new DriveWithJoyStickCommand());
    }
    
    // Manual Drive 
    public void manualDrive(double x, double y) {
        this.disable();
    	//RobotMap.robotDriveTrain.tankDrive(x, y);
    	RobotMap.robotDriveTrain.tankDrive(x, y, true);
        
        
    }
    // Stop
    public void stop(){
    	RobotMap.robotDriveTrain.tankDrive(0.0, 0.0);
    }
    
    // Drive Stright Distance Using Encoder
    public void driveStrightUsingEncoder(double dist,double maxTimeOut){
    	//TODO
    	setSetpoint(dist);
    	this.enable();
    	this.timeOut = maxTimeOut;
    	this.timer.reset();
    	this.timer.start();
    }
    
   
    /**
     * Use the left Encoder as the PID sensor. This method is automatically
     * called by the subsystem.
     */
    protected double returnPIDInput() {
        return this.getLeftEncoderDistance();
    }


    /**
     * Use the robotDriveTrain as the PID output. This method is automatically called by
     * the subsystem.
     */
    protected void usePIDOutput(double d) {
        this.robotDriveTrain.drive(d, 0);
    }
    
       
    public boolean reachedDistance(){
    	if (timer.get() > this.timeOut || this.onTarget()){
    		this.disable();
    		timer.stop();
    		timer.reset();
    		return true;
    	}else{
    		return false;
    	}
    		
    	
    	
    }
    
    
    public double getDistanceError(){
		   	
    	return this.getSetpoint() - this.getPosition();
    	
    }
    
    /**
     *
     * @return Count from the encoder (since the last reset?).
     */
    public double getLeftEncoderCount() {
       return this.driveEncoderLeft.getRaw();
    }
    
    public double getRightEncoderCount() {
        return this.driveEncoderRight.getRaw();
     }
    
    /**
     *
     * @return Distance the encoder has recorded since the last reset, adjusted for the gear ratio.
     */
    public double getLeftEncoderDistance() {
        return this.driveEncoderLeft.getDistance();
    }
    
    public double getRightEncoderDistance() {
        return this.driveEncoderRight.getDistance();
    }
    
    public double getAvgEncoderDistance() {
        return (this.driveEncoderLeft.getDistance()+this.driveEncoderRight.getDistance())/2.0;
    }
    
}