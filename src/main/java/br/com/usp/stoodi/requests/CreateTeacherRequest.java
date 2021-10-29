package br.com.usp.stoodi.requests;

import br.com.usp.stoodi.models.Teacher;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;

public class CreateTeacherRequest {

    @Length( min = 4, max = 20 )
    private String username;

    private String password;

    private String name;

    @Email
    private String email;

    @Length( max = 280 )
    private String biography;

    private String linkedin;

    private String youtube;

    private String website;

    public Teacher toEntity(){
        return new Teacher(username, password, name, email, biography, linkedin, youtube, website);
    }

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

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
