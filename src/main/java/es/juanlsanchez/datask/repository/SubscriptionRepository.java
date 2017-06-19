package es.juanlsanchez.datask.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.juanlsanchez.datask.domain.Subscription;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

  public Optional<Subscription> findOneById(Long id);

}
