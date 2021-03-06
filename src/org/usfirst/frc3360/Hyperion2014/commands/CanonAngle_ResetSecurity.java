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
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc3360.Hyperion2014.Robot;
/**
 *
 */
public class  CanonAngle_ResetSecurity extends Command {
    public CanonAngle_ResetSecurity() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
	
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }
    // Called just before this Command runs the first time
    protected void initialize() {
        System.out.println("CaRs init");
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    //    System.out.println("angle manual 1");
        Robot.canonAngle.ResetSecurity();
     //   System.out.println("angle manual 2");
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
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
        Robot.canonAngle.AngleStop();
        Robot.canonAngle.ResetSecurity();
    }
}
