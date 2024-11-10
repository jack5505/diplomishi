package com.tuit.diplomish.dao.service.kernel;

import java.util.Optional;

public interface CrudService <T> {
    T save(T entity);

    void delete(T entity);

    Optional<T> findById(Object id);

    void deleteById(Object id);
}
