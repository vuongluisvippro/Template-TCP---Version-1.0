package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

@SuppressWarnings("all")
public class Client {
	
	private static Socket client;
	private static DataInputStream in;
	private static DataOutputStream out;
	
	public static void main(String[]args) throws IOException{
		System.out.println("OPEN PROGRAM CLIENT!");
		System.out.print("Nhập host: ");
		String hostname = new Scanner(System.in).nextLine();
		InetAddress host = null;
		try {
			host = InetAddress.getByName(hostname);
		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
		}
		System.out.print("Nhập port: ");
		int port = Integer.parseInt(new Scanner(System.in).nextLine());
		boolean contx = true;
		do{
			client = new Socket(host, port);
			System.out.print("Nhập tên chuỗi vắc xin: ");
			String nameVX = new Scanner(System.in).nextLine();
			new DataOutputStream(client.getOutputStream()).writeUTF(nameVX);
			String msg = "";
			msg = new DataInputStream(client.getInputStream()).readUTF();
			if(msg.charAt(0) == 'a'){
				String msg1 = msg.substring(1,msg.length());
				System.out.println("Số mũi tiêm: "+msg1);
			}else{
				System.out.println(msg);
			}
			System.out.print("tiep tuc khong[c/k] = ");
			String tt = new Scanner(System.in).nextLine();
			if(tt.equals("k")){
				contx = false;
			}
		}while(contx);
	}
}
