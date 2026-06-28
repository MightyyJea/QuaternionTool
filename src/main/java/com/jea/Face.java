package com.jea;

import org.joml.Vector2fc;
import org.joml.Vector3fc;

public record Face(Vector3fc[] vertices, Vector3fc[] normals, Vector2fc[] texCoords) {

}
