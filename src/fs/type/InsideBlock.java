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

import fs.FsColor;

public class InsideBlock extends Block{
	
	public float oxygenMax = 0;
	public float peopleMax = 0;
	public int workPriority = 0;
	public boolean needPeople;
	//0~9 0最低
	
	public float timeMultiplier = 1;
	
	public InsideBlock(String name){
		super(name);
		needPeople = false;
	}
	
	public InsideBlock(String name, boolean bool){
		super(name);
		needPeople = bool;
	}
	
	@Override
	public void setBars(){
		super.setBars();
		if(oxygenMax>0){
			bars.add("oxygen", 
				(InsideBlockBuild entity) -> new Bar(
					() -> "OxygenConcentration",
					() -> FsColor.oxygenC,
					() -> entity.oxygen / oxygenMax
				)
			);
		};
		if(peopleMax>0){
			bars.add("people", 
				(InsideBlockBuild entity) -> new Bar(
					() -> "People",
					() -> FsColor.peopleC,
					() -> entity.people / peopleMax
				)
			);
		};
	}
	
	public class InsideBlockBuild extends Building{
		public float oxygen=0;
		public float people=2;
		private int deathPoint;
		public float oxygenConcentration;
		@Override
		public void updateTile(){
			oxygenConcentration = oxygen / oxygenMax;
			if(people>0){
					if(oxygenConcentration<0.2f) deathPoint++;
					if(deathPoint>60*10) people--;
			}

			if(oxygenConcentration<0.8&&needPeople){
				timeMultiplier = oxygenConcentration;
			}else timeMultiplier = 1f;
		}
	}
}