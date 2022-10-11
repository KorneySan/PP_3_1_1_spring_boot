package korneysan.springboot.repository;

import korneysan.springboot.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
