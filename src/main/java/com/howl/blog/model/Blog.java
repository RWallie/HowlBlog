package com.howl.blog.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
  
  @Column(name = "message", length = 2000, nullable = false)
  private String message;

}
