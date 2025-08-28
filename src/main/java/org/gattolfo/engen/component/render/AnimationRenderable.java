package org.gattolfo.engen.component.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import org.gattolfo.engen.component.TransformComponent;

public class AnimationRenderable implements Renderable {

    private Animation<TextureRegion> animation;
    public AnimationRenderable(Animation<TextureRegion> animation){
        this.animation =animation;
    }

    public AnimationRenderable(Texture sheet,int frameCols, int frameRows,float frameDuration,Animation.PlayMode playMode){
        TextureRegion[][] tmp = TextureRegion.split(
            sheet,
    sheet.getWidth()/frameCols,
    sheet.getHeight()/frameRows
        );

        Array<TextureRegion> frames = new Array<>();

        for (int i = 0; i < frameRows; i++) {
            for (int j = 0; j < frameCols; j++) {
                frames.add(tmp[i][j]);
            }
        }

        animation = new Animation<>(frameDuration, frames, playMode);
    }

    float stateTime = 0f;
    @Override
    public void render(SpriteBatch batch, TransformComponent transform, float deltaTime) {
        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = animation.getKeyFrame(stateTime);


        Vector3 pos = transform.getWorldPosition();
        float rotation = transform.getWorldRotation().getAngleAround(Vector3.Z);
        Vector3 scale = transform.getWorldScale();


        float originX = currentFrame.getRegionWidth() / 2f;
        float originY = currentFrame.getRegionHeight() / 2f;


        batch.draw(
                currentFrame,
                pos.x - originX,          // x position of bottom-left
                pos.y - originY,          // y position of bottom-left
                originX,                  // originX for rotation & scale
                originY,                  // originY for rotation & scale
                currentFrame.getRegionWidth(),  // width
                currentFrame.getRegionHeight(), // height
                scale.x,                  // scaleX
                scale.y,                  // scaleY
                rotation                  // rotation in degrees,
        );

    }
}
