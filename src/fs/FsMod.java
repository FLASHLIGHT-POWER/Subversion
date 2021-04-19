package fs;

import arc.*;
import arc.util.*;
import mindustry.*;
import mindustry.content.*;
import mindustry.game.EventType.*;
import mindustry.gen.*;
import mindustry.mod.*;
import mindustry.ui.dialogs.*;

public class FsMod extends Mod{

    public FsMod(){
    
        Log.info("已加载颠覆mod");

    }

    @Override
    public void loadContent(){
		Log.info("啥也没有");
		new FsBlocks().load();
    }

}
