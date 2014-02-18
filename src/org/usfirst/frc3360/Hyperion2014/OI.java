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
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc3360.Hyperion2014.commands.AutonomousBackAndForth;
import org.usfirst.frc3360.Hyperion2014.commands.DriveTrain_MoveTo;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    public Joystick driverLeftJoystick;
    public Joystick driverRightJoystick;

    public JoystickButton Button_DriveTrain_MoveTo;

    public DigitalIOButton Button_CanonShooter_Shoot;

    public DigitalIOButton Button_CanonSpinner_SetManualMode;
    public DigitalIOButton Button_CanonSpinner_ShootSpeed;
    public DigitalIOButton Button_CanonSpinner_CatchSpeed;

    public DigitalIOButton Button_CanonAngle_SetManualMode;
    public DigitalIOButton Button_CanonAngle_SetAutoAngle;

    public OI() {
        driverRightJoystick = new Joystick(2);
        driverLeftJoystick = new Joystick(1);

        Button_DriveTrain_MoveTo = new JoystickButton(driverRightJoystick, 9);
        Button_DriveTrain_MoveTo.whenPressed(new DriveTrain_MoveTo(8));

        Button_CanonShooter_Shoot = new DigitalIOButton(1);
        Button_CanonShooter_Shoot.whenPressed(new CanonShooter_Shoot());

        Button_CanonSpinner_ShootSpeed = new DigitalIOButton(2);
        Button_CanonSpinner_ShootSpeed.whileHeld(new CanonSpinner_PrepareTopGoal());

        Button_CanonSpinner_CatchSpeed = new DigitalIOButton(4);
        Button_CanonSpinner_CatchSpeed.whileHeld(new CanonSpinner_ReceivePass());
        
        Button_CanonSpinner_SetManualMode = new DigitalIOButton(8);
        Button_CanonSpinner_SetManualMode.whenInactive(new CanonSpinner_HandleManualMode());
        Button_CanonSpinner_SetManualMode.whileHeld(new CanonSpinner_HandlePresetMode());
        
        Button_CanonAngle_SetManualMode = new DigitalIOButton(6);
        Button_CanonAngle_SetManualMode.whileHeld(new CanonAngle_HandleAutoMode());
        Button_CanonAngle_SetManualMode.whenInactive(new CanonAngle_HandleManualMode());
    }

    public Joystick getDriverLeftJoystick() {
        return driverLeftJoystick;
    }
    public Joystick getDriverRightJoystick() {
        return driverRightJoystick;
    }
    public Joystick getCoPilotJoystick() {
        return coPilotJoystick;
    }
}
