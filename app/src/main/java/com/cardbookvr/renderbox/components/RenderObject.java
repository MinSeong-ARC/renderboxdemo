package com.cardbookvr.renderbox.components;

import com.cardbookvr.renderbox.RenderBox;
import com.cardbookvr.renderbox.materials.Material;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/**
 * Created by Schoen and Jonathan on 4/16/2016.
 */
public abstract class RenderObject extends Component {
    private static final String TAG = "RenderObject";

    protected Material material;
    public static float[] model;
    public static float[] lightingModel;

    public RenderObject(){
        super();
        RenderBox.instance.renderObjects.add(this);
    }

    protected static FloatBuffer allocateFloatBuffer(float[] data) {
        ByteBuffer bbVertices = ByteBuffer.allocateDirect(data.length * 4);
        bbVertices.order(ByteOrder.nativeOrder());
        FloatBuffer buffer = bbVertices.asFloatBuffer();
        buffer.put(data);
        buffer.position(0);
        return buffer;
    }

    protected static ShortBuffer allocateShortBuffer(short[] data) {
        ByteBuffer bbVertices = ByteBuffer.allocateDirect(data.length * 2);
        bbVertices.order(ByteOrder.nativeOrder());
        ShortBuffer buffer = bbVertices.asShortBuffer();
        buffer.put(data);
        buffer.position(0);
        return buffer;
    }

    public Material getMaterial(){
        return material;
    }
    public RenderObject setMaterial(Material material){
        this.material = material;
        return this;
    }

    public void draw(float[] view, float[] perspective){
        if(!enabled)
            return;
        //Compute position every frame in case it changed
        transform.drawMatrices();
        material.draw(view, perspective);
    }

}
