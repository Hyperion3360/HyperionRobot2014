
package org.usfirst.frc3360.Hyperion2014.commands;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc3360.Hyperion2014.Robot;
import org.usfirst.frc3360.Hyperion2014.subsystems.CanonAngle;

public class  CanonAngle_HandleManualMode extends Command {
    public CanonAngle_HandleManualMode() {
        requires(Robot.ms_canonAngle);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.ms_canonAngle.AngleStop();
        Robot.ms_canonAngle.ResetSecurity();
        Robot.ms_canonAngle.EnableAngleMode();

        //System.out.println("CaHmm init");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        //System.out.println("angle manual 1");
        
        // Read the co-pilot throttle.
        double dbThrottleValue = DriverStation.getInstance().getAnalogIn(1);
        double dbAngleWanted = ((CanonAngle.ms_MAX_CANON_ANGLE_VALUE - CanonAngle.ms_MIN_CANON_ANGLE_VALUE) /
                                (CanonAngle.ms_MAX_THROTTLE_VALUE-CanonAngle.ms_MIN_THROTTLE_VALUE) *
                                dbThrottleValue) +
                               114;
                              
        Robot.ms_canonAngle.HandleAngleMode(dbAngleWanted);
        
        //System.out.println("angle manual 2");
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
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
        Robot.ms_canonAngle.DisableAngleMode();
    }
}
