package heritage;


import java.util.ArrayList;
import java.util.List;

public class Person {
    private String name;
    private List<Person> children;

    public Person(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }

    public void addChild(Person child) {
        this.children.add(child);
    }

    public String getName() {
        return name;
    }

    public List<Person> getChildren() {
        return children;
    }
}
