package com.socialnetwork.project.service;

import java.util.List;

public interface BaseService<T, I> {
    T create(T object);
    T readById(I id);
    T update(T object);
    T delete(I id);
    List<T> getAll();
}
