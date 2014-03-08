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
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStationEnhancedIO;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc3360.Hyperion2014.Robot;
/**
 *
 */
public class  CanonSpinner_Shoot extends Command {
    
    public CanonSpinner_Shoot() {
        requires(Robot.ms_canonSpinner);
    }
    // Called just before this Command runs the first time
    protected void initialize() {
        System.out.println("CS Shoot init");
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double dbShootSpeed = 0;
        try {
            if (!DriverStation.getInstance().getEnhancedIO().getDigital(8))
            {
                dbShootSpeed = Robot.ms_canonSpinner.GetManualSpeed();                
            }
            else
            {
                dbShootSpeed = Robot.ms_canonSpinner.GetPresetShootSpeed();
            }
        } catch (DriverStationEnhancedIO.EnhancedIOException ex) {
            ex.printStackTrace();
        }
   //     System.out.println("topspeed 1");
        Robot.ms_canonSpinner.PrepareShoot(dbShootSpeed);
     //   System.out.println("topspeed 2");
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }
    // Called once after isFinished returns true
    protected void end() {
        System.out.println("CS Shoot - end");
        Robot.ms_canonSpinner.StopSpinning();
    }
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        System.out.println("CS Shoot - int");
        Robot.ms_canonSpinner.StopSpinning();
    }
}
