package common;

public enum Education {
	A("Bez vzdìlání"), 
	B("Neúplné základní vzdìlání"),
	C("Základní vzdìlání"), 
	D("Nižší støední vzdìlání"),
	E("Nižší støed.odbor.vzdìl."),
	H("Stø.odb.vzd.s výuè.listem"),
	J("Stø./stø.odb.bez Mat.a VL"),
	K("Úplné stø.všeob.vzdìlání"),
	L("	Úpl.stø.odb.vzd.s VL i Ma"),
	M("	Úpl.stø.odb.vz.s M bez VL"),
	N("Vyšší odborné vzdìlání"),
	P("	Vyšší odb.vzd.konzervatoø"),
	R("	VŠ bakaláøské vzdìlání"),
	T("Vysokoškolské mag.vzdìl.	"),
	V("Vysokoškolské dokt.vzdìl.");
	
	private String label;
	
	private Education(String label) {
		this.label = label;
	}
	
	public String toString() {
		return this.name() + " - " + label;
	}
	
	public static Education findByName(String name) {
		for (Education ed : Education.values()) {
			if (ed.toString().equals(name)) {
				return ed;
			}
		}
		return null;
	}

}
