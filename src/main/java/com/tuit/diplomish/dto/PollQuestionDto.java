package com.tuit.diplomish.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PollQuestionDto {

    @JsonProperty("chat_id")
    private String chatId;

    @JsonProperty("question")
    private String question;

    @JsonProperty("options")
    private List<String> options;

    @JsonProperty("type")
    private String type = "quiz";

    @JsonProperty("correct_option_id")
    private Integer correctOptionId;

    @JsonProperty("is_anonymous")
    private Boolean isAnonymous;

}
