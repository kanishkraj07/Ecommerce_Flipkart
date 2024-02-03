package com.flipkartservice.productservice.category;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CategoryPanel {
    private UUID panelId;
    private String panelName;

    private boolean miniPanel;

    public CategoryPanel() {
    }
    public CategoryPanel(Builder builder) {
        this.panelId = builder.panelId;
        this.panelName = builder.panelName;
        this.miniPanel = builder.miniPanel;
    }

    public CategoryPanel(UUID panelId, String panelName, boolean miniPanel) {
        this.panelId = panelId;
        this.panelName = panelName;
        this.miniPanel = miniPanel;
    }

    public UUID getPanelId() {
        return panelId;
    }

    public void setPanelId(UUID panelId) {
        this.panelId = panelId;
    }

    public String getPanelName() {
        return panelName;
    }

    public void setPanelName(String panelName) {
        this.panelName = panelName;
    }

    public boolean isMiniPanel() {
        return miniPanel;
    }

    public CategoryPanel setMiniPanel(boolean miniPanel) {
        this.miniPanel = miniPanel;
        return this;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private UUID panelId;
        private String panelName;
        private boolean miniPanel;
        public Builder setPanelId(UUID panelId) {
            this.panelId = panelId;
            return this;
        }

        public Builder setPanelName(String panelName) {
            this.panelName = panelName;
            return this;
        }

        public Builder setMiniPanel(boolean miniPanel) {
            this.miniPanel = miniPanel;
            return this;
        }
        public CategoryPanel build() {
            return new CategoryPanel(this);
        }
    }
}
