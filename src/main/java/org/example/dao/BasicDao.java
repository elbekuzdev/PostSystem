package org.example.dao;

import java.util.List;
import java.util.Optional;

public interface BasicDao<T> {

    boolean saveDB(T t);
    List<T> getAll();
    Optional<T> findById(Integer id);
    boolean deleteById(Integer id);
    boolean update(T t);



}
