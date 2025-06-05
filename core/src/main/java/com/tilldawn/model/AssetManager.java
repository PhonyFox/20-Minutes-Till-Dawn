package com.tilldawn.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetManager {
    private static AssetManager assetManager;

    public static final float SCALE = 4f;

    private final String tree = "enemies/tree.png";

    private final String levelup_0 = "levelup/0.png";
    private final String levelup_1 = "levelup/1.png";
    private final String levelup_2 = "levelup/2.png";
    private final String levelup_3 = "levelup/3.png";
    private final String levelup_4 = "levelup/4.png";
    private final String levelup_5 = "levelup/5.png";
    private final String levelup_6 = "levelup/6.png";
    private final String levelup_7 = "levelup/7.png";
    private final String levelup_8 = "levelup/8.png";

    private final String heart_0 = "heart/0.png";
    private final String heart_1 = "heart/1.png";
    private final String heart_2 = "heart/2.png";

    private final String heart_no = "heart/no.png";

    private final String thunder_0 = "thunder/0.png";
    private final String thunder_1 = "thunder/1.png";
    private final String thunder_2 = "thunder/2.png";
    private final String thunder_3 = "thunder/3.png";
    private final String thunder_4 = "thunder/4.png";
    private final String thunder_5 = "thunder/5.png";
    private final String thunder_6 = "thunder/6.png";

    private final String tentacle_0 = "enemies/tentacle/0.png";
    private final String tentacle_1 = "enemies/tentacle/1.png";
    private final String tentacle_2 = "enemies/tentacle/2.png";
    private final String tentacle_3 = "enemies/tentacle/3.png";

    private final String eyebat_0 = "enemies/eyebat/0.png";
    private final String eyebat_1 = "enemies/eyebat/1.png";
    private final String eyebat_2 = "enemies/eyebat/2.png";

    private final String elder_walking_0 = "enemies/elder/walking/0.png";
    private final String elder_walking_1 = "enemies/elder/walking/1.png";
    private final String elder_walking_2 = "enemies/elder/walking/2.png";

    private final String elder_dashing_0 = "enemies/elder/dashing/0.png";
    private final String elder_dashing_1 = "enemies/elder/dashing/1.png";
    private final String elder_dashing_2 = "enemies/elder/dashing/2.png";
    private final String elder_dashing_3 = "enemies/elder/dashing/3.png";
    private final String elder_dashing_4 = "enemies/elder/dashing/4.png";
    private final String elder_dashing_5 = "enemies/elder/dashing/5.png";
    private final String elder_dashing_6 = "enemies/elder/dashing/6.png";
    private final String elder_dashing_7 = "enemies/elder/dashing/7.png";

    private final String ch0_idle0 = "idle/0/0.png";
    private final String ch0_idle1 = "idle/0/1.png";
    private final String ch0_idle2 = "idle/0/2.png";
    private final String ch0_idle3 = "idle/0/3.png";
    private final String ch0_idle4 = "idle/0/4.png";
    private final String ch0_idle5 = "idle/0/5.png";


    private final String ch0_walk0 = "walk/0/0.png";
    private final String ch0_walk1 = "walk/0/1.png";
    private final String ch0_walk2 = "walk/0/2.png";
    private final String ch0_walk3 = "walk/0/3.png";
    private final String ch0_walk4 = "walk/0/4.png";
    private final String ch0_walk5 = "walk/0/5.png";
    private final String ch0_walk6 = "walk/0/6.png";

    private final String ch1_idle0 = "idle/1/0.png";
    private final String ch1_idle1 = "idle/1/1.png";
    private final String ch1_idle2 = "idle/1/2.png";
    private final String ch1_idle3 = "idle/1/3.png";
    private final String ch1_idle4 = "idle/1/4.png";
    private final String ch1_idle5 = "idle/1/5.png";


    private final String ch1_walk0 = "walk/1/0.png";
    private final String ch1_walk1 = "walk/1/1.png";
    private final String ch1_walk2 = "walk/1/2.png";
    private final String ch1_walk3 = "walk/1/3.png";
    private final String ch1_walk4 = "walk/1/4.png";
    private final String ch1_walk5 = "walk/1/5.png";


    private final String ch2_idle0 = "idle/2/0.png";
    private final String ch2_idle1 = "idle/2/1.png";
    private final String ch2_idle2 = "idle/2/2.png";
    private final String ch2_idle3 = "idle/2/3.png";
    private final String ch2_idle4 = "idle/2/4.png";
    private final String ch2_idle5 = "idle/2/5.png";


    private final String ch2_walk0 = "walk/2/0.png";
    private final String ch2_walk1 = "walk/2/1.png";
    private final String ch2_walk2 = "walk/2/2.png";
    private final String ch2_walk3 = "walk/2/3.png";
    private final String ch2_walk4 = "walk/2/4.png";
    private final String ch2_walk5 = "walk/2/5.png";


    private final String ch3_idle0 = "idle/3/0.png";
    private final String ch3_idle1 = "idle/3/1.png";
    private final String ch3_idle2 = "idle/3/2.png";
    private final String ch3_idle3 = "idle/3/3.png";
    private final String ch3_idle4 = "idle/3/4.png";
    private final String ch3_idle5 = "idle/3/5.png";


    private final String ch3_walk0 = "walk/3/0.png";
    private final String ch3_walk1 = "walk/3/1.png";
    private final String ch3_walk2 = "walk/3/2.png";
    private final String ch3_walk3 = "walk/3/3.png";
    private final String ch3_walk4 = "walk/3/4.png";
    private final String ch3_walk5 = "walk/3/5.png";


    private final String ch4_idle0 = "idle/4/0.png";
    private final String ch4_idle1 = "idle/4/1.png";
    private final String ch4_idle2 = "idle/4/2.png";
    private final String ch4_idle3 = "idle/4/3.png";
    private final String ch4_idle4 = "idle/4/4.png";
    private final String ch4_idle5 = "idle/4/5.png";


    private final String ch4_walk0 = "walk/4/0.png";
    private final String ch4_walk1 = "walk/4/1.png";
    private final String ch4_walk2 = "walk/4/2.png";
    private final String ch4_walk3 = "walk/4/3.png";
    private final String ch4_walk4 = "walk/4/4.png";
    private final String ch4_walk5 = "walk/4/5.png";

    private final TextureRegion tree_tex = new TextureRegion(new Texture(tree));

    private final TextureRegion heart_0_tex = new TextureRegion(new Texture(heart_0));
    private final TextureRegion heart_1_tex = new TextureRegion(new Texture(heart_1));
    private final TextureRegion heart_2_tex = new TextureRegion(new Texture(heart_2));

    private final TextureRegion levelup_0_tex = new TextureRegion(new Texture(levelup_0));
    private final TextureRegion levelup_1_tex = new TextureRegion(new Texture(levelup_1));
    private final TextureRegion levelup_2_tex = new TextureRegion(new Texture(levelup_2));
    private final TextureRegion levelup_3_tex = new TextureRegion(new Texture(levelup_3));
    private final TextureRegion levelup_4_tex = new TextureRegion(new Texture(levelup_4));
    private final TextureRegion levelup_5_tex = new TextureRegion(new Texture(levelup_5));
    private final TextureRegion levelup_6_tex = new TextureRegion(new Texture(levelup_6));
    private final TextureRegion levelup_7_tex = new TextureRegion(new Texture(levelup_7));
    private final TextureRegion levelup_8_tex = new TextureRegion(new Texture(levelup_8));

    private final TextureRegion heart_no_tex = new TextureRegion(new Texture(heart_no));

    private final TextureRegion thunder_0_tex = new TextureRegion(new Texture(thunder_0));
    private final TextureRegion thunder_1_tex = new TextureRegion(new Texture(thunder_1));
    private final TextureRegion thunder_2_tex = new TextureRegion(new Texture(thunder_2));
    private final TextureRegion thunder_3_tex = new TextureRegion(new Texture(thunder_3));
    private final TextureRegion thunder_4_tex = new TextureRegion(new Texture(thunder_4));
    private final TextureRegion thunder_5_tex = new TextureRegion(new Texture(thunder_5));
    private final TextureRegion thunder_6_tex = new TextureRegion(new Texture(thunder_6));

    private final TextureRegion tentacle_0_tex = new TextureRegion(new Texture(tentacle_0));
    private final TextureRegion tentacle_1_tex = new TextureRegion(new Texture(tentacle_1));
    private final TextureRegion tentacle_2_tex = new TextureRegion(new Texture(tentacle_2));
    private final TextureRegion tentacle_3_tex = new TextureRegion(new Texture(tentacle_3));

    private final TextureRegion eyebat_0_tex = new TextureRegion(new Texture(eyebat_0));
    private final TextureRegion eyebat_1_tex = new TextureRegion(new Texture(eyebat_1));
    private final TextureRegion eyebat_2_tex = new TextureRegion(new Texture(eyebat_2));

    private final TextureRegion elder_walking_0_tex = new TextureRegion(new Texture(elder_walking_0));
    private final TextureRegion elder_walking_1_tex = new TextureRegion(new Texture(elder_walking_1));
    private final TextureRegion elder_walking_2_tex = new TextureRegion(new Texture(elder_walking_2));

    private final TextureRegion elder_dashing_0_tex = new TextureRegion(new Texture(elder_dashing_0));
    private final TextureRegion elder_dashing_1_tex = new TextureRegion(new Texture(elder_dashing_1));
    private final TextureRegion elder_dashing_2_tex = new TextureRegion(new Texture(elder_dashing_2));
    private final TextureRegion elder_dashing_3_tex = new TextureRegion(new Texture(elder_dashing_3));
    private final TextureRegion elder_dashing_4_tex = new TextureRegion(new Texture(elder_dashing_4));
    private final TextureRegion elder_dashing_5_tex = new TextureRegion(new Texture(elder_dashing_5));
    private final TextureRegion elder_dashing_6_tex = new TextureRegion(new Texture(elder_dashing_6));
    private final TextureRegion elder_dashing_7_tex = new TextureRegion(new Texture(elder_dashing_7));

    private final TextureRegion ch0_idle0_tex = new TextureRegion(new Texture(ch0_idle0));
    private final TextureRegion ch0_idle1_tex = new TextureRegion(new Texture(ch0_idle1));
    private final TextureRegion ch0_idle2_tex = new TextureRegion(new Texture(ch0_idle2));
    private final TextureRegion ch0_idle3_tex = new TextureRegion(new Texture(ch0_idle3));
    private final TextureRegion ch0_idle4_tex = new TextureRegion(new Texture(ch0_idle4));
    private final TextureRegion ch0_idle5_tex = new TextureRegion(new Texture(ch0_idle5));


    private final TextureRegion ch0_walk1_tex = new TextureRegion(new Texture(ch0_walk1));
    private final TextureRegion ch0_walk0_tex = new TextureRegion(new Texture(ch0_walk0));
    private final TextureRegion ch0_walk2_tex = new TextureRegion(new Texture(ch0_walk2));
    private final TextureRegion ch0_walk3_tex = new TextureRegion(new Texture(ch0_walk3));
    private final TextureRegion ch0_walk4_tex = new TextureRegion(new Texture(ch0_walk4));
    private final TextureRegion ch0_walk5_tex = new TextureRegion(new Texture(ch0_walk5));
    private final TextureRegion ch0_walk6_tex = new TextureRegion(new Texture(ch0_walk6));

    private final TextureRegion ch1_idle0_tex = new TextureRegion(new Texture(ch1_idle0));
    private final TextureRegion ch1_idle1_tex = new TextureRegion(new Texture(ch1_idle1));
    private final TextureRegion ch1_idle2_tex = new TextureRegion(new Texture(ch1_idle2));
    private final TextureRegion ch1_idle3_tex = new TextureRegion(new Texture(ch1_idle3));
    private final TextureRegion ch1_idle4_tex = new TextureRegion(new Texture(ch1_idle4));
    private final TextureRegion ch1_idle5_tex = new TextureRegion(new Texture(ch1_idle5));


    private final TextureRegion ch1_walk0_tex = new TextureRegion(new Texture(ch1_walk0));
    private final TextureRegion ch1_walk1_tex = new TextureRegion(new Texture(ch1_walk1));
    private final TextureRegion ch1_walk2_tex = new TextureRegion(new Texture(ch1_walk2));
    private final TextureRegion ch1_walk3_tex = new TextureRegion(new Texture(ch1_walk3));
    private final TextureRegion ch1_walk4_tex = new TextureRegion(new Texture(ch1_walk4));
    private final TextureRegion ch1_walk5_tex = new TextureRegion(new Texture(ch1_walk5));


    private final TextureRegion ch2_idle0_tex = new TextureRegion(new Texture(ch2_idle0));
    private final TextureRegion ch2_idle1_tex = new TextureRegion(new Texture(ch2_idle1));
    private final TextureRegion ch2_idle2_tex = new TextureRegion(new Texture(ch2_idle2));
    private final TextureRegion ch2_idle3_tex = new TextureRegion(new Texture(ch2_idle3));
    private final TextureRegion ch2_idle4_tex = new TextureRegion(new Texture(ch2_idle4));
    private final TextureRegion ch2_idle5_tex = new TextureRegion(new Texture(ch2_idle5));


    private final TextureRegion ch2_walk0_tex = new TextureRegion(new Texture(ch2_walk0));
    private final TextureRegion ch2_walk1_tex = new TextureRegion(new Texture(ch2_walk1));
    private final TextureRegion ch2_walk2_tex = new TextureRegion(new Texture(ch2_walk2));
    private final TextureRegion ch2_walk3_tex = new TextureRegion(new Texture(ch2_walk3));
    private final TextureRegion ch2_walk4_tex = new TextureRegion(new Texture(ch2_walk4));
    private final TextureRegion ch2_walk5_tex = new TextureRegion(new Texture(ch2_walk5));


    private final TextureRegion ch3_idle0_tex = new TextureRegion(new Texture(ch3_idle0));
    private final TextureRegion ch3_idle1_tex = new TextureRegion(new Texture(ch3_idle1));
    private final TextureRegion ch3_idle2_tex = new TextureRegion(new Texture(ch3_idle2));
    private final TextureRegion ch3_idle3_tex = new TextureRegion(new Texture(ch3_idle3));
    private final TextureRegion ch3_idle4_tex = new TextureRegion(new Texture(ch3_idle4));
    private final TextureRegion ch3_idle5_tex = new TextureRegion(new Texture(ch3_idle5));


    private final TextureRegion ch3_walk0_tex = new TextureRegion(new Texture(ch3_walk0));
    private final TextureRegion ch3_walk1_tex = new TextureRegion(new Texture(ch3_walk1));
    private final TextureRegion ch3_walk2_tex = new TextureRegion(new Texture(ch3_walk2));
    private final TextureRegion ch3_walk3_tex = new TextureRegion(new Texture(ch3_walk3));
    private final TextureRegion ch3_walk4_tex = new TextureRegion(new Texture(ch3_walk4));
    private final TextureRegion ch3_walk5_tex = new TextureRegion(new Texture(ch3_walk5));


    private final TextureRegion ch4_idle0_tex = new TextureRegion(new Texture(ch4_idle0));
    private final TextureRegion ch4_idle1_tex = new TextureRegion(new Texture(ch4_idle1));
    private final TextureRegion ch4_idle2_tex = new TextureRegion(new Texture(ch4_idle2));
    private final TextureRegion ch4_idle3_tex = new TextureRegion(new Texture(ch4_idle3));
    private final TextureRegion ch4_idle4_tex = new TextureRegion(new Texture(ch4_idle4));
    private final TextureRegion ch4_idle5_tex = new TextureRegion(new Texture(ch4_idle5));


    private final TextureRegion ch4_walk0_tex = new TextureRegion(new Texture(ch4_walk0));
    private final TextureRegion ch4_walk1_tex = new TextureRegion(new Texture(ch4_walk1));
    private final TextureRegion ch4_walk2_tex = new TextureRegion(new Texture(ch4_walk2));
    private final TextureRegion ch4_walk3_tex = new TextureRegion(new Texture(ch4_walk3));
    private final TextureRegion ch4_walk4_tex = new TextureRegion(new Texture(ch4_walk4));
    private final TextureRegion ch4_walk5_tex = new TextureRegion(new Texture(ch4_walk5));

    private final Animation<TextureRegion> tentacleAnimation = new Animation<>(0.1f,
        tentacle_0_tex, tentacle_1_tex, tentacle_2_tex, tentacle_3_tex);

    private final Animation<TextureRegion> thunderAnimation = new Animation<>(0.05f,
        thunder_0_tex, thunder_1_tex, thunder_2_tex, thunder_3_tex, thunder_4_tex, thunder_5_tex, thunder_6_tex);

    private final Animation<TextureRegion> levelUpAnimation = new Animation<>(0.2f,
        levelup_0_tex, levelup_1_tex, levelup_2_tex, levelup_3_tex, levelup_4_tex, levelup_5_tex, levelup_6_tex, levelup_7_tex, levelup_8_tex);

    private final Animation<TextureRegion> heartAnimation = new Animation<>(0.1f,
        heart_0_tex, heart_1_tex, heart_2_tex);

    private final Animation<TextureRegion> eyebatAnimation = new Animation<>(0.1f,
        eyebat_0_tex, eyebat_1_tex, eyebat_2_tex);

    private final Animation<TextureRegion> elderWalkingAnimation = new Animation<>(0.1f,
        elder_walking_0_tex, elder_walking_1_tex, elder_walking_2_tex);

    private final Animation<TextureRegion> elderDashingAnimation = new Animation<>(0.1f,
        elder_dashing_0_tex,
        elder_dashing_0_tex,
        elder_dashing_0_tex,
        elder_dashing_0_tex,
        elder_dashing_0_tex,
        elder_dashing_0_tex,

        //elder_dashing_1_tex,
        elder_dashing_1_tex,
        elder_dashing_1_tex,

        //elder_dashing_2_tex,
        elder_dashing_2_tex,
        elder_dashing_2_tex,

        //elder_dashing_3_tex,
        elder_dashing_3_tex,
        elder_dashing_3_tex,

        //elder_dashing_4_tex,
        elder_dashing_4_tex,
        elder_dashing_4_tex,

        elder_dashing_5_tex,
        elder_dashing_5_tex,
        elder_dashing_5_tex,

        elder_dashing_6_tex,
        elder_dashing_6_tex,
        elder_dashing_6_tex,

        elder_dashing_7_tex,
        elder_dashing_7_tex,
        elder_dashing_7_tex
    );

    // ch0 idle
    private final Animation<TextureRegion> ch0_idle_frames = new Animation<>(0.1f,
        ch0_idle0_tex, ch0_idle1_tex, ch0_idle2_tex, ch0_idle3_tex, ch0_idle4_tex, ch0_idle5_tex);
    // ch0 walk
    private final Animation<TextureRegion> ch0_walk_frames = new Animation<>(0.1f,
        ch0_walk0_tex, ch0_walk1_tex, ch0_walk2_tex, ch0_walk3_tex, ch0_walk4_tex, ch0_walk5_tex, ch0_walk6_tex);

    // ch1 idle
    private final Animation<TextureRegion> ch1_idle_frames = new Animation<>(0.1f,
        ch1_idle0_tex, ch1_idle1_tex, ch1_idle2_tex, ch1_idle3_tex, ch1_idle4_tex, ch1_idle5_tex);
    // ch1 walk
    private final Animation<TextureRegion> ch1_walk_frames = new Animation<>(0.1f,
        ch1_walk0_tex, ch1_walk1_tex, ch1_walk2_tex, ch1_walk3_tex, ch1_walk4_tex, ch1_walk5_tex);

    // ch2 idle
    private final Animation<TextureRegion> ch2_idle_frames = new Animation<>(0.1f,
        ch2_idle0_tex, ch2_idle1_tex, ch2_idle2_tex, ch2_idle3_tex, ch2_idle4_tex, ch2_idle5_tex);
    // ch2 walk
    private final Animation<TextureRegion> ch2_walk_frames = new Animation<>(0.1f,
        ch2_walk0_tex, ch2_walk1_tex, ch2_walk2_tex, ch2_walk3_tex, ch2_walk4_tex, ch2_walk5_tex);

    // ch3 idle
    private final Animation<TextureRegion> ch3_idle_frames = new Animation<>(0.1f,
        ch3_idle0_tex, ch3_idle1_tex, ch3_idle2_tex, ch3_idle3_tex, ch3_idle4_tex, ch3_idle5_tex);
    // ch3 walk
    private final Animation<TextureRegion> ch3_walk_frames = new Animation<>(0.1f,
        ch3_walk0_tex, ch3_walk1_tex, ch3_walk2_tex, ch3_walk3_tex, ch3_walk4_tex, ch3_walk5_tex);

    // ch4 idle
    private final Animation<TextureRegion> ch4_idle_frames = new Animation<>(0.1f,
        ch4_idle0_tex, ch4_idle1_tex, ch4_idle2_tex, ch4_idle3_tex, ch4_idle4_tex, ch4_idle5_tex);
    // ch4 walk
    private final Animation<TextureRegion> ch4_walk_frames = new Animation<>(0.1f,
        ch4_walk0_tex, ch4_walk1_tex, ch4_walk2_tex, ch4_walk3_tex, ch4_walk4_tex, ch4_walk5_tex);


    private AssetManager() {

    }


    public static AssetManager getAssetManager() {
        if (assetManager == null) {
            assetManager = new AssetManager();
        }
        return assetManager;
    }

    public Animation<TextureRegion> getIdleFrames(String name) {
        switch (name.toLowerCase().trim()) {
            case "diamond": return ch1_idle_frames;
            case "scarlet": return ch2_idle_frames;
            case "lilith": return ch3_idle_frames;
            case "dasher": return ch4_idle_frames;
            default: return ch0_idle_frames;
        }
    }

    public Animation<TextureRegion> getWalkFrames(String name) {
        switch (name.toLowerCase().trim()) {
            case "diamond": return ch1_walk_frames;
            case "scarlet": return ch2_walk_frames;
            case "lilith": return ch3_walk_frames;
            case "dasher": return ch4_walk_frames;
            default: return ch0_idle_frames;
        }
    }

    public TextureRegion getTreeTexture() {
        return tree_tex;
    }

    public Animation<TextureRegion> getTentacleAnimation() {
        return tentacleAnimation;
    }

    public Animation<TextureRegion> getEyebatAnimation() {
        return eyebatAnimation;
    }

    public Animation<TextureRegion> getThunderAnimation() {
        return thunderAnimation;
    }

    public Animation<TextureRegion> getElderWalkingAnimation() {return elderWalkingAnimation;}

    public Animation<TextureRegion> getElderDashingAnimation() {return elderDashingAnimation;}

    public Animation<TextureRegion> getHeartAnimation() {return heartAnimation;}

    public Animation<TextureRegion> getLevelUpAnimation() {return levelUpAnimation;}

    public TextureRegion getHeartTexture() {
        return heart_no_tex;
    }

    public String getStartingMusic() {
        return "musics/sleeping.mp3";
    }
}
