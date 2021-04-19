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
import mindustry.content.*;
import mindustry.entities.units.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.*;

import static mindustry.Vars.*;

public class InsideBlock extends Block{

	public int robotMax = 0;
	public int peopleMax = 5;
	public float oxygenMax = 5;
	
	public InsideBlock(String name , int robot){
		super(name);
		init(robot);
	}
	
	public void init(int robotMax){
		super.init();
		
		update = true;
        solid = true;
		this.robotMax = robotMax;
	}
	
	@Override
    public void setBars(){
        super.setBars();
        
        if(robotMax>0) bars.add("Robot", (InsideBlockBuilding entity) -> new Bar(
        "Robot", Pal.health, entity.oxygen/robotMax));
        bars.add("People", (InsideBlockBuilding entity) -> new Bar(
        "People", Pal.health, entity.robots/robotMax));
        bars.add("OxygenConcentration", (InsideBlockBuilding entity) -> new Bar(
        "Robot", Pal.health, entity.robots/robotMax));
	}
	
	public class InsideBlockBuilding extends Building{
		
		public float oxygenConcentration = 0;
		public float oxygen = 0;
		public int robots;
		public int people;
	
		@Override
		public void updateTile(){
		
		}
	}
}
