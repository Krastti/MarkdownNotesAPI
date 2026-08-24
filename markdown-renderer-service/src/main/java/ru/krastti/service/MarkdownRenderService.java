package ru.krastti.service;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import org.springframework.stereotype.Service;

@Service
public class MarkdownRenderService {

    private final Parser parser;
    private final HtmlRenderer htmlRenderer;

    public MarkdownRenderService(Parser parser, HtmlRenderer htmlRenderer) {
        this.parser = parser;
        this.htmlRenderer = htmlRenderer;
    }

    public String renderToHtml(String markdown) {
        if (markdown == null || markdown.isEmpty()) {
            return "";
        }

        Node document = parser.parse(markdown);
        return htmlRenderer.render(document);
    }
}
