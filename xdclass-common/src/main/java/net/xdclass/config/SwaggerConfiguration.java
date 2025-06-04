package net.xdclass.config;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.*;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

/**
 * @projectName: xdclass-1024-shop
 * @package: net.xdclass.config
 * @className: SwaggerConfiguration
 * @author: duruijuan
 * @description:SwaggerUI3.0配置
 * @since: 2025-06-04 11:25
 * @version: 1.0
 */
@Component
@Data
@EnableOpenApi
public class SwaggerConfiguration {
  /**
   * description:对C端用户的的接口文档
   * @param
   * @return {@link Docket}
   * @author: duruijuan
   * @since: 2025-06-04 13:43
   **/
    @Bean
    public Docket webApiDoc(){
        return new Docket(DocumentationType.OAS_30)
                .groupName("用户端接口文档")
                .pathMapping("/")
                //定义是否开启Swagger,false是关闭，可以通过变量控制，线上关闭
                .enable(true)
                //配置文档的元信息
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("net.xdclass"))
                //正则匹配请求路径并分配到当前项目组
                .paths(PathSelectors.ant("/api/**"))
                .build()
                //新版SwaggerUI3.0
                .globalRequestParameters(globalRequestParameter())
                .globalResponses(HttpMethod.GET,getGlobalResponseMessage())
                .globalResponses(HttpMethod.POST,getGlobalResponseMessage());



    }
   /**
    * description:对管理端用户的的接口文档
    * @param
    * @return {@link Docket}
    * @author: duruijuan
    * @since: 2025-06-04 13:43
    **/
    @Bean
    public Docket adminApiDoc(){
        return new Docket(DocumentationType.OAS_30)
                .groupName("管理端接口文档")
                .pathMapping("/")
                //定义是否开启Swagger,false是关闭，可以通过变量控制，线上关闭
                .enable(true)
                //配置文档的元信息
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("net.xdclass"))
                //正则匹配请求路径并分配到当前项目组
                .paths(PathSelectors.ant("/api/**"))
                .build();
    }
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder().title("1024电商平台")
                .description("微服务接口文档")
                .contact(new Contact("sunshine","http://xdclass.net","sunshine3468@163.com"))
                .version("1.0")
                .build();

    }
    /**
     * description:配置全局通用参数
     * @param
     * @return {@link List<RequestParameter>}
     * @author: duruijuan
     * @since: 2025-06-04 13:39
     **/

    private List<RequestParameter> globalRequestParameter(){
        List<RequestParameter> parameters=new ArrayList<>();
        parameters.add(new RequestParameterBuilder()
                .name("token")
                .description("登录令牌")
                .in(ParameterType.HEADER)
                .query(q -> q.model(m ->m.scalarModel(ScalarType.STRING)))
                .required(false)
                .build());
        return parameters;
    }
    /**
     * description:生成通用的响应信息
     * @param
     * @return {@link List< Response>}
     * @author: duruijuan
     * @since: 2025-06-04 13:48
     **/
    private List<Response> getGlobalResponseMessage(){
        List<Response> responses=new ArrayList<>();
        responses.add(new ResponseBuilder()
                .code("4XX")
                .description("请求参数，根据code和msg检查")
                .build()
        );
        return responses;
    }

}
