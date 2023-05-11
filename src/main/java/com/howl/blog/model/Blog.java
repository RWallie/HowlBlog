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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="BLOGS")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Blog {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "author", nullable = false)
  private String author;

  @Column(name = "category", nullable = false)
  private String category;
  
  @Column(name = "text", length = 2000, nullable = false)
  private String text;

  @Column(name = "publish_date", nullable = false)
  @Temporal(TemporalType.DATE)
  private Date publishDate;
  
  @Column(name = "publish_time", nullable = false)
  @Temporal(TemporalType.TIME)
  private Time publishTime;
}
