package fs.type;

import arc.math.geom.QuadTree;
import mindustry.game.Teams;
import mindustry.gen.Building;

public class Producer extends InsideBlock{

    public int produceType;

    public Producer(String name){
        super(name, true);
    }

    public class ProducerBuild extends InsideBlockBuild{

        public Storage.StorageBuild storageBuild;
        public Teams.TeamData data = team.data();

        @Override
        public void updateTile(){
            for (Building building : data.buildings.objects){
                if(building instanceof Storage.StorageBuild){
                    storageBuild = (Storage.StorageBuild) building;
                    if(this.oxygen<oxygenMax) storageBuild.shareThing(0,1,this);
                    if(this.food<foodMax) storageBuild.shareThing(1,1,this);
                    if(this.people<peopleMax) storageBuild.shareThing(2,1,this);
                }
            }
        }
    }
}