package com.raffenio.service;

import java.io.Closeable;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileManagerNoCloseable {
	
	private static final Logger log = LoggerFactory.getLogger(FileManagerNoCloseable.class);
	
	public void write(String text, String file) {
		log.info("Writing to file: {}", file);
		
		
	}
	

	public void releaseResource() throws IOException {
		log.info("releasing resources (not using closeable interface)"); 
	}
}
