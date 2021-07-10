package fs.type;

import arc.util.io.*;
import mindustry.gen.*;
import mindustry.world.*;
import mindustry.ui.*;

import fs.FsColor;

public class InsideBlock extends Block{
	public float oxygenMax = 5;
	public float peopleMax = 0;
	public float foodMax = 0;
	public int workPriority = 0;
	public boolean needPeople;
	//0~9 0最低
	
	public float timeMultiplier = 1;
	
	public InsideBlock(String name, boolean bool){
		super(name);
		needPeople = bool;
		solid = true;
		update = true;
	}
	
	public InsideBlock(String name){
		this(name,false);
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
		if(foodMax>0){
			bars.add("food", 
				(InsideBlockBuild entity) -> new Bar(
					() -> "Food",
					() -> FsColor.peopleC,
					() -> entity.people / peopleMax
				)
			);
		};
	}
	
	public class InsideBlockBuild extends Building{
		public float oxygen=0;
		public float people=0;
		public float food=0;
		private int deathPoint;
		public float oxygenConcentration;
		@Override
		public void updateTile(){
			oxygenConcentration = oxygen / oxygenMax;
			if(people>0){
					if(oxygenConcentration<0.2f) deathPoint++;
					if(deathPoint>60*5){ people--; deathPoint=0;};
					if(oxygen>0){
						oxygen-=people*0.01f;
					}
					if(food>0){
						food-=people*0.02f;
					}else if(food<0){
						food=0;
						deathPoint+=0.5f;
					}else{
						deathPoint+=0.5f;
					}
			}

			if(oxygenConcentration<0.8&&needPeople){
				timeMultiplier = oxygenConcentration;
			}else timeMultiplier = 1f;
			
		}
		
		@Override
        public void write(Writes write){
            super.write(write);
            write.f(people);
            write.f(oxygen);
            write.f(food);
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);
            people = read.f();
            oxygen = read.f();
            food = read.f();
        }
	}
}
