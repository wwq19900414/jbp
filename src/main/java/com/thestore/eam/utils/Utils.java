package com.thestore.eam.utils;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class Utils {
        
    public static InetAddress getIp() {
        InetAddress ip=null;
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) interfaces.nextElement();
                Enumeration<InetAddress> inetAddresses = ni.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = inetAddresses.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        ip = inetAddress;
                    }
                }
            }
        } catch (SocketException e) {
            System.out.println(e);
        }
        if(ip==null) {
            try {
                ip=InetAddress.getLocalHost();
            } catch (UnknownHostException e) {
            	System.out.println(e);
            }
        }
        return ip;
    }
    


	public static String getCurrVersion() {
		return CurrentVersionUtil.getCurrentVersion();
	}
    
}
