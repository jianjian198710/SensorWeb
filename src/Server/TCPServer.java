package Server;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TCPServer{
	private String data;
	private boolean flag;
	private static TCPServer instance;
	private ServerSocket serverSocket = null;

	private TCPServer() {
	}

	// 通过单例模式创建TCPServer
	public TCPServer getInstance() {
		if (instance == null) {
			synchronized (TCPServer.class) {
				if (instance == null) {
					instance = new TCPServer();
				}
				return instance;
			}
		}
		return instance;
	}

	public void start() throws IOException {
		if(flag == true) {
			serverSocket = new ServerSocket(30000);
			ExecutorService exe = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*10);
			Socket socket = serverSocket.accept();
			InputStreamReader isr = new InputStreamReader(socket.getInputStream());
			char[] chs = new char[256];
			int hasRead;
			while((hasRead=isr.read(chs))>0){
				String data = new String(chs,0,hasRead);
				System.out.println("收到的数据:"+data);
			}
		}
	}
}
