/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc3360.Hyperion2014.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author Hyperion 3360
 */
public class Autonomous_2balls extends CommandGroup{
    
    public Autonomous_2balls() {
        // Shoot first ball
        addParallel(new Canon_ShootTopGoal());
        addSequential(new SystemWait(4));
        addSequential(new Canon_CopilotGrab(false, true));
        // Move forward to eat the ball and wait a bit
        // to make sure the ball is in.
        addParallel(new DriveTrain_MoveTo(0.5));
        addSequential(new SystemWait(0.8));
        // Shoot the second ball    
        addSequential(new Canon_ShootTopGoal());
        addSequential(new DriveTrain_MoveTo(1.5));
        
    }

   
}
