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
public class  DriveTrain_MoveTo extends Command {
    double m_dbDistanceM = 0;
    double m_dbTopSpeedMS = 0;
    double m_dbTimeToTravel = 0;
    
    public DriveTrain_MoveTo(double dbDistanceM, double dbTopSpeedMS) {
        // La distance ne peut pas etre negative!!!!
        m_dbDistanceM = dbDistanceM;
        m_dbTopSpeedMS = dbTopSpeedMS;
        
        m_dbTimeToTravel = Math.abs(dbDistanceM) / Math.abs(dbTopSpeedMS);
        
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
	
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.driveTrain);
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }
    // Called just before this Command runs the first time
    protected void initialize() {
        System.out.println("MT: Init move to Dist=" + m_dbDistanceM + ", Speed=" + m_dbTopSpeedMS + ", Time=" + m_dbTimeToTravel);
        setTimeout(m_dbTimeToTravel);        
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.driveTrain.setSpeed(m_dbTopSpeedMS, m_dbTopSpeedMS);        
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }
    // Called once after isFinished returns true
    protected void end() {
        System.out.println("MT: END");
        Robot.driveTrain.setSpeed(0, 0);
    }
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        System.out.println("MT: INTERRUPT");
        Robot.driveTrain.setSpeed(0, 0);
    }
}
