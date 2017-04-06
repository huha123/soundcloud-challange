package com.dev.process;

import java.io.IOException;
import java.net.Socket;

public interface Process {
	
	public void process(Socket socket) throws IOException;
}
