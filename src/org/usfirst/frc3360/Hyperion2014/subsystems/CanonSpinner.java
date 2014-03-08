

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
