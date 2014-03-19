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
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SpeedController;
import org.usfirst.frc3360.Hyperion2014.RobotMap;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc3360.Hyperion2014.Robot;
import org.usfirst.frc3360.Hyperion2014.commands.CanonSpinner_HandlePresetMode;
/**
 *
 */
public class CanonSpinner extends Subsystem {

    SpeedController allWheelSpinnerMotor = RobotMap.canonSpinnerAllWheelSpinnerMotor;
    
    double m_dbSpinStartTime = 0;
    double m_dbSpinCurrentTime = 0;
    double m_dbCatchSpeed = 0.6;
    double m_dbShootSpeed = -1;
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.q
    public void initDefaultCommand() {
        
        setDefaultCommand(new CanonSpinner_HandlePresetMode());
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void PrepareTopGoalShot(){
        //Est ce que cette fonction s execute tjrs pcq sinon on pourra pas compter le temps
        if(m_dbSpinStartTime == 0){
            m_dbSpinStartTime = System.currentTimeMillis();
        }
        m_dbSpinCurrentTime = System.currentTimeMillis();
        if((m_dbSpinCurrentTime - m_dbSpinStartTime) >= 2000){
            Robot.LedsSetter.ErrorBoolean();
        }
        
        allWheelSpinnerMotor.set(m_dbShootSpeed);
    }
    
    public void InitCatchSpeed(){
        allWheelSpinnerMotor.set(1);
    }
    
    public void StopSpinning(){
        Robot.LedsSetter.RemoveErrorBoolean();
        m_dbSpinStartTime = 0;
        allWheelSpinnerMotor.set(0);
    }
    
    
    public void PrepareCatchSpin(){
        allWheelSpinnerMotor.set(m_dbCatchSpeed);
    }
    
    
    public void HandleSpinnerManualMode(){
        
        //the axis 3 is the potentiometer on the driver station that adjusts
        //the spin when the switch is in manual mode
        m_dbCatchSpeed = (DriverStation.getInstance().getAnalogIn(3));
        m_dbShootSpeed = -(DriverStation.getInstance().getAnalogIn(3));
        
        //System.out.println("SpeedAxisValue" + (DriverStation.getInstance().getAnalogIn(3)));
    }
    
    public void HandleSpinnerPresetMode(){
        
        double CatchSpeed = DriverStation.getInstance().getAnalogIn(2) / 3.3;
        // the axis 2 is a screw potentiometer directly on the driver station to adjust the catch speed
       // System.out.println("axis 3 (catch speed)" + Robot.oi.getCoPilotJoystick().getRawAxis(3));
        m_dbCatchSpeed = CatchSpeed;
        m_dbShootSpeed = -1;
    }
            
}
