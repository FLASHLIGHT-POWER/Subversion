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

    }

    @Override
    public void loadContent(){
		new FsBlocks().load();
    }

}
