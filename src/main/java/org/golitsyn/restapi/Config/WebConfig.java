package org.golitsyn.restapi.Config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.util.List;
import java.util.Optional;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by Sergey Golitsyn (deft) on 15.06.2019
 */

@Configuration
@EnableWebMvc
@NoArgsConstructor
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
    Optional<HttpMessageConverter<?>> converterFound = converters.stream()
        .filter(c -> c instanceof AbstractJackson2HttpMessageConverter).findFirst();
    if (converterFound.isPresent()){
      AbstractJackson2HttpMessageConverter httpMessageConverter = (AbstractJackson2HttpMessageConverter) converterFound.get();
      httpMessageConverter.getObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
      httpMessageConverter.getObjectMapper().enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }
  }
}
