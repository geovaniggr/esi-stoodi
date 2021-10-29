package br.com.usp.stoodi.requests;

import br.com.usp.stoodi.models.Discipline;
import br.com.usp.stoodi.models.Lecture;

import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

public class CreateDisciplineRequest {

    @Size(min = 3)
    private String name;

    @Size(max = 500)
    private String description;

    private LocalDate createdAt = LocalDate.now();

    private List<Lecture> lectures;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public List<Lecture> getLectures() {
        return lectures;
    }

    public void setLectures(List<Lecture> lectures) {
        this.lectures = lectures;
    }

    public Discipline toEntity(){
        return new Discipline(name, description, createdAt, lectures);
    }
}
