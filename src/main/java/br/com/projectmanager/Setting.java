package br.com.projectmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

/**
 * @author Marcos Mendes - https://github.com/marcosmendes9389
 */


@SpringBootApplication
@Controller
public class Setting {
	
	public static void main(String[] args) {
		SpringApplication.run(Setting.class, args);
	}
}
