package br.com.gimenez.avaliacao;

import br.com.gimenez.avaliacao.controller.PessoaController;
import br.com.gimenez.avaliacao.model.Contato;
import br.com.gimenez.avaliacao.model.Pessoa;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class AvaliacaoApplicationTests {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@InjectMocks
	private PessoaController pessoaController;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(this.pessoaController).build();
	}

	@Test
	void adicionar() throws Exception {
		List<Contato> contatos = new ArrayList<>();
		contatos.add(new Contato("Juliana", "123456789", "email@email.com"));
		Pessoa pessoa = new Pessoa("Bruno", "12345678912", LocalDate.of(1991,05,16), contatos);

		this.mockMvc.perform(post("/pessoa")
		.contentType(MediaType.APPLICATION_JSON)
		.content(new ObjectMapper().writeValueAsString(pessoa)))
				.andExpect(status().isOk());
	}


	@Test
	void contextLoads() throws Exception {
		this.mockMvc.perform(get("/pessoa")
		.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

}
