package com.springboot.example.rest.repository;

import com.springboot.example.rest.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/**
 * @ClassName: ActorRepository
 * @Description:
 * @User: gxing
 * @Date: 2018-05-29 09:51
 **/
@RepositoryRestResource(path = "actor")
public interface ActorRepository extends JpaRepository<Actor, Long> {

    /**
     * @RestResource注解常用属性
     * path: 映射路径
     * rel: 引用别名,不设置默认是方法名
     * exported: 两个值,不设置默认是true,设置为false时表示不作为Rest资源暴露;也可作用在类上
     */

    // 路径是方法名：http://localhost:8080/api/actor/search/findByFirstName?firstName=BOB
    Actor findByFirstName(@Param("firstName") String firstName);

    // 定制路径：http://localhost:8080/api/actor/search/firstNameStartsWith?firstName=NICK
    @RestResource(path = "firstNameStartsWith")
    List<Actor> findByFirstNameStartsWith(@Param("firstName") String firstName);


     //定制路径：http://localhost:8080/api/actor/search/firstNameStartsWith?firstName=NICK
    @RestResource(path = "findByLikeFirstName", rel = "likeFirstName")
    List<Actor> findByFirstNameContains(@Param("firstName") String firstName);

    //方法不暴露给Rest,引属性也可以类上使用,即整个类的方法都不暴露给Rest
    @RestResource(exported = false)
    Actor findByLastName(@Param("lastName") String lastName);

}