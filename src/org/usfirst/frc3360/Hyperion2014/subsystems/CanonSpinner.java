// RobotBuilder Version: 1.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.
package org.usfirst.frc3360.Hyperion2014.subsystems;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SpeedController;
import org.usfirst.frc3360.Hyperion2014.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CanonSpinner extends Subsystem {

    SpeedController allWheelSpinnerMotor = RobotMap.ms_scCanonSpinner_AllWheel;
    
    double m_dbCatchSpeedPreset = 0.6;
    double m_dbShootSpeedPreset = -1;
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.q
    public void initDefaultCommand() {
    }
    
    public double GetManualSpeed()
    {
        return DriverStation.getInstance().getAnalogIn(3);
    }
    
    public double GetPresetShootSpeed()
    {
        return -1;
    }
    
    public double GetInitCatchSpeed(){
        return 1;
    }
    
    public double GetPresetCatchSpeed()
    {
        return DriverStation.getInstance().getAnalogIn(2) / 3.3;
    }
    
    public void PrepareShoot(double dbSpeed){
        allWheelSpinnerMotor.set(dbSpeed);
    }
    
    public void PrepareCatchSpin(double dbCatchSpeed){
        allWheelSpinnerMotor.set(dbCatchSpeed);
    }
    
    public void StopSpinning(){
        allWheelSpinnerMotor.set(0);
    }
}
