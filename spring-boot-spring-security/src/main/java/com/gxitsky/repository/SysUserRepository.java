package com.gxitsky.repository;

import com.gxitsky.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SysUserRepository extends JpaRepository<SysUser, Long> {
}
