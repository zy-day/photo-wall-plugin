package run.halo.plugin.photowall.controller;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import run.halo.plugin.photowall.entity.Photo;
import run.halo.plugin.photowall.service.PhotoService;

@RestController
@RequestMapping("/apis/api.photowall.halo.run/v1alpha1/photos")
public class PhotoController {

    private final PhotoService photoService;

    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @GetMapping
    public Flux<Photo> listPhotos(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int size) {
        return photoService.listByPage(page, size);
    }

    @GetMapping("/visible")
    public Flux<Photo> listVisiblePhotos() {
        return photoService.listVisible();
    }

    @GetMapping("/{name}")
    public Mono<Photo> getPhoto(@PathVariable String name) {
        return photoService.get(name);
    }

    @PostMapping
    public Mono<Photo> createPhoto(@RequestBody Photo photo) {
        return photoService.create(photo);
    }

    @PutMapping("/{name}")
    public Mono<Photo> updatePhoto(@PathVariable String name,
                                   @RequestBody Photo photo) {
        return photoService.update(name, photo);
    }

    @DeleteMapping("/{name}")
    public Mono<Void> deletePhoto(@PathVariable String name) {
        return photoService.delete(name);
    }
}
