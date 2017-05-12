package com.theironyard.charlotte.PiLedTest;

import com.pi4j.io.gpio.*;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

/**
 * Created by Jake on 5/11/17.
 */

@Controller
public class PiLedController {
    public static final GpioController gpio = GpioFactory.getInstance();
    public static GpioPinDigitalOutput myLed = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04,   // PIN NUMBER
            "My LED",           // PIN FRIENDLY NAME (optional)
            PinState.LOW);      // PIN STARTUP STATE (optional)
    // explicitly set a state on the pin object


}
