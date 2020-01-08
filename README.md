# fast-cloud
springboot 快速开发工具



web依赖

````xml
		<dependency>
			<groupId>cn.fast</groupId>
			<artifactId>spring-boot-starter-fast-web</artifactId>
			<version>0.0.1-RELEASE</version>
		</dependency>
````

当不需要数据库的时候加上如下配置就行了

````java
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class, MybatisPlusConfig.class})
````

