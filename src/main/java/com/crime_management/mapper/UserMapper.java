package com.crime_management.mapper;

import com.crime_management.dto.user.UserDTO;
import com.crime_management.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = GlobalMapperConfig.class)
public interface UserMapper {

//    @Mapping(target = "active", source = "isActive")
    @Mapping(target = "roles", expression = "java(user.getRoles().stream().map(r -> r.getName()).collect(java.util.stream.Collectors.toSet()))")
    UserDTO toDto(User user);
}
