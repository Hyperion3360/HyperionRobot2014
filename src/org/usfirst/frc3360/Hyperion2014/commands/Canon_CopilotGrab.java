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
        addSequential(new CanonSpinner_ReceivePass());
        addParallel(new CanonAngle_SetShooterAngle(CanonAngle.ms_MIN_CANON_ANGLE_VALUE));
    }
    
    public Canon_CopilotGrab(boolean bStopMotorAtEnd,
                             boolean bCompleteAtAngle) {
        addParallel(new CanonSpinner_ReceivePass(bStopMotorAtEnd));
        addSequential(new CanonAngle_SetShooterAngle(CanonAngle.ms_MIN_CANON_ANGLE_VALUE, bCompleteAtAngle));
    }
}
