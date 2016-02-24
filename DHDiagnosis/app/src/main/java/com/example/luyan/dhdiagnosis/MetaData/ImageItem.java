package com.example.luyan.dhdiagnosis.MetaData;

import java.io.Serializable;

/**
 * Created by luyan on 2/23/16.
 */
public class ImageItem implements Serializable {
    public String imageId;
    public String thumbnailPath;
    public String imagePath;
    public boolean isSelected = false;
}
