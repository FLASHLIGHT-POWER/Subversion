package fs;

import mindustry.ctype.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.content.*;

import fs.type.*;

public class FsBlocks implements ContentList{

	public static Block 
	robotBase;

	@Override
	public void load(){
		robotBase = new InsideBlock("RobotBase",3){{
			peopleMax = 0;
			size = 3;
			requirements(Category.effect, with(Items.copper, 50, Items.lead, 90, Items.graphite, 70));
			health = 300;
		}};
	}
}