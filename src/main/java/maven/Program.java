package maven;

public class Program {
    public static void main(String[] args) {
        Person person = new Person("Olga", "Larina",18);
        Person person2 = new Person("Tatjana", "Larina",19);
        Person person3 = new Person("Vladimir", "Lensky",23);

        Person.savePersonInFileByJSON(person2);
        System.out.println("");
        String json = Person.parsPersonToJSON(person2);
        Person person1 = Person.parsJsonToPerson(json);
        System.out.println(person1.toString());
    }
}
