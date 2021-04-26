package fs.type;

import arc.*;
import arc.func.Cons;
import arc.math.geom.*;
import arc.struct.*;
import arc.scene.ui.*;
import arc.scene.ui.layout.*;
import arc.math.*;
import arc.util.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.scene.style.*;
import mindustry.game.*;
import mindustry.ctype.*;
import mindustry.content.*;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.entities.*;
import mindustry.entities.bullet.*;
import mindustry.gen.*;
import mindustry.ui.*;
import mindustry.ui.dialogs.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.logic.*;
import mindustry.world.*;
import mindustry.world.blocks.*;
import mindustry.world.blocks.campaign.*;
import mindustry.world.blocks.defense.*;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.world.blocks.distribution.*;
import mindustry.world.blocks.environment.*;
import mindustry.world.blocks.experimental.*;
import mindustry.world.blocks.legacy.*;
import mindustry.world.blocks.liquid.*;
import mindustry.world.blocks.logic.*;
import mindustry.world.blocks.power.*;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.sandbox.*;
import mindustry.world.blocks.storage.*;
import mindustry.world.blocks.units.*;
import mindustry.world.consumers.*;
import mindustry.world.draw.*;
import mindustry.world.meta.*;
import static mindustry.Vars.*;

public class InsideBlock extends Block{

	public float peopleMax = 0;
	public float oxygenMax = 0;
	
	public InsideBlock(String name){
		super(name);
		init();
	}
	
	public void init(){
		update = true;
        solid = true;
	}
	
	@Override
    public void setBars(){
        super.setBars();

		if(peopleMax>0) bars.add("People", 
			(InsideBlockBuilding entity) -> new Bar(
				() -> "People",
				() -> Pal.health,
				() -> entity.people/peopleMax
			)
		);
		if(oxygenMax>0) bars.add("OxygenConcentration", 
			(InsideBlockBuilding entity) -> new Bar(
				() -> "OxygenConcentration",
				() -> Pal.health,
				() -> entity.oxygenConcentration
			)
		);
	}
	
	public class InsideBlockBuilding extends Building{
		
		public float oxygenConcentration = 0;
		public float oxygen = 0;
		public int people = 0;
		public float oxygenM = 10;
		public float craftTimeMultiple = 0;
		public float killedPoint;
	
		@Override
		public void updateTile(){
			
			if(oxygenM!=oxygenMax) oxygenM = oxygenMax;
			if(oxygenM >0) oxygenConcentration = oxygen/oxygenM;
			
			for(int i = 0; i<4;i++){
				InsideBlockBuilding near = nearBuilding(i);
				if(near==null) continue;
				if(near.oxygenConcentration<oxygenConcentration) 
					oxygen -= (near.oxygenConcentration - oxygenConcentration) * oxygenM;
				near.increaseOxygen((near.oxygenConcentration - oxygenConcentration) * oxygenM);
			}
			
			/*if(people>0){
				consumeOxygen();
				if(oxygenConcentration<0.75f){
					killedPoint++;
					craftTimeMultiple = 0.5f;
					if(oxygenConcentration<0.5f){
						killedPoint++;
						craftTimeMultiple = 0.3f;
					}
					if(oxygenConcentration<0.2f){
						killedPoint++;
						craftTimeMultiple = 0.1f;
					}
				}
			}
			
			if(killedPoint>=10){
				people--;
				killedPoint=0;
			}*/
		}
		
		public InsideBlockBuilding nearBuilding(int num){
			Tile next = tile.nearby(num);
			if(next.build instanceof InsideBlockBuilding) return (InsideBlockBuilding)next.build;
			return null;
		}
		
		public void increaseOxygen(float amount){
			oxygen += amount;
		}
		
		public void consumeOxygen(){
			oxygen -= people*0.05;
		}
	}
}
 