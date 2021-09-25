package utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;

import org.apache.commons.io.FileUtils;

public class SocketCommu {

	public static boolean SendMessage(String ip, String msg) {
		try {
            Socket s = new Socket(ip, 8091);

            BufferedOutputStream out = new BufferedOutputStream(s.getOutputStream(), 85);
            out.write(msg.getBytes("utf-8").length);
            out.write(msg.getBytes("utf-8"));
            out.close();
            s.close();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
	}
	
	public static boolean SendMessageByUDP(String ip, String msg) {
		DatagramSocket connectUDP = null;
		DatagramPacket messageUDP = null;
		try {
			connectUDP = new DatagramSocket();
			byte[] data = new byte[msg.getBytes("utf-8").length + 1];
			data[0] = (byte)msg.getBytes("utf-8").length;
			System.arraycopy(msg.getBytes("utf-8"), 0, data, 1, msg.getBytes("utf-8").length);
			messageUDP = new DatagramPacket(data, data.length, new InetSocketAddress(ip, 8091));
			connectUDP.send(messageUDP);
			connectUDP.close();
			return true;
		} catch(Exception e) {
			e.printStackTrace();
            return false;
		}
	}
	
	public static boolean DownloadPicture(String a_url) throws Exception {
		String filename = a_url.substring(a_url.lastIndexOf("/") + 1, a_url.length());
		System.out.println("tag: " + filename + "|" + a_url);
		URL url = new URL(a_url);
		HttpURLConnection connection = (HttpURLConnection)url.openConnection();
		connection.setRequestMethod("GET"); 
		connection.setReadTimeout(5 * 1000);
		InputStream  is = connection.getInputStream();
		int picSize = connection.getContentLength();
		int i = 0;
		FileOutputStream os = new FileOutputStream(filename);
		while( i < picSize) {
			os.write(is.read());
			i++;
		}
		is.close();
		os.close();
		return true;
	}
	
	public static boolean DownloadPictureByFileUtils(String a_url) throws Exception {
		String filename = a_url.substring(a_url.lastIndexOf("/") + 1, a_url.length());
		System.out.println("tag: " + filename + "|" + a_url);
		URL url = new URL(a_url);
		FileUtils.copyURLToFile(url, new File(filename));
		return true;
	}
	
	public static String GetThreeString(int num) {
		StringBuilder numString = new StringBuilder(String.valueOf(num));
		if(numString.length() == 3) {
			return numString.toString();
		} else if(numString.length() == 2) {
			return "0" + numString.toString();
		} else if(numString.length() == 1) {
			return "00" + numString.toString();
		}
		return "";
	}
}
