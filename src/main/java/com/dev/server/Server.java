package com.dev.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

import com.dev.process.Process;

public class Server extends ServerAbs {
	private final ServerSocket serverSocket;
	private final Process process;
	private final ExecutorService executorService;
	
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
							e.printStackTrace();
						}
					}
				});
			} catch (IOException e) {
//				e.printStackTrace();
			}
		}
	}
}
