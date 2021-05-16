package com.u2d.qa;

import com.u2d.qa.service.CalculadoraService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class QaApplicationTests {

	@Autowired
	CalculadoraService calculadoraService;

	@Test
	void contextLoads() {
		Assertions.assertThat(calculadoraService).isNotNull();
	}

}
