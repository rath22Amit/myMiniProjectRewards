package com.rewards.repository;

import com.rewards.entity.Reward;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RewardRepository implements PanacheRepository<Reward> {
}