package com.rewards.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Entity
public class Reward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique reward identifier", example = "1")
    public Long id;

    @Schema(description = "Reward name", example = "Amazon Voucher")
    public String name;

    @Schema(description = "Points required for the reward", example = "500")
    public Integer points;
}