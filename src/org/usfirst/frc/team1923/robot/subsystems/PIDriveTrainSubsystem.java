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
public class PIDriveTrainSubsystem extends PIDSubsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	// distance per pulse of a drive-wheel encoder, in inches. [CHANGE THESE VALUES!!!!!!!!!!]
	// Declare variable for the robot drive system
    //public RobotDrive robotDriveTrain = RobotMap.robotDriveTrain;
    private Timer timer;
    private double timeOut = 2.0;
    private int DRIVE_MODE = 1;
    public double cLeft = 0;
	public double cRight = 0;
    // Drive Wheel Encoders
    //private Encoder driveEncoderLeft = RobotMap.driveEncoderLeft;
    //private Encoder driveEncoderRight = RobotMap.driveEncoderRight;
    // gyro.
    //private Gyro gyro = RobotMap.gyro;
	
	
	private static final double NUM_CLICKS = 256, //distance per pulse = 0.0491"/pulse
    							GEAR_RATIO = 1.0/1.0, 
					            WHEEL_CIRCUMFERENCE = 12.56,   // 4 inches wheels
					            Pg = 0.1, Ig = 0.005, Dg = 0.0,     // LEAVE THESE CONSTANTS ALONE!
					            Pe = 0.5, Ie = 0.01, De = 0.0,      // LEAVE THESE CONSTANTS ALONE!
					            PID_LOOP_TIME = .05, 
					            gyroTOLERANCE = 0.3,            // 0.2778% error ~= 0.5 degrees...?
					            encoderTOLERANCE = 2.0;         // +/- 2" tolarance
	
	
	private static final int MANUAL_MODE = 1, ENCODER_MODE = 2, GYRO_MODE = 3;
	
	public PIDriveTrainSubsystem() {
        super(Pe, Ie, De);
     // TODO
    	// Set distance per pulse for each encoder
        RobotMap.driveEncoderLeft.setDistancePerPulse(GEAR_RATIO*WHEEL_CIRCUMFERENCE/NUM_CLICKS);
        RobotMap.driveEncoderRight.setDistancePerPulse(GEAR_RATIO*WHEEL_CIRCUMFERENCE/NUM_CLICKS);
        this.getPIDController().setPID(Pe, Ie, De);
        this.setAbsoluteTolerance(encoderTOLERANCE);
        this.setOutputRange(-1.0, 1.0);
        this.setInputRange(-200.0, 200.0);
        this.disable();
        RobotMap.gyro.reset();
        DRIVE_MODE = MANUAL_MODE;
        
        // Timer        
        timer = new Timer();
        timer.reset();
        timer.stop();
        
        
        
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
    public void coalesceLeft(double left){
    	if (left < cLeft - 0.02){
    		cLeft = cLeft - 0.02;
    	} else if (left > cLeft + 0.02) {
    		cLeft = cLeft + 0.02;
    	} else {
    		cLeft = left;
    	}    	
    }
    // Stop
    public void stop(){
    	this.disable();
    	RobotMap.robotDriveTrain.tankDrive(0.0, 0.0);
    }
    
    // Drive Stright Distance Using Encoder
    public void driveStrightUsingEncoder(double dist,double maxTimeOut){
    	//TODO
    	
    	
    	setSetpoint(dist);
    	setEncoderPID();
    	
    	this.timeOut = maxTimeOut;
    	this.timer.reset();
    	this.timer.start();
    }
    
   
    /**
     * Use the left Encoder as the PID sensor. This method is automatically
     * called by the subsystem.
     */
    protected double returnPIDInput() {
    	return sensorFeedback();
    }


    /**
     * Use the robotDriveTrain as the PID output. This method is automatically called by
     * the subsystem.
     */
    protected void usePIDOutput(double d) {
    	applyPIDOutput(d);
    }
    
       
    public boolean reachedTarget(){
    	if (timer.get() > this.timeOut || this.onTarget()){
    		this.disable();
    		DRIVE_MODE = MANUAL_MODE;
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
       return RobotMap.driveEncoderLeft.getRaw();
    }
    
    public double getRightEncoderCount() {
        return RobotMap.driveEncoderRight.getRaw();
     }
    
    /**
     *
     * @return Distance the encoder has recorded since the last reset, adjusted for the gear ratio.
     */
    public double getLeftEncoderDistance() {
        return RobotMap.driveEncoderLeft.getDistance();
    }
    
    public double getRightEncoderDistance() {
        return RobotMap.driveEncoderRight.getDistance();
    }
    
    public double getAvgEncoderDistance() {
        return (RobotMap.driveEncoderLeft.getDistance()+RobotMap.driveEncoderRight.getDistance())/2.0;
    }
    
    public double getSpeedDiff(){
    	return RobotMap.driveEncoderLeft.getRate() - RobotMap.driveEncoderRight.getRate();
    }
    
    // Gyro Base Turns
    public void turnRobotHeading(double angle, double maxTimeOut){
    	
    	setGyroPID();
    	
    	setSetpoint(angle);
    	    	
    	this.timeOut = maxTimeOut;
    	this.timer.reset();
    	this.timer.start();
    	
    }
    private void setEncoderPID(){
    	// PID tolerance
    	DRIVE_MODE = ENCODER_MODE;
    	this.getPIDController().setPID(Pe, Ie, De);
        this.setAbsoluteTolerance(encoderTOLERANCE);
        this.setOutputRange(-1.0, 1.0);
        this.setInputRange(-200.0, 200.0);
        this.enable();
    	
    }
    
    public void setGyroPID(){
    	DRIVE_MODE = GYRO_MODE;
    	this.getPIDController().setPID(Pg, Ig, Dg);
        this.setAbsoluteTolerance(gyroTOLERANCE);
        this.setOutputRange(-1.0, 1.0);
        this.setInputRange(-360.0, 360.0);
        this.enable();
    	
    }
    private double sensorFeedback(){
    	double sensorReading = 0.0;
    	if (DRIVE_MODE == ENCODER_MODE){
    		sensorReading =  getAvgEncoderDistance();
        	}else if (DRIVE_MODE == GYRO_MODE){
        		sensorReading =  RobotMap.gyro.getAngle();
        		
        	}
    	
    	return sensorReading;
    }
    
    private void applyPIDOutput(double d){
    	if (DRIVE_MODE == ENCODER_MODE){
    		RobotMap.robotDriveTrain.drive(d, 0);
        	}else if (DRIVE_MODE == GYRO_MODE){
        			RobotMap.robotDriveTrain.tankDrive(-d/2, d/2);
        		}
        		
        	}
    	
    public double getGyroAngle(){
    	return RobotMap.gyro.getAngle();
    }
  
    
}

