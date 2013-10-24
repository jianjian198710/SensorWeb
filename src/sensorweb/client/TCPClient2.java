package sensorweb.client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class TCPClient2 {
	private static Socket socket;
	private static OutputStream os;
	
	public static int num;
	protected static final int MAX_VALUE = 40;
	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException{
		try {
			socket = new Socket("127.0.0.1", 7878);
			os = socket.getOutputStream();
			new Timer().schedule(new TimerTask() {
				
				@Override
				public void run(){
					int random = new Random().nextInt(MAX_VALUE); //0,1,2
					String s = "BBB##Temperature##"+random+"##degree"+"\n";
						try {
							os.write(s.getBytes());
							os.flush();
						} catch (IOException e) {
							e.printStackTrace();
						} 
				}
			}, 0, 3*1000);  
		} catch(Exception e) {
			e.printStackTrace();
		} 
	}

}
