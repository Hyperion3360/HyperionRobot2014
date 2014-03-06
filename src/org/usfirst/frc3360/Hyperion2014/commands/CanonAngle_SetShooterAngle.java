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
 * @author Hyperion 3360
 */
public class CanonAngle_SetShooterAngle extends Command {
    double m_dbAngleDegree = 0;
    boolean m_bStopAtAngle = false;
    boolean m_bShootAtPerfectAngle = false;
    
    public CanonAngle_SetShooterAngle(boolean bShootAtPerfectAngle) {
        requires(Robot.canonAngle);
        m_dbAngleDegree = 0;
        m_bStopAtAngle = false;
        m_bShootAtPerfectAngle = bShootAtPerfectAngle;
    }
    
    public CanonAngle_SetShooterAngle(double dbAngleDegree) {
        requires(Robot.canonAngle);
        m_dbAngleDegree = dbAngleDegree;
        m_bStopAtAngle = false;
        m_bShootAtPerfectAngle = false;
    }
    
    public CanonAngle_SetShooterAngle(double dbAngleDegree, boolean bStopAtAngle) {
        requires(Robot.canonAngle);
        m_dbAngleDegree = dbAngleDegree;
        m_bStopAtAngle = bStopAtAngle;
        m_bShootAtPerfectAngle = false;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        if (m_bShootAtPerfectAngle)
        {
            m_dbAngleDegree = Robot.canonAngle.getPerfectAngle();
        }
        System.out.println("Start set Shooter Angle = " + m_dbAngleDegree);
        
        Robot.canonAngle.AngleStop();
        Robot.canonAngle.ResetSecurity();
        Robot.canonAngle.EnableAngleMode();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        
        Robot.canonAngle.HandleAngleMode(m_dbAngleDegree);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        boolean bIsFinished = false;
        if (m_bStopAtAngle && 
            ((Robot.canonAngle.getCurrentAngle() < m_dbAngleDegree + 1) &&
             (Robot.canonAngle.getCurrentAngle() > m_dbAngleDegree - 1)))
        {
            bIsFinished = true;
        }
        return bIsFinished;
    }
    
    // Called once after isFinished returns true
    protected void end() {
        CommandExit();
    }
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        CommandExit();
    }
    
    private void CommandExit() {
        Robot.canonAngle.DisableAngleMode();
    }
}
