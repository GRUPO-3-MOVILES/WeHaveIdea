package com.roademics.platform.upcprep202402cc238wv61wehaveanideaapi.shared.domain.model.service.config;

public class RemoveSlashes {
    public static String remove(String input) {
        return input.replaceAll("/", "");
    }
}
