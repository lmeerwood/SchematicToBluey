/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mcschematictosebluey;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;


/**
 *
 * @author Leonard
 */
public class PrintBlueprint {
    private byte[] usedBlockIDS = new byte[] {0x01, 0x02, 0x03, 0x04, 0x05, 0x07, 0x0c, 0x0d, 0x0e, 0x0f,
    0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x18, 0x19, 0x23, 0x29, 0x2a, 0x2b, 0x2d, 0x2f, 0x30, 0x31, 0x32,
    0x35, 0x35, 0x38, 0x39, 0x3a, 0x3c, 0x66, 0x5f, 0x62, -0x7a, -0x7, -0x6f, 0x6d, 0x52};
    
    private double hu, sa, va;
    
    public void generateBluey(Schematic s, File f, String name) throws IOException {
        try{
            PrintWriter writer = new PrintWriter(f, "UTF-8");
            String xmlHeader;
            xmlHeader = "<?xml version=\"1.0\"?>\n" +
                        "<Definitions xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                        "  <ShipBlueprints>\n" +
                        "    <ShipBlueprint>\n" +
                        "      <Id>\n" +
                        "        <TypeId>MyObjectBuilder_ShipBlueprintDefinition</TypeId>\n" +
                        "        <SubtypeId>MCS - " + name + "</SubtypeId>\n" +
                        "      </Id>\n" +
                        "      <DisplayName>MC Schematic</DisplayName>\n" +
                        "      <CubeGrids>\n" +
                        "        <CubeGrid>\n" +
                        "          <EntityId>164736533536122585</EntityId>\n" +
                        "          <PersistentFlags>CastShadows InScene</PersistentFlags>\n" +
                        "          <PositionAndOrientation>\n" +
                        "            <Position x=\"29.997346708019904\" y=\"160.13121107919414\" z=\"-112.61598849006077\" />\n" +
                        "            <Forward x=\"0.347969353\" y=\"0.16441834\" z=\"0.9229756\" />\n" +
                        "            <Up x=\"-0.5092237\" y=\"-0.7934629\" z=\"0.333328545\" />\n" +
                        "          </PositionAndOrientation>\n" +
                        "          <GridSizeEnum>Large</GridSizeEnum>\n" +
                        "          <CubeBlocks>\n";
            writer.print(xmlHeader);
            String xmlBlocks = "";
            byte[] blocks = s.getBlocks();
            byte[] data = s.getData();
            int torchCount = 0;
            for(int y = 0; y < s.getHeight(); y++){
                for(int z = 0; z < s.getLength(); z++){
                    for(int x = 0; x < s.getWidth(); x++){
                        byte currentBlock = blocks[(y*s.getLength() + z)*s.getWidth()+x];
                        if (arrayContains(currentBlock, usedBlockIDS)){
                            boolean isWool = false;
                            boolean isStairs = false;
                            boolean isTorch = false;
                            double hue, sat, val;
                            val = 0;
                            hue = 0;
                            sat = 0;
                            switch(currentBlock){
                                case 0x43:
                                case 0x6d:
                                    isStairs = true;
                                case 0x01:
                                case 0x04:
                                case 0x0d:
                                case 0x2b:
                                case 0x62:
                                    //grey blocks
                                    sat = 0;
                                    val = 40;
                                    break;
                                case 0x02:
                                case 0x12:
                                case 0x30:
                                    //green blocks
                                    hue = 120;
                                    sat = 100;
                                    val = 62;
                                    break;
                                case 0x35:
                                case -0x7a:
                                case -0x70:
                                case -0x6f:
                                    isStairs = true;
                                case 0x03:
                                case 0x05:
                                case 0x11:
                                case 0x19:
                                case 0x3a:
                                case 0x3c:
                                case 0x2f:
                                    //brown blocks
                                    hue = 30;
                                    sat = 100;
                                    val = 25;
                                    break;
                                case 0x72:
                                    isStairs = true;
                                case 0x07:
                                case 0x31:
                                    //black blocks
                                    sat = 0;
                                    val = 10;
                                    break;
                                case -0x80:
                                    isStairs = true;
                                case 0x0c:
                                case 0x13:
                                case 0x18:
                                    //light yellow blocks
                                    hue = 54;
                                    sat = 78;
                                    val = 89;
                                    break;
                                case 0x0e:
                                case 0x0f:
                                case 0x10:
                                    //dark grey
                                    val = 30;
                                    break;
                                case 0x52:
                                    //light grey
                                    val = 70;
                                    break;
                                case 0x14:
                                case 0x66:
                                    //pink
                                    //I make glass pink so you know where
                                    //to cut out and replace with glass
                                    hue = 300;
                                    sat = 70;
                                    val = 100;
                                    break;
                                case 0x15:
                                case 0x16:
                                case 0x38:
                                    //blue
                                    hue = 190;
                                    sat = 100;
                                    val = 100;
                                    break;
                                case 0x29:
                                    //yellow
                                    hue = 54;
                                    sat = 98;
                                    val = 89;
                                    break;
                                case 0x2a:
                                    //white
                                    val =95;
                                    break;
                                case 0x6c:
                                    isStairs = true;
                                case 0x2d:
                                    //dark red
                                    sat = 100;
                                    val = 45;
                                    break;
                                case 0x39:
                                    //light blue
                                    hue = 190;
                                    sat = 89;
                                    val = 100;
                                    break;
                                case 0x23:
                                    isWool = true;
                                    break;
                                //case 0x32:
                                //    isTorch = true;
                                default:
                                    //make any item left over purple
                                    hue = 0.82;
                                    sat = 1.0;
                                    break;       
                            }
                                
                            if(isStairs) {
                                byte currentData = data[(y*s.getLength() + z)*s.getWidth()+x];
                                boolean isUpsideDown = (((currentData >> 2) != 1));
                                int direction;
                                String upOrDown = "";
                                String facing = "";
                                if (isUpsideDown) {
                                    direction = currentData;
                                    upOrDown = "Up";
                                } else {
                                    direction = currentData - 4;
                                    upOrDown = "Down";
                                }
                                switch (direction){
                                    case 0:
                                        facing = "Right";
                                        break;
                                    case 1:
                                        facing = "Left";
                                        break;
                                    case 2:
                                        facing = "Backward";
                                        break;
                                    case 3:
                                        facing = "Forward";
                                        break;
                                }
                                xmlBlocks = "            <MyObjectBuilder_CubeBlock xsi:type=\"MyObjectBuilder_CubeBlock\">\n" +
                                            "              <SubtypeName>LargeHeavyBlockArmorSlope</SubtypeName>\n" +
                                            "              <Min x=\"" + x + "\" y=\"" + y + "\" z=\"" + z + "\" />\n" +
                                            "              <BlockOrientation Forward=\"" + facing + "\" Up=\"" + upOrDown + "\" />\n" +
                                            "              <ColorMaskHSV x=\"" + hu + "\" y=\"" + sa + "\" z=\"" + va + "\" />\n" +
                                            "              <ShareMode>None</ShareMode>\n" +
                                            "              <DeformationRatio>0</DeformationRatio>\n" +
                                            "            </MyObjectBuilder_CubeBlock>\n";
                            } else if (isTorch) {
                                byte currentData = data[(y*s.getLength() + z)*s.getWidth()+x];
                                String facing;
                                String top = "Up";
                                switch (currentData){
                                    case 1:
                                        facing = "Right";
                                        break;
                                    case 2:
                                        facing = "Left";
                                        break;
                                    case 3:
                                        facing = "Backward";
                                        break;
                                    case 4:
                                        facing = "Forward";
                                        break;
                                    default:
                                        facing = "Up";
                                        top = "Right";
                                }
                                int EntityID_1, EntityID_2;
                                EntityID_1 = 1843331581;
                                EntityID_2 = 71005650;
                                xmlBlocks = "            <MyObjectBuilder_CubeBlock xsi:type=\"MyObjectBuilder_InteriorLight\">\n" +
                                            "              <SubtypeName>SmallLight</SubtypeName>\n" +
                                            "              <EntityId>"+ Integer.toString(EntityID_1) + (EntityID_2 + torchCount) + "</EntityId>\n" +
                                            "              <Min x=\"" + x + "\" y=\"" + y + "\" z=\"" + z + "\" />\n" +
                                            "              <BlockOrientation Forward=\"" + facing + "\" Up=\"" + top + "\" />\n" +
                                            "              <ColorMaskHSV x=\"0\" y=\"-1\" z=\"0\" />\n" +
                                            "              <ShareMode>None</ShareMode>\n" +
                                            "              <DeformationRatio>0</DeformationRatio>\n" +
                                            "              <CustomName>Interior Light " + (torchCount + 1) + "</CustomName>\n" +
                                            "              <ShowOnHUD>false</ShowOnHUD>\n" +
                                            "              <Enabled>true</Enabled>\n" +
                                            "              <Radius>3.6</Radius>\n" +
                                            "              <Falloff>1</Falloff>\n" +
                                            "              <Intensity>1.5</Intensity>\n" +
                                            "              <BlinkIntervalSeconds>0</BlinkIntervalSeconds>\n" +
                                            "              <BlinkLenght>10</BlinkLenght>\n" +
                                            "              <BlinkOffset>0</BlinkOffset>\n" +
                                            "            </MyObjectBuilder_CubeBlock>";
                                torchCount += 1;
                                
                            }
                            else {
                                if(isWool){
                                    byte currentData = data[(y*s.getLength() + z)*s.getWidth()+x];
                                    switch (currentData){
                                        case 0:
                                            hue = 0;
                                            sat = 0;
                                            val = 95;
                                            break;
                                        case 1:
                                            hue = 30;
                                            sat = 100;
                                            val = 50;
                                            break;
                                        case 2:
                                            hue = 300;
                                            sat = 100;
                                            val = 100;
                                            break;
                                        case 3:
                                            hue = 190;
                                            sat = 89;
                                            val = 100;
                                            break;
                                        case 4:
                                            hue = 54;
                                            sat = 98;
                                            val = 89;
                                            break;
                                        case 5:
                                            hue = 148;
                                            sat = 98;
                                            val = 89;
                                            break;
                                        case 6:
                                            hue = 294;
                                            sat = 50;
                                            val = 100;
                                            break;
                                        case 7:
                                            hue = 0;
                                            sat = 0;
                                            val = 70;
                                            break;
                                        case 8:
                                            hue = 0;
                                            sat = 0;
                                            val = 82;
                                            break;
                                        case 9:
                                            hue = 180;
                                            sat = 100;
                                            val = 100;
                                            break;
                                        case 10:
                                            hue = 306;
                                            sat = 100;
                                            val = 50;
                                            break;
                                        case 11:
                                            hue = 240;
                                            sat = 100;
                                            val = 100;
                                            break;
                                        case 12:
                                            hue = 30;
                                            sat = 100;
                                            val = 25;
                                            break;
                                        case 13:
                                            hue = 120;
                                            sat = 100;
                                            val = 62;
                                            break;
                                        case 14:
                                            hue = 0;
                                            sat = 100;
                                            val = 65;
                                            break;
                                        case 15:
                                            hue = 0;
                                            sat = 0;
                                            val = 10;
                                            break;
                                        default:
                                            hue = 0;
                                            sat = -1;
                                            val = 0.6;
                                            break;
                                    }
                                }
                                setHSV(hue, sat, val);
                                xmlBlocks = "            <MyObjectBuilder_CubeBlock xsi:type=\"MyObjectBuilder_CubeBlock\">\n" +
                                            "              <SubtypeName>LargeHeavyBlockArmorBlock</SubtypeName>\n" +
                                            "              <Min x=\"" + x + "\" y=\"" + y + "\" z=\"" + z + "\" />\n" +
                                            "              <BlockOrientation Forward=\"Forward\" Up=\"Up\" />\n" +
                                            "              <ColorMaskHSV x=\"" + hu + "\" y=\"" + sa + "\" z=\"" + va + "\" />\n" +
                                            "              <ShareMode>None</ShareMode>\n" +
                                            "              <DeformationRatio>0</DeformationRatio>\n" +
                                            "            </MyObjectBuilder_CubeBlock>\n";
                            }
                            
                        writer.print(xmlBlocks);
                        }
                    }
                }
            }
            String xmlFooter;
            xmlFooter = "          </CubeBlocks>\n" +
                        "          <IsStatic>false</IsStatic>\n" +
                        "          <Skeleton />\n" +
                        "          <LinearVelocity x=\"0\" y=\"0\" z=\"0\" />\n" +
                        "          <AngularVelocity x=\"0\" y=\"0\" z=\"0\" />\n" +
                        "          <XMirroxPlane xsi:nil=\"true\" />\n" +
                        "          <YMirroxPlane xsi:nil=\"true\" />\n" +
                        "          <ZMirroxPlane xsi:nil=\"true\" />\n" +
                        "          <BlockGroups>\n" +
                        "          </BlockGroups>\n" +
                        "          <Handbrake>false</Handbrake>\n" +
                        "          <DisplayName>Test House</DisplayName>\n" +
                        "          <CreatePhysics>false</CreatePhysics>\n" +
                        "          <EnableSmallToLargeConnections>false</EnableSmallToLargeConnections>\n" +
                        "        </CubeGrid>\n" +
                        "      </CubeGrids>\n" +
                        "      <WorkshopId>0</WorkshopId>\n" +
                        "      <OwnerSteamId>00000000000000000</OwnerSteamId>\n" +
                        "    </ShipBlueprint>\n" +
                        "  </ShipBlueprints>\n" +
                        "</Definitions>";
            writer.print(xmlFooter);
            writer.close();
        }catch (ClassCastException | NullPointerException ex) {
            throw new IOException("Invalid level format: " + ex);
        }
    }
    private boolean arrayContains(byte b, byte[] ba){
        for (byte i : ba){
            if(i==b){
                return true;
            }
        }
        return false;
    }
    private void setHSV(double h, double s, double v){
        hu = h/360;
        sa = ((s - 50) * 2)/100;
        va = ((v - 50) * 2)/100;
    }
}
