package com.raffenio.service;

import java.io.Closeable;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileManager implements Closeable{
	
	private static final Logger log = LoggerFactory.getLogger(FileManager.class);
	
	public void write(String text, String file) {
		log.info("Writing to file: {}", file);
		
		
	}
	
	@Override
	public void close() throws IOException {
		log.info("releasing resources");
	}
}
