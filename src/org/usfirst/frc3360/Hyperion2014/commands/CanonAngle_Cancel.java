
package org.usfirst.frc3360.Hyperion2014.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc3360.Hyperion2014.Robot;

public class CanonAngle_Cancel extends Command {
    
    public CanonAngle_Cancel() {
        requires(Robot.ms_canonAngle);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        System.out.println("CaC init");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
        System.out.println("Canceled canon angle commands");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        System.out.println("Canceled canon angle commands");
    }
}
