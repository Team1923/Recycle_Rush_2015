package org.usfirst.frc.team1923.robot.subsystems;

import org.usfirst.frc.team1923.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 *
 */
public class PIElevatorSubsystem extends PIDSubsystem {
    
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
					            WHEEL_CIRCUMFERENCE = 6.0,   // 4 inches wheels
					            kp = 0.5, ki = 0.0, kd = 0.3,     // LEAVE THESE CONSTANTS ALONE!
					            PID_LOOP_TIME = .05, 
					            encoderTOLERANCE = 0.1;         // +/- 2" tolarance
	
	
	
	public PIElevatorSubsystem() {
        super(kp,ki,kd);
        this.init();
        this.disable();
	}
	
	
	public void init(){
		
		// Set distance per pulse for each encoder
		RobotMap.elevatorEncoder.setDistancePerPulse(GEAR_RATIO*WHEEL_CIRCUMFERENCE/NUM_CLICKS);
	   // Set PID source parameter to Distance...
		//RobotMap.elevatorEncoder.setPIDSourceParameter(Encoder.PIDSourceParameter.kDistance);
	    
	    // PID tolerance
	    this.setAbsoluteTolerance(encoderTOLERANCE);
	    this.setOutputRange(-1.0, 1.0);
	    this.setInputRange(-40.0, 40.0);
	    
	    
	    
	    // Timer        
	    timer = new Timer();
	    timer.reset();
	    timer.stop();
	    
	    // Homing Elevator
	    this.elevatorHomePositionSet = false;
	    // Comment line below once bottom limit switch installed
	    this.setElevatorReferance();
	    
	    // Live Window
	    //LiveWindow.addSensor("ElevatorSubsystem", "Elevator Encoder", RobotMap.elevatorEncoder);
	    	    
	}
	
	// Homing Elevator
	public void setElevatorReferance(){
		// Uncomment after bottom limit switch is installed. Till then robot should be start with elevator fully down position
		/*
		while(!RobotMap.elevatorBottomLimitSwitch.get()){
			RobotMap.elevatorDrive.drive(-0.4, 0);
		}
		*/
		RobotMap.elevatorEncoder.reset();
		this.elevatorHomePositionSet = true;
	}
	
	
	/**
     * Use the elevator Encoder as the PID sensor. This method is automatically
     * called by the subsystem.
     */
    protected double returnPIDInput() {
        return RobotMap.elevatorEncoder.getDistance();
    }


    /**
     * Use the elevatorDrive as the PID output. This method is automatically called by
     * the subsystem.
     */
    protected void usePIDOutput(double d) {
       // if(! (RobotMap.elevatorTopLimitSwitch.get() || RobotMap.elevatorBottomLimitSwitch.get())){
    	RobotMap.elevatorDrive.drive(d,0);
      //  }else{
       // 	this.elevatorDrive.drive(0.0,0.0);
        	
       // }
    }
	
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
   
    // Drive Stright Distance Using Encoder
    public void moveElevatorToPosition(double position,double maxTimeOut){
    	//TODO
    	setSetpoint(position);
    	this.enable();
    	this.timeOut = maxTimeOut;
    	this.timer.reset();
    	this.timer.start();
    }
    
    
    
    public void moveElevatorUp(double speed) {
        this.disable();
        //if (! RobotMap.elevatorTopLimitSwitch.get()){
    	RobotMap.elevatorDrive.drive(speed, 0);
        //}else{
        //	RobotMap.elevatorDrive.drive(0.0, 0.0);
       // }
        
    }
    
    public void moveElevatorDown(double speed) {
        this.disable();
        
        //if (! RobotMap.elevatorBottomLimitSwitch.get()){
        	RobotMap.elevatorDrive.drive(-speed, 0);
        //   }else{
        //    	RobotMap.elevatorDrive.drive(0.0, 0.0);
        //    }
        
        
    }
    
    public boolean reachedPosition(){
    	if (timer.get() > this.timeOut || this.onTarget()){
    		this.disable();
    		timer.stop();
    		timer.reset();
    		return true;
    	}else{
    		return false;
    	}
    		
    	
    	
    }
    
    public void elevatorStop(){
    	this.disable();
    	RobotMap.elevatorDrive.drive(0.0, 0.0);
    }
    
    public double getPositionError(){
		   	
    	return this.getSetpoint() - this.getPosition();
    	
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

