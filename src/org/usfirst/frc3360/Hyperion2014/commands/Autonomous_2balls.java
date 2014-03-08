
package org.usfirst.frc3360.Hyperion2014.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author Hyperion 3360
 */
public class Autonomous_2balls extends CommandGroup{
    
    public Autonomous_2balls() {
        // Shoot first ball, and wait a few to make sure one on two
        // in the hot goal (hence the wait).
        addParallel(new CanonSpinner_Shoot());
        addParallel(new CanonAngle_SetShooterAngle(48));
        addSequential(new System_Wait(2));
        addSequential(new CanonShooter_Shoot());
        
        // Prepare to grab the ball in front.
        addSequential(new Canon_AutoGrab(), 1);
        
        // Move forward to eat the ball and wait a bit
        // to make sure the ball is in.        
        addSequential(new DriveTrain_MoveTo(4));
        
        // Shoot the second ball
        addParallel(new DriveTrain_Cancel());
        // Prepare the able and let the ball the time to enter.
        addSequential(new CanonAngle_SetShooterAngle(55), 1.75);
        addSequential(new Canon_AutoShoot());
        addSequential(new DriveTrain_MoveTo(1.5));
        
        addSequential(new DriveTrain_Cancel());
        addSequential(new CanonSpinner_Cancel());
    }

   
}
