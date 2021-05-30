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

public class Hallway extends InsideBlock{

	public Hallway(String name){
		super(name);
	}
	
	public class HallwayBuild extends InsideBlockBuild {
	
		public Building next;
		public InsideBlockBuild near;
		@Override
		public void updateTile(){
			super.updateTile();
			for(int i = 0;i<4;i++){
				next = tile.nearbyBuild(i);
				if(next instanceof InsideBlockBuild&& next.team == team ){
					near = (InsideBlockBuild)next;
					float oxygenConN = near.oxygenConcentration;
					if(oxygenConN>oxygenConcentration){
						float oxygenN  = near.oxygen;
						near.moveIntoOxygen((oxygenConcentration-oxygenConN)*oxygenMax*0.2f);
						oxygen-=(oxygenConcentration-oxygenConN)*oxygenMax*0.2f;
						continue;
					}
					if(oxygenConN<oxygenConcentration){
						float oxygenN  = near.oxygen;
						near.moveIntoOxygen((oxygenConcentration-oxygenConN)*oxygenMax*0.2f*-0.1f);
						oxygen+=(oxygenConcentration-oxygenConN)*oxygenMax*0.2f;
					}
				}
			}
		}
	}
}