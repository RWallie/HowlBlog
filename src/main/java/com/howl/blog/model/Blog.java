package com.howl.blog.model;

import java.sql.Date;
import java.sql.Time;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.GeneratedValue;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="BLOGS")
@Setter
@Getter
public class Blog {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;

  @Column
  private String title;

  @Column
  private String author;

  @Column
  private String category;
  
  @Column(length = 2000)
  private String text;

  @Temporal(TemporalType.DATE)
  private Date publishDate;

  @Temporal(TemporalType.TIME)
  private Time publishTime;
}
