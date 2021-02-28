package dev.alexandrevieira.sm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.alexandrevieira.sm.domain.Broker;


@Repository
public interface BrokerRepository extends JpaRepository<Broker, Long>{

}
