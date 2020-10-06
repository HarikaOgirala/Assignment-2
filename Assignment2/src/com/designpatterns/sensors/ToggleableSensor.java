package com.designpatterns.sensors;

/**
 * Sensor that can be toggled on and off.
 * 
 * @author Chad Williams
 */
public interface ToggleableSensor {
  /**
   * If the sensor is On changes to Off or vice versa.
   * @return Returns the new status of the sensor.
   */
  public SensorStatus toggle();

  /**
   * Returns the current status of the sensor.
   * @return Returns the status of the sensor
   */
  public SensorStatus getStatus();
}
