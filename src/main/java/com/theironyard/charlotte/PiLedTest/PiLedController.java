package com.theironyard.charlotte.PiLedTest;

import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.trigger.GpioSetStateTrigger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Scanner;

/**
 * Created by Jake on 5/11/17.
 */

@Controller
public class PiLedController {
    public static final GpioController gpio = GpioFactory.getInstance();
    public static Scanner inputScanner = new Scanner(System.in);
    GpioPinDigitalOutput whiteLED = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04, "White LED",
            PinState.LOW);
    GpioPinDigitalOutput yellowLED = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05, "Yellow LED",
            PinState.LOW);
    GpioPinDigitalOutput redLED = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_00, "Red LED",
            PinState.LOW);
    GpioPinDigitalOutput greenLED = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_25, "Green LED",
            PinState.LOW);
    private boolean white, yellow, red, green = false;
    public static PiLedController leds;

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, String led) {
        toggleLED(led);
        return "home";
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

    public static void consoleUI() {
        while (true) {
            System.out.println("Please enter your commands. \n For a list of commands, type \"help\"");
            String inputCommand = inputScanner.nextLine();
            if (inputCommand.equalsIgnoreCase("toggle") || inputCommand.equalsIgnoreCase("t")) {
                System.out.println("Please enter LED to toggle:");
                String powerled = inputScanner.nextLine();
                leds.toggleLED(powerled);
            } else if (inputCommand.equalsIgnoreCase("status") || inputCommand.equalsIgnoreCase("s")) {
                System.out.println("White LED:" + "\n" + leds.whiteLED.getProperties() + "\n" + leds.whiteLED.getPullResistance()
                        + "\n" + leds.whiteLED.getState() + "\n");
                System.out.println("Yellow LED:" + "\n" + leds.yellowLED.getProperties() + "\n" + leds.yellowLED.getPullResistance()
                        + "\n" + leds.yellowLED.getState() + "\n");
                System.out.println("Red LED:" + "\n" + leds.redLED.getProperties() + "\n" + leds.redLED.getPullResistance()
                        + "\n" + leds.redLED.getState() + "\n");
                System.out.println("Green LED:" + "\n" + leds.greenLED.getProperties() + "\n" + leds.greenLED.getPullResistance()
                        + "\n" + leds.greenLED.getState() + "\n");
            } else if (inputCommand.equalsIgnoreCase("blink") || inputCommand.equalsIgnoreCase("b")) {
                leds.blinkLEDs();
                System.out.println("All LEDs are now blinking");
            } else if (inputCommand.equalsIgnoreCase("all off") || inputCommand.equalsIgnoreCase("o")) {
                leds.toggleLED("allOff");
                System.out.println("All LEDs are now turned off");
            } else if (inputCommand.equalsIgnoreCase("help") || inputCommand.equalsIgnoreCase("h")) {
                System.out.println("[toggle, status, blink, all off]");
            } else {
                System.err.println("Invalid Entry");
            }
        }
    }

    public GpioPinDigitalOutput getWhiteLED() {
        return whiteLED;
    }

    public GpioPinDigitalOutput getYellowLED() {
        return yellowLED;
    }

    public GpioPinDigitalOutput getRedLED() {
        return redLED;
    }

    public GpioPinDigitalOutput getGreenLED() {
        return greenLED;
    }
}
