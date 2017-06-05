package com.theironyard.charlotte.PiLedTest;

import com.pi4j.io.gpio.*;

import java.util.Scanner;

/**
 * Created by Jake on 5/12/17.
 */
public class RaspberryPiManager {
    private final GpioController gpio;
    private GpioPinDigitalOutput whiteLED;
    private GpioPinDigitalOutput yellowLED;
    private GpioPinDigitalOutput redLED;
    private GpioPinDigitalOutput greenLED;


    public RaspberryPiManager() {
        gpio = GpioFactory.getInstance();
        whiteLED = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "White LED", PinState.LOW);
        yellowLED = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, "Yellow LED", PinState.LOW);
        redLED = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "Red LED", PinState.LOW);
        greenLED = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_25, "Green LED", PinState.LOW);
    }

    public void toggleLED(String led) {
        if (led.equalsIgnoreCase("white")) {
            whiteLED.toggle();

        } else if (led.equalsIgnoreCase("yellow")) {
            yellowLED.toggle();

        } else if (led.equalsIgnoreCase("red")) {
            redLED.toggle();

        } else if (led.equalsIgnoreCase("green")) {
            greenLED.toggle();

        } else if (led.equalsIgnoreCase("allOff")) {
            allOff();
        } else if (led.equalsIgnoreCase("blink")) {
            blinkLEDs(500l, 10000l);
        }
    }

    public void blinkLEDs(Long speed, Long duration) {
        whiteLED.blink(speed, duration);
        yellowLED.blink(speed, duration);
        redLED.blink(speed, duration);
        greenLED.blink(speed, duration);

    }
    public void specialMode(String mode, Integer duration) {
        allOff();
        if (mode.equalsIgnoreCase("police")) {
            for (int i = 0; (duration/1000) < i; i++) {
                whiteLED.blink(100l, 500l);
                yellowLED.blink(100l, 500l);
            }

        } else if (mode.equalsIgnoreCase("yellow")) {
            yellowLED.toggle();

        } else if (mode.equalsIgnoreCase("red")) {
            redLED.toggle();

        } else if (mode.equalsIgnoreCase("green")) {
            greenLED.toggle();

        } else if (mode.equalsIgnoreCase("allOff")) {
            allOff();
        } else if (mode.equalsIgnoreCase("blink")) {
            blinkLEDs(500l, 10000l);
        }
    }

    public void allOff() {
        if (whiteLED.isHigh()) {whiteLED.toggle();}
        if (yellowLED.isHigh()) {yellowLED.toggle();}
        if (redLED.isHigh()) {redLED.toggle();}
        if (greenLED.isHigh()) {greenLED.toggle();}
    }


    public void consoleUI() {

        while (true) {
            Scanner inputScanner = new Scanner(System.in);
            System.out.println("Please enter your commands. \n For a list of commands, type \"help\"");
            String inputCommand = inputScanner.nextLine();
            if (inputCommand.equalsIgnoreCase("toggle") || inputCommand.equalsIgnoreCase("t")) {
                System.out.println("Please enter LED to toggle:");
                String powerled = inputScanner.nextLine();
                toggleLED(powerled);
            } else if (inputCommand.equalsIgnoreCase("status") || inputCommand.equalsIgnoreCase("s")) {
                System.out.println("White LED:" + "\n" + whiteLED.getProperties() + "\n" + whiteLED.getPullResistance()
                        + "\n" + whiteLED.getState() + "\n");
                System.out.println("Yellow LED:" + "\n" + yellowLED.getProperties() + "\n" + yellowLED.getPullResistance()
                        + "\n" + yellowLED.getState() + "\n");
                System.out.println("Red LED:" + "\n" + redLED.getProperties() + "\n" + redLED.getPullResistance()
                        + "\n" + redLED.getState() + "\n");
                System.out.println("Green LED:" + "\n" + greenLED.getProperties() + "\n" + greenLED.getPullResistance()
                        + "\n" + greenLED.getState() + "\n");
            } else if (inputCommand.equalsIgnoreCase("blink") || inputCommand.equalsIgnoreCase("b")) {
                System.out.println("Please type in duration:");
                Long duration = Long.valueOf(inputScanner.nextLine());
                System.out.println("Please type in the speed in which they will blink:");
                Long speed = Long.valueOf(inputScanner.nextLine());
                blinkLEDs(speed, duration);
                System.out.println("All LEDs are now blinking for " + duration + ".");
            } else if (inputCommand.equalsIgnoreCase("all off") || inputCommand.equalsIgnoreCase("o")) {
                toggleLED("allOff");
                System.out.println("All LEDs are now turned off");
            } else if (inputCommand.equalsIgnoreCase("help") || inputCommand.equalsIgnoreCase("h")) {
                System.out.println("[toggle, status, blink, all off]");
            } else {
                System.err.println("Invalid Entry");
            }
        }
    }
}
