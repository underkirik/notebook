package org.example;

public interface Notebook {

  void init();

  void save();

  void add(Contact contact);

  void show();

  void delete(String email);

  boolean isExistInNotebook(String email);

}
