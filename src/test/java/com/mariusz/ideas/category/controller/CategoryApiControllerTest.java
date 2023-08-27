package com.mariusz.ideas.category.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mariusz.ideas.category.domain.model.Category;
import com.mariusz.ideas.category.service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class CategoryApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @Autowired
    private ObjectMapper objectMapper;

    private PageImpl<Category> page;
    private Category category;

    @BeforeEach
    void setUp() {
        category = new Category("Cat1");
        page = new PageImpl<>(
                List.of(category, new Category("Cat2"), new Category("Cat3"))
        );
        when(categoryService.getCategories(any())).thenReturn(page);
        when(categoryService.getCategory(category.getId())).thenReturn(category);

        when(categoryService.createCategory(any())).thenAnswer(
                (InvocationOnMock invocationOnMock) -> invocationOnMock.getArguments()[0]);
        when(categoryService.updateCategory(any(), any())).thenAnswer(
                (InvocationOnMock invocationOnMock) -> invocationOnMock.getArguments()[1]);
    }

    @Test
    void shouldGetCategories() throws Exception {
        mockMvc.perform(get("http://localhost:8080/api/v1/categories"))
                .andExpect(status().isOk())
                .andExpect(
                        content().json(objectMapper.writeValueAsString(page))
                );
    }

    @Test
    void shouldGetCategory() throws Exception {
        mockMvc.perform(get("http://localhost:8080/api/v1/categories/{categoryId}", category.getId()))
                .andExpect(status().isOk())
                .andExpect(
                        content().json(objectMapper.writeValueAsString(category))
                );
    }

    @Test
    void shouldCreateCategory() throws Exception {
        mockMvc.perform(post("http://localhost:8080/api/v1/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(category))
        )
                .andExpect(status().isCreated())
                .andExpect(
                        content().json(objectMapper.writeValueAsString(category))
                );
    }

    @Test
    void shouldUpdateCategory() throws Exception {
        mockMvc.perform(put("http://localhost:8080/api/v1/categories/{categoryId}", category.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(category))
        )
                .andExpect(status().isAccepted())
                .andExpect(
                        content().json(objectMapper.writeValueAsString(category))
                );
    }

    @Test
    void shouldDeleteCategory() throws Exception {
        mockMvc.perform(delete("http://localhost:8080/api/v1/categories/{categoryId}", category.getId()))
                .andExpect(status().isNoContent());
    }
}