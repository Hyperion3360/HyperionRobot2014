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
import org.usfirst.frc3360.Hyperion2014.commands.CanonAngle_HandleAutoMode;
import org.usfirst.frc3360.Hyperion2014.commands.CanonAngle_HandleManualMode;
import org.usfirst.frc3360.Hyperion2014.commands.CanonShooter_Shoot;
import org.usfirst.frc3360.Hyperion2014.commands.CanonSpinner_HandleManualMode;
import org.usfirst.frc3360.Hyperion2014.commands.CanonSpinner_HandlePresetMode;
import org.usfirst.frc3360.Hyperion2014.commands.CanonSpinner_PrepareTopGoal;
import org.usfirst.frc3360.Hyperion2014.commands.CanonSpinner_ReceivePass;
import org.usfirst.frc3360.Hyperion2014.commands.Vision_FindHotTarget;
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // Another type of button you can create is a DigitalIOButton, which is
    // a button or switch hooked up to the cypress module. These are useful if
    // you want to build a customized operator interface.
    // Button button = new DigitalIOButton(1);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
    
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public Joystick driverLeftJoystick;
    public Joystick driverRightJoystick;
    public Joystick coPilotJoystick;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    public JoystickButton Button_CanonShooter_Shoot;
    
    public JoystickButton Button_CanonSpinner_SetManualMode;
    public JoystickButton Button_CanonSpinner_ShootSpeed;
    public JoystickButton Button_CanonSpinner_CatchSpeed;
    
    public JoystickButton Button_CanonAngle_SetManualMode;
    
    
    public JoystickButton Button_Vision_FindHotTarget;
    
    
    
    public OI() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        coPilotJoystick = new Joystick(3);
        
        driverRightJoystick = new Joystick(2);
        
        driverLeftJoystick = new Joystick(1);
        
	    
        // SmartDashboard Buttons
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        
        
        Button_Vision_FindHotTarget = new JoystickButton(driverLeftJoystick, 7);
        Button_Vision_FindHotTarget.whenPressed(new Vision_FindHotTarget());
        
        
        Button_CanonShooter_Shoot = new JoystickButton(coPilotJoystick, 1);
        Button_CanonShooter_Shoot.whenPressed(new CanonShooter_Shoot());
        
        
        Button_CanonSpinner_ShootSpeed = new JoystickButton(coPilotJoystick, 2);
        Button_CanonSpinner_ShootSpeed.whileHeld(new CanonSpinner_PrepareTopGoal());
        
        Button_CanonSpinner_CatchSpeed = new JoystickButton(coPilotJoystick, 3);
        Button_CanonSpinner_CatchSpeed.whileHeld(new CanonSpinner_ReceivePass());
    
        Button_CanonSpinner_SetManualMode = new JoystickButton(coPilotJoystick, 4);
        Button_CanonSpinner_SetManualMode.whileHeld(new CanonSpinner_HandleManualMode());
        Button_CanonSpinner_SetManualMode.whenInactive(new CanonSpinner_HandlePresetMode());
        
        Button_CanonAngle_SetManualMode = new JoystickButton(coPilotJoystick, 5);
        Button_CanonAngle_SetManualMode.whileHeld(new CanonAngle_HandleManualMode());
        Button_CanonAngle_SetManualMode.whenInactive(new CanonAngle_HandleAutoMode());
        
    }
    
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    public Joystick getDriverLeftJoystick() {
        return driverLeftJoystick;
    }
    public Joystick getDriverRightJoystick() {
        return driverRightJoystick;
    }
    public Joystick getCoPilotJoystick() {
        return coPilotJoystick;
    }
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
}
