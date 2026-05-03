package ru.practicum.dto.comment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class CommentDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long id;
    private String text;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long eventId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long userId;

    private String created;

    private String status;
}
