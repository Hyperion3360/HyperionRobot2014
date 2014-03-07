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
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import org.usfirst.frc3360.Hyperion2014.RobotMap;
import org.usfirst.frc3360.Hyperion2014.Robot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc3360.Hyperion2014.commands.DriveTrain_MoveWithJoystick;

/**
 *
 */
public class DriveTrain extends PIDSubsystem {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    SpeedController allWheelRightMotor = RobotMap.driveTrainAllWheelRightMotor;
    SpeedController allWheelLeftMotor = RobotMap.driveTrainAllWheelLeftMotor;
    RobotDrive allWheelRobotDrive = RobotMap.driveTrainAllWheelRobotDrive;
    Gyro robotFrameGyro = RobotMap.driveTrainRobotFrameGyro;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    static final double ms_MAX_ANGLE_SPEED_DEGREE_PER_SECOND = 360;
    
    double m_dbLinearSpeed = 0;
    
    public DriveTrain() {
        super("DriveTrainAngle", 0.01, 0.018, 0, 0.020);
        getPIDController().setContinuous(false);
        getPIDController().setInputRange(-ms_MAX_ANGLE_SPEED_DEGREE_PER_SECOND, ms_MAX_ANGLE_SPEED_DEGREE_PER_SECOND);
        getPIDController().setOutputRange(-1, 1);
        getPIDController().setPercentTolerance(5);
    }
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void initDefaultCommand() {
        setDefaultCommand(new DriveTrain_MoveWithJoystick());        
        robotFrameGyro.reset();
    }
    
    public void driveWithJoystick()
    {
        // Joystick axis are inverted (- is forward and + is backward).
        double dLeftJoystick = -Robot.oi.getDriverLeftJoystick().getRawAxis(2);
        SmartDashboard.putNumber("Left motor speed", dLeftJoystick );
        double dRightJoystick = -Robot.oi.getDriverRightJoystick().getRawAxis(2);
        SmartDashboard.putNumber("Rigth motor speed", dRightJoystick );

        //System.out.println("teleop: left=" + dLeftJoystick + ", right=" + dRightJoystick);

        allWheelRobotDrive.tankDrive(dLeftJoystick , dRightJoystick);
        
        SmartDashboard.putNumber("Gyro rate", robotFrameGyro.getRate());
    }
    
    public void driveArcade(double linearSpeed, double rotationSpeed)
    {        
        //System.out.println("driveArcade");
        m_dbLinearSpeed = linearSpeed;
        setSetpoint(rotationSpeed);
    }
    
    public void driveForwardSpeed(double batteryPercent)
    {
        //System.out.println("driveForwardSpeed");
        SmartDashboard.putNumber("Left motor speed", 0 );
        SmartDashboard.putNumber("Rigth motor speed", 0 );

        //System.out.println("Drive train disabled: left=" + 0 + ", right=" + 0);
        allWheelRobotDrive.arcadeDrive(batteryPercent , 0);
    }
    
    public void resetGyro()
    {
        robotFrameGyro.reset();
    }
    
    public void enableFollowAngleMode()
    {
        resetGyro();
        setSetpoint(0);
        enable();
    }
    
    public void disableFollowAngle()
    {
        resetGyro();
        setSetpoint(0);
        disable();
    }
    
    protected double returnPIDInput() {
        //System.out.println("PID Read: Gyro rate = " + robotFrameGyro.getRate());
        SmartDashboard.putNumber("PID Read: Gyro rate", robotFrameGyro.getRate());
        return robotFrameGyro.getRate();
    }

    protected void usePIDOutput(double d) {
        //System.out.println("PID Output: DriveTrain rotation output = " + -d);
        allWheelRobotDrive.arcadeDrive(m_dbLinearSpeed, -d);
    }
}
