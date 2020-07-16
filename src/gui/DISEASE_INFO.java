package gui;

import load.Load;

/**
* @author Mohd Ainal Farhan Mohamad Johari
* 
* Project     : Hospital System
* Date        : May 14, 2020
* Data Source : https://www.benaroyaresearch.org/what-is-bri/disease-information
* 
*/

public enum DISEASE_INFO {
	D1("Type 1 Diabetes", 
			"icon-t1d.png", 
			"Type 1 diabetes (T1D) is an autoimmune disease in which the" + 
			" body’s immune system attacks and destroys the beta cells i" + 
			"n the pancreas that make insulin."),
	D2("Multiple Sclerosis", 
			"icon-ms.png", 
			"Multiple sclerosis (MS) is an autoimmune disease in which t" + 
			"he body's immune system mistakenly attacks myelin, the fatt" + 
			"y substance that surrounds and protects the nerve fibers in" + 
			" the central nervous system."),
	D3("Crone\\'s & Colitis",
			"icon-crohns-colitis.png", 
			"Crohn's disease and ulcerative colitis (UC), both also know" + 
			"n as inflammatory bowel diseases (IBD), are autoimmune dise" + 
			"ases in which the body's immune system attacks the intestines."),
	D4("Lupus", 
			"icon-lupus.png", 
			"Systemic lupus erythematosus (lupus) is a chronic, systemic" + 
			" autoimmune disease which can damage any part of the body, " + 
			"including the heart, joints, skin, lungs, blood vessels, li" + 
			"ver, kidneys and nervous system."),
	D5("Rheumatoid Arthritis", 
			"icon-rheumatoid-arth.png", 
			"Rheumatoid arthritis (RA) is an autoimmune disease in which" + 
			" the body's immune system mistakenly attacks its own tissue" + 
			"s, primarily the synovium, the membrane that lines the joints."),
	D6("Allergies & Asthma", 
			"icon-allergies-asthma.png", 
			"Allergies and asthma are immune mediated diseases that occu" + 
			"r when the body's immune system overreacts to a foreign sub" + 
			"stance (an allergen), such as pollen or animal dander, that" + 
			" in most people is generally harmless."),
	D7("Celiac Disease", 
			"icon-celiac-disease.png", 
			"Celiac disease is estimated to affect 1 in 100 people in the" + 
			" United States and its incidence appears to be rising. In ad" + 
			"dition, 2.5 million Americans are undiagnosed and may be at " + 
			"risk for long-term health complications."),
	D8("Relapsing Polychondritis", 
			"icon-rp.png", 
			"Relapsing polychondritis (RP) is a rheumatic autoimmune dise" + 
			"ase. It is a rare disease in which the immune system attacks" +
			" the body's cartilage."),
	D9("Scleroderma", 
			"icon-scleroderma.png", 
			"Scleroderma is a rheumatic autoimmune disease of the connect" + 
			"ive tissue which causes skin thickening, spontaneous scarrin" + 
			"g, blood vessel disease and varying degrees of inflammation."),
	D10("Liver Disease", 
			"icon-liver-disease.png", 
			"There are many diseases and disorders that can cause the liv" + 
			"er to stop functioning properly. Some of the different cause" + 
			"s of liver disease include viral infection, alcohol or other" + 
			" environmental toxins, autoimmune disease and genetics."),
	D11("Infectious Diseases", 
			"icon-infection.png", 
			"Infectious diseases are caused by pathogens (\"germs\") incl" + 
			"uding viruses, bacteria, fungi and parasites, and are ranked" + 
			" as the second leading cause of death worldwide by the World" + 
			" Health Organization."),
	D12("Cancer", 
			"icon-cancer.png", 
			"Cancer represents more than 200 different types of malignanc" + 
			"ies—diseases caused by the uncontrolled and destructive grow" + 
			"th of cells. When cancer cells grow unregulated, they can de" +
			"velop into tumors, invade nearby parts of the body and sprea" +
			"d throughout the body."),
	D13("Heart Disease", 
			"icon-heart.png", 
			"Heart disease encompasses many diseases of the heart and blo" +
			"od vessels, such as high blood pressure, heart attacks, angi" +
			"na pectoris (chest pain or discomfort caused by a reduced bl" +
			"ood supply to the heart muscle), stroke and heart failure.");
	
	private String diseaseName;
	private String iconLocation;
	private String briefDescription;
	
	private DISEASE_INFO(String name, String location, String desc) {
		diseaseName = name;
		iconLocation = location;
		briefDescription = desc;
	}
	
	public String getDiseaseName() {
		return diseaseName;
	}
	
	public String getIconLocation() {
		return (Load.sourcePath + "img\\disease\\" + iconLocation);
	}
	
	public String getBriefDescription() {
		String desc = "";
		int MAX_CHARACTER_PER_LINE = 50;
		int characters = 0;
		int count = 0;
		
		if(briefDescription.length() > MAX_CHARACTER_PER_LINE) {
			while(briefDescription.length() > (characters + MAX_CHARACTER_PER_LINE)) {
				count = 0;
				if((briefDescription.charAt(characters + MAX_CHARACTER_PER_LINE) - ' ') != 0) {
					count++;
					while((briefDescription.charAt(characters + MAX_CHARACTER_PER_LINE - count) - ' ') != 0) {
						count++;
					}
				}
				if((briefDescription.charAt(characters) - ' ') == 0)
					characters++;
				
				desc += briefDescription.substring(characters, characters + MAX_CHARACTER_PER_LINE - count) + "\n";
				
				characters += MAX_CHARACTER_PER_LINE - count;
			}
			if((briefDescription.charAt(characters) - ' ') == 0)
				characters++;
			desc += briefDescription.substring(characters);
		}
		
		else
			desc += briefDescription;
		
		return desc;
	}
}
