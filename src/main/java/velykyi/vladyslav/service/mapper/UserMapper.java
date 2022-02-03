package velykyi.vladyslav.service.mapper;

import org.mapstruct.Mapper;
import velykyi.vladyslav.dto.UserDto;
import velykyi.vladyslav.model.User;
import velykyi.vladyslav.model.impl.UserImpl;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto userToUserDto(User user);

    UserImpl userDtoToUser(UserDto userDto);
}
