package com.tcontainer.testcontainers;

import com.tcontainer.testcontainers.entity.Student;
import com.tcontainer.testcontainers.repository.StudentRepository;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
//@Testcontainers
class TestcontainersApplicationTests extends AbstractContainerBaseTest{

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private MockMvc mockMvc;

//    @Container
//    private static MySQLContainer mySQLContainer = new MySQLContainer("mysql:8.0.28-oracle");

    @Test
    //given/when/then format--->BDD style
    public void givenStudents_whenGetAllStudents_thenListOfStudents() throws Exception {
//        System.out.println("====== >>>  Database Name : "+mySQLContainer.getDatabaseName());
//        System.out.println("++++++ >>>   Database username : "+mySQLContainer.getUsername());
        //given---> set up or preconditions
        List<Student> studentList = List.of(
                Student.builder().firstName("Nelson").lastName("Otieno").email("nelson123@gmail.com").build(),
                Student.builder().firstName("Arnold").lastName("Owiny").email("vin123@gmail.com").build(),
                Student.builder().firstName("Eric").lastName("Onyango").email("bashida123@gmail.com").build(),
                Student.builder().firstName("Vivian").lastName("Anne").email("valler123@gmail.com").build()
        );
        studentRepository.saveAll(studentList);

        //when-----> action
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders.get("/student/all"));

        //then----> verify the output

        response.andExpect(MockMvcResultMatchers.status().isOk());
        response.andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(studentList.size())));



    }


}
