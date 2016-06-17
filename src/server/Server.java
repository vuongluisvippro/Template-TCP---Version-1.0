package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

@SuppressWarnings("all")
public class Server {
	
	private static ServerSocket server;
	private static Socket sk;
	private static DataInputStream in;
	private static DataOutputStream out;
	
	public static void main(String[]args) throws IOException{
		System.out.println("OPEN PROGRAM SERVER!");
		System.out.print("Nhập cổng port server: ");
		int port = Integer.parseInt(new Scanner(System.in).nextLine());
		server = new ServerSocket(port);
		System.out.println("Server already!");
		int clientNumber = 1;
		while(true){
			try {
				sk = server.accept();
				new ServerThread(sk,clientNumber++).start();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
