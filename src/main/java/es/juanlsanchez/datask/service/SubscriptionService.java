package es.juanlsanchez.datask.service;

import java.util.Optional;

import es.juanlsanchez.datask.domain.Subscription;

public interface SubscriptionService {

  public Optional<Subscription> findOne(Long subscriptionId);

  public Subscription getOneById(Long subscriptionId);

}
