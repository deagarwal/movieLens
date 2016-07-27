package movie.lens.data.models;

public enum Gender {
	M(0),
	F(1);
	
	int index;
	
	Gender(int index){
		this.index = index;
	}
	
	private int getIndex(){
		return index;
	}
	
	public static Gender getGender(int index){
		for(Gender gen : Gender.values()){
			if(gen.getIndex() == index){
				return gen;
			}
		}
		
		return null;
	}
}
