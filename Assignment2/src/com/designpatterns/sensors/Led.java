package com.designpatterns.sensors;

/*
 * ## License
 * 
 * The MIT License (MIT)
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */


/**
 * This class simulates interaction with a 3rd party LED sensor for GrovePi.
 * 
 * Note this is a dumbed down version of the actual sensor implementation
 * that can be found here: 
 * https://github.com/DexterInd/GrovePi/blob/master/Software/Java/src/com/dexterind/grovepi/sensors/Led.java
 * 
 * 
 * 
 * @author Chad Williams
 *
 */
public class Led implements ToggleableSensor{
  private final String ledIdentifier;
  private SensorStatus status = SensorStatus.OFF;
  public final static int MAX_BRIGHTNESS = 255;
  
    /**
   * Create a new LED with the passed identifier.
   * 
   */
  public Led(String ledIdentifier) {
      this.ledIdentifier = ledIdentifier;
  }

  /**
   * Turns the LED on to the maximum brightness.
   */
   public void turnOn() {
    this.write(MAX_BRIGHTNESS);
  }

  /**
   * Turns the LED off.
   */
   void turnOff() {
    this.write(0);
  }

  /**
   * Sets the LED brightness to the specified percentage of the maximum brightness,
   * note dimming only works if connected to a pin that supports PWM otherwise this 
   * will just appear to turn the LED on/off.
   * @param percent Percentage of maximum brightness, expects a number from 0 to 100
   */
  void setBrightness(float percent) {
    if (percent <= 0){
      turnOff();
    }else if(percent >= 100){
      turnOn();
    }else{
      this.write((int)(MAX_BRIGHTNESS * percent/100));
    }
  }

  /**
   * Set the analog value of the LED and sensor status.
   * @param value Expects a value from 0 to MAX_BRIGHTNESS
   * @return
   */
  boolean write(int value)  {
    if (value <= 0){
      status = SensorStatus.OFF;
      //super.write(0);
    }else{
      status = SensorStatus.ON;
      //super.write(Math.min(value, MAX_BRIGHTNESS));
    }
    return true;
  }

  /**
   * Toggles the LED on/off.
   * @return Returns the new status of the LED
   */
  public SensorStatus toggle() {
    setStatus(SensorStatus.toggle(status));
    return status;
  }

  /**
   * Returns the current status of the LED.
   * @return Returns the status of the LED
   */
  public SensorStatus getStatus(){
    return status;
  }

  /**
   * Set the status of the LED to the passed status.
   * @param status
   */
   void setStatus(SensorStatus status) {
    if (status == SensorStatus.OFF){
      turnOff();
    }else{
      turnOn();
    }
  }
  
  public String toString(){
      return "Led("+ledIdentifier+") status: "+status;
  }
}
