package com.ndeeti.cwd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.io.IOException;

@SpringBootApplication
public class CwdApplication {

	public static void main(String[] args) throws IOException {
		// SpringApplication.run(CwdApplication.class, args);
		new SpringApplicationBuilder(CwdApplication.class).headless(false).run(args);
		CwdExecutor cwdExecutor = new CwdExecutor();
		String absoluteCWD = cwdExecutor.absoluteCWD();
		System.out.println("absoluteCWD = " + absoluteCWD);
	}

}
