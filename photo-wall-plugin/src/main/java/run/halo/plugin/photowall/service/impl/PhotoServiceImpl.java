package run.halo.plugin.photowall.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import run.halo.plugin.photowall.entity.Photo;
import run.halo.plugin.photowall.repository.PhotoRepository;
import run.halo.plugin.photowall.service.PhotoService;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PhotoServiceImpl implements PhotoService {

    private final PhotoRepository photoRepository;

    @Override
    public Mono<Photo> create(Photo photo) {
        if (photo.getMetadata() == null) {
            photo.setMetadata(new Photo.Metadata());
        }
        if (photo.getMetadata().getName() == null) {
            photo.getMetadata().setName(UUID.randomUUID().toString());
        }
        photo.getMetadata().setCreationTimestamp(Instant.now());

        if (photo.getSpec().getRotation() == null) {
            photo.getSpec().setRotation((int)(Math.random() * 30) - 15);
        }
        if (photo.getSpec().getZIndex() == null) {
            photo.getSpec().setZIndex((int)(Math.random() * 10) + 1);
        }
        if (photo.getSpec().getVisible() == null) {
            photo.getSpec().setVisible(true);
        }
        if (photo.getSpec().getMediaType() == null) {
            photo.getSpec().setMediaType("image");
        }

        return photoRepository.create(photo);
    }

    @Override
    public Mono<Photo> update(String name, Photo photo) {
        return get(name).flatMap(existing -> {
            photo.getMetadata().setVersion(existing.getMetadata().getVersion());
            return photoRepository.update(photo);
        });
    }

    @Override
    public Mono<Photo> get(String name) {
        return photoRepository.get(name);
    }

    @Override
    public Mono<Void> delete(String name) {
        return photoRepository.delete(name);
    }

    @Override
    public Flux<Photo> listAll() {
        return photoRepository.listAll();
    }

    @Override
    public Flux<Photo> listVisible() {
        return photoRepository.listVisible();
    }

    @Override
    public Flux<Photo> listByPage(int page, int size) {
        return photoRepository.listByPage(page, size);
    }
}
