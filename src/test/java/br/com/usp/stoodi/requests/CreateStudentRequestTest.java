package br.com.usp.stoodi.requests;

import org.junit.jupiter.api.*;

import javax.validation.*;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;


class CreateStudentRequestTest {

    private static Validator validator;

    @BeforeAll
    public static void setup(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    @DisplayName("Estudante deve ter nome de usuário maior que 4 caracteres")
    public void validate_1(){
        var student = new CreateStudentRequest();
        student.setUsername("usr");
        student.setEmail("usr@usp.br");

        var result = validator.validate(student);

        var invalidFields = result.stream().map( field -> field.getPropertyPath().toString()).collect(Collectors.toList());

        assertEquals(1, invalidFields.size());
        assertTrue(invalidFields.contains("username"));
    }

    @Test
    @DisplayName("Estudante deve ter nome de usuário menor que 20 caracteres")
    public void validate_2(){
        var student = new CreateStudentRequest();
        student.setUsername("usrusr_usr_usr_usr_usr_");
        student.setEmail("usr@usp.br");

        var result = validator.validate(student);

        var invalidFields = result.stream().map( field -> field.getPropertyPath().toString()).collect(Collectors.toList());

        assertEquals(1, invalidFields.size());
        assertTrue(invalidFields.contains("username"));
    }

    @Test
    @DisplayName("Estudante deve ter um formato de email valido")
    public void validate_3(){
        var student = new CreateStudentRequest();
        student.setUsername("usuario");
        student.setEmail("usr_without_email");

        var result = validator.validate(student);

        var invalidFields = result.stream().map( field -> field.getPropertyPath().toString()).collect(Collectors.toList());

        assertEquals(1, invalidFields.size());
        assertTrue(invalidFields.contains("email"));
    }


    @Test
    @DisplayName("Estudante com informações corretas não deve conter erros de validação")
    public void validate_4(){
        var student = new CreateStudentRequest();
        student.setUsername("usuario");
        student.setEmail("usuario@usp.com.br");

        var result = validator.validate(student);

        assertEquals(0, result.size());
    }

}