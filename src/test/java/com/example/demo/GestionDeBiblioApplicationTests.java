package com.example.demo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class GestionDeBiblioApplicationTests {

	@Test
	void contextLoads() {
	}
	private Calculator underTest;
	@BeforeEach
	public void  setup(){
		underTest = new Calculator();
	}

	@Test
	void itShouldAddTwoNumbers() {
		//given
		int numberOne = 30;
		int numberTwo = 20;

		//when
		int result = underTest.add(numberOne,numberTwo);

		//then
		int expected = 50;
		assertThat(result).isEqualTo(expected);

	}

	@Test
	void testIntegration() {
		//given
		int a = 2;
		int b = 3;
		int c = 10;

		//when
		int result = underTest.multiply(underTest.add(a,b), c);

		//then
		int expected = 50;
		assertThat(result).isEqualTo(expected);

	}



	class Calculator {
		int add(int a,int b){
				return a+b;
		}
		int multiply(int a, int b){
			return a*b;
		}
	}

}
