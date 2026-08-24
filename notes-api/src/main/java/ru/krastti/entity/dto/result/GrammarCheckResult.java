package ru.krastti.entity.dto.result;

import java.util.List;

public record GrammarCheckResult(String languageCode, List<GrammarIssue> issues) {

    public boolean hasIssues() {
        return issues != null && !issues.isEmpty();
    }
}
