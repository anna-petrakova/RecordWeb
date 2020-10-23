package common;

public enum Education {
	A("Bez vzd�l�n�"), 
	B("Ne�pln� z�kladn� vzd�l�n�"),
	C("Z�kladn� vzd�l�n�"), 
	D("Ni��� st�edn� vzd�l�n�"),
	E("Ni��� st�ed.odbor.vzd�l."),
	H("St�.odb.vzd.s v�u�.listem"),
	J("St�./st�.odb.bez Mat.a VL"),
	K("�pln� st�.v�eob.vzd�l�n�"),
	L("	�pl.st�.odb.vzd.s VL i Ma"),
	M("	�pl.st�.odb.vz.s M bez VL"),
	N("Vy��� odborn� vzd�l�n�"),
	P("	Vy��� odb.vzd.konzervato�"),
	R("	V� bakal��sk� vzd�l�n�"),
	T("Vysoko�kolsk� mag.vzd�l.	"),
	V("Vysoko�kolsk� dokt.vzd�l.");
	
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
