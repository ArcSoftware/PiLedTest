package com.theironyard.charlotte.PiLedTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class PiLedTestApplication {
    public static PiLedController console;


	public static void main(String[] args) {
        SpringApplication.run(PiLedTestApplication.class, args);
        console.consoleUI();

	}
}
