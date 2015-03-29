/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcschematictosebluey;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Leonard
 */
public class McSchematicToSeBluey {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        for (String s : args) {
            System.out.println(s);
        }
        LoadSchematic ls = new LoadSchematic();
        Schematic s = new Schematic();
        File content, output;
        content = new File(args[0]);
        String name = content.getName();
        int dotIndex = name.indexOf(".");
        if (dotIndex != -1)
        {
            name = name.substring(0, dotIndex);
        }
        File outputFolder = new File(System.getenv("APPDATA") + "\\SpaceEngineers\\" + 
                "Blueprints\\local\\"+name);
        if(!outputFolder.exists()){
            
            try{
                outputFolder.mkdir();
            }
            catch(SecurityException se){
                System.out.println("There was an error." + se);
            }
        }
        output = new File(outputFolder+"\\bp.sbc");
        ls.getRawData(content, s);
        PrintBlueprint pb = new PrintBlueprint();
        pb.generateBluey(s, output, name);
    }
}
