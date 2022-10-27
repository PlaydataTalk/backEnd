package pdt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class IlikeDTO {
	
	private Long likeId;
	
	private String userId;
	
	private Long postId;

}
