# WXAPP
毕设微信小程序后端

# 环境配置

1. 需要安装的软件:

   * tomcat(8.5)
   * maven(3.6.3,高版本会出问题)
   * java(1.8)
   * mysql5.7

2. 在idea中新建maven项目

   * 直接点next

    ![image-20220502201341055](https://user-images.githubusercontent.com/73418254/167250550-e3528b09-145c-4af2-a38d-f01119951705.png)


   * 不建议使用IDEA自带的maven,自己安装maven并配置镜像

   * 加载完成之后会自动生成java目录

    ![image-20220502201552709](https://user-images.githubusercontent.com/73418254/167250555-85c0b902-5aec-4374-8a7b-2210a1150fdd.png)


3. 安装依赖

   * 参考链接https://www.springcloud.cc/spring-boot.html

   > 在pom.xml插入如下配置

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <project xmlns="http://maven.apache.org/POM/4.0.0"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
       <modelVersion>4.0.0</modelVersion>
   
       <groupId>org.example</groupId>
       <artifactId>Server4WX</artifactId>
       <version>1.0-SNAPSHOT</version>
       <!--spingboot的配置-->
       <parent>
           <artifactId>spring-boot-starter-parent</artifactId>
           <groupId>org.springframework.boot</groupId>
           <version>2.6.6</version>
       </parent>
       <dependencies>
           <dependency>
               <groupId>org.springframework.boot</groupId>
               <artifactId>spring-boot-starter-web</artifactId>
           </dependency>
       </dependencies>
   
       <properties>
           <maven.compiler.source>8</maven.compiler.source>
           <maven.compiler.target>8</maven.compiler.target>
       </properties>
       
   </project>
   ```

   * 直接粘贴出问题的话手敲试试
   * 加入依赖之后,右键项目号,然后reload project

4. 创建测试文件

   >  java路径下创建Example.java

   ```java
   import org.springframework.boot.*;
   import org.springframework.boot.autoconfigure.*;
   import org.springframework.web.bind.annotation.*;
   
   @RestController
   @EnableAutoConfiguration
   public class Example {
   
   	@RequestMapping("/")
   	String home() {
   		return "Hello World!";
   	}
   
   	public static void main(String[] args) throws Exception {
   		SpringApplication.run(Example.class, args);
   	}
   
   }
   ```

   * 启动项目,浏览器打开8080

     ![image-20220502202148431](微信小程序后端Spring-Boot.assets/image-20220502202148431.png)

# 项目结构

> 项目是一个前后端分离的项目,前端微信小程序完成,后端Springboot+Mybatis,项目结构将会和本级子标题相同,此处按项目结构记述,启动类在中间的位置看完再运行

## POM全部配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.example</groupId>
    <artifactId>WXAPP</artifactId>
    <version>1.0-SNAPSHOT</version>

    <!--spingboot的配置-->
    <parent>
        <artifactId>spring-boot-starter-parent</artifactId>
        <groupId>org.springframework.boot</groupId>
        <version>2.6.6</version>
    </parent>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!-- 将Java转化为json格式的数据 -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
            <version>1.2.15</version>
        </dependency>

        <!--mybatis依赖-->
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>2.2.2</version>
        </dependency>
        <!--mysql依赖-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.28</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jdbc</artifactId>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.12</version>
            <scope>provided</scope>
        </dependency>

        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>1.2.1</version>
        </dependency>
        <dependency>
            <groupId>org.xmlunit</groupId>
            <artifactId>xmlunit-core</artifactId>
        </dependency>
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.4.6</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
            <exclusions>
                <exclusion>
                    <groupId>org.junit.vintage</groupId>
                    <artifactId>junit-vintage-engine</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
    </dependencies>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>

</project>
```

## main

### java

#### com.boyier

##### controller

> 该部分的代码主要用于接收用户请求,并调用相应服务模块进行处理,网址的导航如域名后的/hello都会到这里

1. 所有的Controller类名上方都要写@RestController,spring根据这个标签才能将/hello导航到这

2. 这个包下可以有多个类,本项目只有一个

3. 类内声明的方法,方法名上方要加上@RequestMapping("/hello"),/hello可以自定义

4. 接收请求发过来的数据时,可以在方法的参数列表里声明和数据同名的属性,这样就能获取

5. 当定义多个参数,但是发送的请求只有一个参数时可以正常运行,其他属性时null

6. 例子

   ```java
   import org.springframework.beans.factory.annotation.Autowired;
   import org.springframework.web.bind.annotation.RequestMapping;
   import org.springframework.web.bind.annotation.RestController;
   
   
   @RestController
   public class WXController {
       
       @RequestMapping("/test")
       public String Test(String id) {
           return id;
       }
   }   
   ```

   > 启动项目后访问http://localhost:8080/test?id=zhangsan,浏览器会打印zhangsan

##### mapper

> 数据库存储**接口**,调用SQL语句,提供方法给service

1. 接口名称上方要加@Mapper和@Repository
2. 此处的文件和resource下中的mapper文件对应,不用写具体的数据库语句

```java
package com.boyier.mapper;

import com.boyier.pojo.Cond;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CondMapper {

    public List<Cond> queryCond(String c_facility);

    public int addCond(Cond cond);

}
```

##### net

> 我自己取名的文件夹,包含在springboot中使用UDP监听socket数据的方法

1. 该类类名有@WebListener标签,其内部有多个内部类
2. 唯一要注意的点是,在这里使用@AutoWired会获取空对象,需要自定义ApplicationContext工具类

##### pojo

> 内部是多个和数据库对应的类,其属性要和数据库属性名称一致,类名也要和数据库表名一致

1. 加三个标签@Data@AllArgsConstructor@NoArgsConstructor,就不用写get,set和构造器方法了

##### service

> 服务模块,通过注解的方式被controller调用

1. 用于处理较为复杂的需求,和mapper不同的是,mapper只做较为简单的数据库操作,这里你可以写一些更加复杂一点的操作
   * 比如我要删除数据库数据,提前查一遍有没有这个数据,有的话删除,没有的话return一个失败给上级函数,上级函数可以将这个返回给微信小程序
2. 这里面一个服务要声明对应的接口和对应的实现类(学的课程这么教我也不知道为啥),如UserService.java(这个是接口)和UserServiceImpl.java
3. 实现类(Impl后缀)类名上要添加@service标签
4. 在这里使用mapper里面定义的类声明变量,然后使用@Autowired获取对应的对象

##### 主类

> 这个是启动类,和上面的那些文件夹平级

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class MyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }
}
```

* 两个标签必加,@SpringBootApplication会扫描本级及以下的文件

### resource

> 存放所有的静态配置文件,配置文件名字不要改

#### mapper

> 该目录下配置xml文件,用于为mapper下的方法实现数据库功能

#### application.properties

```properties
server.port=8080
type=com.alibaba.druid.pool.DruidDataSource
mybatis.mapper-locations=classpath:mapper/*.xml
```

* 这个不用改

#### application.yml

```yml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/nilm?serverTimezone=GMT%2B8&useSSL=true
    username: ***
    password: *****
```

* 配置数据库,这里面driver-class-name以前没有cj,新改动需要加上

# 部署云端

> 用 war部署云端，环境配置与开发环境一样

## 依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.example</groupId>
    <artifactId>WXAPP</artifactId>
    <version>1.0-SNAPSHOT</version>

    <!--    打包配置-->
    <packaging>war</packaging>
    <!--spingboot的配置-->
    <parent>
        <artifactId>spring-boot-starter-parent</artifactId>
        <groupId>org.springframework.boot</groupId>
        <version>2.6.6</version>
    </parent>
    <dependencies>
        <!--    正常运行取消下面注释-->
        <!--        <dependency>-->
        <!--            <groupId>org.springframework.boot</groupId>-->
        <!--            <artifactId>spring-boot-starter-web</artifactId>-->
        <!--        </dependency>-->
        <!--    打包配置-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
            <exclusions>
                <exclusion>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-starter-tomcat</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
        <!--    打包配置-->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <version>3.1.0</version>
            <scope>provided</scope>
        </dependency>

        <!-- 将Java转化为json格式的数据 -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
            <version>1.2.15</version>
        </dependency>

        <!--mybatis依赖-->
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>2.2.2</version>
        </dependency>
        <!--mysql依赖-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.28</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jdbc</artifactId>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.12</version>
            <scope>provided</scope>
        </dependency>

        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>1.2.1</version>
        </dependency>
        <dependency>
            <groupId>org.xmlunit</groupId>
            <artifactId>xmlunit-core</artifactId>
        </dependency>
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.4.6</version>
        </dependency>
    </dependencies>
    <!--    打包配置-->
    <build>

        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <fork>true</fork>
                    <addResources>true</addResources>
                </configuration>
            </plugin>

        </plugins>
    </build>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>

</project>
```

## 主类

```java
package com.boyier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@ServletComponentScan
@SpringBootApplication
public class MyApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }

    //为了打包springboot项目起到web.xml作用
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MyApplication.class);
    }
}

```

## 打包

1. idea中maven的按键clean
2. 再点package就可以在项目下发现target

# 注解解析

> 注解执行顺序应该是从上到下

## 自动配置

### @SpringBootApplication

> 放在类名上,告诉spring扫描当前类所在的包

1. 可以在后面指定属性,指定扫描哪些包

   ```java
   @SpringBootApplication(scanBasePackages="com.boyier.controller")
   ```

2. 也会将这个类声明为配置类,下面也可以放bean

3. 这一个注解可以有EnableAutoConfiguration,ComponentScan,Configuraton的效果

### @Configuraton

> 放在类名上,告诉spring这是一个配置类,然后spring会扫描这个类,遇到@Bean就把他转化为bean

1. 需要能被扫描到,注意要在@SpringBootApplication的生效范围内
2. proxyBeanMethods属性,如果是true则启用代理(默认是true)
   * 启用代理就是先在spring容器内找找有没有这个bean,有就返回没有再创建
   * 一般配合@Bean使用

### @Bean

> 告诉spring这个方法要转化为bean

### @Import

> 导入bean

1. 属性为xml可以导入resource下的xml文件
2. 属性放类的class也可以导入bean

### @EnableAutoConfiguration

> 开启自动配置

### @ComponentScan

> 里面有排除过滤器,过滤重复的bean 

### @Component

> 让这个类变成bean

## 条件编译

### @ConditionalOnBean

> 加在某个需要变成bean的方法上,存在指定类型的bean才生效

1. value指定class文件

   ```java
   @ConditionalOnBean(value = UserService.class)
   ```

2. name属性可以指定名字

   ```java
   @ConditionalOnBean(name = "UserService")
   ```

### @ConditionalOnMissingBean

> 缺失指定文件才触发,加在某个需要变成bean的方法上

1. value指定class文件

   ```java
   @ConditionalOnMissingBean(value = UserService.class)
   ```

2. name属性可以指定名字

   ```java
   @ConditionalOnMissingBean(name = "UserService")
   ```

3. 默认情况下,是缺失当前bean(就是加在某个bean的代码上的)才生效

### @ConditionalOnSingleCandidate

> 是否符合指定类型的Bean只有⼀个

* 可以指定类型(.class)或者名称

### @ConditionalOnClass

> 是否存在某个类

### @ConditionalOnMissingClass

> 是否缺失某个类

### @ConditionalOnExpression

> 后接表达式,当表达式生效则执行

```java
@ConditionalOnExpression(value = "${test.condition}==true")//EL表达式
```

```
test.condition=false
//这是application.properties
```

### @ConditionalOnJava

> 判断Java版本,版本对才生效

```java
@ConditionalOnJava(value = JavaVersion.EIGHT)
```

### @ConditionalOnWebApplication

> 当前应用是⼀个Web应用执行

### @ConditionalOnNotWebApplication

> 当前应⽤不是⼀个Web应⽤ 

### @ConditionalOnProperty

> Environment中是否存在某个属性 

1. name属性指定文件里的某个key
2. matchIfMissing=true

### @ConditionalOnResource

> 指定的资源是否存在 

1. resources指定路径

### @ConditionalOnWarDeployment

> 当前项目是不是以War包部署的方式运行

### @ConditionalOnCloudPlatform

> 是不是在某个云平台上

## 其他

### @ConfigurationProperties

> 这是一个属性类,将配置文件注入这个类

1. 默认是找配置文件对应key
2. 添加prefix属性后会查找对应前缀的key 
3. 通常要加@Component

### @ConfigurationPropertiesScan

> 指定包路径

### @AutoWired

> 自动注入对象

### @EnableConfigurationProperties

> 让配置类自动进行属性绑定

1. 后面指定对应的配置类的class
2. 可以和条件编译结合

### @Profile

> 指定生产环境和开发环境分别运行什么方法

1. dev为开发,prod为生产
2. 通过在配置文件等指定当前是开发还是生产环境(用再学)

# 原理解释

## Bean

1. springboot帮我们配置了很多bean,当我们自己没有配时会使用默认的bean
2. bean能够帮我们以更小耦合度获取对应的对象
3. bean的名称默认是类或者方法首字母小写

## Spring.factories

> springboot启动后会解析classpath下的meta-inf文件夹,然后解析这个文件,然后会自动加载这些自动配置

## 属性绑定

> application.properties位于resources文件夹下

1. 在项目的工程二级文件夹下定义一个类MyProperties

   * 在里面放需要共享绑定的属性,然后添加get和set方法
   * 类名上添加@ConfigurationProperties注解,@Component
   * 然后使用时就可以通过@AutoWired注解自动注入属性
   * 然后使用的时候直接点出来get就好

2. 配置文件还支持application.yml

   * 前缀可以合并,形成层级结构,前缀之间用冒号隔开

     ```yml
     spring:
     	datasource:
     		url:jdbc:mysql://127.0.0.1:3306/
     ```

   * properties优先级大于yml

# 框架整合

## JDBCTemplate

1. 引入依赖

   ```xml
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-jdbc</artifactId>
   </dependency>
   <dependency>
       <groupId>mysql</groupId>
       <artifactId>mysql-connector-java</artifactId>
       <version>8.0.28</version>
   </dependency>
   ```

2. 然后在需要执行sql的地方,定义变量,@Autowired,就可以用了

3. 需要在application.properties内部配置数据库连接等属性

## Mybatis

1. 引入依赖

   ```xml
   <!--mybatis依赖-->
   <dependency>
       <groupId>org.mybatis.spring.boot</groupId>
       <artifactId>mybatis-spring-boot-starter</artifactId>
       <version>2.2.2</version>
   </dependency>
   <!--mysql依赖-->
   <dependency>
       <groupId>mysql</groupId>
       <artifactId>mysql-connector-java</artifactId>
       <version>8.0.28</version>
   </dependency>
   ```

2. 编写mapper,可能还要编写自己的实体类,**学一下**



# 错误处理

## 新建maven项目报错

> Unresolved plugin:'org.apache.maven.plugins:maven-jar-plugin:2.4'

1. 解决方法:在pom.xml插入如下代码并且等待(我也不知道为啥这样)

   ```xml
   <dependencies>
         <dependency>
               <groupId>org.apache.maven.plugins</groupId>
               <artifactId>maven-install-plugin</artifactId>
               <version>2.4</version>
               <type>maven-plugin</type>
           </dependency>
   
           <dependency>
               <groupId>org.apache.maven.plugins</groupId>
               <artifactId>maven-deploy-plugin</artifactId>
               <version>2.7</version>
               <type>maven-plugin</type>
           </dependency>
   
           <dependency>
               <groupId>org.apache.maven.plugins</groupId>
               <artifactId>maven-surefire-plugin</artifactId>
               <version>2.12.4</version>
               <type>maven-plugin</type>
           </dependency>
   
           <dependency>
               <groupId>org.apache.maven.plugins</groupId>
               <artifactId>maven-site-plugin</artifactId>
               <version>3.3</version>
               <type>maven-plugin</type>
           </dependency>
   </dependencies>
   ```

## Tomcat端口占用

> The Tomcat connector configured to listen on port 8080 failed to start. The port may already be in use or the connector may be misconfigured.

1. 出现

   使用springCLI测试安装是否成功报错

2. 解决

   ```cmd
   netstat -aon|findstr 8080
   ```

   ![image-20220423155738540](https://user-images.githubusercontent.com/73418254/167250587-b936705e-481d-48f2-9437-ee07bb7816d1.png)


   ```cmd
   taskkill /f /t /im 12144
   ```

   ![image-20220423155832956](https://user-images.githubusercontent.com/73418254/167250589-c557fe06-1c78-4135-8fa4-98ac204ef411.png)

