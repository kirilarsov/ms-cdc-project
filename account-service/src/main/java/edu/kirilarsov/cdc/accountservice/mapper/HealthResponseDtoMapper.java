package edu.kirilarsov.cdc.accountservice.mapper;

import edu.kirilarsov.cdc.accountservice.controller.dto.HealthResponseDto;
import edu.kirilarsov.cdc.accountservice.service.dto.HealthDto;
import org.mapstruct.Mapper;

/**
 * HealthResponseDtoMapper for mapping health related objects.
 */
@Mapper(componentModel = "spring")
public interface HealthResponseDtoMapper {

    HealthResponseDto mapToHealthResponseDto(HealthDto healthDto);

}
