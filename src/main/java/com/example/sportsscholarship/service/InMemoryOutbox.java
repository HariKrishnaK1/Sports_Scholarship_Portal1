package com.example.sportsscholarship.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class InMemoryOutbox {
    public static class MailEntry {
        public final String from;
        public final String to;
        public final String subject;
        public final String body;
        public final Instant timestamp;
        public MailEntry(String from, String to, String subject, String body, Instant timestamp) {
            this.from = from;
            this.to = to;
            this.subject = subject;
            this.body = body;
            this.timestamp = timestamp;
        }
    }

    private final List<MailEntry> messages = Collections.synchronizedList(new ArrayList<>());

    public void add(SimpleMailMessage msg) {
        String from = (msg.getFrom() != null ? msg.getFrom() : "");
        String[] tos = msg.getTo();
        String to = (tos != null && tos.length > 0 ? tos[0] : "");
        String subject = (msg.getSubject() != null ? msg.getSubject() : "");
        String body = (msg.getText() != null ? msg.getText() : "");
        messages.add(new MailEntry(from, to, subject, body, Instant.now()));
    }

    public List<MailEntry> list() {
        synchronized (messages) {
            return new ArrayList<>(messages);
        }
    }

    public void clear() {
        messages.clear();
    }
}
