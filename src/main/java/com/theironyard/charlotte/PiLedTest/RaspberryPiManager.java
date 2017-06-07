package com.theironyard.charlotte.PiLedTest;

import com.pi4j.io.gpio.*;

/**
 * Created by Jake on 5/12/17.
 */
public class RaspberryPiManager {
    private final GpioController gpio;
    private GpioPinDigitalOutput whiteLED;
    private GpioPinDigitalOutput yellowLED;
    private GpioPinDigitalOutput redLED;
    private GpioPinDigitalOutput greenLED;
    private GpioPinDigitalOutput blueLED;


    public RaspberryPiManager() {
        gpio = GpioFactory.getInstance();
        whiteLED = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "White LED", PinState.LOW);
        yellowLED = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, "Yellow LED", PinState.LOW);
        redLED = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_02, "Red LED", PinState.LOW);
        greenLED = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_25, "Green LED", PinState.LOW);
        blueLED = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_03, "Blue LED", PinState.LOW);
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
        } else if (led.equalsIgnoreCase("blue")) {
            blueLED.toggle();

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
        blueLED.blink(speed, duration);

    }
    public synchronized void specialMode(String mode, Integer duration) throws InterruptedException {
        allOff();
        duration *= 2;
        if (mode.equalsIgnoreCase("police")) {
            allOff();
            Thread.sleep(200);
            policeMode(duration);
        }
        if (mode.equalsIgnoreCase("sequential")) {
            allOff();
            Thread.sleep(200);
            sequential(duration);
        }
    }

    public void allOff() {
        if (whiteLED.isHigh()) {whiteLED.toggle();}
        if (yellowLED.isHigh()) {yellowLED.toggle();}
        if (redLED.isHigh()) {redLED.toggle();}
        if (greenLED.isHigh()) {greenLED.toggle();}
        if (blueLED.isHigh()) {blueLED.toggle();}
    }

    public void policeMode(int duration) throws InterruptedException {
        if (duration > 0) {
            policeMode(-- duration);
        }
        if (duration%2 == 0) {
            blueLED.blink(100, 400);
            System.out.println("even" + duration);
            Thread.sleep(400);
        } else {
            whiteLED.blink(100, 400);
            System.out.println("odd" + duration);
            Thread.sleep(400);
        }
    }

    public void sequential(int duration) throws InterruptedException {
        if(duration > 0) {
            sequential(-- duration);
        }
        redLED.blink(800, 1600);
        Thread.sleep(800);
        greenLED.blink(800, 1600);
        Thread.sleep(800);
        blueLED.blink(800, 1600);
        Thread.sleep(800);
        yellowLED.blink(800, 1600);
        Thread.sleep(800);
    }
//    public void consoleUI() {
//
//        while (true) {
//            Scanner inputScanner = new Scanner(System.in);
//            System.out.println("Please enter your commands. \n For a list of commands, type \"help\"");
//            String inputCommand = inputScanner.nextLine();
//            if (inputCommand.equalsIgnoreCase("toggle") || inputCommand.equalsIgnoreCase("t")) {
//                System.out.println("Please enter LED to toggle:");
//                String powerled = inputScanner.nextLine();
//                toggleLED(powerled);
//            } else if (inputCommand.equalsIgnoreCase("status") || inputCommand.equalsIgnoreCase("s")) {
//                System.out.println("White LED:" + "\n" + whiteLED.getProperties() + "\n" + whiteLED.getPullResistance()
//                        + "\n" + whiteLED.getState() + "\n");
//                System.out.println("Yellow LED:" + "\n" + yellowLED.getProperties() + "\n" + yellowLED.getPullResistance()
//                        + "\n" + yellowLED.getState() + "\n");
//                System.out.println("Red LED:" + "\n" + redLED.getProperties() + "\n" + redLED.getPullResistance()
//                        + "\n" + redLED.getState() + "\n");
//                System.out.println("Green LED:" + "\n" + greenLED.getProperties() + "\n" + greenLED.getPullResistance()
//                        + "\n" + greenLED.getState() + "\n");
//            } else if (inputCommand.equalsIgnoreCase("blink") || inputCommand.equalsIgnoreCase("b")) {
//                System.out.println("Please type in duration:");
//                Long duration = Long.valueOf(inputScanner.nextLine());
//                System.out.println("Please type in the speed in which they will blink:");
//                Long speed = Long.valueOf(inputScanner.nextLine());
//                blinkLEDs(speed, duration);
//                System.out.println("All LEDs are now blinking for " + duration + ".");
//            } else if (inputCommand.equalsIgnoreCase("all off") || inputCommand.equalsIgnoreCase("o")) {
//                toggleLED("allOff");
//                System.out.println("All LEDs are now turned off");
//            } else if (inputCommand.equalsIgnoreCase("help") || inputCommand.equalsIgnoreCase("h")) {
//                System.out.println("[toggle, status, blink, all off]");
//            } else {
//                System.err.println("Invalid Entry");
//            }
//        }
//    }
}
