package ua.com.vtkachenko;

public class ExitCommand implements Command {
    @Override
    public void execute() {
        System.exit(0);
    }

    @Override
    public String getDescription() {
        return "Quit from the programm";
    }
}
