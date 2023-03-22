package com.advDB.neoCrud.dto;

import lombok.Data;

/**
 * @author Sravanti Cherukuri
 * #700743770
 * @Date 21-03-2023
 */

@Data
public class MovieUpdateDto {
  private String title;
  private String description;
  private Long year;
  private Long runTime;
  private Long votes;
  private Long rating;
  private Long revenue;
}
