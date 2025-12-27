package run.halo.plugin.photowall.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import run.halo.plugin.photowall.entity.Photo;

public interface PhotoService {
    Mono<Photo> create(Photo photo);
    Mono<Photo> update(String name, Photo photo);
    Mono<Photo> get(String name);
    Mono<Void> delete(String name);
    Flux<Photo> listAll();
    Flux<Photo> listVisible();
    Flux<Photo> listByPage(int page, int size);
}
