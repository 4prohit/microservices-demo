package com.cg.rp.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.cg.rp.demo.domain.User;

@RepositoryRestResource(itemResourceRel = "user", collectionResourceRel = "users", path = "users")
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
