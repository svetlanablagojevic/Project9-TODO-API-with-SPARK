package com.teamtreehouse.techdegrees.dao;

import com.teamtreehouse.techdegrees.exc.DaoException;
import com.teamtreehouse.techdegrees.model.Todo;

import java.util.List;

/**
 * Created by GoranB on 2017-02-20.
 */
public interface TodoDao {
  void add(Todo todo) throws DaoException;
  void update (Todo todo) throws DaoException;
  void delete (int id) throws DaoException;

  List<Todo> findAll();

  Todo findById(int id);
}
