package com.wielkopolan.gymscheduler.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GymResponseBody(
        @JsonProperty("IsLogged") boolean isLogged,
        @JsonProperty("Success") boolean success,
        @JsonProperty("ShowCaptcha") boolean showCaptcha,
        @JsonProperty("RedirectUrl") String redirectUrl,
        @JsonProperty("CodeMd5") String codeMd5,
        @JsonProperty("PictureString") String pictureString,
        @JsonProperty("PaymentRequired") boolean paymentRequired,
        @JsonProperty("PaymentInfo") String paymentInfo,
        @JsonProperty("ShowSubmitButtonWithErrors") boolean showSubmitButtonWithErrors,
        @JsonProperty("ClassID") int classId,
        @JsonProperty("ErrorMessage") String errorMessage,
        @JsonProperty("SuccessMessage") String successMessage
) {}