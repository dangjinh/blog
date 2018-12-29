package com.my.blog.website.controller;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.*;

/**
 * ************************************
 *
 * @ClassName: IndexControllerTest
 * @Auther: dangjinhu
 * @Date: 2018/3/22
 * @Description: 接口测试方法
 * @Copyright: All rights reserver.
 * ************************************
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class IndexControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Test
    @Ignore
    public void index() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("")).andExpect(MockMvcResultMatchers.status().isOk());

    }

}