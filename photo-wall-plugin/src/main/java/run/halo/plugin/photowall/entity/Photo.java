package run.halo.plugin.photowall.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import run.halo.app.extension.AbstractExtension;
import run.halo.app.extension.GVK;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@GVK(group = "photowall.halo.run",
        version = "v1alpha1",
        kind = "Photo",
        plural = "photos",
        singular = "photo")
public class Photo extends AbstractExtension {

    private PhotoSpec spec;

    @Data
    public static class PhotoSpec {
        private String title;
        private String description;
        private String imageUrl;
        private String thumbnailUrl;
        private Integer width;
        private Integer height;
        private Integer rotation;
        private Integer zIndex;
        private Boolean visible = true;
        private String link;
        private List<String> categories;
        private List<String> tags;
        private String mediaType = "image"; // image 或 video
    }
}
