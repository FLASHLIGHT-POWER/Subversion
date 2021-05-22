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
			//produceTime = 120f;
			//produceAmount = 3f;
			oxygenMax = 8;
			peopleMax = 4;
			size = 1;
			requirements(Category.effect, with(Items.copper, 50, Items.lead, 90, Items.graphite, 70));
			health = 300;
			//consumes.power(0.5f);
            //consumes.liquid(Liquids.water, 0.4f);
            hasPower = true;
            hasLiquids = true;
            liquidCapacity = 60;
		}};
	}
}
