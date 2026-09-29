package com.rewards.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(description = "Request object used to create or update a reward")
public class RewardRequest {

    @Schema(
            description = "Name of the reward",
            example = "Amazon Voucher"
    )
    @NotBlank(message = "Reward name is required")
    @Size(
            min = 3,
            max = 100,
            message = "Reward name must be between 3 and 100 characters"
    )
    public String name;

    @Schema(
            description = "Number of points required for the reward",
            example = "500"
    )
    @NotNull(message = "Points are required")
    @Min(value = 1, message = "Points must be greater than 0")
    public Integer points;
}