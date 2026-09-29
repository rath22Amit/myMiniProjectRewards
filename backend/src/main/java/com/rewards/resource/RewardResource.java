package com.rewards.resource;

import com.rewards.entity.Reward;
import com.rewards.service.RewardService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import com.rewards.dto.RewardRequest;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.DELETE;
import jakarta.validation.Valid;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;

@Path("/api/rewards")
@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Rewards", description = "Reward management APIs")
public class RewardResource {

    private final RewardService rewardService;

    public RewardResource(RewardService rewardService) {
        this.rewardService = rewardService;
    }

    @GET
    @Operation(summary = "Get all rewards",
            description = "Returns all rewards")
    @APIResponse(
            responseCode = "200",
            description = "Rewards retrieved successfully"
    )
    @APIResponse(
            responseCode = "404",
            description = "Reward not found"
    )
    public List<Reward> getRewards() {
        return rewardService.getAllRewards();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Get reward by ID",
            description = "Returns a reward for the specified ID")
    public Response getRewardById(@PathParam("id") Long id) {

        Reward reward = rewardService.getRewardById(id);

        if (reward == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .build();
        }

        return Response.ok(reward).build();
    }

    @POST
    @Operation(
            summary = "Create reward",
            description = "Creates a new reward"
    )
    @APIResponse(
            responseCode = "201",
            description = "Reward created successfully"
    )
    @APIResponse(
            responseCode = "400",
            description = "Invalid reward data"
    )
    public Response createReward(@Valid RewardRequest request) {

        Reward reward = rewardService.createReward(request);

        return Response.status(Response.Status.CREATED)
                .entity(reward)
                .build();
    }

    @PUT
    @Path("/{id}")
    @Operation(
            summary = "Update reward",
            description = "Updates an existing reward"
    )
    @APIResponse(
            responseCode = "200",
            description = "Reward updated successfully"
    )
    @APIResponse(
            responseCode = "400",
            description = "Invalid reward data"
    )
    @APIResponse(
            responseCode = "404",
            description = "Reward not found"
    )
    public Response updateReward(
            @PathParam("id") Long id,
            @Valid RewardRequest request) {

        Reward reward = rewardService.updateReward(id, request);

        return Response.ok(reward).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(
            summary = "Delete reward",
            description = "Deletes an existing reward"
    )
    @APIResponse(
            responseCode = "204",
            description = "Reward deleted successfully"
    )
    @APIResponse(
            responseCode = "404",
            description = "Reward not found"
    )
    public Response deleteReward(@PathParam("id") Long id) {

        rewardService.deleteReward(id);

        return Response.noContent().build();
    }
}