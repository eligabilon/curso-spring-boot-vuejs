package br.com.brunobrasildev.aplicacao.service;

import br.com.brunobrasildev.aplicacao.dto.UserDTO;
import br.com.brunobrasildev.aplicacao.entity.User;
import br.com.brunobrasildev.aplicacao.repository.UserRepository;
import br.com.brunobrasildev.aplicacao.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;

    public Page<UserDTO> list(Pageable pageable) {
        Page<User> userPage = userRepository.findAll(pageable);
        int totalElements = userPage.getNumberOfElements();

        return userMapper.userPageToUserDTOPage(userPage, pageable, totalElements);
    }

    @Transactional
    public UserDTO save(UserDTO userDTO) {
        User user = userMapper.userDTOToUser(userDTO);
        user = userRepository.save(user);

        return userMapper.userToUserDTO(user);
    }

    public UserDTO byId(Long id) {
        User user = userRepository.getOne(id);
        return userMapper.userToUserDTO(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public UserDTO byLogin(String name) {
        User user = userRepository.findByLogin(name);
        return userMapper.userToUserDTO(user);
    }
}
