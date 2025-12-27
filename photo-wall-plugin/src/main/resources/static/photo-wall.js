document.addEventListener('DOMContentLoaded', function() {
    const container = document.getElementById('photo-wall-container');
    fetch('/apis/api.photowall.halo.run/v1alpha1/photos/visible')
        .then(res => res.json())
        .then(photos => {
            photos.forEach(photo => {
                let media;
                if(photo.spec.mediaType === 'video'){
                    media = document.createElement('video');
                    media.src = photo.spec.imageUrl;
                    media.autoplay = true;
                    media.loop = true;
                    media.muted = true;
                }else{
                    media = document.createElement('img');
                    media.src = photo.spec.imageUrl;
                }
                media.style.width = '200px';
                media.style.margin = '5px';
                container.appendChild(media);
            });
        });
});
