
package org.usfirst.frc3360.Hyperion2014;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.DigitalIOButton;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.usfirst.frc3360.Hyperion2014.commands.CanonAngle_ResetSecurity;
import org.usfirst.frc3360.Hyperion2014.commands.CanonShooter_Shoot;
import org.usfirst.frc3360.Hyperion2014.commands.CanonSpinner_Shoot;
import org.usfirst.frc3360.Hyperion2014.commands.CanonSpinner_Grab;
import org.usfirst.frc3360.Hyperion2014.commands.Canon_AutoGrab;
import org.usfirst.frc3360.Hyperion2014.commands.Canon_AutoGrab_Cancel;
import org.usfirst.frc3360.Hyperion2014.commands.Canon_ShootTopGoal;
import org.usfirst.frc3360.Hyperion2014.commands.Canon_ShootTopGoal_Cancel;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    Joystick driverLeftJoystick;
    Joystick driverRightJoystick;

    JoystickButton Button_Canon_PilotShootAuto;
    JoystickButton Button_Canon_PilotAutoGrab;
    JoystickButton Button_Canon_ResetSecurity;
    
    DigitalIOButton Button_CanonShooter_Shoot;
    DigitalIOButton Button_CanonSpinner_ShootSpeed;
    DigitalIOButton Button_CanonSpinner_CatchSpeed;
    DigitalIOButton Button_CanonAngle_SetManualMode;
    
    public OI() {
        driverLeftJoystick = new Joystick(1);
        driverRightJoystick = new Joystick(2);        
        
        // The pilot will use the left and right joystick Y axis to
        // perform a tank drive. The driver can also use automatic
        // shooting and automatic grabing.
        Button_Canon_PilotShootAuto = new JoystickButton(driverRightJoystick, 1);
        Button_Canon_PilotShootAuto.whileHeld(new Canon_ShootTopGoal());
        Button_Canon_PilotShootAuto.whenReleased(new Canon_ShootTopGoal_Cancel());
        
        Button_Canon_PilotAutoGrab = new JoystickButton(driverLeftJoystick, 1);
        Button_Canon_PilotAutoGrab.whileHeld(new Canon_AutoGrab());
        Button_Canon_PilotAutoGrab.whenReleased(new Canon_AutoGrab_Cancel());

        Button_Canon_ResetSecurity = new JoystickButton(driverLeftJoystick, 6);
        Button_Canon_ResetSecurity.whenPressed(new CanonAngle_ResetSecurity());
        
        
        // In case of any other usage/emergency, the copilot may
        // use the enhanced cypress joystick to control the canon.
        // It may control a preset or a manual spinning value for
        // both catching and shooting. The copilot may also trigger
        // the shoot if there is an issue with the auto shoot. He may
        // also manualy control the canon angle
        Button_CanonShooter_Shoot = new DigitalIOButton(1);
        Button_CanonShooter_Shoot.whenPressed(new CanonShooter_Shoot());

        Button_CanonSpinner_ShootSpeed = new DigitalIOButton(2);
        Button_CanonSpinner_ShootSpeed.whileHeld(new CanonSpinner_Shoot());

        Button_CanonSpinner_CatchSpeed = new DigitalIOButton(4);
        Button_CanonSpinner_CatchSpeed.whileHeld(new CanonSpinner_Grab());
    }

    public Joystick getDriverLeftJoystick() {
        return driverLeftJoystick;
    }

    public Joystick getDriverRightJoystick() {
        return driverRightJoystick;
    }
}
