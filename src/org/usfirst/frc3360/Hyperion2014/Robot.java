package org.usfirst.frc3360.Hyperion2014;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import org.usfirst.frc3360.Hyperion2014.commands.*;
import org.usfirst.frc3360.Hyperion2014.subsystems.*;
import edu.wpi.first.wpilibj.Compressor;
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
    
    public static CanonAngle ms_canonAngle;
    public static CanonSpinner ms_canonSpinner;
    public static CanonShooter ms_canonShooter;
    public static DriveTrain ms_driveTrain;
    public static StatusLeds ms_statusLeds;
    public static Robot ms_robotInstance;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        System.out.println("Robot Init");
        
        ms_robotInstance = this;
	RobotMap.init();

        ms_canonAngle = new CanonAngle();
        ms_canonSpinner = new CanonSpinner();
        ms_canonShooter = new CanonShooter();
        ms_driveTrain = new DriveTrain();
        ms_statusLeds = new StatusLeds();

        // This MUST be here. If the OI creates Commands (which it very likely
        // will), constructing it during the construction of CommandBase (from
        // which commands extend), subsystems are not guaranteed to be
        // yet. Thus, their requires() statements may grab null pointers. Bad
        // news. Don't move it.
        oi = new OI();
	
        // instantiate the command used for the autonomous period
        if(oi.getDriverLeftJoystick().getRawAxis(3) > 0){        
            autonomousCommand = new Autonomous_2balls();
        }else{
            autonomousCommand = new AutonomousMode();
        }
        
        Compressor m_compressor = RobotMap.m_compressor;
        m_compressor.start();
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
    }
    /**
     * This function called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    
    public void disabledInit() {
        System.out.println("Robot.disabledInit");
        
        // Make sure the autonomous command is canceled.
        if (autonomousCommand != null) autonomousCommand.cancel();
        
        ms_driveTrain.resetGyro();
        ms_canonAngle.AngleStop();
        ms_canonAngle.ResetSecurity();
    }
}
