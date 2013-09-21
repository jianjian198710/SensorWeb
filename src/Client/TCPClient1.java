package Client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class TCPClient1 {
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
					String s = "AAA##Temperature##"+random+"##degree"+"\n";
						try {
							os.write(s.getBytes());
							os.flush();
						} catch (IOException e) {
							e.printStackTrace();
						} 
				}
			}, 0, 3*1000);  //每半分无延迟调用一次run方法里面的逻辑
		} catch(Exception e) {
			System.out.println("!!!!!!!!!发送出错!!!!!!");
		} 
	}

}

