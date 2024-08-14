package learning.practice.web.config;

import learning.practice.web.interceptor.CustomInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

  private final CustomInterceptor customInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry)
  {
    registry.addInterceptor(customInterceptor);
  }
}
