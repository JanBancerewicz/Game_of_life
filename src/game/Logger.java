package game;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Logger {
    private int turn = 0;
    private final ArrayList<String> logs = new ArrayList<>();

    public void addLog(String msg) {
        // System.out.println("log: " + msg);

        logs.add(msg);
    }

    public boolean printLogs(JTextArea loggingSpace) {
        try (PrintWriter fileWriter = new PrintWriter(new FileWriter("logs.txt", true))) {
            turn++;
            fileWriter.printf("\tTura: %d\n", getTurn());

            for (String log : logs) {
                System.out.println(log);
                fileWriter.println(log);
                loggingSpace.append(" "+log + "\n");
            }
            logs.clear();
            System.out.println("-----------------------------------");
            loggingSpace.append(" "+"-------------------------------------------------------------------------\n");

            return true;
        } catch (IOException e) {
            System.out.println("File not opened");
            return false;
        }
    }

    public boolean resetLogging() {
        try (PrintWriter fileWriter = new PrintWriter("logs.txt")) {
            return true;
        } catch (IOException e) {
            System.out.println("File not opened");
            return false;
        }
    }

    private int getTurn() {
        return turn;
    }
}