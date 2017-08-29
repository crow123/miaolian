package com.miaolian.cn;

import com.miaolian.cn.config.SpringConfig;
import com.miaolian.cn.config.YmlConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableConfigurationProperties({YmlConfig.class})
@Import(value={SpringConfig.class})
public class BaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaseApplication.class, args);
	}
}
