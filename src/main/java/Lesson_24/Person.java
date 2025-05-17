package Lesson_24;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Person {

    private String name;
    private String lastName;
    private Integer age;
    private String gender;
    private String email;

//    public Person (String name, String email, String lastName, Integer age, String gender) {
//        this.name = name;
//        this.lastName = lastName;
//        this.age = age;
//        this.gender = gender;
//        this.email = email;
//
//    }
//
//    public Person() {
//
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public Integer getAge() {
//        return age;
//    }
//
//    public String getGender() {
//        return gender;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public void setAge(Integer age) {
//        this.age = age;
//    }
//
//    public void setGender(String gender) {
//        this.gender = gender;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
}
