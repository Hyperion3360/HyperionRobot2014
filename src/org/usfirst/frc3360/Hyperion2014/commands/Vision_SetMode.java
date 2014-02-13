// RobotBuilder Version: 1.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.
package org.usfirst.frc3360.Hyperion2014.commands;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc3360.Hyperion2014.Robot;
import org.usfirst.frc3360.Hyperion2014.subsystems.IVisionCallback;
import org.usfirst.frc3360.Hyperion2014.subsystems.Vision;

/**
 *
 */
public class  Vision_SetMode extends Command implements IVisionCallback {
    
    int m_visionMode = -1;
    int m_captureMode = -1;
    boolean m_bCaptureReported = false;
    boolean m_bTargetFound = false;
    boolean m_bHotTarget = false;
    double m_bDistance = 0;
    
    public Vision_SetMode(int visionMode,
                          int captureMode){
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
	m_visionMode = visionMode;
        m_captureMode = captureMode;
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    //    requires(Robot.vision);
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }
    
    public Vision_SetMode(int visionMode){
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
	m_visionMode = visionMode;
        m_captureMode = Vision.ms_CAMERA_CAPTURE_MODE_PERIODIC;
    }
    // Called just before this Command runs the first time
    protected void initialize() {
        
        if (m_visionMode == Vision.ms_CAMERA_MODE_VISION_TRACKING)
        {
            Robot.vision.setDistanceMode(Vision.ms_DISTANCE_MODE_DISABLED); 
        }
        
        Robot.vision.setCameraCaptureMode(m_captureMode); 
        Robot.vision.setVisonMode(m_visionMode);
        
        // When using periodic mode, wait at least 3/4 second to be sure to have a report. 
        if (m_captureMode == Vision.ms_CAMERA_CAPTURE_MODE_PERIODIC)
        {
            setTimeout(0.75);
        }
        else
        {
            Robot.vision.requestCapture(this);
        }
        
        // When using request mode, the report wait for the report to be sent automatically.
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        boolean isFinished = false;
        
        switch (m_captureMode)
        {
            case Vision.ms_CAMERA_CAPTURE_MODE_PERIODIC:
            {
                if (isTimedOut())
                {
                    System.out.println("Vision periodic - hot target:" + Robot.vision.isHotTarget());
                    isFinished = true;
                }
                break;
            }
            case Vision.ms_CAMERA_CAPTURE_MODE_DEMAND:
            {
                if (m_bCaptureReported)
                {
                    System.out.println("Vision periodic - target found:" + m_bHotTarget);
                    System.out.println("Vision periodic - hot target:" + m_bHotTarget);
                    System.out.println("Vision periodic - distance:" + m_bHotTarget);
                    isFinished = true;
                }
                break;
            }
        }
        
        return isFinished;
    }
    // Called once after isFinished returns true
    protected void end() {
        
    }
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        
    }

    public void evNewCameraTrackingReport(boolean bTargetFound, boolean bHotTarget, double bDistance) {
        m_bTargetFound = bTargetFound;
        m_bHotTarget = bHotTarget;
        m_bDistance = bDistance;
        
        m_bCaptureReported = true;
    }
}
