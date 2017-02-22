package com.teamtreehouse.techdegree.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import com.teamtreehouse.techdegrees.dao.Sql2oTodoDao;
import com.teamtreehouse.techdegrees.model.Todo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

/**
 * Created by GoranB on 2017-02-21.
 */
public class Sql2oTodoDaoTest {

  private Sql2oTodoDao dao;
  private Connection conn;

  @Before
  public void setUp() throws Exception {
    String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/init.sql'";
    Sql2o sql2o = new Sql2o(connectionString, "", "");
    dao = new Sql2oTodoDao(sql2o);
    // Keep connection open through entire test so that it isn't wiped out.
    conn = sql2o.open();
  }

  @After
  public void tearDown() throws Exception {
    conn.close();
  }


  @Test
  public void addingTodoSetsId() throws Exception {
    Todo todo = newTestTodo();
    int originalTodoId = todo.getId();

    dao.add(todo);
    //originalniTodoId is 0
    assertNotEquals(originalTodoId, todo.getId());

  }

  @Test
  public void addedTodosAreReturnedFromFindAll() throws Exception {
    Todo todo = newTestTodo();
    dao.add(todo);
    assertEquals(1, dao.findAll().size());

  }

  @Test
  public void noTasksReturnsEmptyList() throws Exception {
    assertEquals(0, dao.findAll().size());
  }

  @Test
  public void existingTodoCanBeFoundById() throws Exception {

    Todo todo = newTestTodo();
    dao.add(todo);

    Todo foundTodo = dao.findById(todo.getId());

    assertEquals(todo, foundTodo);

  }

  @Test
  public void deletingTodoDeletesEntry() throws Exception {
    Todo todo = newTestTodo();
    int originalTodoId = todo.getId();

    dao.delete(todo.getId());

    assertEquals(null, dao.findById(originalTodoId));
  }


  @Test
  public void updatingTodoUpdatesNameAndCompletedStatus() throws Exception {
    // Arrange: create new todoTask
    Todo todo = newTestTodo();
    // add it to test db
    dao.add(todo);
    // change name of todoTask
    todo.setName("new name");

    // Act, and Assert
    // When update method is called
    dao.update(todo);

    // found todoTask should be equal to original todoTask
    Todo foundTodoTask = dao.findById(todo.getId());
    assertEquals(todo, foundTodoTask);
  }

  private Todo newTestTodo() {
   Todo  todo=new Todo("learn java");
    return todo;
  }
}