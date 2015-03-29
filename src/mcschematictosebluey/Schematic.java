/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcschematictosebluey;

/**
 *
 * @author Leonard
 */
public class Schematic {
    public int height;
    public int width;
    public int length;
    public byte [] blocks;
    public byte [] data;
    
    public void setHeight(int h){
        height = h;
    }
    
    public void setWidth(int w){
        width = w;
    }
    
    public void setLength(int l){
        length = l;
    }
    
    public int getHeight(){
        return height;
    }
    
    public int getWidth(){
        return width;
    }
    
    public int getLength(){
        return length;
    }
    
    public void setBlocks(byte[] b){
        blocks = b;
    }
    
    public void setData(byte[] d){
        data = d;
    }
    
    public byte[] getBlocks(){
        return blocks;
    }
    
    public byte[] getData(){
        return data;
    }
    
}
