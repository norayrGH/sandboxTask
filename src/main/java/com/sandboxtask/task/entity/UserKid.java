package com.sandboxtask.task.entity;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Setter;
import org.hibernate.annotations.Type;


@Table(name = "send_box_user_kid")
@Entity
@Setter
public class UserKid {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;


  @Type(type = "pg-uuid")
  @Column( name = "uuid")
  private UUID uuid;

  @ManyToOne(fetch = FetchType.LAZY)
  private User user;


}
