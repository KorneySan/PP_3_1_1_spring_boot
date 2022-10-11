package korneysan.springboot.service;

import korneysan.springboot.model.User;
import korneysan.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void create(User user) {
        userRepository.save(user);
    }

    @Transactional
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    @Transactional
    public void update(long id, User user) {
        user.setId(id);
        userRepository.save(user);
    }

    public User show(long id) {
        return userRepository.findById(id).orElse(new User());
    }

    public Iterable<User> getList() {
        return userRepository.findAll();
    }
}
