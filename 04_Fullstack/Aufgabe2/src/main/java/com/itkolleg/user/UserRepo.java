package com.itkolleg.user;

import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Integer> {
    public Long countById(Integer id);
}
