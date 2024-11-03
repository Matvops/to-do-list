package com.cadenassi.to_do_list.unittests.mockito.integrationtests;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

import com.cadenassi.to_do_list.dto.TaskDTO;
import com.cadenassi.to_do_list.enums.DayEnum;
import com.cadenassi.to_do_list.enums.PriorityEnum;
import com.cadenassi.to_do_list.unittests.mockito.TestConfigs;
import com.cadenassi.to_do_list.unittests.mockito.integrationtests.testcontainers.AbstractIntegrationTest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.shaded.com.fasterxml.jackson.core.type.TypeReference;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.DeserializationFeature;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Matheus
 */

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TaskControllerWithJsonTest extends AbstractIntegrationTest {

    private static RequestSpecification specification;
    private static ObjectMapper mapper;
    private static TaskDTO taskDTO;

    @BeforeAll
    void setUp() {
        mapper = new ObjectMapper();
        mapper = mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        taskDTO = new TaskDTO();
    }

    @Test
    @Order(0)
    void corsOriginTest() {
        given()
                .basePath("/tasks/v1")
                .port(TestConfigs.SERVER_PORT)
                .accept(ContentType.JSON)
                .filter(new RequestLoggingFilter(LogDetail.ALL))
                .filter(new ResponseLoggingFilter(LogDetail.ALL))
                .header("Origin", "http://localhost:3308")
                .when()
                .get()
                .then()
                .statusCode(403);
    }

    @Test
    @Order(1)
    void findByDayTest() throws IOException {

        //PREPARATION
        specification = new RequestSpecBuilder()
                .setBasePath("/tasks/v1")
                .setAccept(ContentType.JSON)
                .setPort(TestConfigs.SERVER_PORT)
                .addHeader("Origin", "http://localhost:8080")
                .addFilter(new RequestLoggingFilter(LogDetail.ALL))
                .addFilter(new ResponseLoggingFilter(LogDetail.ALL))
                .build();


        //ACTION
        var content = given()
                .spec(specification)
                .pathParam("day", "QUARTA")
                .when()
                .get("/{day}")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        List<TaskDTO> tasks = mapper.readValue(content, new TypeReference<List<TaskDTO>>() {
        });

        //VALIDATION
        assertNotNull(tasks);
        tasks.forEach(x -> assertEquals(DayEnum.QUARTA, x.getDay()));
        assertEquals(7, tasks.size());
    }

    @Test
    @Order(2)
    void findAllTest() throws IOException {

        var content = given()
                .spec(specification)
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        List<TaskDTO> tasks = mapper.readValue(content, new TypeReference<List<TaskDTO>>() {
        });

        assertNotNull(tasks);
        assertEquals(51, tasks.size());
        tasks.forEach(x -> assertNotNull(x.getLinks()));
    }


    @Test
    @Order(3)
    void insertTest() throws IOException {
        createDTO();

        var content = given()
                .spec(specification)
                .contentType(ContentType.JSON)
                .body(taskDTO)
                .when()
                .post()
                .then()
                .statusCode(201)
                .extract()
                .body()
                .asString();

        var task = mapper.readValue(content, TaskDTO.class);

        assertNotNull(task);
        assertFalse(task.isCompleted());
        assertEquals("Conversar com garotas", task.getName());
        assertEquals(DayEnum.QUARTA, task.getDay());
        assertEquals(PriorityEnum.ALTA, task.getPriority());
    }

    @Test
    @Order(4)
    void updateCompletedTest() throws IOException {

        var content = given()
                .spec(specification)
                .pathParams(Map.of(
                        "day", "QUARTA",
                        "id", "8"
                ))
                .when()
                .put("/completed/{day}/{id}")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        var task = mapper.readValue(content, taskDTO.getClass());

        assertNotNull(task);
        assertTrue(task.isCompleted());
        assertEquals(DayEnum.QUARTA, task.getDay());
    }

    @Test
    @Order(5)
    void updateTest() throws IOException {
        taskDTO.setDay(null);
        taskDTO.setCompleted(true);
        taskDTO.setPriority(PriorityEnum.BAIXA);

        var content = given()
                .spec(specification)
                .pathParams(Map.of(
                        "day", "QUARTA",
                        "id", "8"
                ))
                .contentType(ContentType.JSON)
                .body(taskDTO)
                .when()
                .put("/{day}/{id}")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        var task = mapper.readValue(content, taskDTO.getClass());

        assertNotNull(task);
        assertTrue(task.isCompleted());
        assertEquals(DayEnum.QUARTA, task.getDay());
        assertEquals(PriorityEnum.BAIXA, task.getPriority());
    }

    @Test
    @Order(6)
    void deleteTest() throws IOException {

        //ACTION
        given()
                .spec(specification)
                .pathParams(Map.of(
                        "day", "QUARTA",
                        "id", "8"
                ))
                .when()
                .delete("/{day}/{id}")
                .then()
                .statusCode(204);

        var content = given()
                .spec(specification)
                .pathParam("day", "QUARTA")
                .when()
                .get("/{day}")
                .then()
                .statusCode(200)
                .extract()
                .body()
                .asString();

        List<TaskDTO> tasks = mapper.readValue(content, new TypeReference<List<TaskDTO>>() {
        });

        //VALIDATION

        assertEquals(7, tasks.size());
    }



    private void createDTO() {
        taskDTO.setName("Conversar com garotas");
        taskDTO.setCompleted(false);
        taskDTO.setDay(DayEnum.QUARTA);
        taskDTO.setPriority(PriorityEnum.ALTA);
    }
}
