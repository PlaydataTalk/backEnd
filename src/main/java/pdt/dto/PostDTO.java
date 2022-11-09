package pdt.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PostDTO {

	private Long postId;

	private UserDTO userId;

	private String text;

	private String keyword;
	
	private String keyword1;
	
	private String keyword2;
	
	private String keyword3;
	
	

}