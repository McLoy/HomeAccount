package ua.com.vtkachenko;

/**
 * Hello world!
 */
public class App implements Hellower {
    private final String phrase;
    private String name;

    public App(String phrase) {
        this.phrase = phrase;
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void seyHello() {
        System.out.printf("%s: %s", name, phrase);
    }
}
