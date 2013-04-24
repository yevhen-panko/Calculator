package com.teamdev.calculator;

import com.teamdev.calculator.consoleUI.ConsoleUI;
import com.teamdev.calculator.desktopUI.DesktopUI;

public class RunCalculator {

    public static void main(String[] args) throws Exception {
        if (args.length > 0){
            if ("-cmd".equals(args[0])){
                new ConsoleUI().printResult();
            }
        }
        else {
            new DesktopUI();
        }
    }
}
