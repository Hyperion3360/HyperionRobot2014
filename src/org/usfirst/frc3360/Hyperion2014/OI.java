// RobotBuilder Version: 1.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.
package org.usfirst.frc3360.Hyperion2014;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.DigitalIOButton;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc3360.Hyperion2014.commands.CanonAngle_Cancel;
import org.usfirst.frc3360.Hyperion2014.commands.CanonAngle_HandleAutoMode;
import org.usfirst.frc3360.Hyperion2014.commands.CanonAngle_HandleManualMode;
import org.usfirst.frc3360.Hyperion2014.commands.CanonAngle_ResetSecurity;
import org.usfirst.frc3360.Hyperion2014.commands.CanonAngle_SetShooterAngle;
import org.usfirst.frc3360.Hyperion2014.commands.CanonShooter_Shoot;
import org.usfirst.frc3360.Hyperion2014.commands.CanonSpinner_HandleManualMode;
import org.usfirst.frc3360.Hyperion2014.commands.CanonSpinner_HandlePresetMode;
import org.usfirst.frc3360.Hyperion2014.commands.CanonSpinner_PrepareTopGoal;
import org.usfirst.frc3360.Hyperion2014.commands.CanonSpinner_ReceivePass;
import org.usfirst.frc3360.Hyperion2014.commands.Canon_CancelPrepareTopGoal;
import org.usfirst.frc3360.Hyperion2014.commands.Canon_CopilotGrab;
import org.usfirst.frc3360.Hyperion2014.commands.Canon_PrepareTopGoal;
import org.usfirst.frc3360.Hyperion2014.commands.Canon_ShootTopGoal;
import org.usfirst.frc3360.Hyperion2014.commands.DriveTrain_MoveTo;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    public Joystick driverLeftJoystick;
    public Joystick driverRightJoystick;

    public JoystickButton Button_Canon_PilotShootAuto;
    public JoystickButton Button_DriveTrain_MoveTo;
    
    public JoystickButton Button_DriverCanon_PrepareTopGoal;
    public JoystickButton Button_DriverCanon_Shoot;

    public DigitalIOButton Button_CanonShooter_Shoot;

    public DigitalIOButton Button_CanonSpinner_SetManualMode;
    public DigitalIOButton Button_CanonSpinner_ShootSpeed;
    public DigitalIOButton Button_CanonSpinner_CatchSpeed;

    public DigitalIOButton Button_CanonAngle_SetManualMode;
    public DigitalIOButton Button_CanonAngle_SetAutoAngle;
    
    public JoystickButton Button_Canon_CopilotGrab;
    public JoystickButton Button_VisionPrintDistance;
    
    public JoystickButton  Button_ResetShooterMIN;
    public JoystickButton  Button_ResetShooterMAX;

    public OI() {
        driverRightJoystick = new Joystick(2);
        driverLeftJoystick = new Joystick(1);
        
        Button_DriverCanon_PrepareTopGoal = new JoystickButton(driverLeftJoystick, 6);
        Button_DriverCanon_PrepareTopGoal.whileHeld(new Canon_PrepareTopGoal());
        Button_DriverCanon_PrepareTopGoal.whenReleased(new Canon_CancelPrepareTopGoal());
        
        Button_DriverCanon_Shoot = new JoystickButton(driverLeftJoystick, 7);
        Button_DriverCanon_Shoot.whenPressed(new CanonShooter_Shoot());
        
        Button_Canon_PilotShootAuto = new JoystickButton(driverRightJoystick, 1);
        Button_Canon_PilotShootAuto.whileHeld(new Canon_ShootTopGoal());
        Button_Canon_PilotShootAuto.whenInactive(new CanonAngle_HandleManualMode());

        Button_DriveTrain_MoveTo = new JoystickButton(driverRightJoystick, 9);
        Button_DriveTrain_MoveTo.whenPressed(new DriveTrain_MoveTo(2));

        Button_Canon_CopilotGrab = new JoystickButton(driverLeftJoystick, 1);
        Button_Canon_CopilotGrab.whileHeld(new Canon_CopilotGrab());
        Button_Canon_CopilotGrab.whenInactive(new CanonAngle_HandleManualMode());

        Button_CanonShooter_Shoot = new DigitalIOButton(1);
        Button_CanonShooter_Shoot.whenPressed(new CanonShooter_Shoot());

        Button_CanonSpinner_ShootSpeed = new DigitalIOButton(2);
        Button_CanonSpinner_ShootSpeed.whileHeld(new CanonSpinner_PrepareTopGoal());

        Button_CanonSpinner_CatchSpeed = new DigitalIOButton(4);
        Button_CanonSpinner_CatchSpeed.whileHeld(new CanonSpinner_ReceivePass());
        
        Button_CanonSpinner_SetManualMode = new DigitalIOButton(8);
        Button_CanonSpinner_SetManualMode.whenInactive(new CanonSpinner_HandlePresetMode());
        Button_CanonSpinner_SetManualMode.whileHeld(new CanonSpinner_HandleManualMode());
        
        Button_ResetShooterMIN = new JoystickButton(driverRightJoystick, 6);
        Button_ResetShooterMIN.whileHeld(new CanonAngle_SetShooterAngle(8));
        Button_ResetShooterMIN.whenReleased(new CanonAngle_Cancel());
        
        Button_ResetShooterMAX = new JoystickButton(driverRightJoystick, 11);
        Button_ResetShooterMAX.whileHeld(new CanonAngle_SetShooterAngle(90));
        Button_ResetShooterMAX.whenReleased(new CanonAngle_Cancel());

    }

    public Joystick getDriverLeftJoystick() {
        return driverLeftJoystick;
    }

    public Joystick getDriverRightJoystick() {
        return driverRightJoystick;
    }
}
