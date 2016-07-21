package eej1.models;

public enum Profession {

	Teacher(0),
	Doctor(1),
	Engineer(2),
	Farmer(3),
	BusinessMan(4),
	Administrator(5),
	Artist(6),
	Professor(7),
	Executive(8),
	HealthCare(9),
	HomeMaker(10),
	Lawyer(11),
	Librarian(12),
	Marketing(13),
	None(14),
	Other(15),
	Programmer(16),
	Retired(17),
	Salesman(18),
	Scientist(19),
	Student(20),
	Technician(21),
	Writer(22);
	
	int professionId;
	
	Profession(int professionId){
		this.professionId = professionId;
	}
	
	
	public static Profession getProfession(int professionId){
		for(Profession pro : Profession.values()){
			if(pro.professionId == professionId){
				return pro;
			}
		}
		return null;
	}
}