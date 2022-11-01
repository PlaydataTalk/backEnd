package pdt.dto;

import lombok.*;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PostDTO {

	private Long postId;

	private Long userId;

	private String text;

	private String createDate;

	private String keyword;

}