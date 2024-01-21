package ru.netology.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.netology.controller.PostController;
import ru.netology.repository.PostRepository;
import ru.netology.service.PostService;

@Configuration
public class JavaConfig {
    @Bean
    public PostRepository repository() {
        return new PostRepository();
    }

    @Bean
    public PostService service() {
        return new PostService(repository());
    }

    @Bean
    public PostController controller() {
        return new PostController(service());
    }
}
