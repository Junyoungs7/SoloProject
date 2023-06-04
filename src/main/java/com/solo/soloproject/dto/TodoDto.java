package com.solo.soloproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TodoDto {

    @Getter
    @AllArgsConstructor
    public static class post {

        private long id;

        @NotBlank(message = "할 일을 작성해주셔야 합니다.")
        private String title;

        @NotNull(message = "숫자만 입력해주세요.")
        private int order;

        @NotNull
        private boolean completed;
    }

    @Getter
    @AllArgsConstructor
    public static class patch {

        private long id;

        @NotBlank(message = "할 일을 작성해주셔야 합니다.")
        private String title;

        @NotNull(message = "숫자만 입력해주세요.")
        private int order;

        @NotNull
        private boolean completed;
    }

    @Getter
    @AllArgsConstructor
    public static class response{
        private Long id;
        private String title;
        private int order;
        private boolean completed;
    }

}
