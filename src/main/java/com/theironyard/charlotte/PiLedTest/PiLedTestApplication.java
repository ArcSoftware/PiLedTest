package com.theironyard.charlotte.PiLedTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

import static com.theironyard.charlotte.PiLedTest.PiLedController.myLed;

@SpringBootApplication
public class PiLedTestApplication {


	public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        SpringApplication.run(PiLedTestApplication.class, args);

        while(true) {
            System.out.println("Led [on/off]");
            String onOff = inputScanner.nextLine();
            if (onOff.equalsIgnoreCase("on")) {
                PiLedController.myLed.high();
                System.out.println("Turning LED On!");
            } else if (onOff.equalsIgnoreCase("off")) {
                PiLedController.myLed.toggle();
                System.out.println("Turing LED Off..");
            } else if (onOff.equalsIgnoreCase("low")){
                PiLedController.myLed.low();
                System.out.println("Setting LED to low");
            } else {
                System.err.println("Invalid Entry");
            }
		}

	}
}
