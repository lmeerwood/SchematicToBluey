/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcschematictosebluey;

import java.io.*;
import org.jnbt.*;

/**
 *
 * @author Leonard
 */
public class LoadSchematic {
    protected static void getRawData(File level, Schematic s) throws IOException {
        try {
         NBTInputStream input = new NBTInputStream(new FileInputStream(level));
         CompoundTag originalTopLevelTag = (CompoundTag) input.readTag();
         input.close();
         
         ShortTag height = (ShortTag) originalTopLevelTag.getValue().get("Height");
         ShortTag width = (ShortTag) originalTopLevelTag.getValue().get("Width");
         ShortTag length = (ShortTag) originalTopLevelTag.getValue().get("Length");
         ByteArrayTag blocks = (ByteArrayTag) originalTopLevelTag.getValue().get("Blocks");
         ByteArrayTag data = (ByteArrayTag) originalTopLevelTag.getValue().get("Data");
         s.setBlocks(blocks.getValue());
         s.setData(data.getValue());
         s.setWidth(width.getValue());
         s.setHeight(height.getValue());
         s.setLength(length.getValue());
         
        } catch (ClassCastException | NullPointerException ex) {
         throw new IOException("Invalid level format: " + ex);
        }
    }
}
