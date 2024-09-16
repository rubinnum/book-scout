package com.bookscout.backend.utilities;

import java.util.List;

public class Helper {

    private static final List<String> subjectsList = List.of(
            "antiques", "architecture", "art", "bibles", "biography", "body",
            "business", "comics", "computers", "cooking", "crafts", "crime", "design",
            "drama", "education", "family", "fiction", "games", "gardening", "health",
            "history", "house", "humor", "juvenile", "law", "literary", "mathematics",
            "medical", "music", "nature", "pets", "philosophy", "photography", "poetry",
            "political", "psychology", "reference", "religion", "science", "social",
            "sports", "technology", "transportation", "travel", "young"
    );

    public static boolean isValidSubject(String subject) {
        return (subjectsList.contains(subject));
    }
}
