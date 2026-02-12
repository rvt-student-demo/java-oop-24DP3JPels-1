package rvt;

public class Person {
    private String name;  //Ja ir privāts, Tu NEVARĒSI no citiem failiem pievienot jebko!!!
    private String address;
    private int credits;
    
    public Person(String name ,String address){
        this.name = name;
        this.address = address;
    }
public String GetName() {
    return this.name;
}
public String GetAddress() {
    return this.address;
}

public int Study(int credits){
    this.credits = credits;
    credits =+1;
}
}
