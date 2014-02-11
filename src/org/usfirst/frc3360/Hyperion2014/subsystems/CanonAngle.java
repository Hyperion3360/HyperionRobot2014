// RobotBuilder Version: 1.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.
package org.usfirst.frc3360.Hyperion2014.subsystems;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import org.usfirst.frc3360.Hyperion2014.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc3360.Hyperion2014.Robot;
import org.usfirst.frc3360.Hyperion2014.commands.CanonAngle_HandleManualMode;
/**
 *
 */
public class CanonAngle extends Subsystem {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    SpeedController allWheelAngleMotor = RobotMap.canonAngleAllWheelAngleMotor;
    AnalogChannel anglePot = RobotMap.canonAngleAnglePot;
    DigitalInput upperAngleLimitSwitch = RobotMap.canonAngleUpperAngleLimitSwitch;
    DigitalInput lowerAngleLimitSwitch = RobotMap.canonAngleLowerAngleLimitSwitch;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    
    // values to be changed after testing
    
    //the maxAngle randomly occured to be PI  - Antoine
    
    // WARNING: The logic will only work if ms_MAX_CANON_ANGLE_VALUE > ms_MIN_CANON_ANGLE_VALUE;
    final double ms_MAX_CANON_ANGLE_VALUE = 2.88;
    final double ms_MIN_CANON_ANGLE_VALUE = 0.1;
    final double ms_MAX_ANGLE_TOLERANCE = 0.1;
    
    double m_dbDesiredSpeed = 0;
    double m_dbAngleDifference = 0;
    double m_dbCurrentAngle;
    double m_dbRequestedAngleValue;
    double m_dbThrottleValue;
    
    
    double m_dbAngleCorrection;
    
    boolean m_bIsGoingDown = false;
    boolean m_bIsGoingUp = false;
    
    boolean m_bElevationStop = false;
    boolean m_bLoweringStop = false;
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void initDefaultCommand() {
        
        setDefaultCommand(new CanonAngle_HandleManualMode());
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
	
    }
    
    
    public void AngleStop(){
        allWheelAngleMotor.set(0);
    }
   
    public void ResetSecurity()
    {
        m_bElevationStop = false;
        m_bLoweringStop = false;
    }
    
    public void HandleManualMode(){
        m_dbDesiredSpeed = -Robot.oi.getCoPilotJoystick().getRawAxis(4);
        System.out.println("HandleManualMode: desiredSpeed" + m_dbDesiredSpeed);
        
        m_dbCurrentAngle = getCurrentAngle();
        System.out.println("HandleManualMode: currentAngle" + m_dbCurrentAngle);
        
        m_dbDesiredSpeed = GetSafeSpeed(m_dbDesiredSpeed);
        
        System.out.println("HandleManualMode: Motor speed" + m_dbDesiredSpeed);
        allWheelAngleMotor.set(m_dbDesiredSpeed);
    }
    
    public void HandleAutoMode(){
        // Automatic mode is currently disabled.
        System.out.println(anglePot.getAverageVoltage() + "potentiometer value");
        
        System.out.println(distanceUltrasonic.getAverageVoltage() + "ultrasonic voltage");

        //insert fuction here
        requestedAngleValue = 1;
        
        //insert function here
        
        currentAngle = anglePot.getAverageVoltage();
        
        angleDifference = requestedAngleValue - currentAngle;
     
        currentDistance = (int)distanceUltrasonic.getAverageVoltage();
        
       
       
           desiredSpeed = angleDifference * 3;
           
           desiredSpeed = GetSafeSpeed(desiredSpeed);
           
           allWheelAngleMotor.set(desiredSpeed);
    }
    
    private double GetSafeSpeed(double speed)
    {
        if (isElevatingSpeed(speed))
        {
            if (!m_bElevationStop)
            {
                if (m_dbCurrentAngle > ms_MAX_CANON_ANGLE_VALUE)
                {
                    System.out.println("HandleManualMode: Maximum angle reach based on potentiometer.");
                    speed = 0;
                }
                else if (isUpperLimitSwitchPressed())
                {
                    System.out.println("HandleManualMode: Maximum angle reach based on limit switch.");
                    m_bElevationStop = true;
                    speed = 0;
                }
                else if (m_bLoweringStop)
                {
                    System.out.println("HandleManualMode: Canceling lowering emergency stop.");
                    m_bLoweringStop = false;
                }
            }
            else
            {
                System.out.println("HandleManualMode: Emergency stop for elevation activated");
                speed = 0;
            }
        }
        else if (isLoweringSpeed(m_dbDesiredSpeed))
        {
            if (!m_bLoweringStop)
            {
                if (m_dbCurrentAngle < ms_MIN_CANON_ANGLE_VALUE)
                {
                    System.out.println("HandleManualMode: Minimum angle reach based on potentiometer.");
                    speed = 0;
                }
                else if (isLowerLimitSwitchPressed())
                {
                    System.out.println("HandleManualMode: Minimum angle reach based on limit switch.");
                    m_bLoweringStop = true;
                    speed = 0;
                }
                else if (m_bElevationStop)
                {
                    System.out.println("HandleManualMode: Canceling elevation emergency stop.");
                    m_bElevationStop = false;
                }
            }
            else
            {
                System.out.println("HandleManualMode: Emergency stop for lowering activated");
                speed = 0;
            }
        }
        
        return speed;
    }
    
    private boolean isUpperLimitSwitchPressed()
    {
        return !upperAngleLimitSwitch.get();
    }
    
    private boolean isLowerLimitSwitchPressed()
    {
        return !lowerAngleLimitSwitch.get();
    }
    
    private double getCurrentAngle()
    {
        return anglePot.getAverageVoltage();
    }
    
    private boolean isLoweringSpeed(double speed)
    {
        return speed < 0;
    }
    
    private boolean isElevatingSpeed(double speed)
    {
        return speed > 0;
    }
}
