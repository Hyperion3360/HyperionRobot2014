
package org.usfirst.frc3360.Hyperion2014;
    
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Relay;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    public static SpeedController ms_scCanonAngle_AllWheel;
    public static AnalogChannel ms_anCanonAngle;
    public static DigitalInput ms_diCanonAngle_UpperLimit;
    public static DigitalInput ms_diCanonAngle_LowerLimit;
    
    public static SpeedController ms_scCanonSpinner_AllWheel;
    
    public static DoubleSolenoid ms_dsCanonShooter;
    
    public static SpeedController ms_scDriveTrain_AllWheelRight;
    public static SpeedController ms_scDriveTrain_AllWheelLeft;
    public static RobotDrive ms_rdDriveTrain_AllWheel;
    public static Gyro ms_gyDriveTrain;
    
    public static AnalogChannel ms_anVision_FrontSonar;
    
    public static Compressor m_compressor;
    
    public static Relay ms_rlTeamColorLeds;
    public static Relay ms_rlCanonStatusLeds;
    
    public static void init() {
        ms_rlTeamColorLeds = new Relay(3);
        ms_rlCanonStatusLeds = new Relay(2);
        
        m_compressor = new Compressor(7, 1);
        
        ms_scCanonAngle_AllWheel = new Talon(1, 4);
	LiveWindow.addActuator("CanonAngle", "AllWheelAngleMotor", (Talon) ms_scCanonAngle_AllWheel);
        
        ms_anCanonAngle = new AnalogChannel(1, 4);
	LiveWindow.addSensor("CanonAngle", "AnglePot", ms_anCanonAngle);
        
        ms_diCanonAngle_UpperLimit = new DigitalInput(1, 1);
	LiveWindow.addSensor("CanonAngle", "UpperAngleLimitSwitch", ms_diCanonAngle_UpperLimit);
        
        ms_diCanonAngle_LowerLimit = new DigitalInput(1, 2);
	LiveWindow.addSensor("CanonAngle", "LowerAngleLimitSwitch", ms_diCanonAngle_LowerLimit);
        
        ms_scCanonSpinner_AllWheel = new Talon(1, 3);
	LiveWindow.addActuator("CanonSpinner", "AllWheelSpinnerMotor", (Talon) ms_scCanonSpinner_AllWheel);
     
        ms_dsCanonShooter = new DoubleSolenoid(1, 1, 2);
	LiveWindow.addActuator("CanonShooter", "ShooterSolenoid", ms_dsCanonShooter);
        ms_dsCanonShooter.set(DoubleSolenoid.Value.kReverse);
        
        ms_scDriveTrain_AllWheelRight = new Talon(1, 2);
	LiveWindow.addActuator("DriveTrain", "AllWheelRightMotor", (Talon) ms_scDriveTrain_AllWheelRight);
        
        ms_scDriveTrain_AllWheelLeft = new Talon(1, 1);
	LiveWindow.addActuator("DriveTrain", "AllWheelLeftMotor", (Talon) ms_scDriveTrain_AllWheelLeft);
        
        ms_rdDriveTrain_AllWheel = new RobotDrive(ms_scDriveTrain_AllWheelLeft, ms_scDriveTrain_AllWheelRight);
	
        ms_rdDriveTrain_AllWheel.setSafetyEnabled(true);
        ms_rdDriveTrain_AllWheel.setExpiration(0.1);
        ms_rdDriveTrain_AllWheel.setSensitivity(0.5);
        ms_rdDriveTrain_AllWheel.setMaxOutput(1.0);
        ms_rdDriveTrain_AllWheel.setInvertedMotor(RobotDrive.MotorType.kRearLeft, false);
        ms_rdDriveTrain_AllWheel.setInvertedMotor(RobotDrive.MotorType.kRearRight, false);  

        ms_gyDriveTrain = new Gyro(1, 1);
	LiveWindow.addSensor("DriveTrain", "RobotFrameGyro", ms_gyDriveTrain);
        ms_gyDriveTrain.setSensitivity(0.007);
    }
}
