package com.bookscout.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookDTO {
    private VolumeInfo volumeInfo;

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class VolumeInfo {
        private String title;
        private List<String> authors;
        private String description;
        private Integer pageCount;
        private String publishedDate;
        private ImageLinks imageLinks;

        @Getter
        @Setter
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class ImageLinks {
            private String thumbnail;
        }
    }

}
