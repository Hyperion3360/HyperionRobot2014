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
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import org.usfirst.frc3360.Hyperion2014.RobotMap;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc3360.Hyperion2014.Robot;
import org.usfirst.frc3360.Hyperion2014.commands.CanonAngle_HandleManualMode;
/**
 *
 */
public class CanonAngle extends PIDSubsystem {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    SpeedController allWheelAngleMotor = RobotMap.canonAngleAllWheelAngleMotor;
    AnalogChannel anglePot = RobotMap.canonAngleAnglePot;
    DigitalInput upperAngleLimitSwitch = RobotMap.canonAngleUpperAngleLimitSwitch;
    DigitalInput lowerAngleLimitSwitch = RobotMap.canonAngleLowerAngleLimitSwitch;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    AnalogChannel FrontSonar = RobotMap.visionFrontSonar;
    
    
    // values to be changed after testing
    
    //the maxAngle randomly occured to be PI  - Antoine
    
    // WARNING: The logic will only work if ms_MAX_CANON_ANGLE_VALUE > ms_MIN_CANON_ANGLE_VALUE;
    final double ms_MAX_CANON_ANGLE_VALUE = 90;
    final double ms_MIN_CANON_ANGLE_VALUE = 5;
    final double ms_MAX_ANGLE_TOLERANCE = 0.5;
    
    double m_dbDesiredSpeed = 0;
    double m_dbAngleDifference = 0;
    double m_dbCurrentAngle;
    double m_dbRequestedAngleValue;
    double m_dbThrottleValue;
    
    double m_dbCurrentDistance;
    
    double m_dbAngleCorrection;
    
    double m_dbAngleWanted;
    
    boolean m_bIsGoingDown = false;
    boolean m_bIsGoingUp = false;
    boolean m_bIsReadyToShoot = false;
    
    boolean m_bElevationStop = false;
    boolean m_bLoweringStop = false;
    
    public CanonAngle() {
        super("CanonAngle", 0.03, 0, 0);
        getPIDController().setContinuous(false);
        getPIDController().setInputRange(ms_MIN_CANON_ANGLE_VALUE, ms_MAX_CANON_ANGLE_VALUE);
        getPIDController().setOutputRange(-1, 1);
        getPIDController().setPercentTolerance(15);
    }
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void initDefaultCommand() {
        
        
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
    
    public void EnableAngleMode()
    {
        setSetpoint(getCurrentAngle());
        enable();
    }
    
    public void DisableAngleMode()
    {
        disable();
        AngleStop();
        ResetSecurity();
    }
    
    public void HandleAngleMode(double dbAngleDegree)
    {
        setSetpoint(dbAngleDegree);
    }
    
    public void HandleVelocityMode(){
        
        System.out.println(FrontSonar.getAverageVoltage() + "Ultrasonic Voltage");
        m_dbCurrentDistance = FrontSonar.getAverageVoltage();
        SmartDashboard.putNumber("current Angle", m_dbCurrentAngle);
        m_dbDesiredSpeed = -Robot.oi.getCoPilotJoystick().getRawAxis(4);
        System.out.println("HandleManualMode: desiredSpeed" + m_dbDesiredSpeed);
        
        m_dbCurrentAngle = getCurrentAngle();
        System.out.println("HandleManualMode: currentAngle" + m_dbCurrentAngle);
        SmartDashboard.putNumber("current Angle", m_dbCurrentAngle);
        
        m_dbDesiredSpeed = GetSafeSpeed(m_dbDesiredSpeed);
        
        System.out.println("HandleManualMode: Motor speed" + m_dbDesiredSpeed);
        allWheelAngleMotor.set(m_dbDesiredSpeed);
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
        final double dbVoltageAt90Degree = 2.99;
        final double dbVoltageAt0Degree = 0.47;
        final double dbVoltageToAngleRatio = 90/(dbVoltageAt90Degree-dbVoltageAt0Degree);
        
        double dbCurrentAnglePotValue = anglePot.getAverageVoltage();
        
        double dbCurrentAngle = dbCurrentAnglePotValue * dbVoltageToAngleRatio;
        // Automatic mode is currently disabled.
        System.out.println("potentiometer value = " + dbCurrentAnglePotValue);
        System.out.println("Canon angle = " + dbCurrentAngle);
        SmartDashboard.putNumber("Canon angle (degree)", dbCurrentAngle);
        return dbCurrentAngle;
    }
    
    private boolean isLoweringSpeed(double speed)
    {
        return speed < 0;
    }
    
    private boolean isElevatingSpeed(double speed)
    {
        return speed > 0;
    }

    protected double returnPIDInput() {
        return getCurrentAngle();
    }

    protected void usePIDOutput(double d) {
        System.out.println("PID ANGLE Motor output = " + d);
        System.out.println("Safe PID output = " + GetSafeSpeed(d));
        allWheelAngleMotor.set(GetSafeSpeed(d));
    }
    
    public double getPerfectAngle(){
        m_dbCurrentDistance = Robot.vision.getCameraInfoDistance();
        
        //formule a modifier****
        m_dbAngleWanted = 20.111*(m_dbCurrentDistance * m_dbCurrentDistance)-60.2156*m_dbCurrentDistance+112.574;
        //formule a modifier****
        
        
        SmartDashboard.putNumber("Angle Wanted: ", m_dbAngleWanted);
        
        return m_dbAngleWanted;
    }
    
    public void setAngleAuto(){
        m_dbAngleWanted = getPerfectAngle();
        
        m_dbCurrentAngle = getCurrentAngle();
        
        m_dbAngleDifference = m_dbAngleWanted - m_dbCurrentAngle;
        
        m_dbDesiredSpeed = m_dbAngleDifference;
        
        if(m_dbAngleDifference > -0.5 && m_dbAngleDifference < 0.5)
        {
            m_bIsReadyToShoot = true;
        }
        else{
            m_bIsReadyToShoot = false;
        }
        
        SmartDashboard.putBoolean("Ready to shoot", m_bIsReadyToShoot);
        
        m_dbDesiredSpeed = GetSafeSpeed(m_dbDesiredSpeed);
        
        allWheelAngleMotor.set(m_dbDesiredSpeed);
        
    }
}
