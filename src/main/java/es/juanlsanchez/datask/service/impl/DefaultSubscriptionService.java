package es.juanlsanchez.datask.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import es.juanlsanchez.datask.domain.Subscription;
import es.juanlsanchez.datask.repository.SubscriptionRepository;
import es.juanlsanchez.datask.service.SubscriptionService;

@Service
public class DefaultSubscriptionService implements SubscriptionService {

  private final SubscriptionRepository subscriptionRepository;

  public DefaultSubscriptionService(final SubscriptionRepository subscriptionRepository) {
    this.subscriptionRepository = subscriptionRepository;
  }

  @Override
  public Optional<Subscription> findOne(Long subscriptionId) {
    return this.subscriptionRepository.findOneById(subscriptionId);
  }

  @Override
  public Subscription getOneById(Long subscriptionId) {
    return this.findOne(subscriptionId)
        .orElseThrow(() -> new IllegalArgumentException("Not found subscription"));
  }

  @Override
  public Subscription getOneByIdWithoutCompany(Long subscriptionId) {
    Subscription result = this.getOneById(subscriptionId);
    if (result.getCompany() != null) {
      throw new IllegalArgumentException("The subscription cannot have a company");
    }
    return result;
  }

}
