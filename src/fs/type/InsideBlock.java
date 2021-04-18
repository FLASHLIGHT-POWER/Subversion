package fs.type;

import arc.*;
import arc.audio.*;
import arc.func.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.graphics.g2d.TextureAtlas.*;
import arc.math.*;
import arc.math.geom.*;
import arc.scene.ui.layout.*;
import arc.struct.EnumSet;
import arc.struct.*;
import arc.util.*;
import arc.util.pooling.*;
import mindustry.content.*;
import mindustry.entities.units.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.world.blocks.*;

import static mindustry.Vars.*;

public class InsideBlock extends Block{

	public int robots;
	public int robotMax = 0;
	public int people;
	public int peopleMax = 5;
	
	public InsideBlock(String name , int robot){
		super(name);
		init(robots);
	}
	
	public void init(int robotMax){
		super.init();
		
		update = true;
        solid = true;
		this.robotMax = robotMax;
		robots = 0;
		people = 0;
		
	}
	
	@Override
    public void setBars(){
        super.setBars();
        
        if(robotMax>0) bars.add("robot", entity -> new Bar("Robot", Pal.health, robots/robotMax).blink(Color.white));
	}
	
	public class InsideBlockBuilding extends Building{
		
		public float oxygenConcentration = 0;
		public float oxygen = 0;
	
		@Override
		public void updateTile(){
			oxygenConcentration = oxygen/100;
		}
	}
}
