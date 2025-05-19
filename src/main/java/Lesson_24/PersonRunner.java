package Lesson_24;

public class PersonRunner {
    public static void main(String[] args) {

        Person person = new Person();
        person.setName ("Sveta");
        person.setLastName ("Svetikova");
        person.setAge (30);
        person.setGender ("Ж");
        person.setEmail ("sveta@mail.com");

        Person vasya = new Person ("Vasya", "Vasechkin", 35, "М", "vasya@mail.com");

        System.out.println(person);
        System.out.println(vasya);
    }
}
