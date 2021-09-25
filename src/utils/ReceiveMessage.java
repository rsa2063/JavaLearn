package utils;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReceiveMessage extends Thread {

	private String messageStr = "";
	private boolean IsNew = true;
	private String ip = "";
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public String getMessage() {
		IsNew = true;
		return messageStr + " -----from " +  ip + " in " + format.format(new Date());
	}
	public boolean getIsNew() {
		return IsNew;
	}
	
	@Override
    public void run() {
        DatagramSocket serverS = null;
        DatagramPacket message = null;

        try {
            message = new DatagramPacket(new byte[256], 256);
            serverS = new DatagramSocket(8091);
            JDBC_SqlServer.log("run: start listening");
        } catch (Exception e) {
            e.printStackTrace();
        }
        while (!Thread.currentThread().isInterrupted()) {

            if(serverS == null) break;
            try {
                serverS .receive(message);
                byte[] data = new byte[message.getData()[0]];
                System.arraycopy(message.getData(), 1, data, 0, data.length);
                
                JDBC_SqlServer.log("run: receive message");
                IsNew = false;
                messageStr = new String(data, "utf-8");
                ip = message.getAddress().getHostAddress();
                
            } catch (Exception e) {
                e.getMessage();
            }
        }
	}
}
