package fs.type;

import arc.graphics.g2d.*;
import arc.math.*;
import arc.struct.*;
import arc.util.*;
import arc.util.io.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.gen.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.draw.*;
import mindustry.world.meta.*;

public class InsideBlock extends Block{
	
	public float oxygenMax = 0;
	
	public InsideBlock(String name){
		super(name);
	}
	
	public class InsideBlockBuild extends Building{
	
	}
}