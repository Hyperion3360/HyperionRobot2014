/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc3360.Hyperion2014.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author Developer
 */
public class Canon_CancelPrepareTopGoal extends CommandGroup {
    
    public Canon_CancelPrepareTopGoal() {
        addSequential(new CanonAngle_Cancel());
        addSequential(new CanonSpinner_Brake());
    }
}
