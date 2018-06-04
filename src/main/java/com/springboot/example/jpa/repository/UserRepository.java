package com.springboot.example.jpa.repository;

import com.springboot.example.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
	
	//JPA SQL语句是 hql,是对对象的查询
	@Query(value="select u from User u where u.name= :name and u.address= :address")
	User queryByNameAndAddress(@Param("name") String name, @Param("address") String address);
}
