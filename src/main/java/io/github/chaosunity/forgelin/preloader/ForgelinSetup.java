package io.github.chaosunity.forgelin.preloader;

import net.minecraftforge.fml.relauncher.IFMLCallHook;

import java.util.Map;

public class ForgelinSetup implements IFMLCallHook {
    @Override
    public void injectData(Map<String, Object> data) {
        ClassLoader loader = (ClassLoader) data.get("classLoader");
        try {
            loader.loadClass("io.github.chaosunity.forgelin.KotlinAdapter");
        } catch (ClassNotFoundException e) {
            // this should never happen
            throw new RuntimeException("Couldn't find Forgelin langague adapter, this shouldn't be happening", e);
        }
    }

    @Override
    public Void call() throws Exception {
        return null;
    }
}