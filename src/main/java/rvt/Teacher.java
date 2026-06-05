package rvt;

public class Teacher extends Person {
    private double salary;

    public Teacher(String name, String address, double salary) {
        super(name, address);
        this.salary = salary;
    }

    public double getSalary() {
        return this.salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return super.toString() + " | Salary: " + this.salary;
    }
}