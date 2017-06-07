package com.theironyard.charlotte.PiLedTest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Jake on 5/11/17.
 */

@Controller
public class PiLedController {
    private RaspberryPiManager piManager;

    public PiLedController() {
        piManager = new RaspberryPiManager();

        // booting up a new thread
        // so we can while(true) the input.
        new Thread(() -> {
            piManager.consoleUI();
        }).start();
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, String led) {
        if (led != null) { piManager.toggleLED(led);}
        return "home";
    }

    @RequestMapping(path = "/blink", method = RequestMethod.POST)
    public String blink(Integer speed, Integer duration) {
        speed = speed == null ? 100 : speed;
        piManager.blinkLEDs(Long.valueOf(speed), Long.valueOf(duration));
        return "redirect:/";
    }

    @RequestMapping(path = "/special", method = RequestMethod.POST)
    public String special(String mode, Integer duration) throws InterruptedException {
        duration = duration == null ? 5000 : duration;
        piManager.specialMode(mode, duration);
        return "redirect:/";
    }

}
