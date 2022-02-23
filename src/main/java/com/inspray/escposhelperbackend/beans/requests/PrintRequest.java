package com.inspray.escposhelperbackend.beans.requests;

import com.inspray.escposhelperbackend.beans.Block;
import lombok.Data;

import java.util.List;

@Data
public class PrintRequest {
    private String printerUrl;
    private List<Block> blocks;
}
