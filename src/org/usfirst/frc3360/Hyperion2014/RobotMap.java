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
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.Solenoid;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    
   
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static SpeedController canonAngleAllWheelAngleMotor;
    public static AnalogChannel canonAngleAnglePot;
    public static DigitalInput canonAngleUpperAngleLimitSwitch;
    public static DigitalInput canonAngleLowerAngleLimitSwitch;
    public static SpeedController canonSpinnerAllWheelSpinnerMotor;
    public static AnalogChannel canonSpinnerLeftWheelCurrentSensor;
    public static AnalogChannel canonSpinnerRightWheelCurrentSensor;
    public static Servo canonSpinnerSafetyLeftSafetyServo;
    public static Servo canonSpinnerSafetyRightSafetyServo;
    public static DoubleSolenoid canonShooterShooterSolenoid;
    public static SpeedController driveTrainAllWheelLeftMotor;
    public static SpeedController driveTrainAllWheelRightMotor;
    public static RobotDrive driveTrainAllWheelRobotDrive;
    public static Gyro driveTrainRobotFrameGyro;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    public static AnalogChannel distanceUltrasonic;
    
    // the solenoid is only the power supply for the ultrasonic
    // wich is working on DC 24V and will only be setted to true
    public static Solenoid UltrasonicPower;
    
    public static void init() {
        
        
        UltrasonicPower = new Solenoid(1, 3);
        
        distanceUltrasonic = new AnalogChannel(2);
        
        
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
        
        canonSpinnerSafetyLeftSafetyServo = new Servo(1, 5);
	LiveWindow.addActuator("CanonSpinnerSafety", "LeftSafetyServo", canonSpinnerSafetyLeftSafetyServo);
        
        canonSpinnerSafetyRightSafetyServo = new Servo(1, 6);
	LiveWindow.addActuator("CanonSpinnerSafety", "RightSafetyServo", canonSpinnerSafetyRightSafetyServo);
        
        canonShooterShooterSolenoid = new DoubleSolenoid(1, 1, 2);
	LiveWindow.addActuator("CanonShooter", "ShooterSolenoid", canonShooterShooterSolenoid);
        
        driveTrainAllWheelLeftMotor = new Talon(1, 1);
	LiveWindow.addActuator("DriveTrain", "AllWheelLeftMotor", (Talon) driveTrainAllWheelLeftMotor);
        
        driveTrainAllWheelRightMotor = new Talon(1, 2);
	LiveWindow.addActuator("DriveTrain", "AllWheelRightMotor", (Talon) driveTrainAllWheelRightMotor);
        
        driveTrainAllWheelRobotDrive = new RobotDrive(driveTrainAllWheelLeftMotor, driveTrainAllWheelRightMotor);
	
        driveTrainAllWheelRobotDrive.setSafetyEnabled(true);
        driveTrainAllWheelRobotDrive.setExpiration(0.1);
        driveTrainAllWheelRobotDrive.setSensitivity(0.5);
        driveTrainAllWheelRobotDrive.setMaxOutput(1.0);
        

    
        driveTrainRobotFrameGyro = new Gyro(1, 1);
	LiveWindow.addSensor("DriveTrain", "RobotFrameGyro", driveTrainRobotFrameGyro);
        driveTrainRobotFrameGyro.setSensitivity(0.007);
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

    }
}
