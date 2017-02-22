package com.teamtreehouse.techdegrees.exc;

/**
 * Created by GoranB on 2017-02-20.
 */
public class DaoException extends Exception {
  private final Exception originalException;

  public DaoException(Exception originalException, String msg){
    //konstruktor super klase tj klase Exception
    super(msg);
    this.originalException = originalException;
  }
}
