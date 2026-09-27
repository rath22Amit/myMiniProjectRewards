package com.rewards.exception;

import com.rewards.dto.ErrorResponse.ErrorResponse;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class RewardExceptionMapper
        implements ExceptionMapper<RewardNotFoundException> {

    @Override
    public Response toResponse(RewardNotFoundException exception) {

        ErrorResponse error = new ErrorResponse(
                404,
                exception.getMessage()
        );

        return Response.status(Response.Status.NOT_FOUND)
                .type(MediaType.APPLICATION_JSON)
                .entity(error)
                .build();
    }
}