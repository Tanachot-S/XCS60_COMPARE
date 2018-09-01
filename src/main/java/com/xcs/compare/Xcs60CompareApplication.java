package com.xcs.compare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.xcs.compare.constant.Config;

@SpringBootApplication
public class Xcs60CompareApplication {

	public static void main(String[] args) {
		SpringApplication.run(Xcs60CompareApplication.class, args);
	}
	
	@Bean
    public WebMvcConfigurer  corsConfigurer() {
        return new WebMvcConfigurerAdapter() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {
            	
            	//Compare
            	registry.addMapping("/ComparegetByKeyword").allowedOrigins(Config.URL1).allowedOrigins(Config.URL2);
            	registry.addMapping("/ComparegetByConAdv").allowedOrigins(Config.URL1).allowedOrigins(Config.URL2);
            	registry.addMapping("/ComparegetByCon").allowedOrigins(Config.URL1).allowedOrigins(Config.URL2);
            	registry.addMapping("/CompareinsAll").allowedOrigins(Config.URL1).allowedOrigins(Config.URL2);
            	registry.addMapping("/CompareupdByCon").allowedOrigins(Config.URL1).allowedOrigins(Config.URL2);
            	registry.addMapping("/CompareupdDelete").allowedOrigins(Config.URL1).allowedOrigins(Config.URL2);
            	
            	//CompareDetail
            	registry.addMapping("/CompareDetailinsAll").allowedOrigins(Config.URL1).allowedOrigins(Config.URL2);
            	registry.addMapping("/CompareDetailupdByCon").allowedOrigins(Config.URL1).allowedOrigins(Config.URL2);
            	registry.addMapping("/CompareDetailReceiptinsAll").allowedOrigins(Config.URL1).allowedOrigins(Config.URL2);
            	registry.addMapping("/CompareDetailReceiptupdByCon").allowedOrigins(Config.URL1).allowedOrigins(Config.URL2);
            	registry.addMapping("/CompareDetailReceiptupdDelete").allowedOrigins(Config.URL1).allowedOrigins(Config.URL2);
            	
            	
            	//CompareStaff
            	registry.addMapping("/CompareStaffInsAll").allowedOrigins(Config.URL1).allowedOrigins(Config.URL2);
            	registry.addMapping("/CompareStaffupdByCon").allowedOrigins(Config.URL1).allowedOrigins(Config.URL2);
            	
            	//CompareFineNotice
            	registry.addMapping("/CompareFineNoticegetByCon").allowedOrigins(Config.URL1).allowedOrigins(Config.URL2);
            	
            	
            	//CompareCountMistreat
            	registry.addMapping("/CompareCountMistreatgetByCon").allowedOrigins(Config.URL1).allowedOrigins(Config.URL2);
            	
            	
                registry.addMapping("/CompareCountRateMistreatgetByCon").allowedOrigins(Config.URL1).allowedOrigins(Config.URL2);
            	registry.addMapping("/CompareMasStaffgetByKeyword").allowedOrigins(Config.URL1).allowedOrigins(Config.URL2);
            	registry.addMapping("/CompareMasDivisionRategetByCon").allowedOrigins(Config.URL1).allowedOrigins(Config.URL2);
            	registry.addMapping("/CompareMasLawgetByCon").allowedOrigins(Config.URL1).allowedOrigins(Config.URL2);
                registry.addMapping("/CompareMasDivisionRategetByCon").allowedOrigins(Config.URL1).allowedOrigins(Config.URL2);
            }
        
        };
    }
}
