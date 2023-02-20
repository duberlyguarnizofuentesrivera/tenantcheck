package com.duberlyguarnizo.tenantcheck.loggeduser;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LoggedUserDTOMapper {
    LoggedUserDTO loggedUserToLoggedUserDTO(LoggedUser user);

    LoggedUser loggedUserDTOToLoggedUser(LoggedUserDTO userDTO);
}
