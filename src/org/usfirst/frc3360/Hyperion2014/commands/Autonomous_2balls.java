/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc3360.Hyperion2014.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc3360.Hyperion2014.Robot;

/**
 *
 * @author Hyperion 3360
 */
public class Autonomous_2balls extends CommandGroup{
    
    public Autonomous_2balls() {
        
        // Shoot first ball, and wait a few to make sure one on two
        // in the hot goal (hence the wait).
        addParallel(new CanonSpinner_PrepareTopGoal());
        addParallel(new CanonAngle_SetShooterAngle(50));
        addSequential(new SystemWait(2));
        addSequential(new CanonShooter_Shoot());
        
        // Prepare to grab the ball in front.
        addSequential(new Canon_CopilotGrab(false, true), 1);
        
        // Move forward to eat the ball and wait a bit
        // to make sure the ball is in.        
        addSequential(new DriveTrain_MoveTo(3));
        
        // Shoot the second ball
        addParallel(new DriveTrain_Idle(0));
        // Prepare the able and let the ball the time to enter.
        addSequential(new CanonAngle_SetShooterAngle(55), 1.75);
        addSequential(new Canon_ShootTopGoalAutonomous());
        addSequential(new DriveTrain_MoveTo(1.5));
        
        addSequential(new DriveTrain_Idle(0));
        addSequential(new CanonSpinner_Brake());
    }

   
}
