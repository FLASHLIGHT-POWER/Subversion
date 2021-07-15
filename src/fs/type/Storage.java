package fs.type;

import arc.util.io.*;
import fs.*;

public class Storage extends InsideBlock{
	
	public Storage(String name){
		super(name);
		oxygenMax = 20;
		foodMax = 20;
		peopleMax = 10;
	}

	public class StorageBuild extends InsideBlockBuild{
		public boolean load = true;

		@Override
		public void updateTile(){
			super.updateTile();
			
			if(FsData.FL&& load){
				FsData.OA=FsData.FA=FsData.PA=0;
				FsData.OM=FsData.FM=FsData.PM=0;
			}
			
			if(load){
				FsData.OM = oxygenMax;
				FsData.FM = foodMax;
				FsData.PM = peopleMax;
				load=false;
			}
		}
		@Override
		public void read(Reads read, byte revision){
			super.read(read, revision);
			load = true;
			FsData.OA = oxygen;
			FsData.FA = food;
			FsData.PA = people;
		}
	}
}
