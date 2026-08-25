package ru.krastti.client;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import org.springframework.web.multipart.MultipartFile;
import ru.krastti.entity.dto.request.CreateNoteRequest;
import ru.krastti.entity.dto.NoteDTO;
import ru.krastti.entity.dto.NotesContainerDto;
import ru.krastti.entity.dto.request.UpdateNoteRequest;

import java.util.List;

@Component
public class NotesApiClient {

    private final RestClient restClient;

    public NotesApiClient(RestClient notesApiRestClient) {
        this.restClient = notesApiRestClient;
    }

    public List<NoteDTO> findAll() {
        NotesContainerDto container = restClient.get()
                .uri("/api/notes")
                .retrieve()
                .body(NotesContainerDto.class);
        return container != null ? container.notes() : List.of();
    }

    public NoteDTO findById(int id) {
        return restClient.get()
                .uri("/api/notes/{id}", id)
                .retrieve()
                .body(NoteDTO.class);
    }

    public NoteDTO create(CreateNoteRequest request) {
        return restClient.post()
                .uri("/api/notes")
                .contentType(MediaType.APPLICATION_JSON)
                .body(request)
                .retrieve()
                .body(NoteDTO.class);
    }

    public NoteDTO update(int id, UpdateNoteRequest request) {
        return restClient.put()
                .uri("/api/notes/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .body(request)
                .retrieve()
                .body(NoteDTO.class);
    }

    public void delete(int id) {
        restClient.delete()
                .uri("/api/notes/{id}", id)
                .retrieve()
                .toBodilessEntity();
    }

    public NoteDTO upload(MultipartFile file, String title) {
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", file.getResource());
        body.add("title", title);

        return restClient.post()
                .uri("/api/notes/upload")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(body)
                .retrieve()
                .body(NoteDTO.class);
    }

    public String render(int id) {
        return restClient.post()
                .uri("/api/notes/{id}/render", id)
                .retrieve()
                .body(String.class);
    }
}