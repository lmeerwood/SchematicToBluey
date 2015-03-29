# SchematicToBluey
A program that converts Minecraft schematics to Space Engineer blueprints

To use it, download the Dist folder. The easy way is just to click and drag the schematic file onto the dragOnMe.bat. 
The hard way is to use command line from within the directory:
"java -jar mcSchematicToSeBluey.jar C:\path\to\schematic.schematic"

When you run this program it will automatically save the blueprint in your blueprints folder.

There are only a few blocks working at this time. Most basic blocks work. Any block which doesn't take up a full cube doesn't
work (i.e. ladders and fences). Some stairs will be converted to sloping blocks.

A major caveat with the conversion process is a cube in Minecraft is 1m whereas a cube in Space Engineers is 2.5m, so everything
will be ALOT bigger.

Things to do still: Allow changing of output directory, Add support for more blocks, figure out how to use git
  
tl;dr: Download dist folder, drag schematics on to dragOnMe.bat
