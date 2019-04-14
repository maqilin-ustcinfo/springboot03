1.新建springboot项目
 1.1利用idea的spring initiaizr
 1.2 引入父工程的依赖和各自启动器
 1.3引入jsp(jsp的依赖，定义spring.mvc.view.prefix和spring.mvc.view.suffix)
 1.4使用servlet(通过@Bean或者注解的方式注册servlet)
     定义servlet
     方式一：在Springboot03Application类中
     @Bean
     public ServletRegistrationBean getServlet(){
         return new ServletRegistrationBean(new Myservlet(),"/servlet/*");
     }
    方式二：在@SpringBootApplication下加@ServletComponentScan，定义自己的servlet:上面加上@WebServlet(urlPatterns = "/servlet2/*")
    定义filter:自己写到filter上加@WebFilter(urlPatterns = "/*")
    定义监听器：@WebListener public class MyListener implements ServletContextListener
  1.5使用spring的拦截器
      第一步：MyInterceptor1 implements HandlerInterceptor
      第二步：写类注册我们写的拦截器
      @Configuration
      public class SpringInterceptorRegister extends WebMvcConfigurerAdapter {
          @Override
          public void addInterceptors(InterceptorRegistry registry) {
              // 注册我们的拦截器
              registry.addInterceptor(new MyInterceptor1()).addPathPatterns("/**");
              registry.addInterceptor(new MyInterceptor2()).addPathPatterns("/getUser/**");
              super.addInterceptors(registry);
          }
       }
   1.6静态资源文件的处理
    建议大家使用Spring Boot的默认配置方式，提供的静态资源映射如下:
    classpath:/META-INF/resources
    classpath:/resources
    classpath:/static
    classpath:/public
    都在resources目录下：优先级顺序为：META-INF/resources > resources > static > public
    自定义静态资源文件的处理：
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/js/**").addResourceLocations("classpath:/jsAndCss/");
            super.addResourceHandlers(registry);
    }
   1.7 启动加载数据
       @Component
       public class MyRun implements CommandLineRunner
   1.8 使用jdbc,mybatis
       引入数据库相关的jar;
       启动类加上：@MapperScan("com.dn.springboot03.mapper")
       properties中加上：
       mybatis.typeAliasesPackage=com.dn.springboot03.entity
       mybatis.mapperLocations=classpath:mapper/*.xml
   1.9 打包
   定义jar的名称：pom.xml <build>下的<finalName>springboot1</finalName>
   将WEB-INF下的文件打包到jar包中：
   <resources>
               <!-- 打包时将jsp文件拷贝到META-INF目录下 -->
               <resource>
                   <!-- 指定resources插件处理哪个目录下的资源文件 -->
                   <directory>src/main/webapp</directory>
                   <!--注意此次必须要放在此目录下才能被访问到 -->
                   <targetPath>META-INF/resources</targetPath>
                   <includes>
                       <include>**/**</include>
                   </includes>
               </resource>
               <resource>
                   <directory>src/main/java</directory>
                   <includes>
                       <include>**/*.xml</include>
                   </includes>
               </resource>
               <resource>
                   <directory>src/main/resources</directory>
                   <includes>
                       <include>**/**</include>
                   </includes>
                   <filtering>false</filtering>
               </resource>
           </resources>
    打成war包： <packaging>war</packaging>
               <!-- 排除Tomcat启动器，如果用jetty需要排除，如果要打包(war)部署到服务器需要排除内置Tomcat
    			<exclusions>
    				<exclusion>
    					<groupId>org.springframework.boot</groupId>
    					<artifactId>spring-boot-starter-tomcat</artifactId>
    				</exclusion>
    			</exclusions>-->
    			启动类继承SpringBootServletInitializer重写：
    			    @Override
                    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
                        return builder.sources(this.getClass());
                    }
   2.0使用shiro权限框架
     引包；
     总的配置类：
     @Configuration
     public class ShiroConfiguration
     登陆：
     public class AuthRealm extends AuthorizingRealm
     密码比较：
     CredentialsMatcher extends SimpleCredentialsMatcher
     以上步骤可以实现请求的拦截
     login的使用时候：
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                     username, password);
             Subject subject = SecurityUtils.getSubject();
             //在这里掉到了shiro中进行登录校验,如果权限校验没有通过就不会往下走
             subject.login(usernamePasswordToken); //完成登录
             User user = (User)subject.getPrincipal();
      其他需要权限的时候：
     Subject subject = SecurityUtils.getSubject();
     User user = (User)subject.getPrincipal();
     Set<RoleVo> roleVos = user.getRoleVos();