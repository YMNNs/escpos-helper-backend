package com.inspray.escposhelperbackend.beans;

import lombok.Data;

@Data
public class Block {
    private String id;
    private String text;
    private boolean lf;
    private int width;
    private int height;
    private int align;
}
