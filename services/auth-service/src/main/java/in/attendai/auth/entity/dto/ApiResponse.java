package in.attendai.auth.entity.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse
{

    private boolean success;
    private int status;
    private String message;
    private LocalDateTime timestamp;
}
