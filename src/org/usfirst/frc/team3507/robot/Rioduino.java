package org.usfirst.frc.team3507.robot;

import edu.wpi.first.wpilibj.SerialPort;

public class Rioduino {
    public static byte LIGHTS_DISABLED = 0;
    public static byte LIGHTS_AUTO = 1;
    public static byte LIGHTS_TELE = 4;
    public static byte LIGHTS_FLYWHEEL = 2;
    public static byte LIGHTS_FIRE = 3;
    public static byte LIGHTS_LAST_20 = 5;
    public static byte LIGHTS_LAST_5 = 6;

    public static SerialPort port;
    
    private long last_repeated_message = 0;
    
    public Rioduino() {
    	port = new SerialPort(9600, SerialPort.Port.kMXP);
    }
    
    public void setLightMode(byte mode) {
    	setLightMode(mode, (byte)-1);
    }
    
    public void setLightMode(byte mode, byte param) {
    	
    	if(mode == LIGHTS_FLYWHEEL || mode == LIGHTS_LAST_20 || mode == LIGHTS_LAST_5) {
    		if((System.currentTimeMillis() < last_repeated_message + 500)) {
    			return;
    		} else {
    			last_repeated_message = System.currentTimeMillis();
    		}
    	}
    	
    	byte[] data = new byte[] {mode, param};
    	int num = 2;
    	if(param<0) {
    		num = 1;
    	}
    	port.write(data, num);
    }
}
