package ru.npcric.asparagus.trainerslog.adapter.web.controller;

import jakarta.transaction.Transactional;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import ru.npcric.asparagus.trainerslog.adapter.repository.UserRepository;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc(printOnlyOnFailure = false)
public class RegistrationControllerIT {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    @Transactional
    void registration_PasswordEncrypted_ReturnsValidOkResponse() throws Exception {
        var requestBuilder = post("/trainerslog/api/v1/public/registration")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {
                        "username": "sample",
                        "password": "hard_pass"
                        }
                        """);

        String passBcrypt = passwordEncoder.encode("hard_pass");
        //Объект ответа и зашифрованный пароль
        String jsonResponse = this.mockMvc.perform(requestBuilder)
                .andReturn()
                .getResponse()
                .getContentAsString();

        JSONObject jsonObject = new JSONObject(jsonResponse);
        String encryptedPassword = jsonObject.getString("encryptedPassword");

        //Формат зашифрованного пароля
        Pattern pattern = Pattern.compile("^\\$2[aby]\\$\\d\\d\\$.+");
        Matcher matcher = pattern.matcher(encryptedPassword);

        assertTrue(matcher.matches());
        assertNotNull(userRepository.findByUsername("sample").get());
        assertEquals(passBcrypt, userRepository.findByUsername("sample").get().getPassword());
    }
}
