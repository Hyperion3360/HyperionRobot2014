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
public class AutonomousBackAndForth extends CommandGroup {
    
    public  AutonomousBackAndForth(double dbDistanceMetre, double dbTopSpeed, int nRepetition) {     
        for (int nIndex = 0; nIndex < nRepetition; nIndex++)
        {
            addSequential(new DriveTrain_MoveTo(dbDistanceMetre,dbTopSpeed));
            addSequential(new DriveTrain_Idle(3));
            addSequential(new DriveTrain_MoveTo(dbDistanceMetre, -dbTopSpeed));
            addSequential(new DriveTrain_Idle(3));
        }
    }
}