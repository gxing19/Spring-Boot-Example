# spring-boot-template 说明
这是一个基于Spring Boot 2.0.4 Release 版本的快速开发模板,尽量采用 boot-starter 依赖, 使用最精简的配置。

此模板集成了 MySQL, Druid, Mybatis, Common Mapper, pageHelper, Log4j2, Shiro, 支持打 war 包, 支持打 Docker 镜像。

## 项目描述
1. 如果是接口数据,建议统一返回 ResultBean 对象。若是分页查询,将查询数据装到 PageInfo 里, pageInfo 对象封装到 ResultBean 的 data 属性中。
2. 将 java 配置文件和组件分开存放,让项目结构更清晰。
3. 实体类
    - 所有实体类建议都实现序列化接口, 生成序列化版本号。
    - 实体类 entity 包下的 base 目录里有个实体类的基类 BaseEntity , 基类里面包含 id,pageNum,pageSize三个公用属性,
其中 pageNum(页码) 和 pageSize(每页显示条数) 是分页参数, 这两个属性不参与持久化。
    - 让实体类继承 BaseEntity 基类, 在 properties 配置文件添加支持方法参数分页配置(pagehelper.supportMethodsArguments=true),
在实体类作为查询参数时就会根据 pageNum 和 pageSize这两个属性执行自动分页, 可以省略查询方法前的 PageHelper.startPage(pageNum, pageSize) 设置。
    - 若查询参数和和分页参数不封装在实体类里, 是直接作为方法参数传递, 在执行查询方法前需要设置分页参数(PageHelper.startPage(pageNum, pageSize))。
    - 通用 Mapper 默认是根据实体类名首字母小写来找对应的表,若实体类名与表名无法对应,可在实体类上添加注解(@Table(name = "table_name"))来映射表名。
    - 通用 Mapper 默认支持下划线和驼峰规则转换,若属性名与表字段无法匹配, 可在属性上添加注解(@Column(name = "column_name"))来映射表字段。
4. MyBatis
    - 在配置文件开启了对实体类别名支持, 别名默认是实体类名首字母小写,在 XML 接收实体类参数和返回实体类时,可直接写别名而不用写全限定实体类名。
    - 开启了实体类属性名驼峰规则和下划线的转换, 若转换后可对应表字段, 在 XML 里的字段和属性映射可省略。
    - MyBatis 的自动配置默认开启了 Mapper 级别(二级)缓存的支持, 若需要使用缓存, 可在业务 mapper 接口上添加缓存引用注解 @CacheNamespaceRef(XxxMapper.class),
        在 mapper.xml 文件中添加缓存配置 <cache />, 有多个属性支持详细配置,例如 flushInterval 设置缓存刷新间隔,单位是毫秒。
5. Mapper
    - 在 mapper 包下的 base 目录里, 有个 BaseMapper<T> 基础接口, 此接口继承了通用 mapper 中的 Mapper<T>, MySqlMapper<T> 两个接口, 
        此基础接口不能被 Spring 扫描到,否则会报错, 此项目配置扫描 mapper 包时设置了 markerInterface 参数, 即只扫描该参数值的父级包下的 mapper 接口文件。


## 项目改造