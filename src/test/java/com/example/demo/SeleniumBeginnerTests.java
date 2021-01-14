package com.example.demo;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SeleniumBeginnerTests extends Setup {


    @Test
    @Disabled("Not doing selenium testing yet")
    public void test1() {
        driver.get("https://www.godaddy.com/");

    }
}
