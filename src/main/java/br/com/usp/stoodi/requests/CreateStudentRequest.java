package br.com.usp.stoodi.requests;

import br.com.usp.stoodi.models.Student;

import javax.validation.constraints.*;

public class CreateStudentRequest {

    @Size(min = 4, max = 20)
    private String username;

    private String name;

    @Email
    private String email;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Student toEntity(){
        return new Student(name, email, username, password);
    }

    @Override
    public String toString() {
        return "CreateStudentRequest{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
