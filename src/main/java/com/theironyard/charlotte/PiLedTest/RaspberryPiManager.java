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
    private boolean white, yellow, red, green;

    public RaspberryPiManager() {
        gpio = GpioFactory.getInstance();
        whiteLED = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "White LED", PinState.LOW);
        yellowLED = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, "Yellow LED", PinState.LOW);
        redLED = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00, "Red LED", PinState.LOW);
        greenLED = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_25, "Green LED", PinState.LOW);
    }

    public void toggleLED(String led) {
        if (led.equalsIgnoreCase("white")) {
            if (white == false) {
                whiteLED.high();
                white = true;
            } else {
                whiteLED.low();
                white = false;
            }
        } else if (led.equalsIgnoreCase("yellow")) {
            if (yellow == false) {
                yellowLED.high();
                yellow = true;
            } else {
                yellowLED.low();
                yellow = false;
            }
        } else if (led.equalsIgnoreCase("red")) {
            if (red == false) {
                redLED.high();
                red = true;
            } else {
                redLED.low();
                red = false;
            }
        } else if (led.equalsIgnoreCase("green")) {
            if (green == false) {
                greenLED.high();
                green = true;
            } else {
                greenLED.low();
                green = false;
            }
        } else if (led.equalsIgnoreCase("allOff")) {
            whiteLED.low();
            yellowLED.low();
            redLED.low();
            greenLED.low();
            white = false;
            yellow = false;
            red = false;
            green = false;
        } else if (led.equalsIgnoreCase("blink")) {
            blinkLEDs();
        }
    }

    public void blinkLEDs() {
        whiteLED.blink(1l);
        yellowLED.blink(1l);
        redLED.blink(1l);
        greenLED.blink(1l);
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
                blinkLEDs();
                System.out.println("All LEDs are now blinking");
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
