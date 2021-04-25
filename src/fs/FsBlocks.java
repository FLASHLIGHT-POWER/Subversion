package fs;

import mindustry.ctype.*;
import mindustry.type.*;
import mindustry.world.*;
import mindustry.content.*;

import static mindustry.type.ItemStack.*;
import static mindustry.Vars.*;

import fs.type.*;

public class FsBlocks implements ContentList{

	public static Block 
	oxygenProducer;

	@Override
	public void load(){
		oxygenProducer = new InsideBlock("oxygenProducer"){{
			peopleMax = 4;
			oxygenMax = 40;
			size = 3;
			requirements(Category.effect, with(Items.copper, 50, Items.lead, 90, Items.graphite, 70));
			health = 300;
		}};
	}
}