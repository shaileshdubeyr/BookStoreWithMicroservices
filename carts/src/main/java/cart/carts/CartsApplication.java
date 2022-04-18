package cart.carts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableAspectJAutoProxy
@ComponentScan("cart.carts")
@Configuration
public class CartsApplication {

	@Bean
	public RestTemplate getRestTemplet(){
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(CartsApplication.class, args);
	}

}
