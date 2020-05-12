# http-client-helper

基于动态代理的http请求客户端
底层实现为OkHttp,通过jdk动态代理创建代理实例完成调用
通过JDK的SPI实现扩展
门面模式实现调用服务对象化
策略模式实现返回结果自定义
整合springcloud https://github.com/chiangzeon/http-client-helper-spring-boot-starter
类似Feign调用
通过FactoryBean完成对象在spring容器的注册
达到松耦合，不必再像从前需要写一大堆HttpClinet代码
