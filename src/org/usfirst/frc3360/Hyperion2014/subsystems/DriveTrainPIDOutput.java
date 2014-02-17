/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.usfirst.frc3360.Hyperion2014.subsystems;

import edu.wpi.first.wpilibj.PIDOutput;

/**
 *
 * @author Hyperion 3360
 */
public class DriveTrainPIDOutput implements PIDOutput{
    DriveTrain m_myDriveTrain = null;
    int m_reportIndex = 0;
    
    public DriveTrainPIDOutput(int index, DriveTrain myDriveTrain)
    {
        m_reportIndex = index;
        m_myDriveTrain = myDriveTrain;
    }

    public void pidWrite(double d) {
        m_myDriveTrain.pidOutput(m_reportIndex, d);
    }
}
