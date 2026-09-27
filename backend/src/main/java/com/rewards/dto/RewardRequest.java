package com.rewards.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RewardRequest {

    @NotBlank(message = "Reward name is required")
    @Size(min = 3, max = 100, message = "Reward name must be between 3 and 100 characters")
    public String name;

    @NotNull(message = "Points are required")
    @Min(value = 1, message = "Points must be greater than 0")
    public Integer points;
}