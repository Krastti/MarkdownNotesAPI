package ru.krastti.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import ru.krastti.client.GrammarCheckerClient;
import ru.krastti.entity.dto.NoteDTO;
import ru.krastti.entity.dto.responce.LanguageToolResponse;
import ru.krastti.entity.dto.result.GrammarCheckResult;
import ru.krastti.entity.dto.result.GrammarIssue;
import ru.krastti.exception.GrammarCheckException;

import java.util.List;

@Service
public class GrammarCheckerService {

    private final GrammarCheckerClient grammarCheckerClient;

    public GrammarCheckerService(GrammarCheckerClient grammarCheckerClient) {
        this.grammarCheckerClient = grammarCheckerClient;
    }

    public GrammarCheckResult checkNote(NoteDTO note, String language) {
        LanguageToolResponse response;

        try {
            response = grammarCheckerClient.grammarCheck(note.text(), language);
        } catch (RestClientException e) {
            throw new GrammarCheckException("Grammar check service is unavailable.", e);
        }

        List<GrammarIssue> issues = response.getMatches().stream()
                .map(match -> new GrammarIssue(
                        match.getMessage(),
                        match.getOffset(),
                        match.getLength(),
                        match.getReplacements().stream()
                                .map(LanguageToolResponse.Replacement::getValue)
                                .toList()
                ))
                .toList();

        return new GrammarCheckResult(response.getLanguage().getCode(), issues);
    }
}
