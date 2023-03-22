package com.advDB.neoCrud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;
import org.springframework.data.neo4j.core.schema.Id;


/**
 * @author Sravanti Cherukuri
 * #700743770
 * @Date 21-03-2023
 */

@NodeEntity(label = "Genre")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Genre {


  @Id
  private Long id;

  @Relationship(type = "IN",direction = Relationship.INCOMING)
  @Property(name = "Genre")
  private String genre;
}
