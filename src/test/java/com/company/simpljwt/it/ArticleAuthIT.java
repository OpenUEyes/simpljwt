package com.company.simpljwt.it;

import com.company.simpljwt.commands.ArticleCommand;
import com.company.simpljwt.config.security.jwt.JwtProvider;
import com.company.simpljwt.model.Article;
import com.company.simpljwt.model.Color;
import com.company.simpljwt.model.User;
import com.company.simpljwt.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ArticleAuthIT {

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    private static String token;

    @BeforeAll
    public static void init(@Autowired JwtProvider jwtProvider) {
        token = jwtProvider.generateToken("testlogin");
    }

    @Test
    public void save() throws Exception {
        final long userId = 1L;

        Article requestArticle = Article.builder()
                .color(Color.BLACK)
                .text("it is my fighting text")
                .user(User.builder().id(userId).build())
                .build();

        User expectedUser = userService.findById(userId).orElse(null);
        ArticleCommand expectedArticle = ArticleCommand.builder()
                .color(Color.BLACK)
                .text("it is my fighting text")
                .user(expectedUser)
                .build();

        this.mockMvc.perform(post("/article/save")
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .content(mapper.writeValueAsString(requestArticle)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString(mapper.writeValueAsString(expectedArticle))));
    }
}