package com.example.demo.endToEnd;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LivresList {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

//    @AfterEach
//    public void tearDown() {
//        driver.quit();
//    }

    @Test
    public void testGetListOfLivres() {

        driver.get("http://localhost:3000");

        WebElement tbody = driver.findElement(By.tagName("tbody"));

        List<WebElement> rows = tbody.findElements(By.tagName("tr"));

//        System.out.println(rows.size()==4);

        // Verifier le nombre de lignes de la table
        assertTrue(rows.size() == 4); // Assuming there's a header row

    }
}
