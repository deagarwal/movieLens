package movie.lens.data.models;

public enum Generes {
    Western(0),
    Action(1),
    Adventure(2),
    Animation(3),
    Children(4),
    Comedy(5),
    Crime(6),
    Documentary(7),
    Drama(8),
    Fantasy(9),
    FilmNoir(10),
    Horror(11),
    Musical(12),
    Mystery(13),
    Romance(14),
    SciFi(15),
    Thriller(16),
    War(17);
    
    int genId;
    
    Generes(int genId){
       this.genId = genId; 
    }
    
    public int getGenId(){
        return genId;
    }
    
    /**
     * @param genId
     * @return
     */
    public static Generes  getGenByGenId(int genId){
        for(Generes gen : Generes.values()){
            if(gen.getGenId()==genId){
                return gen;
            }
        }
        return null;
    }
    
    /**
     * @param genName
     * @return
     */
    public static Generes getGenByGenName(String genName){
         return Generes.valueOf(genName);
    }
}
