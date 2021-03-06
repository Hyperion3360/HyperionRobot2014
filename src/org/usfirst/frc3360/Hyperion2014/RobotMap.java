    // RobotBuilder Version: 1.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.
package org.usfirst.frc3360.Hyperion2014;
    
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PIDSource.PIDSourceParameter;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.image.CriteriaCollection;
import edu.wpi.first.wpilibj.image.NIVision;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    public static SpeedController canonAngleAllWheelAngleMotor;
    public static AnalogChannel canonAngleAnglePot;
    public static DigitalInput canonAngleUpperAngleLimitSwitch;
    public static DigitalInput canonAngleLowerAngleLimitSwitch;
    public static SpeedController canonSpinnerAllWheelSpinnerMotor;
    public static DoubleSolenoid canonShooterShooterSolenoid;
    public static SpeedController driveTrainAllWheelRightMotor;
    public static SpeedController driveTrainAllWheelLeftMotor;
    public static RobotDrive driveTrainAllWheelRobotDrive;
    public static Gyro driveTrainRobotFrameGyro;
    public static AnalogChannel visionFrontSonar;
    public static Solenoid visionFrontSonarSolenoidRelay;
    public static Compressor m_compressor;
    public static Relay ColorLedsRelay;
    public static Relay FlashingLedsRelay;

    public static void init() {
        visionFrontSonar = new AnalogChannel(2);
        visionFrontSonarSolenoidRelay = new Solenoid(3);

        ColorLedsRelay = new Relay(3);
        
        FlashingLedsRelay = new Relay(2);
        
        m_compressor = new Compressor(7, 1);
        
        canonAngleAllWheelAngleMotor = new Talon(1, 4);
	LiveWindow.addActuator("CanonAngle", "AllWheelAngleMotor", (Talon) canonAngleAllWheelAngleMotor);
        
        canonAngleAnglePot = new AnalogChannel(1, 4);
	LiveWindow.addSensor("CanonAngle", "AnglePot", canonAngleAnglePot);
        
        canonAngleUpperAngleLimitSwitch = new DigitalInput(1, 1);
	LiveWindow.addSensor("CanonAngle", "UpperAngleLimitSwitch", canonAngleUpperAngleLimitSwitch);
        
        canonAngleLowerAngleLimitSwitch = new DigitalInput(1, 2);
	LiveWindow.addSensor("CanonAngle", "LowerAngleLimitSwitch", canonAngleLowerAngleLimitSwitch);
        
        canonSpinnerAllWheelSpinnerMotor = new Talon(1, 3);
	LiveWindow.addActuator("CanonSpinner", "AllWheelSpinnerMotor", (Talon) canonSpinnerAllWheelSpinnerMotor);
     
        canonShooterShooterSolenoid = new DoubleSolenoid(1, 1, 2);
	LiveWindow.addActuator("CanonShooter", "ShooterSolenoid", canonShooterShooterSolenoid);
        
        canonShooterShooterSolenoid.set(DoubleSolenoid.Value.kReverse);
        
        driveTrainAllWheelRightMotor = new Talon(1, 2);
	LiveWindow.addActuator("DriveTrain", "AllWheelRightMotor", (Talon) driveTrainAllWheelRightMotor);
        
        driveTrainAllWheelLeftMotor = new Talon(1, 1);
	LiveWindow.addActuator("DriveTrain", "AllWheelLeftMotor", (Talon) driveTrainAllWheelLeftMotor);
        
        driveTrainAllWheelRobotDrive = new RobotDrive(driveTrainAllWheelLeftMotor, driveTrainAllWheelRightMotor);
	
        driveTrainAllWheelRobotDrive.setSafetyEnabled(true);
        driveTrainAllWheelRobotDrive.setExpiration(0.1);
        driveTrainAllWheelRobotDrive.setSensitivity(0.5);
        driveTrainAllWheelRobotDrive.setMaxOutput(1.0);
        driveTrainAllWheelRobotDrive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, false);
        driveTrainAllWheelRobotDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, false);  

        driveTrainRobotFrameGyro = new Gyro(1, 1);
	LiveWindow.addSensor("DriveTrain", "RobotFrameGyro", driveTrainRobotFrameGyro);
        driveTrainRobotFrameGyro.setSensitivity(0.007);
    }
}
