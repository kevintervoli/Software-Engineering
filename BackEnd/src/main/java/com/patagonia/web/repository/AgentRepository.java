package com.patagonia.web.repository;

import com.patagonia.web.entity.Admin;
import com.patagonia.web.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface AgentRepository extends JpaRepository<Agent, Long>, JpaSpecificationExecutor<Agent> {

    Optional<Agent> findByUsername(String username);

}
