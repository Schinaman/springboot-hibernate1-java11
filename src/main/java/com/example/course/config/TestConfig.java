package com.example.course.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.course.entities.User;
import com.example.course.repositories.UserRepository;

//só executa quando estiver configurado o perfil="test"
@Configuration // especifica que é uma classe especifica para configuração
@Profile("test") // tem que ser igual ao indicado no "application.properties em resources";
					// identifica que a configuração roda apenas para o perfil de teste definido em
					// 1-application.properties 2-application-test.properties(proprio nome do file
					// ja identifica que referese ao perfil test.
public class TestConfig implements CommandLineRunner { // clase especifica para configuração test
	// classe utilizada para popular o banco de dados; acessar os dados e salvar
	// a classe que faz isso é o repository e por isso a gente cria uma dependencia
	// ao UserRepository;

	// UserRepository dentro do objeto abaixo
	@Autowired // só com o isso o proprio spring resolve a dependencia e cria uma instancia de
	private UserRepository userRepository; // injeção de dependencia(serviço depende de outro)

	@Override
	public void run(String... args) throws Exception { // tudo que eu colocar aqui executa quando aplicação iniciar

		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		userRepository.saveAll(Arrays.asList(u1, u2));
	}

}
//