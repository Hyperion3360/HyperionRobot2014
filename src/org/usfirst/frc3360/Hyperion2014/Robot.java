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
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import org.usfirst.frc3360.Hyperion2014.commands.*;
import org.usfirst.frc3360.Hyperion2014.subsystems.*;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Relay;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    Command autonomousCommand;
    public static OI oi;
    
    public static CanonAngle canonAngle;
    public static CanonSpinner canonSpinner;
    public static CanonShooter canonShooter;
    public static DriveTrain driveTrain;
    public static LedsSetter LedsSetter;
    public static Robot robotInstance;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        robotInstance = this;
	RobotMap.init();
        canonAngle = new CanonAngle();
        canonSpinner = new CanonSpinner();
        canonShooter = new CanonShooter();
        driveTrain = new DriveTrain();
        LedsSetter = new LedsSetter();

        // This MUST be here. If the OI creates Commands (which it very likely
        // will), constructing it during the construction of CommandBase (from
        // which commands extend), subsystems are not guaranteed to be
        // yet. Thus, their requires() statements may grab null pointers. Bad
        // news. Don't move it.
        oi = new OI();
	
        // instantiate the command used for the autonomous period
        autonomousCommand = new Autonomous_2balls();
        
        Compressor m_compressor = RobotMap.m_compressor;
        m_compressor.start();
        
        RobotMap.visionFrontSonarSolenoidRelay.set(true);
    }
    public void autonomousInit() {
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }
    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        Robot.LedsSetter.FlashLedsPeriodic();
        
        Robot.LedsSetter.SetBumpersColor();
    }
    public void teleopInit() {
	// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }
    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        
        Robot.LedsSetter.FlashLedsPeriodic();
        
        Robot.LedsSetter.SetBumpersColor();
        
    }
    /**
     * This function called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    
    public void disabledInit() {
        System.out.println("Robot.disabledInit");
        
        if (autonomousCommand != null) autonomousCommand.cancel();
        
        Robot.LedsSetter.FlashLedsPeriodic();
        Robot.LedsSetter.SetBumpersColor();

        driveTrain.resetGyro();

        canonAngle.AngleStop();
        canonAngle.ResetSecurity();
    }
}
