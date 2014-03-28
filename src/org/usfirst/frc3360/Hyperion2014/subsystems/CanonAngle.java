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
import edu.wpi.first.wpilibj.DriverStation;
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
    boolean m_bUseSonarForDistance = true;
    
    SpeedController allWheelAngleMotor = RobotMap.canonAngleAllWheelAngleMotor;
    AnalogChannel anglePot = RobotMap.canonAngleAnglePot;
    DigitalInput upperAngleLimitSwitch = RobotMap.canonAngleUpperAngleLimitSwitch;
    DigitalInput lowerAngleLimitSwitch = RobotMap.canonAngleLowerAngleLimitSwitch;
    
    AnalogChannel m_anFrontSonar = RobotMap.visionFrontSonar;
    
    
    // values to be changed after testing
    
    //the maxAngle randomly occured to be PI  - Antoine
    
    // WARNING: The logic will only work if ms_MAX_CANON_ANGLE_VALUE > ms_MIN_CANON_ANGLE_VALUE;
    public final static double ms_MAX_CANON_ANGLE_VALUE = 90;
    public final static double ms_MIN_CANON_ANGLE_VALUE = 8;
    final double ms_MAX_ANGLE_TOLERANCE = 0.025;
    
    final double MaxThrottleValue = 0.55;
    final double MinThrottleValue = 2.43;
    
    final double AngleAt90 = 4.50;
    final double AngleAt0 = 0.35;
    
    double m_dbDesiredSpeed = 0;
    double m_dbAngleDifference = 0;
    double m_dbCurrentAngle;
    double m_dbRequestedAngleValue;
    double m_dbThrottleValue;
    
    double m_dbCurrentDistanceFeet = 0;
    
    double m_dbAngleCorrection;
    
    double m_dbAngleWanted;
    
    double m_dbA = -0.0002;
    double m_dbB = 0.0126;
    double m_dbC = -0.3406;
    double m_dbD = 4.6572;
    double m_dbE = -32.972;
    double m_dbF = 151.4;
    
    
    boolean m_bIsGoingDown = false;
    boolean m_bIsGoingUp = false;
    boolean m_bIsReadyToShoot = false;
    
    boolean m_bElevationStop = false;
    boolean m_bLoweringStop = false;
    
    public CanonAngle() {
        super("CanonAngle", 0.07, .0001, 0);
        getPIDController().setContinuous(false);
        getPIDController().setInputRange(ms_MIN_CANON_ANGLE_VALUE, ms_MAX_CANON_ANGLE_VALUE);
        getPIDController().setOutputRange(-1, 1);
        getPIDController().setPercentTolerance(5);
        
       
    }
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void initDefaultCommand() {
        
        
        setDefaultCommand(new CanonAngle_HandleManualMode());
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
        //System.out.println("HandleAngleMode: " + dbAngleDegree);
        setSetpoint(dbAngleDegree);
        GetSonarDistance();
    }
    
    public void HandleVelocityMode(){
        
        
        double ThrottleValue = DriverStation.getInstance().getAnalogIn(1);
        
        //System.out.println("ThrottleValue:" + ThrottleValue);
        
        m_dbAngleWanted = (ms_MAX_CANON_ANGLE_VALUE-ms_MIN_CANON_ANGLE_VALUE)/(MaxThrottleValue-MinThrottleValue) *ThrottleValue + 114;
        
        
        HandleAngleMode(m_dbAngleWanted);
        
    }
    
    private double GetSafeSpeed(double speed)
    {
        m_dbCurrentAngle = getCurrentAngle();
        if (isElevatingSpeed(speed))
        {
            if (!m_bElevationStop)
            {
                if (m_dbCurrentAngle > ms_MAX_CANON_ANGLE_VALUE)
                {
                    //System.out.println("HandleManualMode: Maximum angle reach based on potentiometer.");
                    speed = 0;
                }
                else if (isUpperLimitSwitchPressed())
                {
                    //System.out.println("HandleManualMode: Maximum angle reach based on limit switch.");
                    m_bElevationStop = true;
                    speed = 0;
                }
                else if (m_bLoweringStop)
                {
                    //System.out.println("HandleManualMode: Canceling lowering emergency stop.");
                    m_bLoweringStop = false;
                }
            }
            else
            {
                //System.out.println("HandleManualMode: Emergency stop for elevation activated");
                speed = 0;
            }
        }
        else if (isLoweringSpeed(speed))
        {
            if (!m_bLoweringStop)
            {
                if (m_dbCurrentAngle < ms_MIN_CANON_ANGLE_VALUE)
                {
                    //System.out.println("HandleManualMode: Minimum angle reach based on potentiometer.");
                    speed = 0;
                }
                else if (isLowerLimitSwitchPressed())
                {
                    //System.out.println("HandleManualMode: Minimum angle reach based on limit switch.");
                    m_bLoweringStop = true;
                    speed = 0;
                }
                else if (m_bElevationStop)
                {
                    //System.out.println("HandleManualMode: Canceling elevation emergency stop.");
                    m_bElevationStop = false;
                }
            }
            else
            {
                //System.out.println("HandleManualMode: Emergency stop for lowering activated");
                speed = 0;
            }
        }
        else{
            speed = 0.05;
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
    
    public double getCurrentAngle()
    {
        final double dbVoltageAt90Degree = AngleAt90;
        final double dbVoltageAt0Degree = AngleAt0;
        final double dbVoltageToAngleRatio = 90/(dbVoltageAt90Degree-dbVoltageAt0Degree);
        
        double dbCurrentAnglePotValue = anglePot.getAverageVoltage();
        
        double dbCurrentAngle = dbCurrentAnglePotValue * dbVoltageToAngleRatio;
        // Automatic mode is currently disabled.
        //System.out.println("potentiometer value = " + dbCurrentAnglePotValue);
        //System.out.println("Canon angle = " + dbCurrentAngle);
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
        //System.out.println("PID ANGLE Motor output = " + d);
        //System.out.println("Safe PID output = " + GetSafeSpeed(d));
        allWheelAngleMotor.set(GetSafeSpeed(d));
    }
    
    private double GetSonarDistance()
    {
        double dbSonarValue = m_anFrontSonar.getAverageVoltage();
        double dbCurrentDistanceFeet = dbSonarValue * 1.9672799229 - 0.1434867439;
        if (dbCurrentDistanceFeet < 0)
        {
            dbCurrentDistanceFeet = 0;
        }
        //System.out.print("Sonar voltage = " + dbSonarValue + ", distance= " + dbCurrentDistanceFeet);
        SmartDashboard.putNumber("Front sonar voltage", dbSonarValue);
        SmartDashboard.putNumber("Sonar measured distance", dbCurrentDistanceFeet); 
        
        return dbCurrentDistanceFeet;
    }
    
    public double getPerfectAngle(){
        m_dbCurrentDistanceFeet = GetSonarDistance();
        System.out.println("Perfect angle using sonar value = " + m_dbCurrentDistanceFeet);
        
        //formule a modifier****
        m_dbAngleWanted = m_dbA * com.sun.squawk.util.MathUtils.pow(m_dbCurrentDistanceFeet, 5) +
                          m_dbB * com.sun.squawk.util.MathUtils.pow(m_dbCurrentDistanceFeet, 4) +
                          m_dbC * com.sun.squawk.util.MathUtils.pow(m_dbCurrentDistanceFeet, 3) +
                          m_dbD * com.sun.squawk.util.MathUtils.pow(m_dbCurrentDistanceFeet, 2) +
                          m_dbE * m_dbCurrentDistanceFeet +
                          m_dbF;
        //formule a modifier****
        if(m_dbAngleWanted<0){
            m_dbAngleWanted= 0;
        }else if(m_dbAngleWanted > 90){            
            m_dbAngleWanted= 90;
        }
        
        System.out.println("Angle wanted = " + m_dbAngleWanted);
        SmartDashboard.putNumber("Angle Wanted: ", m_dbAngleWanted);
        
        return m_dbAngleWanted;
    }
    
    public void setAngleAuto(){
        
        m_dbAngleWanted = getPerfectAngle();
        
        HandleAngleMode(m_dbAngleWanted);
        
        
    }
}
