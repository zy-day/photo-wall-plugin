package run.halo.plugin.photowall;

import lombok.extern.slf4j.Slf4j;
import org.pf4j.PluginWrapper;
import org.springframework.stereotype.Component;
import run.halo.app.extension.SchemeManager;
import run.halo.app.plugin.BasePlugin;
import run.halo.plugin.photowall.entity.Photo;

@Slf4j
@Component
public class PhotoWallPlugin extends BasePlugin {

    private final SchemeManager schemeManager;

    public PhotoWallPlugin(PluginWrapper wrapper, SchemeManager schemeManager) {
        super(wrapper);
        this.schemeManager = schemeManager;
    }

    @Override
    public void start() {
        schemeManager.register(Photo.class);
        log.info("照片视频墙插件启动成功！支持图片、MOV等主流视频格式");
    }

    @Override
    public void stop() {
        schemeManager.unregister(Photo.class);
        log.info("照片视频墙插件停止！");
    }
}
