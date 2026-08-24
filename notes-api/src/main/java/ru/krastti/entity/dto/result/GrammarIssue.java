package ru.krastti.entity.dto.result;

import java.util.List;

public record GrammarIssue (
        String message,
        int offset,
        int length,
        List<String> suggestions
) { }
