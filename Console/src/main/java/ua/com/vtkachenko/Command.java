package ua.com.vtkachenko;

public enum  Command {
    EXIT;

    public void execute() {
        System.out.println(name());
    }
}
