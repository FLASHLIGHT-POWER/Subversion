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
	
	public float produceTime;
	public float produceAmount;
	
	public OxygenProducer(String name, boolean need){
		super(name,need);
		produceTime = 60;
		produceAmount = 3;
	}
	
	public OxygenProducer(String name){
		super(name,false);
	}
	
	public class OxygenProducerBuild extends InsideBlockBuild{
		
		public float progress;
		
		@Override
		public void updateTile(){
			super.updateTile();
			
			if(consValid){
				progress++;
				if(progress>=produceTime){
					consume();
					oxygen+=produceAmount;
					progress=0;
				}
			}
		}
		
		@Override
        public void write(Writes write){
            super.write(write);
            write.f(progress);
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);
            progress = read.f();
        }
	}
}