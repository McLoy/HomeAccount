package ua.com.vtkachenko;

public interface Command {
    void execute();

    String getDescription();
}
