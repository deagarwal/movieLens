package movie.lens.data.models;

public enum Director {
	Denys(0),
	AnuragKashyap(1),
	RamGopalVarma(2),
	PuneetSira(3),
	GAravindan(4),
	JahnuBarua(5),
	ShyamBenegal(6),
	SudhirMishra(7),
	TVChandran(8),
	BudhdhadebDasgupta(9),
	AjitaSuchitraVeera(10),
	KalpanaLajmi(11),
	MiraNair(12),
	DeepaMehta(13),
	GovindNihalani(14),
	JabbarPatel(15),
	DadasahebPhalke(16),
	SridharRangayan(17),
	SatyajitRay(18),
	BimalRoy(19),
	ManiRatnam(20),
	AparnaSen(21),
	MrinalSen(22),
	ParthoSenGupta(23),
	VShantaram(24),
	SureshJoachim(25),
	TKRajeevKumar(26),
	BijuViswanath(27),
	Vijayakrishnan(28),
	NandamuriTarakaRamarao(29),
	JenniferBaichwal(30),
	DavidBaird(31),
	LizaBalkan(32),
	ManonBarbeau(33),
	RennyBartlett(34),
	Jephteastien(35),
	MichaelBawtree(36),
	ReneeBeaulieu(37),
	GuyBeaulne(38),
	KeithBehrman(39),
	ShaneBelcourt(40),
	AngelaBesharah(41),
	RaoulBhaneja(42),
	JackBlum(43),
	FranoisBouvier(44),
	MaureenBradley(45),
	AndreBrassard(46),
	ManonBriand(47),
	DonaldBrittain(48),
	RexBromfield(49),
	DanielBrooks(50),
	PaulBuissonneau(51),
	GaryBurns(52),
	JasonBuxton(53);
	
	int directorId;
	
	private Director(int directorId) {
		this.directorId = directorId;
	}
	
	/**
	 * @return the directorId
	 */
	public int getDirectorId() {
		return directorId;
	}

	public static Director getDirector(int index){
		for(Director dir : Director.values()){
			if(dir.getDirectorId() == index){
				return dir;
			}
		}
		return null;
	}
	
}
