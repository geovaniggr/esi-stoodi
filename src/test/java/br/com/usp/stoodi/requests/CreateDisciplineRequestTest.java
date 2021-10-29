package br.com.usp.stoodi.requests;

import br.com.usp.stoodi.utils.RandomData;
import org.junit.jupiter.api.*;

import javax.validation.*;

import java.util.stream.Collectors;

import static br.com.usp.stoodi.utils.RandomData.randomText;
import static org.junit.jupiter.api.Assertions.*;

class CreateDisciplineRequestTest {

    private static Validator validator;

    @BeforeAll
    public static void setup(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("Disciplina criada deve ter ao menos 4 caraceter de nome")
    public void validate_1(){
        var discipline = new CreateDisciplineRequest();

        discipline.setName("ab");
        discipline.setDescription("Basic Description");

        var result = validator.validate(discipline);

        var invalidFields = result.stream().map( field -> field.getPropertyPath().toString()).collect(Collectors.toList());

        assertEquals(1, invalidFields.size());
        assertTrue(invalidFields.contains("name"));
    }

    @Test
    @DisplayName("Disciplina deve ter descrição com máximo 500 caracteres")
    public void validate_2(){
        var discipline = new CreateDisciplineRequest();

        discipline.setName("Matematica");
        discipline.setDescription(randomText(501));

        var result = validator.validate(discipline);

        var invalidFields = result.stream().map( field -> field.getPropertyPath().toString()).collect(Collectors.toList());

        assertEquals(1, invalidFields.size());
        assertTrue(invalidFields.contains("description"));
    }

    @Test
    @DisplayName("Disciplina respeitando limites deve ser criada corretamente")
    public void validate_3(){
        var discipline = new CreateDisciplineRequest();

        discipline.setName("Matematica");
        discipline.setDescription("Basic Description");

        var result = validator.validate(discipline);

        var invalidFields = result.stream().map( field -> field.getPropertyPath().toString()).collect(Collectors.toList());

        assertEquals(0, invalidFields.size());
    }




}