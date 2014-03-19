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
import org.usfirst.frc3360.Hyperion2014.Robot;

/**
 *
 */
public class Canon_ShootTopGoalTeleop extends CommandGroup {
    
    public  Canon_ShootTopGoalTeleop() {
        //System.out.println("new Canon_ShootTopGoalAutonomous");
        
        addParallel(new CanonAngle_SetShooterAngle(true));
        addSequential(new SystemWait(1.4));
        addSequential(new CanonShooter_Shoot());
        addSequential(new CanonAngle_SetShooterAngle(45), 0.3);
    }
}