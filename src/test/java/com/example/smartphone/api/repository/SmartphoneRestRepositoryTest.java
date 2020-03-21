package com.example.smartphone.api.repository;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.Month;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.smartphone.api.enumeration.ColorEnum;
import com.example.smartphone.api.model.Smartphone;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class SmartphoneRestRepositoryTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private SmartphoneRestRepository smartphoneRepository;

	private Smartphone smartphone;

	@BeforeEach
	public void setUp() {

		Smartphone phone = new Smartphone();
		phone.setBrand("SmartPhone Inc.");
		phone.setCode("ABC12345");
		phone.setColor(ColorEnum.WHITE);
		phone.setEndDate(LocalDate.of(2022, Month.JANUARY, 1));
		phone.setModel("Smart Plus I");
		phone.setPhoto("https://www.niehs.nih.gov/health/assets/images/cell_phones.jpg");
		phone.setPrice(2500.20f);
		phone.setStartDate(LocalDate.of(2020, Month.JANUARY, 1));

		smartphone = smartphoneRepository.save(phone);
	}

	@AfterEach
	public void closeUp() {
		smartphoneRepository.deleteAll();
		smartphone = null;
	}

	@Test
	public void testListSmartphones() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/smartphones")
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$._embedded.smartphone").exists())
			.andExpect(jsonPath("$._embedded.smartphone").isArray())
			.andExpect(jsonPath("$.page.totalElements").value(BigDecimal.ONE));
	}

	@Test
	public void testGetSmartphone() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/smartphones/" + smartphone.getId())
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.code").value(smartphone.getCode()))
			.andExpect(jsonPath("$.model").value(smartphone.getModel()))
			.andExpect(jsonPath("$.price").value(smartphone.getPrice()))
			.andExpect(jsonPath("$.brand").value(smartphone.getBrand()))
			.andExpect(jsonPath("$.photo").value(smartphone.getPhoto()))
			.andExpect(jsonPath("$.startDate").value(smartphone.getStartDate().toString()))
			.andExpect(jsonPath("$.endDate").value(smartphone.getEndDate().toString()))
			.andExpect(jsonPath("$.color").value(smartphone.getColor().toString()));
	}
	
	@Test
	public void testCreateSmartphone() throws Exception {
		MockHttpServletRequestBuilder emptyBodyPostRequest = MockMvcRequestBuilders.post("/smartphones")
			.accept(MediaType.APPLICATION_JSON)
			.characterEncoding(StandardCharsets.UTF_8.name())
			.content("{}");
		
		mvc.perform(emptyBodyPostRequest)
			.andExpect(status().isBadRequest())
			.andExpect(content().encoding(StandardCharsets.UTF_8.name()))
			.andExpect(jsonPath("$.errors").exists())
			.andExpect(content().string(Matchers.containsString("\"property\":\"code\"")))
			.andExpect(content().string(Matchers.containsString("\"message\":\"Deve ser informado o código do celular.\"")))
			.andExpect(content().string(Matchers.containsString("\"property\":\"model\"")))
			.andExpect(content().string(Matchers.containsString("\"message\":\"Deve ser informado o modelo do celular.\"")))
			.andExpect(content().string(Matchers.containsString("\"property\":\"price\"")))
			.andExpect(content().string(Matchers.containsString("\"message\":\"Deve ser informado o preço do celular.\"")))
			.andExpect(content().string(Matchers.containsString("\"property\":\"brand\"")))
			.andExpect(content().string(Matchers.containsString("\"message\":\"Deve ser informado o nome da marca do celular.\"")))
			.andExpect(content().string(Matchers.containsString("\"property\":\"photo\"")))
			.andExpect(content().string(Matchers.containsString("\"message\":\"Deve ser informado a URL da imagem do celular.\"")))
			.andExpect(content().string(Matchers.containsString("\"property\":\"startDate\"")))
			.andExpect(content().string(Matchers.containsString("\"message\":\"Deve ser informada a data de início da venda do celular.\"")))
			.andExpect(content().string(Matchers.containsString("\"property\":\"endDate\"")))
			.andExpect(content().string(Matchers.containsString("\"message\":\"Deve ser informada a data final da venda do celular.\"")))
			.andExpect(content().string(Matchers.containsString("\"property\":\"color\"")))
			.andExpect(content().string(Matchers.containsString("\"message\":\"Deve ser informada a cor do celular.\"")));
		
		MockHttpServletRequestBuilder forbiddenValuesPostRequest = MockMvcRequestBuilders.post("/smartphones")
			.accept(MediaType.APPLICATION_JSON)
			.characterEncoding(StandardCharsets.UTF_8.name())
			.content("{" + 
					"  \"code\": \"ABC1234\"," + 
					"  \"model\": \"A\"," + 
					"  \"price\": -2," + 
					"  \"brand\": \"A\"," + 
					"  \"photo\": \"A\"," + 
					"  \"startDate\": \"2010-01-01\"," + 
					"  \"endDate\": \"2009-01-01\"" + 
					"}");
		
		mvc.perform(forbiddenValuesPostRequest)
			.andExpect(status().isBadRequest())
			.andExpect(content().encoding(StandardCharsets.UTF_8.name()))
			.andExpect(jsonPath("$.errors").exists())
			.andExpect(content().string(Matchers.containsString("\"property\":\"code\"")))
			.andExpect(content().string(Matchers.containsString("\"message\":\"O código do celular deve ter 8 caracteres.\"")))
			.andExpect(content().string(Matchers.containsString("\"property\":\"model\"")))
			.andExpect(content().string(Matchers.containsString("\"message\":\"O modelo do celular deve ter entre 2 e 255 caracteres.\"")))
			.andExpect(content().string(Matchers.containsString("\"property\":\"price\"")))
			.andExpect(content().string(Matchers.containsString("\"message\":\"O preço não deve ser menor que 0.\"")))
			.andExpect(content().string(Matchers.containsString("\"property\":\"brand\"")))
			.andExpect(content().string(Matchers.containsString("\"message\":\"O nome da marca deve ter entre 2 e 255 caracteres.\"")))
			.andExpect(content().string(Matchers.containsString("\"property\":\"photo\"")))
			.andExpect(content().string(Matchers.containsString("\"message\":\"A URL da imagem deve ter entre 2 e 255 caracteres.\"")))
			.andExpect(content().string(Matchers.containsString("\"property\":\"startDate\"")))
			.andExpect(content().string(Matchers.containsString("\"message\":\"A data de início da venda não deve ser anterior à 25/12/2018.\"")))
			.andExpect(content().string(Matchers.containsString("\"property\":\"endDate\"")))
			.andExpect(content().string(Matchers.containsString("\"message\":\"A data final da venda deve não deve ser anterior à data de início.\"")));
		
		MockHttpServletRequestBuilder successPostRequest = MockMvcRequestBuilders.post("/smartphones")
			.accept(MediaType.APPLICATION_JSON)
			.characterEncoding(StandardCharsets.UTF_8.name())
			.content("{" + 
					"  \"code\": \"ABC54321\"," + 
					"  \"model\": \"Smart Plus II\"," + 
					"  \"price\": 2599.99," + 
					"  \"brand\": \"SmartPhone Inc.\"," + 
					"  \"photo\": \"https://www.niehs.nih.gov/health/assets/images/cell_phones.jpg\"," + 
					"  \"startDate\": \"2021-01-01\"," + 
					"  \"endDate\": \"2022-01-01\"," + 
					"  \"color\": \"GOLD\"" + 
					"}");
		
		mvc.perform(successPostRequest)
			.andExpect(status().isCreated())
			.andExpect(content().encoding(StandardCharsets.UTF_8.name()))
			.andExpect(jsonPath("$.errors").doesNotExist());
	}
	
	@Test
	public void testDeleteSmartphone() throws Exception {

		Long id = smartphone.getId();

		mvc.perform(MockMvcRequestBuilders.delete("/smartphones/" + id))
			.andExpect(status().isNoContent());

		mvc.perform(MockMvcRequestBuilders.delete("/smartphones/" + id))
			.andExpect(status().isNotFound());
	}
}
