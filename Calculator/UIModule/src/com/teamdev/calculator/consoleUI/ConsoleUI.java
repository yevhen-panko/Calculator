package com.teamdev.calculator.consoleUI;

import com.teamdev.calculator.impl.Calculator;
import com.teamdev.calculator.impl.EvaluationException;

import java.io.*;
import java.math.BigDecimal;

public class ConsoleUI {

    public ConsoleUI(){
    }

    public void printResult(){
        Boolean runFlag = true;
        String inputString;
        Calculator calculator = new Calculator();
        BigDecimal result;
        new BigDecimal(0);

        while (runFlag)
        {
            System.out.print("Please enter your expression or -help: ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try{
                inputString = reader.readLine();
                if ("-help".equals(inputString)){
                    printHelp();
                }
                else {
                    result = calculator.evaluate(inputString.trim());
                    System.out.println("=================================================================");
                    System.out.println("Result = " + result);
                }
                System.out.print("Would you like to use calculator again? y/n: ");
                inputString = reader.readLine();
                if ("n".equals(inputString.toLowerCase()))
                {
                    runFlag = false;
                    reader.close();
                }
            }
            catch (IOException exception){
                System.out.println("ERROR! Invalid input string.");
            }
            catch (EvaluationException exception){
                System.out.println(exception.toString());
                System.out.println("Missed " + exception.getPossibleStateString());
            }
            catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    public void printHelp() throws IOException{
            File helpFile = new File("help.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(helpFile)));
            int c;
            while ((c=bufferedReader.read())!= -1)
            {
                System.out.print((char) c);
            }
            bufferedReader.close();
    }
}

