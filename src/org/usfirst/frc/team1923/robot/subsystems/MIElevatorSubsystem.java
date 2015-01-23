package org.usfirst.frc.team1923.robot.subsystems;

import org.usfirst.frc.team1923.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 *
 */
public class MIElevatorSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	//private RobotDrive elevatorDrive = RobotMap.elevatorDrive;
    private Timer timer;
    private double timeOut = 2.0;
    // Elevator Encoder
    //private Encoder elevatorEncoder = RobotMap.elevatorEncoder;
    private boolean elevatorHomePositionSet = false;
    
    	
	private static final double NUM_CLICKS = 256, //distance per pulse = 0.0491"/pulse
    							GEAR_RATIO = 1.0/1.0, 
					            WHEEL_CIRCUMFERENCE = 1.0,   // 4 inches wheels
					            kp = 0.1, ki = 0.0, kd = 0.0,     // LEAVE THESE CONSTANTS ALONE!
					            PID_LOOP_TIME = .05, 
					            encoderTOLERANCE = 2.0;         // +/- 2" tolarance
	
	private PIDController elevatorPID;
	
	public MIElevatorSubsystem() {
		// Set distance per pulse for each encoder
		RobotMap.elevatorEncoder.setDistancePerPulse(GEAR_RATIO*WHEEL_CIRCUMFERENCE/NUM_CLICKS);
        
        // Set PID source parameter to Distance...
		RobotMap.elevatorEncoder.setPIDSourceParameter(Encoder.PIDSourceParameter.kDistance);
        
        // Set Tolerances
		elevatorPID.setAbsoluteTolerance(encoderTOLERANCE);
        
        
        // Reset the encoders.
		RobotMap.elevatorEncoder.reset();
                    
		elevatorPID = new PIDController(kp,ki,kd,RobotMap.elevatorEncoder,RobotMap.frontLeftDrive,PID_LOOP_TIME);
        // TODO IMP for this PID to work we need to give PID out put to both Talon
       
        // soft limits: 0 to 90 degrees...
		elevatorPID.setInputRange(-100.0,100.0);       // adjust?
      
		elevatorPID.setOutputRange(-1.0,1.0);
        
        
        // Timer        
        timer = new Timer();
        timer.reset();
        timer.stop();
        
     // Homing Elevator
	    this.elevatorHomePositionSet = false;
	    // Comment line below once bottom limit switch installed
	    this.setElevatorReferance();
	    RobotMap.elevatorEncoder.reset();
        
		
        
	}
	
	
		
	// Homing Elevator
	public void setElevatorReferance(){
		// Uncomment after bottom limit switch is installed. Till then robot should be start with elevator fully down position
		/*
		while(!RobotMap.elevatorBottomLimitSwitch.get()){
			RobotMap.elevatorDrive.drive(-0.2, 0);
		}
		*/
		RobotMap.elevatorEncoder.reset();
		this.elevatorHomePositionSet = true;
	}
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
   
    // Drive Stright Distance Using Encoder
    public void moveElevatorToPosition(double position,double maxTimeOut){
    	//TODO
    	RobotMap.elevatorEncoder.reset();
        
        if(!elevatorPID.isEnable()) elevatorPID.enable();
                
        elevatorPID.setSetpoint(position);      // Check direction
        // Check direction
    	this.timeOut = maxTimeOut;
    	this.timer.reset();
    	this.timer.start();
    }
    
    
    
    public void moveElevatorUp(double speed) {
    	elevatorPID.disable();
        //if (! RobotMap.elevatorTopLimitSwitch.get()){
    	RobotMap.elevatorDrive.drive(speed, 0);
        //}else{
        //	RobotMap.elevatorDrive.drive(0.0, 0.0);
       // }
        
    }
    
    public void moveElevatorDown(double speed) {
    	elevatorPID.disable();
        
        //if (! RobotMap.elevatorBottomLimitSwitch.get()){
        	RobotMap.elevatorDrive.drive(-speed, 0);
        //   }else{
        //    	RobotMap.elevatorDrive.drive(0.0, 0.0);
        //    }
        
        
    }
    
    public boolean reachedPosition(){
    	if (timer.get() > this.timeOut || elevatorPID.onTarget()){
    		elevatorPID.disable();
    		timer.stop();
    		timer.reset();
    		return true;
    	}else{
    		return false;
    	}
    		
    	
    	
    }
    
    public void elevatorStop(){
    	elevatorPID.disable();
    	RobotMap.elevatorDrive.drive(0.0, 0.0);
    }
    
    public double getPositionError(){
		   	
    	return elevatorPID.getError();
    	
    }
    
    /**
     *
     * @return Count from the encoder (since the last reset?).
     */
    public double getElevatorEncoderCount() {
       return RobotMap.elevatorEncoder.getRaw();
    }
    
    
    /**
     *
     * @return Distance the encoder has recorded since the last reset, adjusted for the gear ratio.
     */
    public double getElevatorEncoderPosition() {
    	
        return RobotMap.elevatorEncoder.getDistance();
        
    }
    
    /*
    private void moveElevator(double speed){
    	RobotMap.elevatorLeftMotor.set(speed);
    	RobotMap.elevatorRightMotor.set(speed);
    	
    }
    */
    public boolean isElevatorHomePositionSet(){
    	return this.elevatorHomePositionSet;
    }
    
   
    
}

