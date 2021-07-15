package fs.type;

import fs.*;

public class Producer extends InsideBlock{

    public int produceType;
	public float produceTime;
	public float produceAmount;
	
    public Producer(String name){
        super(name, true);
        produceType = 0;
        produceTime = 60;
        needPeople = false;
    }

    public class ProducerBuild extends InsideBlockBuild{
    	public float process;
        @Override
        public void updateTile(){
        	super.updateTile();
        	process++
       
        	if(needPeople){
        		timeMultiplier = peopleMax/people;
        	}
        	if(process==timeMultiplier*produceTime){
        		switch (produceType){
        			case 0:
        				if(oxygen >=oxygenMax) FsData.OA+=produceAmount
        				oxygen+=produceAmount;
        				process=0;
        				break;
        			case 1:
        				if(food >=foodMax) FsData.FA+=produceAmount
        				food+=produceAmount;
        				process=0;
        				break;
        			case 2:
        				if(people >=peopleMax) FsData.PA+=produceAmount
        				people+=produceAmount;
        				process=0;
        				break;
        		}
        	}
        }
    }
}
