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

import static mindustry.Vars.*;

public class InsideBlock extends Block implements Autotiler{

	public TextureRegion topRegion = new TextureRegion(new Texture(Core.atlas.find(name+"-top")));
    public TextureRegion bottomRegion = new TextureRegion(new Texture(Core.atlas.find(name+"-bottom")));
	public float pressure = 1f;
	
	public InsideBlock(String name , float pre){
		super(name);
		init(pre);
	}
	
	public InsideBlock(String name){
		super(name);
		init(1f);
	}
	
	public void init(float pre){
		super.init();
		
		pressure = pre;
		
	}
	
	@Override
    public boolean blends(Tile tile, int rotation, int otherx, int othery, int otherrot, Block otherblock){
        return otherblock instanceof InsideBlock &&  (lookingAt(tile, rotation, otherx, othery, otherblock)) && lookingAtEither(tile, rotation, otherx, othery, otherrot, otherblock);
    }
    

    @Override
    public TextureRegion[] icons(){
        return new TextureRegion[]{bottomRegion, topRegion};
    }
	
	public class InsideBlockBuilding extends Building{
	
		public Tile[] tiles;
		
		public float oxygenConcentration = 0;
		public float oxygen = 0;
	
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
        
        	for(int i=0;i<4;i++){
        		Tile next = tiles[i];
        		
        		if(next == null) return 0;

		        if(next.build instanceof InsideBlockBuilding){
		   	        return moveOxygen(next.build, amount);
		 		}
       		 	return 0;
        	}
       }
       
       Building next(){
       		Tile[] tiles = nearBy();
            return tiles[rotation].build;
       }

    public float moveOxygen(Building next, float amount){
        if(next == null || ! next instanceof InsideBlockBuilding ) return 0;
        next = (InsideBlockBuilding)next;
        if(next.team == team && next.oxygenConcentration < oxygenConcentration){
            next.oxygen+=amount;
            oxygen -= amount;
            return amount;
            }
        return 0;
        }
    }
}
