package br.com.usp.stoodi.requests;

import br.com.usp.stoodi.models.Discipline;

public class UpdateDisciplineRequest {
    private String name;

    private String description;

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

    public void updateDiscipline(Discipline actual){
        if(name != null){
            actual.setName(name);
        }

        if(description != null){
            actual.setDescription(description);
        }
    }
}
