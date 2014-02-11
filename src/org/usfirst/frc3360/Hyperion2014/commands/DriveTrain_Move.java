// RobotBuilder Version: 1.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.
package org.usfirst.frc3360.Hyperion2014.commands;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc3360.Hyperion2014.Robot;
/**
 *
 */
public class  DriveTrain_Move extends Command {
    double m_speedWanted;
    double m_distanceWanted ;

    public DriveTrain_Move() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
	
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.driveTrain);
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }
    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.driveTrain.initEncoders();
        Robot.driveTrain.initSpeedController();
        m_distanceWanted = 5;//SmartDashboard.getNumber("Distance du mode autonome");
        m_speedWanted = 1.2;//SmartDashboard.getNumber("Vitesse du mode autonome");
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        System.out.println("Autonomous set speed = " + m_speedWanted);
        Robot.driveTrain.driveWithEncoders(m_speedWanted);
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (m_distanceWanted - Robot.driveTrain.getDrivedDistance()) <= 0;
    }
    // Called once after isFinished returns true
    protected void end() {
    }
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
