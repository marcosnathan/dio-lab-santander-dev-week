package me.dio.diolabsantanderdevweek.controller.dto;

import me.dio.diolabsantanderdevweek.model.Feature;

public record FeatureDTO(Long id, String icon, String description) {
    public FeatureDTO(Feature feature) {
        this(feature.getId(), feature.getIcon(), feature.getDescription());
    }

    public Feature toModel() {
        Feature model = new Feature();
        model.setId(id);
        model.setIcon(icon);
        model.setDescription(description);
        return model;
    }
}
