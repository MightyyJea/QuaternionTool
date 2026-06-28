package com.jea;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.VertexFormatElement;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.LightCoordsUtil;
import org.codehaus.plexus.util.dag.Vertex;
import org.joml.Vector2f;
import org.joml.Vector2fc;
import org.joml.Vector3f;
import org.joml.Vector3fc;

import java.io.*;
import java.util.ArrayList;

public class OBJLoader {


    public static ArrayList<Face> readObj( String path) throws FileNotFoundException, IOException {
        BufferedReader read = new BufferedReader(new FileReader(new File(path)));
        String line;
        ArrayList<Vector3fc> vertices = new ArrayList<>();
        ArrayList<Vector3fc> normals = new ArrayList<>();
        ArrayList<Vector2fc> texCoords = new ArrayList<>();
        ArrayList<Face> faces = new ArrayList<>();

        while((line = read.readLine()) != null) {
            if (line.startsWith("v ")){
                float x = Float.valueOf(line.split(" ")[1]);
                float y = Float.valueOf(line.split(" ")[2]);
                float z = Float.valueOf(line.split(" ")[3]);
                vertices.add(new Vector3f(x,y,z));
            }
            if (line.startsWith("vn ")) {
                float x = Float.valueOf(line.split(" ")[1]);
                float y = Float.valueOf(line.split(" ")[2]);
                float z = Float.valueOf(line.split(" ")[3]);
                normals.add(new Vector3f(x,y,z));
            }
            if (line.startsWith("vt ")){
                float x = Float.valueOf(line.split(" ")[1]);
                float y = Float.valueOf(line.split(" ")[2]);
                texCoords.add(new Vector2f(x,y));
            }
            if (line.startsWith("f ")) {
                Vector3fc[] vertexFace = new Vector3fc[3];
                vertexFace[0] = vertices.get(Integer.valueOf(line.split(" ")[1].split("/")[0])-1);
                vertexFace[1] = vertices.get(Integer.valueOf(line.split(" ")[2].split("/")[0])-1);
                vertexFace[2] = vertices.get(Integer.valueOf(line.split(" ")[3].split("/")[0])-1);
                Vector3fc[] normalsFace = new Vector3fc[3];
                normalsFace[0] = normals.get(Integer.valueOf(line.split(" ")[1].split("/")[2])-1);
                normalsFace[1] = normals.get(Integer.valueOf(line.split(" ")[2].split("/")[2])-1);
                normalsFace[2] = normals.get(Integer.valueOf(line.split(" ")[3].split("/")[2])-1);
                Vector2fc[] texCoordsFace = new Vector2fc[3];

                texCoordsFace[0] = texCoords.get(Integer.valueOf(line.split(" ")[1].split("/")[1])-1);
                texCoordsFace[1] = texCoords.get(Integer.valueOf(line.split(" ")[2].split("/")[1])-1);
                texCoordsFace[2] = texCoords.get(Integer.valueOf(line.split(" ")[3].split("/")[1])-1);
                faces.add(new Face(vertexFace,normalsFace,texCoordsFace));
            }
        }
        read.close();
        return faces;
    }
}