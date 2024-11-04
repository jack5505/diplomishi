package com.tuit.diplomish.mapper;

import com.tuit.diplomish.dao.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.telegram.telegrambots.meta.api.objects.Contact;

@Mapper(componentModel = "spring")
public interface ContactToUserDtoMapper {


    @Mapping(target = "userId",source = "userId")
    @Mapping(target = "phone",source = "phoneNumber")
    UserDTO toUserDto(Contact contact);
}
