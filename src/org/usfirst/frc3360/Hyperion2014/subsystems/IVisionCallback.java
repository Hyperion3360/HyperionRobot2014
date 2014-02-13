/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.usfirst.frc3360.Hyperion2014.subsystems;

/**
 *
 * @author Hyperion 3360
 */
public interface IVisionCallback {
    public void evNewCameraTrackingReport(boolean bTargetFound, boolean bHotTarget, double bDistance);
}
