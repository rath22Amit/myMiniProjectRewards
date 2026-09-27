package com.rewards.service;

import com.rewards.dto.RewardRequest;
import com.rewards.entity.Reward;
import com.rewards.exception.RewardNotFoundException;
import com.rewards.repository.RewardRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class RewardService {

    private final RewardRepository rewardRepository;

    public RewardService(RewardRepository rewardRepository) {
        this.rewardRepository = rewardRepository;
    }

    // GET all rewards
    public List<Reward> getAllRewards() {
        return rewardRepository.listAll();
    }

    // GET reward by ID
    public Reward getRewardById(Long id) {

        Reward reward = rewardRepository.findById(id);

        if (reward == null) {
            throw new RewardNotFoundException(id);
        }

        return reward;
    }

    // CREATE reward
    @Transactional
    public Reward createReward(RewardRequest request) {

        Reward reward = new Reward();

        reward.name = request.name;
        reward.points = request.points;

        rewardRepository.persist(reward);

        return reward;
    }

    // UPDATE reward
    @Transactional
    public Reward updateReward(Long id, RewardRequest request) {

        Reward reward = rewardRepository.findById(id);

        if (reward == null) {
            throw new RewardNotFoundException(id);
        }

        reward.name = request.name;
        reward.points = request.points;

        return reward;
    }

    // DELETE reward
    @Transactional
    public void deleteReward(Long id) {

        Reward reward = rewardRepository.findById(id);

        if (reward == null) {
            throw new RewardNotFoundException(id);
        }

        rewardRepository.delete(reward);
    }
}