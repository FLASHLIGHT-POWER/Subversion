package fs.type;

public class Storage extends InsideBlock{
	
	public Storage(String name){
		super(name);
		oxygenMax = 20;
		foodMax = 20;
		peopleMax = 10;
	}

	public class StorageBuild extends InsideBlockBuild{
		public float oxygenA,foodA,peopleA;

		public void shareThing(int i,float amount ,InsideBlockBuild build){
				switch (i) {
					case 0:
						if(oxygenA>amount) {
							build.oxygen += amount;
							oxygenA -= amount;
						}else{
							build.oxygen += oxygenA;
							oxygenA = 0;
						}
						break;
					case 1:
						if(foodA>amount) {
							build.food += amount;
							foodA -= amount;
						}else{
							build.food += foodA;
							foodA = 0;
						}
						break;
					case 2:
						if(peopleA>amount) {
							build.people += amount;
							peopleA -= amount;
						}else{
							build.people += people;
							peopleA = 0;
						}
						break;
			}
		}
	}
}