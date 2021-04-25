package fs.type;

public class OxygenProducer extends InsideBlock{

	public float produceTime = 60;
	public float produceAmount = 0;

	public OxygenProducer(String name){
		super(name);
	}

	public class OxygenProducerBuilding extends InsideBlockBuilding{
		
		public float time = 0;
		
		@Override
		public void updateTile(){
			super.updateTile();
			
			if(consValid()&&oxygen<oxygenM){
				time++;
			}
			if(time>=produceTime){
				consume();
				oxygen += produceAmount;
			}
		}
		
		@Override
        public boolean shouldConsume(){
            return oxygen < oxygenM;
        }
	}
} 