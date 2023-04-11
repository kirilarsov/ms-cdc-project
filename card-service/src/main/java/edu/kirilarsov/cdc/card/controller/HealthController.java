package edu.kirilarsov.cdc.card.controller;

import edu.kirilarsov.cdc.card.controller.dto.HealthResponseDto;
import edu.kirilarsov.cdc.card.exception.RestApiErrorResponse;
import edu.kirilarsov.cdc.card.mapper.HealthResponseDtoMapper;
import edu.kirilarsov.cdc.card.service.HealthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * HealthController for performing service health check.
 */
@Validated
@RestController
@RequestMapping("/health")
@Tag(name = "Maintenance")
public class HealthController {

    private final HealthService healthService;

    private final HealthResponseDtoMapper healthResponseDtoMapper;

    public HealthController(
        HealthService healthService,
        HealthResponseDtoMapper healthResponseDtoMapper
    ) {
        this.healthService = healthService;
        this.healthResponseDtoMapper = healthResponseDtoMapper;
    }

    /**
     * Get service health.
     *
     * @return health response dto.
     */
    @Operation(summary = "Check the current health status of the service")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Service is healthy",
            content = @Content(schema = @Schema(implementation = HealthResponseDto.class))),
        @ApiResponse(responseCode = "503", description = "Service is unhealthy",
            content = @Content(schema = @Schema(implementation = RestApiErrorResponse.class)))
    })
    @GetMapping
    public ResponseEntity<HealthResponseDto> healthCheck() {
        var healthDto = healthService.health();

        var healthResponseDto = healthResponseDtoMapper.mapToHealthResponseDto(healthDto);

        var status = healthDto.isHealthy() ? HttpStatus.OK : HttpStatus.SERVICE_UNAVAILABLE;
        return ResponseEntity.status(status).body(healthResponseDto);
    }

    /**
     * Get service auth health.
     */
    @Operation(summary = "Check authorization is valid and service responsive")
    @ApiResponse(responseCode = "200", description = "Service auth is healthy and authorization is valid")
    @GetMapping("/auth")
    public void auth() {
        // OK
    }

}
