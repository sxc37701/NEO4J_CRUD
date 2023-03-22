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

@NodeEntity(label = "Person")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
  @Id
  private Long id;

  @Relationship(type = "DIRECTED")
  @Property(name = "Director")
  private String director;

  @Relationship(type = "ACTED_IN")
  @Property(name = "Actors")
  private String actors;
}
