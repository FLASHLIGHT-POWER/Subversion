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
import mindustry.ui.*;
import mindustry.world.meta.*;

public class OxygenProducer extends InsideBlock{

	public OxygenProducer(String name, boolean need){
		super(name,need);
	}
	
	public OxygenProducer(String name){
		super(name);
	}
	
	public class OxygenProducerBuilding extends InsideBlockBuilding{
		@Override
		public void updateTile(){
			super.updateTile();
			
		}
	}
}