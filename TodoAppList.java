class Sample {
    String name;
    int age;
    String designation;

    Sample(String name, int age, String designation){
        this.name = name;
        this.age = age;
        this.designation = designation;
    }
}

public class TodoAppList {
    public static void main(String[] args){

        Sample sample = new Sample("Deependra", 23, "SE");
        System.out.println("Nam e is: " + sample.name);
        System.out.println("Age is: " + sample.age);
    }
}