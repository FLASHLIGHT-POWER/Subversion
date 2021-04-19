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

	public float robotMax = 0;
	public float peopleMax = 5f;
	public float oxygenMax = 5f;
	
	public InsideBlock(String name , float robot){
		super(name);
		init(robot);
	}
	
	public void init(float robotMax){
		
		update = true;
        solid = true;
		this.robotMax = robotMax;
	}
	
	@Override
    public void setBars(){
        super.setBars();
        
        if(robotMax>0) bars.add("Robot", 
			(InsideBlockBuilding entity) -> new Bar(
				() -> "Robot",
				() -> Pal.health,
				() -> entity.robots/robotMax
			)
		);
        bars.add("People", 
			(InsideBlockBuilding entity) -> new Bar(
				() -> "People",
				() -> Pal.health,
				() -> entity.people/peopleMax
			)
		);
		bars.add("OxygenConcentration", 
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
		public float robots = 0;
		public float people = 0;
	
		@Override
		public void updateTile(){
			oxygenConcentration = oxygen/oxygenMax;
		}
	}
}
