package gui;

/**
* @author Mohd Ainal Farhan Mohamad Johari
* 
* Project     : Hospital System
* Date        : May 14, 2020
* 
*/

public enum STATE_CODE {
	JOHOR("01")       , KEDAH("02")          , KELANTAN("03") , 
	MELAKA("04")      , NEGERI_SEMBILAN("05"), PAHANG("06")   , 
	PULAU_PINANG("07"), PERAK("08")          , PERLIS("09")   , 
	SELANGOR("10")    , TERENGGANU("11")     , SABAH("12")    , 
	SARAWAK("13")     , WILAYAH_PERSEKUTUAN_KUALA_LUMPUR("14"),
	WILAYAH_PERSEKUTUAN_LABUAN("15"),
	WILAYAH_PERSEKUTUAN_PUTRAJAYA("16");
	
	private final String code;
	
	private STATE_CODE(String code) {
		this.code = code;
	}
	
	@Override
	public String toString() {
		return code;
	}
}