package run.halo.plugin.photowall.repository;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import run.halo.app.extension.ReactiveExtensionClient;
import run.halo.app.extension.router.selector.FieldSelector;
import run.halo.plugin.photowall.entity.Photo;
import run.halo.app.extension.PageRequestImpl;

@Repository
public class PhotoRepository {

    private final ReactiveExtensionClient client;

    public PhotoRepository(ReactiveExtensionClient client) {
        this.client = client;
    }

    public Mono<Photo> create(Photo photo) {
        return client.create(photo);
    }

    public Mono<Photo> update(Photo photo) {
        return client.update(photo);
    }

    public Mono<Photo> get(String name) {
        return client.get(Photo.class, name);
    }

    public Mono<Void> delete(String name) {
        return client.delete(Photo.class, name);
    }

    public Flux<Photo> listAll() {
        return client.list(Photo.class, null, null);
    }

    public Flux<Photo> listByPage(int page, int size) {
        return client.list(Photo.class, null, null,
                PageRequestImpl.of(page, size));
    }

    public Flux<Photo> listVisible() {
        return client.list(Photo.class,
                FieldSelector.of("spec.visible=true"),
                null);
    }
}
