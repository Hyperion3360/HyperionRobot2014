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
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import org.usfirst.frc3360.Hyperion2014.RobotMap;
import org.usfirst.frc3360.Hyperion2014.Robot;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc3360.Hyperion2014.commands.DriveTrain_MoveWithJoystick;
/**
 *
 */
public class DriveTrain extends Subsystem {

    static final double m_dbRATIO_SPEED_TO_POWER = 0.55;

    static final double m_dbDEAD_ZONE_MARGIN = 0.05;

    
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    SpeedController allWheelRightMotor = RobotMap.driveTrainAllWheelRightMotor;
    SpeedController allWheelLeftMotor = RobotMap.driveTrainAllWheelLeftMotor;
    RobotDrive allWheelRobotDrive = RobotMap.driveTrainAllWheelRobotDrive;
    Gyro robotFrameGyro = RobotMap.driveTrainRobotFrameGyro;
    Encoder leftWheelsEncoder = RobotMap.driveTrainleftWheelsEncoder;
    Encoder rightWheelsEncoder = RobotMap.driveTrainrightWheelsEncoder;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    double m_dbActualCommandLeft;
    double m_dbActualCommandRight;
    double m_dbControllerGain;

    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        setDefaultCommand(new DriveTrain_MoveWithJoystick());
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
    }
    
    public void setSpeed(double leftSpeedMS, double rightSpeedMS)
    {
        SmartDashboard.putNumber("Left motor speed", leftSpeedMS);
        SmartDashboard.putNumber("Rigth motor speed", rightSpeedMS);
        System.out.println("autonomous: left=" + leftSpeedMS * m_dbRATIO_SPEED_TO_POWER +
                           ", right=" + rightSpeedMS * m_dbRATIO_SPEED_TO_POWER);
        allWheelRobotDrive.tankDrive(leftSpeedMS * m_dbRATIO_SPEED_TO_POWER,
                                     rightSpeedMS * m_dbRATIO_SPEED_TO_POWER);
    }
    
    public void driveWithJoystick()
    {
        // Joystick axis are inverted (- is forward and + is backward).
        double dLeftJoystick = -Robot.oi.getDriverLeftJoystick().getRawAxis(2);
        SmartDashboard.putNumber("Left motor speed", dLeftJoystick );
        double dRightJoystick = -Robot.oi.getDriverRightJoystick().getRawAxis(2);
        SmartDashboard.putNumber("Rigth motor speed", dRightJoystick );

        System.out.println("teleop: left=" + -Robot.oi.getDriverLeftJoystick().getRawAxis(2) +
                           ", right=" + -Robot.oi.getDriverLeftJoystick().getRawAxis(4));

        // Give a dead zone to the joystick.        
        if (dLeftJoystick < m_dbDEAD_ZONE_MARGIN && dLeftJoystick > -m_dbDEAD_ZONE_MARGIN)
        {
            dLeftJoystick = 0;
        }
        
        if (dRightJoystick < m_dbDEAD_ZONE_MARGIN && dRightJoystick > -m_dbDEAD_ZONE_MARGIN)
        {
            dRightJoystick = 0;
        }

        allWheelRobotDrive.tankDrive(-dLeftJoystick , -dRightJoystick);
    }


    //autonomousCommand Software
    public void initEncoders() {
        leftWheelsEncoder.reset();
        rightWheelsEncoder.reset();
        // Use these 2 lines to calibrate number of pulses
        //leftWheelsEncoder.setDistancePerPulse(1);
        //rightWheelsEncoder.setDistancePerPulse(1);
        
        // Use these 2 lines when calibrated
        leftWheelsEncoder.setDistancePerPulse(0.0008284961);
        rightWheelsEncoder.setDistancePerPulse(0.00082338);
        
        leftWheelsEncoder.setReverseDirection(true);
        leftWheelsEncoder.start();
        rightWheelsEncoder.start();

    }

    public void stopDriving() {
        allWheelRobotDrive.tankDrive(0.0, 0.0);
        m_dbActualCommandLeft = 0;
        m_dbActualCommandRight = 0;
    }

    public void driveWithEncoders(double speed) {
        double sentCommandLeft = m_dbActualCommandLeft;
        double sentCommandRight = m_dbActualCommandRight;
        controlSpeed(speed);
        if (Math.abs(m_dbActualCommandLeft) < 0.65) {
            sentCommandLeft = 0;
        } else if (m_dbActualCommandLeft > 1) {
            sentCommandLeft = 1;
        } else if (m_dbActualCommandLeft < -1) {
            sentCommandLeft = -1;
        }
        if (Math.abs(m_dbActualCommandRight) < 0.5) {
            sentCommandRight = 0;
        } else if (m_dbActualCommandRight > 1) {
            sentCommandRight = 1;
        } else if (m_dbActualCommandRight < -1) {
            sentCommandRight = -1;
        }

        allWheelRobotDrive.tankDrive(sentCommandLeft, sentCommandRight);
        
        System.out.println("Left" + leftWheelsEncoder.getDistance());
        System.out.println("Right" + rightWheelsEncoder.getDistance());
        SmartDashboard.putNumber("Distance droit", rightWheelsEncoder.getDistance());
        SmartDashboard.putNumber("Distance gauche", leftWheelsEncoder.getDistance());
        System.out.println("Vitesse droite (m par s)" + leftWheelsEncoder.getRate());
        System.out.println("Vitesse gauche (m par s)" + rightWheelsEncoder.getRate());
        SmartDashboard.putNumber("Vitesse droite (m)", rightWheelsEncoder.getRate());
        SmartDashboard.putNumber("Vitesse gauche (m)", leftWheelsEncoder.getRate());

    }
    
    public void initSpeedController() {
        m_dbActualCommandLeft = 0;
        m_dbActualCommandRight = 0;
        m_dbControllerGain = SmartDashboard.getNumber("Speed controller gain", 0.02);

    }

    public double getDrivedDistance() {
        double distance = (leftWheelsEncoder.getDistance() + rightWheelsEncoder.getDistance()) / 2;
        System.out.println("Distance parcourue (m)" + distance);
        return distance;
    }


    private void controlSpeed(double speed) {
        // Calcul de la nouvelle commande
        double speedErrorLeft = speed - leftWheelsEncoder.getRate();
        m_dbActualCommandLeft = m_dbActualCommandLeft + m_dbControllerGain * speedErrorLeft;

        double speedErrorRight = speed - rightWheelsEncoder.getRate();
        m_dbActualCommandRight = m_dbActualCommandRight + m_dbControllerGain * speedErrorRight;

        // Affichage des valeurs
        System.out.println("Erreur (m/s) = L:" + speedErrorLeft + " R:" + speedErrorRight);

        SmartDashboard.putNumber("Erreur en vitesse gauche (m par s)", speedErrorLeft);
        SmartDashboard.putNumber("Erreur en vitesse droite (m par s)", speedErrorRight);
        SmartDashboard.putNumber("Commande gauche (-1 a 1)", m_dbActualCommandLeft);
        SmartDashboard.putNumber("Commande droite (-1 a 1)", m_dbActualCommandRight);
    }
}
