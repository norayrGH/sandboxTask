package com.sandboxtask.task.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Setter;
import org.hibernate.annotations.Type;

@Table(name = "send_box_user")
@Entity
public class User {

  public User(){
    this.uuid = UUID.randomUUID();

  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Type(type = "pg-uuid")
  @Column( name = "uuid")
  private UUID uuid;

  @Column(name = "first_name")
  @Setter
  private String firstName;

  @Column(name = "last_name")
  @Setter
  private String lastName;

  @Column(name = "email_address")
  @Setter
  private String emailAddress;


}
