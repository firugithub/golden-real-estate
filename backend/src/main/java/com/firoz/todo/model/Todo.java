package com.firoz.todo.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Todo {

  @Id @GeneratedValue
  private long id;
  private String name;
  private String building;
  private String assigned;
  private boolean completed;
  private Date date;


}
