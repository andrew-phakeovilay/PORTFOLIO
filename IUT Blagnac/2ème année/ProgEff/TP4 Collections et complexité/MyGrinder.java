import java.util.ArrayList;
import java.util.Collection;

import stone.Grinder;
import stone.Stone;

public class MyGrinder implements Grinder {

	@Override
	public Collection<Stone> grind(Stone stone, int diameter) {
		// TODO Auto-generated method stub
		Collection<Stone> stones = new ArrayList<Stone>();  
		while(stone.diameter() > diameter) {
			Stone splitStone = stone.split();
			if(splitStone.diameter() > diameter) {
				Collection<Stone> collStones = grind(splitStone, diameter);
				stones.addAll(collStones);
			}
			else {
				stones.add(splitStone);
			}
		}
		stones.add(stone);
		return stones;
	}

}
