package cn.unowen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ServletComponentScan
@MapperScan("cn.unowen.mapper")
public class FirsttestApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirsttestApplication.class, args);
	}

}
