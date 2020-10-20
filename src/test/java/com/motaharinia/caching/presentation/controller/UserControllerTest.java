package com.motaharinia.caching.presentation.controller;

//https://springframework.guru/using-resttemplate-in-spring/

import com.fasterxml.jackson.databind.ObjectMapper;
import com.motaharinia.caching.presentation.model.UserModel;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

//https://reflectoring.io/unit-testing-spring-boot/
//https://www.javaguides.net/2018/09/spring-boot-2-rest-apis-integration-testing.html


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    /**
     * شیی crud
     */
    private static Integer crudId;

    /**
     * این متد مقادیر پیش فرض قبل از هر تست این کلاس تست را مقداردهی اولیه میکند
     */
    @BeforeEach
    void initUseCase() {

    }

    @Test
    @Order(1)
    public void create() throws Exception {
        String uri="http://localhost:" + port + "/user";
        Map<String, String> variableHashMap = new HashMap<String, String>();

        UserModel userModel = new UserModel();
        userModel.setFirstName("Mostafa");
        userModel.setLastName("Motaharinia");
        userModel.setPassword("123456789");
        userModel.setUsername("eng.motahari@gmail.com");
        userModel.setFollowerCount(20000);

        userModel = restTemplate.postForObject(uri, userModel, UserModel.class, variableHashMap);
        crudId=userModel.getId();
        assertThat(userModel.getId()).isNotEqualTo(null);
    }

    @Test
    @Order(2)
    public void findOne() throws Exception {
        String uri="http://localhost:" + port + "/user/"+crudId;
        UserModel resultModel = restTemplate.getForObject(uri, UserModel.class);
        assertThat(resultModel.getId()).isEqualTo(crudId);
    }

    @Test
    @Order(3)
    public void findAll() throws Exception {
        String uri="http://localhost:" + port + "/user";
       UserModel[] userModelArray = restTemplate.getForObject(uri, UserModel[].class);
        assertThat(Arrays.stream(userModelArray).map(item->item.getId()).collect(Collectors.toList())).contains(crudId);
    }

    @Test
    @Order(4)
    public void update() throws Exception {
        String uri="http://localhost:" + port + "/user";
        Map<String, String> variableHashMap = new HashMap<String, String>();

        UserModel userModel = new UserModel();
        userModel.setId(crudId);
        userModel.setFirstName("Mostafa");
        userModel.setLastName("Motaharinia");
        userModel.setPassword("123456789");
        userModel.setUsername("eng.motahari@gmail.com");
        userModel.setFollowerCount(50);

        ResponseEntity<UserModel> response = restTemplate.exchange(uri, HttpMethod.PUT, new HttpEntity<>(userModel), UserModel.class,variableHashMap);
        assertThat(response.getBody().getFollowerCount()).isEqualTo(50);
    }

    @Test
    @Order(5)
    public void delete() throws Exception {
        String uri="http://localhost:" + port + "/user/"+crudId;
        ResponseEntity<UserModel> response = restTemplate.exchange(uri, HttpMethod.DELETE,null, UserModel.class);
        assertThat(response.getBody().getId()).isEqualTo(crudId);
    }

}
