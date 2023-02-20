package com.duberlyguarnizo.tenantcheck.loggeduser;

import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
abstract class LoggedUserCreationDTOMapper {

    @BeforeMapping
    protected void transformRolesFromEnum(LoggedUser user, @MappingTarget LoggedUserCreationDTO userCreationDTO) {
        List<UserRole> userRoles = user.getRoles();
        List<String> userCreationDTORoles = new ArrayList<>();
        for (UserRole role : userRoles) {
            userCreationDTORoles.add(role.name());
        }
        userCreationDTO.setRoles(userCreationDTORoles);
    }

    @Mapping(target = "roles", source = "roles")
    public abstract LoggedUserCreationDTO loggedUserToLoggedUserCreationDTO(LoggedUser loggedUser);

    @Mapping(target = "roles", source = "roles")
    public abstract LoggedUser loggedUserCreationDTOToLoggedUser(LoggedUserCreationDTO userCreationDTO);
}
