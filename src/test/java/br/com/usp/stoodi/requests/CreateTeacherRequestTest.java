package br.com.usp.stoodi.requests;

import br.com.usp.stoodi.utils.RandomData;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import javax.validation.*;

import java.util.stream.Collectors;

import static br.com.usp.stoodi.utils.RandomData.randomText;
import static org.junit.jupiter.api.Assertions.*;

class CreateTeacherRequestTest {
    private static Validator validator;

    @BeforeAll
    public static void setup(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("Professor deve ter nome de usuário maior que 4 caracteres")
    public void validate_1(){
        var teacher = new CreateTeacherRequest();
        teacher.setUsername("usr");
        teacher.setEmail("usr@usp.br");

        var result = validator.validate(teacher);

        var invalidFields = result.stream().map( field -> field.getPropertyPath().toString()).collect(Collectors.toList());

        assertEquals(1, invalidFields.size());
        assertTrue(invalidFields.contains("username"));
    }

    @Test
    @DisplayName("Professor deve ter nome de usuário menor que 20 caracteres")
    public void validate_2(){
        var teacher = new CreateTeacherRequest();
        teacher.setUsername("usrusr_usr_usr_usr_usr_");
        teacher.setEmail("usr@usp.br");

        var result = validator.validate(teacher);

        var invalidFields = result.stream().map( field -> field.getPropertyPath().toString()).collect(Collectors.toList());

        assertEquals(1, invalidFields.size());
        assertTrue(invalidFields.contains("username"));
    }

    @Test
    @DisplayName("Professor deve ter um formato de email valido")
    public void validate_3(){
        var teacher = new CreateTeacherRequest();
        teacher.setUsername("usuario");
        teacher.setEmail("usr_without_email");

        var result = validator.validate(teacher);

        var invalidFields = result.stream().map( field -> field.getPropertyPath().toString()).collect(Collectors.toList());

        assertEquals(1, invalidFields.size());
        assertTrue(invalidFields.contains("email"));
    }

    @Test
    @DisplayName("Professor deve ter uma biografia menor que 280 caracteres")
    public void validate_4(){
        var teacher = new CreateTeacherRequest();
        teacher.setUsername("usuario");
        teacher.setEmail("usuario@usp.br");
        teacher.setBiography(randomText(281));

        var result = validator.validate(teacher);

        var invalidFields = result.stream().map( field -> field.getPropertyPath().toString()).collect(Collectors.toList());

        assertEquals(1, invalidFields.size());
        assertTrue(invalidFields.contains("biography"));
    }


    @Test
    @DisplayName("Professor com informações corretas não deve conter erros de validação")
    public void validate_5(){
        var teacher = new CreateTeacherRequest();
        teacher.setUsername("usuario");
        teacher.setEmail("usuario@usp.com.br");

        var result = validator.validate(teacher);

        assertEquals(0, result.size());
    }
}