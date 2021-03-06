/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc3360.Hyperion2014.commands;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc3360.Hyperion2014.Robot;
/**
 *
 * @author Hyperion3360
 */
public class DriveTrain_Idle extends Command {
    double m_dbTimeIdleS = 0;
    
    public DriveTrain_Idle(double dbTimeIdleS) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.driveTrain);
        m_dbTimeIdleS = dbTimeIdleS;
    }
    // Called just before this Command runs the first time
    protected void initialize() {
        
        Robot.driveTrain.resetGyro();
        //System.out.println("DTI: TimeToWait=" + m_dbTimeIdleS);
        if (m_dbTimeIdleS > 0)
        {
            setTimeout(m_dbTimeIdleS);   
        }
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.driveTrain.driveForwardSpeed(0);
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return m_dbTimeIdleS > 0 && isTimedOut();
    }
    // Called once after isFinished returns true
    protected void end() {
        //System.out.println("DT Idle end");
    }
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        //System.out.println("DT Idle int");
    }
}
