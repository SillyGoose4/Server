package server.main;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.SQLNonTransientConnectionException;
/**
 * 接受信息 线程
 * @author wangj
 *
 */
public class Handler implements Runnable  {
	private static final String CHARCODE="utf-8";
	private Socket socket;
	private SignIn signIn;
	private DataInputStream in;
	private DataOutputStream out;
	private BufferedOutputStream bout;
	private BufferedInputStream bin;
	public Handler(Socket socket) {
		System.out.println("This is in New Handler");
		this.socket=socket;
		this.run();
	}
	
	public void run() {
		MessageBox messageBox;
		//String data=null;
		byte[] buffer=new byte[4096];
		String data;
		int len=0;
		try {
			in=new DataInputStream(socket.getInputStream());
			out=new DataOutputStream(socket.getOutputStream());
			bout=new BufferedOutputStream(out);
			bin=new BufferedInputStream(in);
			/* Read Data From Client */
			while((len=bin.read(buffer))!=-1) {
				
			}
			data=buffer.toString();
			System.out.println("Recv Success...");
			System.out.println(data);
			
			/* Write Message and Send to Client*/
			signIn=new SignIn(data);
			data=signIn.getMessage().toString();
			buffer=data.getBytes();
			out.write(buffer);
			System.out.println("Write Success");
		} catch (IOException e) {
			System.out.println("Recv Message Failed");
			e.printStackTrace();
		}
	}

}
