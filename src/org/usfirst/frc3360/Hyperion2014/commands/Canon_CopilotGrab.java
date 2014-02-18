/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc3360.Hyperion2014.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc3360.Hyperion2014.subsystems.CanonAngle;

/**
 *
 * @author Hyperion3360
 */
public class Canon_CopilotGrab extends CommandGroup {
    
    public Canon_CopilotGrab() {
        
        
        addParallel(new CanonAngle_SetShooterAngle(CanonAngle.ms_MIN_CANON_ANGLE_VALUE));
        addSequential(new CanonSpinner_ReceivePass());
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.
        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
