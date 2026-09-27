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

import java.util.List;

@Path("/api/rewards")
@Produces(MediaType.APPLICATION_JSON)
public class RewardResource {

    private final RewardService rewardService;

    public RewardResource(RewardService rewardService) {
        this.rewardService = rewardService;
    }

    @GET
    public List<Reward> getRewards() {
        return rewardService.getAllRewards();
    }

    @GET
    @Path("/{id}")
    public Response getRewardById(@PathParam("id") Long id) {

        Reward reward = rewardService.getRewardById(id);

        if (reward == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .build();
        }

        return Response.ok(reward).build();
    }

    @POST
    public Response createReward(@Valid RewardRequest request) {

        Reward reward = rewardService.createReward(request);

        return Response.status(Response.Status.CREATED)
                .entity(reward)
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response updateReward(
            @PathParam("id") Long id,
            @Valid RewardRequest request) {

        Reward reward = rewardService.updateReward(id, request);

        if (reward == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .build();
        }

        return Response.ok(reward).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteReward(@PathParam("id") Long id) {

        rewardService.deleteReward(id);

        return Response.noContent().build();
    }
}