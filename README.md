## springboot druid数据库监控
先来个图吧。
> url监控
![](http://i1.piimg.com/567571/fc7efba8a71c632e.png)

> mysql数据源监控
![](http://i1.piimg.com/567571/90f61bebe84384ed.png)

## 使用方法
### pom依赖
```
		<dependency>
			<groupId>com.alibaba</groupId>
			<artifactId>druid</artifactId>
			<version>1.0.18</version>
		</dependency>
```

### mybatis配置

```
# MYBATIS
mybatis.type-aliases-package=com.niejinping.springbootdruid.model
#mybatis.type-handlers-package=com.niejinping.springbootdruid.mapper
#mybatis.mapper-locations=com.niejinping.springbootdruid.mapper
mybatis.configuration.map-underscore-to-camel-case=true
mybatis.configuration.use-generated-keys=true
mybatis.configuration.default-fetch-size=100
mybatis.configuration.default-statement-timeout=30
```

> 自动扫包得在注解里面搞

```
@SpringBootApplication
@EnableWebMvc
@MapperScan({ "com.niejinping.springbootdruid.mapper" })  // 注意，在这里扫包。application.properties设置好像不行
public class DruidMonitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(DruidMonitorApplication.class, args);
	}
}
```

### java config
从网上抄的，亲测可用。

```
@Configuration
@EnableAutoConfiguration
public class DruidConfiguration {
    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.setServlet(new StatViewServlet());
        servletRegistrationBean.addUrlMappings("/druid/*");
        Map<String, String> initParameters = new HashMap<String, String>();
        initParameters.put("loginUsername", "admin");// 用户名
        initParameters.put("loginPassword", "admin");// 密码
        initParameters.put("resetEnable", "false");// 禁用HTML页面上的“Reset All”功能
        initParameters.put("allow", ""); // IP白名单 (没有配置或者为空，则允许所有访问)
        //initParameters.put("deny", "192.168.20.38");// IP黑名单 (存在共同时，deny优先于allow)
        servletRegistrationBean.setInitParameters(initParameters);
        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}
```

### 注意
#### model

> 配置设置getter和setter，否则反序列化会失败。

#### /mappings是看不到/druid/*的
通过启动日志，可以看到/druid/*
```
2017-01-13 16:28:42.555  INFO 9956 --- [ost-startStop-1] o.s.b.w.servlet.ServletRegistrationBean  : Mapping servlet: 'statViewServlet' to [/druid/*]
2017-01-13 16:28:42.556  INFO 9956 --- [ost-startStop-1] o.s.b.w.servlet.ServletRegistrationBean  : Mapping servlet: 'dispatcherServlet' to [/]
2017-01-13 16:28:42.558  INFO 9956 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   : Mapping filter: 'metricsFilter' to: [/*]
2017-01-13 16:28:42.558  INFO 9956 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   : Mapping filter: 'characterEncodingFilter' to: [/*]
2017-01-13 16:28:42.558  INFO 9956 --- [ost-startStop-1] o.s.b.w.servlet.FilterRegistrationBean   : Mapping filter: 'webRequestLoggingFilter' to: [/*]
```

但通过localhost:xxxx/mappings是看不到的。注意啦。
