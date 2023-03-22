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

@NodeEntity(label = "Movie")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

  @Id
  @Property(name = "Ids")
  private Long ids;

  @Property(name = "Title")
  private String title;

  @Property(name = "Description")
  private String description;

  @Property(name = "Year")
  private Long year;

  @Property(name = "Runtime (Minutes)")
  private Long runTime;

  @Property(name = "Votes")
  private Long votes;

  @Property(name = "Rating")
  private Long rating;

  @Property(name = "Revenue (Millions)")
  private Long revenue;

  @Relationship(type="ACTED_IN", direction = Relationship.INCOMING)
  private String actors;

  @Relationship(type="DIRECTED", direction = Relationship.INCOMING)
  private String director ;

  @Relationship(type="IN")
  private String genre;

}
