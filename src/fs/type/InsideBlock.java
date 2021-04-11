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
import mindustry.world.blocks.distribution.ChainedBuilding;

import static mindustry.Vars.*;

public class InsideBlock extends Block implements Autotiler{

	public float pressure = 1f;
	public float oxygenConcentration;
	public float oxygen;
	
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
		
		oxygen = 0;
		oxygenConcentration = 0;
	}
	
	@Override
    public boolean blends(Tile tile, int rotation, int otherx, int othery, int otherrot, Block otherblock){
        return otherblock instanceof InsideBlock &&  (lookingAt(tile, rotation, otherx, othery, otherblock)) && lookingAtEither(tile, rotation, otherx, othery, otherrot, otherblock);
    }
    
    @Override
    public void drawRequestRegion(BuildPlan req, Eachable<BuildPlan> list){
        int[] bits = getTiling(req, list);

        if(bits == null) return;

        Draw.scl(bits[1], bits[2]);
        Draw.color(botColor);
        Draw.alpha(0.5f);
        Draw.rect(botRegions[bits[0]], req.drawx(), req.drawy(), req.rotation * 90);
        Draw.color();
        Draw.rect(topRegions[bits[0]], req.drawx(), req.drawy(), req.rotation * 90);
        Draw.scl();
    }

    @Override
    public Block getReplacement(BuildPlan req, Seq<BuildPlan> requests){
        Boolf<Point2> cont = p -> requests.contains(o -> o.x == req.x + p.x && o.y == req.y + p.y && o.rotation == req.rotation && (req.block instanceof Conduit || req.block instanceof LiquidJunction));
        return cont.get(Geometry.d4(req.rotation)) &&
            cont.get(Geometry.d4(req.rotation - 2)) &&
            req.tile() != null &&
            req.tile().block() instanceof Conduit &&
            Mathf.mod(req.build().rotation - req.rotation, 2) == 1 ? Blocks.liquidJunction : this;
    }

    @Override
    public void handlePlacementLine(Seq<BuildPlan> plans){
        Placement.calculateBridges(plans, (ItemBridge)Blocks.bridgeConduit);
    }

    @Override
    public TextureRegion[] icons(){
        return new TextureRegion[]{Core.atlas.find(name+"-bottom"), topRegions[0]};
    }
	
	public class InsideBlockBuilding extends Building implements ChainedBuilding{
	
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
        
        	for(int i=0;i<tiles.size();i++){
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
       		Tile[] tiles = nearBy();
            return tiles[rotation].build;
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
