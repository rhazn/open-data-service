package org.jvalue.ods.coreservice.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jvalue.ods.coreservice.model.notification.NotificationConfig;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
public class PipelineConfig implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") // referenced by embedded adapter config for format and protocol
    private Long id;

    @NotNull
    private Long datasourceId;

    @Embedded
    private TransformationConfig transformation;

    @Embedded @NotNull
    private PipelineMetadata metadata;

    @OneToMany(cascade = CascadeType.ALL)
    private List<NotificationConfig> notifications;

    //Constructor for JPA
    public PipelineConfig() {}

    @JsonCreator
    public PipelineConfig(@JsonProperty("datasourceId")  Long datasourceId,
    @JsonProperty("transformation") TransformationConfig transformation,
      @JsonProperty("metadata") PipelineMetadata metadata,
      @JsonProperty("notifications") List<NotificationConfig> notifications) {
        this.datasourceId = datasourceId;
        this.transformation = transformation;
        this.metadata = metadata;
        this.notifications = notifications;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDatasourceId() {
      return datasourceId;
    }

    public void setDatasourceId(Long datasourceId) {
      this.datasourceId = datasourceId;
    }

    public TransformationConfig getTransformation() {
        return transformation;
    }

    public void setTransformation(TransformationConfig transformation) {
        this.transformation = transformation;
    }

    public PipelineMetadata getMetadata() {
        return metadata;
    }

    public List<NotificationConfig> getNotifications() {
        return notifications;
    }

    public void addNotification(NotificationConfig notification) {
        this.notifications.add(notification);
    }

    public void removeNotification(NotificationConfig notification) {
        this.notifications.remove(notification);
    }

    @Override
    public String toString() {
        return "PipelineConfig{" +
          "id=" + id +
          ", datasourceId=" + datasourceId +
          ", transformation=" + transformation +
          ", metadata=" + metadata +
          ", notifications=" + notifications +
          '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PipelineConfig)) return false;
        PipelineConfig that = (PipelineConfig) o;
        return Objects.equals(id, that.id) &&
          Objects.equals(datasourceId, that.datasourceId) &&
          Objects.equals(transformation, that.transformation) &&
          Objects.equals(metadata, that.metadata) &&
          Objects.equals(notifications, that.notifications);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, datasourceId, transformation, metadata, notifications);
    }
}
