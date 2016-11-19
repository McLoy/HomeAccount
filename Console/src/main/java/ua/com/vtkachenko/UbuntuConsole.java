package ua.com.vtkachenko;

import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Locale;

@Service
public class UbuntuConsole {

    private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private PrintStream out = System.out;

    public UbuntuConsole() {
//        this.console = System.out;
//        if (console == null) {
//            throw new RuntimeException("Console not found!!!");
//        }
    }

    public Command readCommand() {
        String commandName  = readLine().toUpperCase();
        Command command = null;
        try {
            command = Command.valueOf(commandName);
        } catch (IllegalArgumentException e) {
            System.out.printf("Command '%s' not found.", commandName);
        }
        return command;
    }


    public String readLine() {
        try {
            return in.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void print(int i) {
        out.print(i);
    }

    public void print(Object obj) {
        out.print(obj);
    }

    public void print(long l) {
        out.print(l);
    }

    public void println(char[] x) {
        out.println(x);
    }

    public void println(double x) {
        out.println(x);
    }

    public void print(double d) {
        out.print(d);
    }

    public void print(char[] s) {
        out.print(s);
    }

    public void println(String x) {
        out.println(x);
    }

    public PrintStream printf(Locale l, String format, Object... args) {
        return out.printf(l, format, args);
    }

    public void print(char c) {
        out.print(c);
    }

    public void println() {
        out.println();
    }

    public void println(float x) {
        out.println(x);
    }

    public void println(char x) {
        out.println(x);
    }

    public void print(float f) {
        out.print(f);
    }

    public void print(String s) {
        out.print(s);
    }

    public PrintStream format(Locale l, String format, Object... args) {
        return out.format(l, format, args);
    }

    public void println(long x) {
        out.println(x);
    }

    public void print(boolean b) {
        out.print(b);
    }

    public PrintStream format(String format, Object... args) {
        return out.format(format, args);
    }

    public void println(boolean x) {
        out.println(x);
    }

    public void println(Object x) {
        out.println(x);
    }

    public void println(int x) {
        out.println(x);
    }

    public PrintStream printf(String format, Object... args) {
        return out.printf(format, args);
    }
}
