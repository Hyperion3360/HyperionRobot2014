
package org.usfirst.frc3360.Hyperion2014;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.DigitalIOButton;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc3360.Hyperion2014.commands.CanonAngle_ResetSecurity;
import org.usfirst.frc3360.Hyperion2014.commands.CanonShooter_Shoot;
import org.usfirst.frc3360.Hyperion2014.commands.CanonSpinner_Cancel;
import org.usfirst.frc3360.Hyperion2014.commands.CanonSpinner_Shoot;
import org.usfirst.frc3360.Hyperion2014.commands.CanonSpinner_Grab;
import org.usfirst.frc3360.Hyperion2014.commands.Canon_AutoGrab;
import org.usfirst.frc3360.Hyperion2014.commands.Canon_AutoGrab_Cancel;
import org.usfirst.frc3360.Hyperion2014.commands.Canon_AutoShoot;
import org.usfirst.frc3360.Hyperion2014.commands.Canon_AutoShoot_Cancel;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    Joystick driverLeftJoystick;
    Joystick driverRightJoystick;

    JoystickButton Button_Canon_PilotAutoShoot;
    JoystickButton Button_Canon_PilotAutoGrab;
    JoystickButton Button_Canon_ResetSecurity;
    
    DigitalIOButton Button_CanonShooter_Shoot;
    DigitalIOButton Button_CanonSpinner_ShootSpeed;
    DigitalIOButton Button_CanonSpinner_CatchSpeed;
    DigitalIOButton Button_CanonAngle_SetManualMode;
    
    Command m_cmdCanon_AutoShoot;
    Command m_cmdCanon_AutoGrab;
    Command m_cmdCanonSpinner_Shoot;
    Command m_cmdCanonSpinner_Grab;
        
    public OI() {
        m_cmdCanon_AutoShoot = new Canon_AutoShoot();
        m_cmdCanon_AutoGrab = new Canon_AutoGrab();
        m_cmdCanonSpinner_Shoot = new CanonSpinner_Shoot();
        m_cmdCanonSpinner_Grab = new CanonSpinner_Grab();
        
        driverLeftJoystick = new Joystick(1);
        driverRightJoystick = new Joystick(2);        
        
        // The pilot will use the left and right joystick Y axis to
        // perform a tank drive. The driver can also use automatic
        // shooting and automatic grabing.
        Button_Canon_PilotAutoShoot = new JoystickButton(driverRightJoystick, 1);
        Button_Canon_PilotAutoShoot.whileHeld(m_cmdCanon_AutoShoot);
        Button_Canon_PilotAutoShoot.whenReleased(new Canon_AutoShoot_Cancel());
        
        Button_Canon_PilotAutoGrab = new JoystickButton(driverLeftJoystick, 1);
        Button_Canon_PilotAutoGrab.whileHeld(m_cmdCanon_AutoGrab);
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

        Button_CanonSpinner_ShootSpeed = new DigitalIOButton(6);
        Button_CanonSpinner_ShootSpeed.whileHeld(m_cmdCanonSpinner_Shoot);
        Button_CanonSpinner_ShootSpeed.whenReleased(new CanonSpinner_Cancel());

        Button_CanonSpinner_CatchSpeed = new DigitalIOButton(4);
        Button_CanonSpinner_CatchSpeed.whileHeld(m_cmdCanonSpinner_Grab);
        Button_CanonSpinner_CatchSpeed.whenReleased(new CanonSpinner_Cancel());
    }

    public Joystick getDriverLeftJoystick() {
        return driverLeftJoystick;
    }

    public Joystick getDriverRightJoystick() {
        return driverRightJoystick;
    }
}
