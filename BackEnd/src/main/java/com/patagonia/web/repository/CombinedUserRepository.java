package com.patagonia.web.repository;

import com.patagonia.web.entity.view.CombinedUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CombinedUserRepository extends JpaRepository<CombinedUser, Long>, JpaSpecificationExecutor<CombinedUser> {

    Optional<CombinedUser> findByUsername(String username);



}
