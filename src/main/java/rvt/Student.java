package rvt;

public class Student extends Person {
    private int credits;

    public Student(String name, String address) {
        super(name, address);
    }

    public String getName() {
        return this.name;
    }

    public void study() {
        this.credits += 1;
    }

    @Override
    public String toString() {
        return super.toString() + " | Credits: " + this.credits;
    }
}
