package pdt.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class PostDTO {

	private Long postId;

	private Long userId;

	private String text;

	private String createDate;

	private String keyword;

}