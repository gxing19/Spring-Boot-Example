package com.springboot.websecurity.repository;

import com.springboot.websecurity.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SysUserRepository extends JpaRepository<SysUser, Long> {
}
