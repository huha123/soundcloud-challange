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
	
	public Server(ServerSocket serverSocket, ExecutorService executorService, Process process) {
		this.serverSocket = serverSocket;
		this.executorService = executorService;
		this.process = process;
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
							System.out.println("exception " + executorService);
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
