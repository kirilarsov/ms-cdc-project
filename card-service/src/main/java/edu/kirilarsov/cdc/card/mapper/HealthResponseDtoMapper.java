package edu.kirilarsov.cdc.card.mapper;

import edu.kirilarsov.cdc.card.controller.dto.HealthResponseDto;
import edu.kirilarsov.cdc.card.service.dto.HealthDto;
import org.mapstruct.Mapper;

/**
 * HealthResponseDtoMapper for mapping health related objects.
 */
@Mapper(componentModel = "spring")
public interface HealthResponseDtoMapper {

    HealthResponseDto mapToHealthResponseDto(HealthDto healthDto);

}
