package edu.kirilarsov.cdc.transaction.mapper;

import edu.kirilarsov.cdc.transaction.controller.dto.HealthResponseDto;
import edu.kirilarsov.cdc.transaction.service.dto.HealthDto;
import org.mapstruct.Mapper;

/**
 * HealthResponseDtoMapper for mapping health related objects.
 */
@Mapper(componentModel = "spring")
public interface HealthResponseDtoMapper {

    HealthResponseDto mapToHealthResponseDto(HealthDto healthDto);

}
