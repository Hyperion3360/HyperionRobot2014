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
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousMode extends CommandGroup {
    
    public  AutonomousMode() {
        addSequential(new DriveTrain_MoveTo(4.5));
        addParallel(new CanonAngle_HandleAutoMode());
        addParallel(new CanonSpinner_PrepareTopGoal());
        addSequential(new SystemWait(2));
        addSequential(new CanonShooter_Shoot());
    }
}
