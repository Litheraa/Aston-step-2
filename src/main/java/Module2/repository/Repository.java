package Module2.repository;

import java.util.List;
import java.util.Optional;

public interface Repository {
	boolean deleteUser(long id);
	boolean updateUser(User user);
	List<User> findAllUsers();
	Optional<User> getUserById(long id);
	long createUser(User user);
	void exit();
}
