package maven;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;

/**
Урок 1. Системы сборки Maven и Gradle для разработки Java приложений
Создать проект с использованием Maven или Gradle, добавить в него несколько зависимостей и написать код,
 использующий эти зависимости.
Задание:
1. Создайте новый Maven или Gradle проект, следуя инструкциям из блока 1 или блока 2.
2. Добавьте зависимости org.apache.commons:commons-lang3:3.12.0 и com.google.code.gson:gson:2.8.6.
3. Создайте класс Person с полями firstName, lastName и age.
4. Используйте библиотеку commons-lang3 для генерации методов toString, equals и hashCode.
5. Используйте библиотеку gson для сериализации и десериализации объектов класса Person в формат JSON.
 */

public class Person implements Serializable {

    private String firstName;
    private String lastName;
    private int age;

    public static AbstractList<Person> persons = new ArrayList<Person>();

    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        persons.add(this);
    }

//    public Person (String firstName, String lastName, int age) {
//        this(firstName, lastName, age);
//    }
    public Person() {}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .append("age", age)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Person person)) return false;

        return new EqualsBuilder().append(getAge(),
                person.getAge()).append(getFirstName(),
                person.getFirstName()).append(getLastName(),
                person.getLastName()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getFirstName())
                .append(getLastName())
                .append(getAge())
                .toHashCode();
    }

    public static void savePersonInFileByJSON(Person person) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try {
            FileWriter writer = new FileWriter("person.json");
            String json = gson.toJson(person);
            System.out.println(json);
            gson.toJson(person, writer);
//            writer.append(json);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
    Парсим экземпляр класса в json
     */
    public static String parsPersonToJSON(Person person) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(person);
        System.out.println("Парсинг в json: "+json);
        return json;
    }

    /**
     * Депарсим json в экземпляр класса
     * @param json
     * @return
     */
    public static Person parsJsonToPerson(String json) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Person person = gson.fromJson(json, Person.class);
        return person;
    }
}
