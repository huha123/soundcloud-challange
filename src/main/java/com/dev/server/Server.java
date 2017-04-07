package com.dev.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

import com.dev.process.Process;

public class Server extends ServerAbs {
	private ServerSocket serverSocket;
	private Process process;
	private ExecutorService executorService;
	
	public Server(ServerSocket serverSocket, Process process, ExecutorService executorService) {
		this.serverSocket = serverSocket;
		this.process = process;
		this.executorService = executorService;
	}
	
	@Override
	public void run() {
		while (true) {
			final Socket socket;
			try {
				socket = serverSocket.accept();
				executorService.execute(new Runnable() {
					@Override
					public void run() {
						try {
							process.process(socket);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				});
			} catch (IOException e) {
//				e.printStackTrace();
//				System.out.println("exception " + executorService);
			}
		}
	}
}
