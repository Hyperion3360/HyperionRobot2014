
package org.usfirst.frc3360.Hyperion2014.commands;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousMode extends CommandGroup {
    
    public  AutonomousMode() {
        addParallel(new DriveTrain_Cancel());
        addParallel(new CanonSpinner_Shoot());
        addParallel(new CanonAngle_SetShooterAngle(45.6));
        addSequential(new System_Wait(2));
        
        addParallel(new CanonShooter_Shoot());
        addSequential(new System_Wait(1));
        
        // Make sure angle and spinner commands are all terminated.
        addSequential(new CanonAngle_Cancel());
        addSequential(new CanonSpinner_Cancel());
        
        addSequential(new DriveTrain_MoveTo(2));
        
        // Make sure driver train command is terminated.
        addSequential(new DriveTrain_Cancel());
    }
}
