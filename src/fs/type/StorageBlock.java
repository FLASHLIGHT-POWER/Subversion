package fs.type;

public class StorageBlock extends InsideBlock{
	
	public StorageBlock(String name){
		super(name);
	}

	public class StorageBlockBuild extends InsideBlockBuild{
		public float oxygenA,foodA,peopleA;

		public void shareThing(int i,float amount ,InsideBlockBuild build){
				switch (i) {
					case 1:
						oxygenA -= amount;
						build.oxygen += amount;
						break;
					case 2:
						foodA -= amount;
						build.food += amount;
						break;
					case 3:
						peopleA -= amount;
						build.people += amount;
						break;
			}
		}
	}
}