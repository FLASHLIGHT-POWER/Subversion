package fs.type;

import arc.*;
import arc.func.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.*;
import mindustry.content.*;
import mindustry.entities.units.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.*;
import mindustry.world.blocks.distribution.*;

import static mindustry.Vars.*;

public class InsideBlock extends Block implements Autotiler{

	public float pressure = 1f;
	
	public InsideBlock(String name , float pre){
		super(name);
		init(pre);
	}
	
	public InsideBlock(String name){
		super(name);
		init(1f);
	}
	
	@Override
	public void init(float pre){
		super.init();
		
		pressure = pre;
		
		oxygen = 0;
		oxygenConcentration = 0;
	}
	
	public class InsideBlockBuilding extends Building implements ChainedBuilding{
	
		public float oxygenConcentration;
		public float oxygen;
		public Tile[] tiles;
	
		@Override
		public void updateTile(){
			oxygenConcentration = oxygen/100;
		}
		
        public Tile[] nearBy(){
			Tile[] tiles = new Tile[4];
			tiles[0] =  tile.nearby(0);
			tiles[1] =  tile.nearby(1);
			tiles[2] =  tile.nearby(2);
			tiles[3] =  tile.nearby(3);
			
			return tiles;
		}
		
        public float moveOxygenForward(Tile[] tiles, float amount){
        
        	for(int i=0;i<tiles.length();i++){
        		Tile next = tiles[i];
        		
        		if(next == null) return 0;

		        if(next.build instanceof InsideBlockBuilding){
		   	        return moveOxygen(next.build, amount);
		 		}
       		 	return 0;
        	}
       }
       
       @Override
       Building next(){
       		Tile[] tiles = nearby();
            return tiles[rotation];
       }

    public float moveOxygen(Building next, float amount){
        if(next == null || !next instanceof InsideBlockBuilding ) return 0;
        
        if(next.team == team && next.oxygenConcentration < oxygenConcentration){
            next.oxygen+=amount;
            oxygen -= amount;
            return amount;
            }
        return 0;
        }
    }
}