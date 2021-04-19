package fs;

import mindustry.ctype.*;
import mindustry.world.*;

import fs.type.*;

public class FsBlocks implements ContentList{

	public void Block robotBase;

	@Override
	public void load(){
		robotBase = new InsideBlock("RobotBase",3){{
			peopleMax = 0;
		}};
	}
}