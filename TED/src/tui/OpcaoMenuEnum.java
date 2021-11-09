package tui;

public enum OpcaoMenuEnum {
	CADASTRAR_MORADOR (1, " - Cadastrar morador"), 
	RESERVAR_QUADRA (2, " - Reservar quadra"), 
	RESERVAR_GBAND (3, " - Reservar Garage band"), 
	RESERVAR_SALAO(4, " - Reservar Salão de festas"), 
	LISTA_DE_RESERVAS(5, " - Lista de reservas"),
	SAIR(9, " - Sair");
	
	private final Integer CODE;
	private String txtOpcao;

	private OpcaoMenuEnum(Integer code, String txt) {
		this.CODE = code;
		this.txtOpcao = txt;
	}

	public Integer getCode() {
		return CODE;
	}

	public String getTextoOpcao() {
		return txtOpcao;
	}
	
	public String getCodeTxt() {
		return getCode() + getTextoOpcao();
	}
	
	public static OpcaoMenuEnum valueOfInt(Integer opcaoInt) {
		OpcaoMenuEnum op = null;
		for (OpcaoMenuEnum opcaoEnum : OpcaoMenuEnum.values()) {
			if(opcaoEnum.getCode().equals(opcaoInt)) {
				op = opcaoEnum;
			}
		}	
		return op;
	}
}
