package me.dio.diolabsantanderdevweek.controller.dto;

import me.dio.diolabsantanderdevweek.model.News;

public record NewsDTO (
        Long id,
        String icon,
        String description
) {
    public NewsDTO(News news) {
        this(news.getId(), news.getIcon(), news.getDescription());
    }

    public News toModel() {
        News model = new News();
        model.setId(id);
        model.setIcon(icon);
        model.setDescription(description);
        return model;
    }
}
