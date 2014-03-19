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
public class  CanonAngle_HandleAutoMode extends Command {
    public CanonAngle_HandleAutoMode() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
	
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.canonAngle);
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }
    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.canonAngle.AngleStop();
        Robot.canonAngle.EnableAngleMode();
        Robot.canonAngle.ResetSecurity();
        System.out.println("CaHam init");
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
     //   System.out.println("angle auto 1");

        Robot.canonAngle.setAngleAuto();
        
     //   System.out.println("angle auto 2");
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }
    // Called once after isFinished returns true
    protected void end() {
        System.out.println("anglehandle auto end");
        
        CommandExit();
    }
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        System.out.println("anglehandle auto interrupt");
        CommandExit();
    }
    
    private void CommandExit() {
        Robot.canonAngle.DisableAngleMode();
    }
}