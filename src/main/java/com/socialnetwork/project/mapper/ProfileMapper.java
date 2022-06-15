package com.socialnetwork.project.mapper;

import com.socialnetwork.project.dto.ProfileDTO;
import com.socialnetwork.project.dto.ProfileUpdateDTO;
import com.socialnetwork.project.dto.UserUpdateDTO;
import com.socialnetwork.project.entity.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(uses = {UserMapper.class})
public interface ProfileMapper {

    @Mapping(source = "userId", target = "user.id")
    Profile toEntity(ProfileUpdateDTO dto);
    @Mapping(source = "userId", target = "user.id")
    Profile toEntity(UserUpdateDTO dto);

    @Mappings({
        @Mapping(source = "user.id", target = "id"),
        @Mapping(source = "user.avatar", target = "avatar"),
        @Mapping(source = "user.name", target = "name"),
        @Mapping(source = "user.surname", target = "surname"),
        @Mapping(source = "user.username", target = "username"),
        @Mapping(source = "user.email", target = "email"),
        @Mapping(source = "user.phone", target = "phone"),
        @Mapping(source = "user.sex", target = "sex")
    })
    ProfileDTO toProfileDTO(Profile profile);
}
