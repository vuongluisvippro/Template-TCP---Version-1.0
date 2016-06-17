package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

@SuppressWarnings("all")
public class ServerThread extends Thread{
	private Socket socket;
	private int clientNumber;
	public ServerThread(Socket socket,int clientNumber){
		this.clientNumber = clientNumber;
		this.socket = socket;
		System.out.println("Client "+this.clientNumber+" at "+socket);
	}
	@Override
	public void run(){
		String nameVX = "";
		try {
			nameVX = new DataInputStream(socket.getInputStream()).readUTF();
			System.out.println("Thông điệp từ client: "+nameVX);
			if(new Handle().checkVX(nameVX) > 0){
				String msg = String.valueOf(new Handle().checkVX(nameVX));
				new DataOutputStream(socket.getOutputStream()).writeUTF("a"+msg);
			}else{
				new DataOutputStream(socket.getOutputStream()).writeUTF("Không có Văcxin này!");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
