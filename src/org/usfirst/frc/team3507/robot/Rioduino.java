package org.usfirst.frc.team3507.robot;

import edu.wpi.first.wpilibj.SerialPort;

public class Rioduino {
    public static byte LIGHTS_DISABLED = 0x00;
    public static byte LIGHTS_AUTO = 0x01;
    public static byte LIGHTS_TELE = 0x02;
    public static byte LIGHTS_FLYWHEEL = 0x03;
    public static byte LIGHTS_FIRE = 0x04;
    
    public static SerialPort port;
    
    public Rioduino() {
    	port = new SerialPort(9600, SerialPort.Port.kMXP);
    }
    
    public void setLightMode(byte mode) {
    	setLightMode(mode, (byte)-1);
    }
    
    public void setLightMode(byte mode, byte param) {
    	byte[] data = new byte[] {mode, param};
    	int num = 2;
    	if(param<0) {
    		num = 1;
    	}
    	port.write(data, num);
    }
}
