package com.veronicaohashi.studyblog.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfig : WebMvcConfigurationSupport() {

  @Bean
  fun swaggerDocket(): Docket = Docket(DocumentationType.SWAGGER_2)
      .select()
      .apis(RequestHandlerSelectors.basePackage("com.veronicaohashi.studyblog"))
      .build()
      .apiInfo(metaData())

  private fun metaData(): ApiInfo = ApiInfoBuilder()
      .title("Spring blog Api")
      .description("Spring study project")
      .version("1.0.0")
      .license("Apache License Version 2.0")
      .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
      .build()

  override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
    registry.addResourceHandler("swagger-ui.html")
        .addResourceLocations("classpath:/META-INF/resources/")

    registry.addResourceHandler("/webjars/**")
        .addResourceLocations("classpath:/META-INF/resources/webjars/")
  }
}
