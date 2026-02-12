package rvt;
import java.util.ArrayList;
public class TodoList {
    private ArrayList<String> darbi;
    public TodoList() {

        this.darbi = new ArrayList<>();
    }
    public void add(String task) {
        this.darbi.add(task);
    }
    public void print() {
        for (int i = 0; i < darbi.size(); i++) {
            System.out.println((i + 1) + ": " + darbi.get(i));
        }
    }
    public void remove(int number) {
        this.darbi.remove(number - 1);
    }
}

