package me.dio.diolabsantanderdevweek.service.impl;

import me.dio.diolabsantanderdevweek.model.User;
import me.dio.diolabsantanderdevweek.repository.UserRepository;
import me.dio.diolabsantanderdevweek.service.UserService;
import me.dio.diolabsantanderdevweek.service.exception.BusinessException;
import me.dio.diolabsantanderdevweek.service.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Optional.ofNullable;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Transactional(readOnly = true)
    public User findById(Long id) {
        return this.userRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public User create(User user) {
        ofNullable(user).orElseThrow(() -> new IllegalArgumentException("User must not be null"));
        ofNullable(user.getAccount()).orElseThrow(() -> new IllegalArgumentException("User account must not be null"));
        ofNullable(user.getCard()).orElseThrow(() -> new IllegalArgumentException("User card must not be null"));

        if (this.userRepository.existsByAccountNumber(user.getAccount().getNumber())) {
            throw new BusinessException("This account number already exists");
        }

        if (this.userRepository.existsByCardNumber(user.getCard().getNumber())) {
            throw new BusinessException("This card number already exists");
        }
        return this.userRepository.save(user);
    }

    @Transactional
    @Override
    public User update(Long id, User user) {
        User userToUpdate = this.userRepository.findById(id)
                .orElseThrow(NotFoundException::new);

        userToUpdate.setAccount(user.getAccount());
        userToUpdate.setCard(user.getCard());
        userToUpdate.setCard(user.getCard());
        userToUpdate.setName(user.getName());
        userToUpdate.setNews(user.getNews());

        return this.userRepository.save(userToUpdate);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        User userToDelete = this.userRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        this.userRepository.delete(userToDelete);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }
}
